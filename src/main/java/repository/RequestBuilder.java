package repository;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openrdf.query.GraphQueryResult;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.query.TupleQueryResultHandlerException;
import org.openrdf.query.resultio.QueryResultIO;
import org.openrdf.query.resultio.TupleQueryResultFormat;
import org.openrdf.query.resultio.UnsupportedQueryResultFormatException;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFHandlerException;
import org.openrdf.rio.UnsupportedRDFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import com.complexible.stardog.StardogException;
import com.complexible.stardog.api.GraphQuery;
import com.complexible.stardog.api.SelectQuery;

import repository.ImportController.database;

@Controller
@RestController

public class RequestBuilder extends CommonFunctions {

	private final static Logger logger = LoggerFactory.getLogger(ImportController.class); 
	
	// JSON pour l'export
	JSONObject conteneur = new JSONObject();
	JSONObject nodes = new JSONObject();
	JSONArray nodesList = new JSONArray();
	JSONArray edgesList = new JSONArray();
	JSONArray dataPropertiesList = new JSONArray();
	JSONObject node; JSONObject nodeId;
	JSONObject edge; JSONObject edgeData;
	
	HashMap<String, String> dictLabel;
	
	int lengthMax = 50;
	
	@CrossOrigin(origins = "http://localhost:8888")
	@RequestMapping (value = "/testRequestBuilder", method = RequestMethod.GET)
	public String testRequestBuilder(@RequestParam(value = "request", required = true) String request) throws JSONException, RDFHandlerException, QueryEvaluationException, UnsupportedRDFormatException, IOException  {  
		System.out.println("test : "+request);
		createAdminConnection(database.ontoMedirad, false);
		//logger.debug("Request "+request);
		SelectQuery aQuery = starDogConnection.select(request);
		TupleQueryResult aResult=null; ByteArrayOutputStream out=null;
		aResult = aQuery.execute();
		out = new ByteArrayOutputStream();
		QueryResultIO.writeTuple(aResult, TupleQueryResultFormat.JSON, out);
		String resultats = out.toString();
		if (aResult!=null) {aResult.close();}
		starDogConnection.close();
		System.out.println(resultats);
		createJsonObject();
		JSONObject jsonResult = new JSONObject(resultats);
		System.out.println(jsonResult);
		
		JSONArray resultsBind = jsonResult.getJSONObject("results").getJSONArray("bindings") ;
		JSONObject res;
		for (int i=0;i<resultsBind.length(); i++) {
			res = resultsBind.getJSONObject(i);

			Iterator<String> iterKeys = res.keys();
			while (iterKeys.hasNext()) {
				String k = iterKeys.next();
				
				JSONObject res2 = res.getJSONObject(k);
				String res2Type = res2.getString("type");
				String res2Value= res2.getString("value");
				if (res2Type.equals("uri")) {
					addNode(res2Value);
				}
			}
		}
		return conteneur.toString();
		//return resultats;
		//return "Tipoui !\n";
	}
	
	public void generateLabelDict() throws JSONException {
		dictLabel = new HashMap<String, String>();
		JSONObject obj;
		String request = "SELECT DISTINCT ?iri ?label WHERE { ?iri rdfs:label ?label . FILTER ( lang(?label) = 'en' ) } ";
		String res = executeQuerry(request, "false").getBody();
		//System.out.println(res);
		JSONObject resJ = new JSONObject(res);
		JSONArray listeRes = resJ.getJSONObject("results").getJSONArray("bindings");
		for(int i = 0; i<listeRes.length(); i++) {
			obj = listeRes.getJSONObject(i);
			String iri = obj.getJSONObject("iri").getString("value");
			String label = obj.getJSONObject("label").getString("value");
			//System.out.println(label+" : "+iri);
			dictLabel.put(iri, label);
			//System.out.println("------------------------");
		}
		
		String request2 = "SELECT DISTINCT ?iri ?label WHERE { ?iri skos:prefLabel ?label . FILTER ( lang(?label) = 'en' ) }";
		res = executeQuerry(request2, "false").getBody();
		//System.out.println(res);
		resJ = new JSONObject(res);
		listeRes = resJ.getJSONObject("results").getJSONArray("bindings");
		for(int i = 0; i<listeRes.length(); i++) {
			obj = listeRes.getJSONObject(i);
			String iri = obj.getJSONObject("iri").getString("value");
			String label = obj.getJSONObject("label").getString("value");
			//System.out.println(label+" : "+iri);
			dictLabel.put(iri, label);
			//System.out.println("------------------------");
		}
		
	}
	
	@CrossOrigin(origins = "http://localhost:8888")
	@RequestMapping (value = "/getOrigins", method = RequestMethod.GET)
	public String getOrigins() throws IOException, JSONException {  
		String line; 
		InputStream listeOriginsStream = new ClassPathResource("/requestBuilder/listOrigin.txt").getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(listeOriginsStream));
		
		JSONObject elements = new JSONObject();
		JSONObject nodes = new JSONObject();
		JSONArray nodesList = new JSONArray();
		JSONArray edgesList = new JSONArray();
		JSONObject node; JSONObject nodeId;
		
		while ((line = br.readLine()) != null) {
			//System.out.println(line);
			node = new JSONObject();
			nodeId = new JSONObject();
			nodeId.put("id", line);
			node.put("data", nodeId);
			nodesList.put(node);
		}
		
		listeOriginsStream.close();
		
		elements.put("elements", nodes);
		nodes.put("nodes", nodesList);
		
		nodes.put("edges", edgesList);
		
		//System.out.println(elements.toString());
		
		if (dictLabel==null) {
			generateLabelDict();
		}
		
		return elements.toString();
		//return "tipoui !";
	}
	
	public void createJsonObject() {
		conteneur = new JSONObject();
		nodes = new JSONObject();
		nodesList = new JSONArray();
		edgesList = new JSONArray();
		dataPropertiesList = new JSONArray();
		
		try {
			conteneur.put("elements", nodes);
			nodes.put("nodes", nodesList);
			
			conteneur.put("dataProperties", dataPropertiesList);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addDataProperty(String nodeName, String property, String value) throws JSONException {
		//System.out.println("addDataProperty");
		if (value.length()>lengthMax) {
			value=value.substring(0, lengthMax)+"...";
		}
		JSONObject prop = new JSONObject();
		prop.put("s", nodeName);
		prop.put("o", property);
		prop.put("p", value);
		dataPropertiesList.put(prop);
	}
	
	public void addNode(String nodeName) throws JSONException {
		node = new JSONObject();
		nodeId = new JSONObject();
		nodeId.put("id", collapseName(nodeName));
		node.put("data", nodeId);
		nodesList.put(node);
		nodes.put("nodes", nodesList);
		//elements.put("elements", nodes);
	}
	
	public void addEdge(String nodeSource, String edgeName, String nodeCible) throws JSONException {
		edge = new JSONObject();
		edgeData = new JSONObject();
		edgeData.put("id", collapseName(edgeName)+"_"+collapseName(nodeSource)+"_"+collapseName(nodeSource));
		edgeData.put("source", collapseName(nodeSource));
		edgeData.put("target", collapseName(nodeCible));
		//System.out.println("edgeName : "+edgeName);
		edgeData.put("label", collapseName(edgeName));
		edge.put("data", edgeData);
		edgesList.put(edge);
		nodes.put("edges", edgesList);
		//elements.put("elements", nodes);
	}
	
	public String collapseName(String name)  {
		name = name.replace("http://medicis.univ-rennes1.fr/ontologies/ontospm/MEDIRADClinicalResearchStudies.owl#", "ontomedirad:");
		name = name.replace("http://medicis.univ-rennes1.fr/ontologies/ontospm/OntoMEDIRAD.owl#", "ontomedirad:");	
		name = name.replace("http://dicom.nema.org/resources/ontology/DCM/", "dcm:");	
		name = name.replace("http://www.w3.org/2004/02/skos/core#", "skos:");	
		name = name.replace("http://purl.obolibrary.org/obo/", "purl:");	
		name = name.replace("http://www.w3.org/1999/02/22-rdf-syntax-ns#", "rdf:");			
		return name;
	}
	
	public String replaceAdressByName(String name) throws TupleQueryResultHandlerException, QueryEvaluationException, UnsupportedQueryResultFormatException, StardogException, IOException, JSONException  {
		if (name.contains("http")) {
			//String request = "#pragma describe.strategy bidirectional\n DESCRIBE <"+name+">";
			String resultats = describeRequest(name);	
			JSONObject jsonResult = new JSONObject(resultats);
			if (jsonResult.length()>0) {
				Iterator<String> iter = jsonResult.keys();
				while (iter.hasNext()) {
					String indivName = iter.next();
					//System.out.println("(l184) indivName "+indivName);
					//addNode(indivName);
					JSONObject properties = jsonResult.getJSONObject(indivName);
					//System.out.println("(l187) properties.length() "+properties.length());
					Iterator<String> iterProp = properties.keys();
					while (iterProp.hasNext()) {
						String prop = iterProp.next();
						//System.out.println("(l191) prop "+prop);
						
						String value = properties.getJSONArray(prop).getJSONObject(0).getString("value");
						if (value.contains("http://") && !value.contains(":bnode")) {
							//System.out.println("(l195) value : "+value);
							//System.out.println("-----------------------");
							//addNode(value);
							//addEdge(indivName, prop, value);
							if (prop.contains("label")) {
								return(value);
							}
							//addEdge(indivName, "rdf:type", name);
						}
					}
				}
			}
		} else {
			return(name);
		}
		//return"Tipoui";
		return name;
	}
	
	public String expandName(String name)  {
		if (name.contains("clinical_research_study")) {
			name = name.replace("ontomedirad:", "http://medicis.univ-rennes1.fr/ontologies/ontospm/MEDIRADClinicalResearchStudies.owl#");
		}
		name = name.replace("ontomedirad:", "http://medicis.univ-rennes1.fr/ontologies/ontospm/OntoMEDIRAD.owl#");	
		name = name.replace("rdf:", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");	
		name = name.replace("dcm:", "http://dicom.nema.org/resources/ontology/DCM/");	
		name = name.replace("skos:", "http://www.w3.org/2004/02/skos/core#");	
		name = name.replace("purl:", "http://purl.obolibrary.org/obo/");
		return name;
	}
	
	@CrossOrigin(origins = "http://localhost:8888")
	@RequestMapping(value = "/clicNode", method = RequestMethod.GET)    // , headers = "Accept=text/xml"
	public String testClicGraph(@RequestParam(value = "name", required = true) String name) 
			throws JSONException, TupleQueryResultHandlerException, QueryEvaluationException, UnsupportedQueryResultFormatException,
			StardogException, IOException, ParserConfigurationException, SAXException  {
		//System.out.println("clic sur "+name);

		String resultats;

		resultats = describeRequest(name);
		createJsonObject();
		
		JSONObject jsonResult = new JSONObject(resultats);
		if (jsonResult.length()>0) {
			Iterator<String> iter = jsonResult.keys();
			while (iter.hasNext()) {
				String indivName = iter.next();
				addNode(indivName);
				JSONObject properties = jsonResult.getJSONObject(indivName);
				Iterator<String> iterProp = properties.keys();
				while (iterProp.hasNext()) {
					String prop = iterProp.next();
					JSONArray propArray = properties.getJSONArray(prop);
					for (int i=0;i<propArray.length(); i++) {
						String value = propArray.getJSONObject(i).getString("value");
						String lang;
						if (propArray.getJSONObject(i).has("lang")) {
						lang = propArray.getJSONObject(i).getString("lang");}
						else {lang = null;}
												
						if (lang==null || lang.equals("en")) {
							if (dictLabel.containsKey(prop)) {
								prop = dictLabel.get(prop);
							}
							
							if (value.contains("http://") && !value.contains(":bnode")) {
								addNode(value);
								addEdge(indivName, prop, value);
								//addEdge(indivName, replaceAdressByName(prop), value);
								//addEdge(indivName, "rdf:type", name);
							} else if (!value.contains("http://") && !value.contains(":bnode")) {
								//if (indivName==name) {
									addDataProperty(indivName, prop, value);
								//}
							}
						}
					}
				}
			}
		} else {
			resultats = describeRequestClass(name);
			jsonResult = new JSONObject(resultats);
			Iterator<String> iter = jsonResult.keys();
			while (iter.hasNext()) {
				String indivName = iter.next();
				addNode(indivName);
				JSONObject properties = jsonResult.getJSONObject(indivName);
				Iterator<String> iterProp = properties.keys();
				while (iterProp.hasNext()) {
					String prop = iterProp.next();
					String value = properties.getJSONArray(prop).getJSONObject(0).getString("value");
					if (dictLabel.containsKey(prop)) {
						prop = dictLabel.get(prop);
					}
					if (value.contains("http://")) {
						addNode(value);
						addEdge(indivName, prop, value);
						//addEdge(indivName, "rdf:type", name);
					} else {
						//if (indivName==name) {
							addDataProperty(indivName, prop, value);
						//}
					}
				}
			}
		}
	
		//System.out.println("conteneur : "+conteneur.toString());
		return conteneur.toString();
		//return("Tipoui !");
	}
	
	private synchronized String describeRequest(String name) throws TupleQueryResultHandlerException, QueryEvaluationException, 
	UnsupportedQueryResultFormatException, IOException, StardogException {	
		//System.out.println("describeRequest");
		String request = "#pragma describe.strategy bidirectional\n"
				+"DESCRIBE <"+expandName(name)+">";
		
		createAdminConnection(database.ontoMedirad, true);
		logger.debug("Request "+request);
		GraphQuery aQuery = starDogConnection.graph(request);
		GraphQueryResult aResult=null; ByteArrayOutputStream out=null;
		aResult = aQuery.execute();
		out = new ByteArrayOutputStream();
		QueryResultIO.writeGraph(aResult, RDFFormat.RDFJSON, out);
		String resultats = out.toString();
		if (aResult!=null) {aResult.close();}
		starDogConnection.close();

		return resultats;
	}
	
	private synchronized String describeRequestClass(String name) throws TupleQueryResultHandlerException, QueryEvaluationException, 
	UnsupportedQueryResultFormatException, IOException, StardogException {	
		//System.out.println("describeRequestClass");
		String request = "DESCRIBE ?x WHERE {\n" + 
				"  ?x rdf:type "+name+" .\n" + 
				"}";
		createAdminConnection(database.ontoMedirad, false);
		logger.debug("Request "+request);
		GraphQuery aQuery = starDogConnection.graph(request);
		GraphQueryResult aResult=null; ByteArrayOutputStream out=null;
		aResult = aQuery.execute();
		out = new ByteArrayOutputStream();
		QueryResultIO.writeGraph(aResult, RDFFormat.RDFJSON, out);
		String resultats = out.toString();
		if (aResult!=null) {aResult.close();}
		starDogConnection.close();
		return resultats;
	}

	// Services de tests/debug
	
	
}
