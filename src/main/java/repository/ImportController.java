package repository;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.jena.ontology.Individual;
import org.apache.tomcat.util.http.fileupload.FileUploadBase.FileUploadIOException;
import org.apache.tomcat.util.http.fileupload.MultipartStream;
import org.apache.tomcat.util.http.fileupload.MultipartStream.MalformedStreamException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.query.TupleQueryResultHandlerException;
import org.openrdf.query.resultio.QueryResultIO;
import org.openrdf.query.resultio.UnsupportedQueryResultFormatException;
import org.openrdf.rio.RDFFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.complexible.common.rdf.query.resultio.TextTableQueryResultWriter;
import com.complexible.stardog.StardogException;
import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.api.ConnectionPool;
import com.complexible.stardog.api.ConnectionPoolConfig;
import com.complexible.stardog.api.SelectQuery;
import com.complexible.stardog.server.UnknownTransactionException;
import com.pixelmed.dicom.AttributeList;
import com.pixelmed.dicom.ContentItem;
import com.pixelmed.dicom.DicomException;
import com.pixelmed.dicom.StructuredReport;

import querries.Querry;

@Controller
@RestController
public class ImportController {	
	
	static String username = "admin"; 			//credentials for StarDog
	static String password = "admin";
	
	boolean reasoningDefault = false; 			// by defaut will request use reasoning
	
	String dockerHost = Application.dockerHost ;
	String starDogUrl = Application.starDogUrl;

	public enum database {ontoMedirad, test}; 	//StarDog Database list

	StructuredReport SR;
	String rdfName;
	
	Connection starDogConnection;
	HttpURLConnection connection;  
	
	Individual patient;
	
	static String handle; static String studyInstanceUID; static String seriesInstanceUID;
	
	Memory memory = Application.memory;
	
	int z = 0; 									// used for names RDF files and avoid overwriting

	private final static Logger logger = LoggerFactory.getLogger(ImportController.class);
	
	@RequestMapping(value = "/test")
	public String test(@RequestParam Map<String,String> requestParams) throws Exception{
		   String p1=requestParams.get("p1");
		   String p2=requestParams.get("p2");
		   return "Hello "+p1+" "+p2+"\n";
		}
	
	@RequestMapping ( value = "/getRequestList", method = RequestMethod.GET, headers = "Accept=text/xml", produces = {"application/json"})
	public String getRequestList() {return Application.listQuerries.getJsonString();} 
	
	@RequestMapping ( value = "/validateDicomFileSetDescriptor", method = RequestMethod.POST, headers = "Accept=text/xml", produces = {"application/json"})
	public String validateDicomFileSetDescriptor(@RequestBody String filesetDescriptorString) {

		logger.info("Validating DicomFileSetDescriptor");

		try {
			String tmpFilePath = "tmp.xml"; // XML content will be written in a temporary file (will be overwriten each time)
			JAXBContext jc = JAXBContext.newInstance("repository");

			PrintWriter out = new PrintWriter(tmpFilePath); 
			out.println(filesetDescriptorString); // write XML content to be validated in the file
			out.close();

			SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
			
			Schema schema = factory.newSchema(new StreamSource(new ClassPathResource("/xsd/dicomFileSetDescriptor.xsd").getInputStream())); 
			//read the XML schema

			Unmarshaller unmarshaller = jc.createUnmarshaller();
			unmarshaller.setSchema(schema);

			DicomFileSetDescriptor fileSetDescriptor = (DicomFileSetDescriptor) unmarshaller.unmarshal(new File(tmpFilePath));
		
		} catch (UnmarshalException  e) {
			String msg;
			if (e.getCause() != null ) {
				msg =  e.getCause().getMessage();
			} else {
				msg =  e.toString();
			}
			e.printStackTrace();
			return new ValidationReport(false, msg).getJson().toString();
		} catch (Exception  e) {
			e.printStackTrace();
			return new ValidationReport(false, e.toString()).getJson().toString();
		}
		return new ValidationReport(true, "").getJson();
	}
	
	@RequestMapping ( value = "/validateNonDicomFileSetDescriptor", method = RequestMethod.POST, headers = "Accept=text/xml", produces = "application/json")
	public String validateNonDicomFileSetDescriptor(@RequestBody String filesetDescriptorString) {

		logger.info("Validating NonDicomFileSetDescriptor");

		try {
			String tmpFilePath = "tmp.xml";
			JAXBContext jc = JAXBContext.newInstance("repository");

			PrintWriter out = new PrintWriter(tmpFilePath);
			out.println(filesetDescriptorString);
			out.close();

			SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

			Schema schema = factory.newSchema(new StreamSource(new ClassPathResource("/xsd/nonDicomFileSetDescriptor.xsd").getInputStream()));
			
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			unmarshaller.setSchema(schema);

			NonDicomFileSetDescriptor fileSetDescriptor = (NonDicomFileSetDescriptor) unmarshaller.unmarshal(new File(tmpFilePath));

		} catch (UnmarshalException  e) {

			String msg;
			if( e.getCause() != null ) {
				msg =  e.getCause().getMessage();
			} else {msg =  e.toString();}
			e.printStackTrace();
			return new ValidationReport(false, msg).getJson().toString();
		} catch (Exception  e) {
			e.printStackTrace();
			return new ValidationReport(false, e.toString()).getJson().toString();
		}
		return new ValidationReport(true, "").getJson().toString();
	}
	
	public static boolean GateKeeper(String request) {
		if (request.contains("CONSTRUCT")) {return false;}
		return true;
	}
	
	public String executeQuerryFromList(String idRequest, String isReasoning) {
		System.out.println("executeQuerryFromList "+idRequest);
		Querry q = Application.listQuerries.getRequest(idRequest);
		
		if (q!=null) {
			System.out.println(q.getRequest());
			String a = executeQuerry(q.getRequest(), isReasoning);
			System.out.println("executeQuerryFromList return : "+a);
			return a;
		} else {
			return ("{\"res\": \"Error : Unknown request\"}");
		}
	}
	
	public String executeQuerry(String request, String isReasoning) {
		System.out.println("executeQuerry");
		if (GateKeeper(request)==false) {return "Request refused for Security Reason";}
		
		switch(isReasoning) {
		case "true": 
			System.out.println("case : true");
			createAdminConnection(database.ontoMedirad, true); 
			break;
		case "false":
			createAdminConnection(database.ontoMedirad, false); 
			break;
		default:
			createAdminConnection(database.ontoMedirad, reasoningDefault);
			break;
		}
		
		SelectQuery aQuery = starDogConnection.select(request);
		
		TupleQueryResult aResult = null;

		System.out.println("execute");
		try { aResult = aQuery.execute();}
		catch (StardogException e) {
			starDogConnection.close();
			return ("{\"res\": \""+e.toString()+"\"}");
		}
		System.out.println("post execute");
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		try {QueryResultIO.writeTuple(aResult, TextTableQueryResultWriter.FORMAT.JSON, out);
		} catch (TupleQueryResultHandlerException | QueryEvaluationException | UnsupportedQueryResultFormatException | IOException e) {
			e.printStackTrace();
		} finally {starDogConnection.close();aResult.close();}
		
		System.out.println("executeQuerry return : "+out.toString());
		
		return out.toString();
		
	}
	
	@RequestMapping( value = "/requestFromList", method = RequestMethod.GET, headers = "Accept=text/xml", produces = "application/sparql-results+json")
	public String requestFromList(@RequestParam("id") String id) {
		String isReasoning = "false";
		System.out.println("requestFromList "+id+" "+isReasoning);
		String a = executeQuerryFromList(id, isReasoning);
		System.out.println("requestFromList return : "+a);
		return a;
	}
	
	@RequestMapping( value = "/testRequestControle", method = RequestMethod.GET, headers = "Accept=text/xml")
	public String TestRequestControle(@RequestParam Map<String,String> requestParams) throws Exception{
		System.out.println(requestParams);
		String name = requestParams.get("name");
		int valueRef = Integer.parseInt(requestParams.get("valueRef"));
		String request = "SELECT (COUNT(DISTINCT ?entity) AS ?count) WHERE {?entity rdf:type "+name+" . }";
		System.out.println(request);
		String res = executeQuerry(request,"false");
		
		try {
			int valeur = ((JSONObject) ((JSONObject) ((JSONArray) ((JSONObject) new JSONObject(res).get("results")).getJSONArray("bindings")).get(0)).get("count")).getInt("value");
			System.out.println("value : "+valeur);
			if (valeur==valueRef) {
				return "{\"res\": \"True\"}" ;
			} else {
				return "{\"res\": \"False\"}" ;
			}
		} catch (JSONException e) {
			return ("{\"res\": \"Error : "+e.getMessage()+"\"}");
		} 
	}
	
	@RequestMapping( value = "/testReturnReq", method = RequestMethod.GET, headers = "Accept=text/xml")
	public String RequeteTestReturn(@RequestParam("isReasoning") String isReasoning) {

		String sparql1 = "SELECT DISTINCT ?dataset ?model ?manufacturer ?kvpvalue ?kvpunitlabel ?tubecurrentvalue ?tubecurrentunitlabel ?exptimevalue ?exptimeunitlabel ?useofxraymodvalue\n" + 
				"	WHERE {\n" + 
				"?dataset rdf:type ontomedirad:CT_image_dataset .\n" + 
				"?dataset ontomedirad:is_specified_output_of ?ctacq .\n" + 
				"?ctacq rdf:type ontomedirad:CT_acquisition .\n" + 
				"?scanner rdf:type ontomedirad:CT_scanner .\n" + 
				"?acqrole rdf:type ontomedirad:image_acquisition_role .\n" + 
				"?acqrole purl:BFO_0000052 ?scanner.     \n" + 
				"?acqrole purl:BFO_0000054 ?ctacq . \n" + 
				"OPTIONAL {?ctacq ontomedirad:has_setting ?tubecurrent .\n" + 
				"?tubecurrent rdf:type dcm:113734 .\n" + 
				"?tubecurrent purl:IAO_0000004 ?tubecurrentvalue . \n" + 
				"OPTIONAL {?tubecurrent purl:IAO_0000039 ?tubecurrentunit . \n" + 
				"?tubecurrentunit rdfs:label ?tubecurrentunitlabel }}.\n" + 
				"OPTIONAL {?ctacq ontomedirad:has_setting ?xraymodsetting .\n" + 
				"?xraymodsetting rdf:type ?xraymodclass .\n" + 
				"?xraymodclass rdfs:subClassOf* dcm:113842 .\n" + 
				"?xraymodsetting purl:IAO_0000004 ?useofxraymodvalue } .\n" + 
				"OPTIONAL {?ctacq ontomedirad:has_setting ?kvpsetting .\n" + 
				"?kvpsetting rdf:type dcm:113733 .\n" + 
				"?kvpsetting purl:IAO_0000004 ?kvpvalue . \n" + 
				"OPTIONAL {?kvpsetting purl:IAO_0000039 ?kvpunit .\n" + 
				"?kvpunit rdfs:label ?kvpunitlabel }}.\n" + 
				"OPTIONAL {?ctacq ontomedirad:has_setting ?exptime .\n" + 
				"?exptime rdf:type dcm:113824 .\n" + 
				"?exptime purl:IAO_0000004 ?exptimevalue .\n" + 
				"OPTIONAL {?exptime purl:IAO_0000039 ?exptimeunit .\n" + 
				"?exptimeunit rdfs:label ?exptimeunitlabel }}.\n" + 
				"?scanner ontomedirad:has_manufacturer_name ?manufacturer .\n" + 
				"?scanner ontomedirad:has_model_name ?model .\n" + 
				"}\n" ;
		
		String sparql2 = "SELECT DISTINCT ?dataset ?model ?manufacturer ?kvpvalue ?kvpunitlabel ?tubecurrentvalue ?tubecurrentunitlabel ?exptimevalue ?exptimeunitlabel ?useofxraymodvalue\n" + 
				"	WHERE {\n" + 
				"?dataset rdf:type ontomedirad:CT_image_dataset .\n" + 
				"?dataset ontomedirad:is_specified_output_of ?ctacq .\n" + 
				"?ctacq rdf:type ontomedirad:CT_acquisition .\n" + 
				"?scanner rdf:type ontomedirad:CT_scanner .\n" + 
				"?acqrole rdf:type ontomedirad:image_acquisition_role .\n" + 
				"?acqrole purl:BFO_0000052 ?scanner.     \n" + 
				"?acqrole purl:BFO_0000054 ?ctacq . \n" +   
				"?scanner ontomedirad:has_manufacturer_name ?manufacturer .\n" + 
				"?scanner ontomedirad:has_model_name ?model .\n" + 
				"}\n";
		
		String sparql3  = "SELECT DISTINCT ?dataset ?model ?manufacturer ?kvpvalue ?kvpunitlabel ?tubecurrentvalue ?tubecurrentunitlabel ?exptimevalue ?exptimeunitlabel ?useofxraymodvalue\n" + 
				"	WHERE {\n" + 
				"?dataset rdf:type ontomedirad:CT_image_dataset .\n" + 
				"?dataset ontomedirad:is_specified_output_of ?ctacq .\n" + 
				"?ctacq rdf:type ontomedirad:CT_acquisition .\n" + 
				"?scanner rdf:type ontomedirad:CT_scanner .\n" + 
				"?acqrole rdf:type ontomedirad:image_acquisition_role .\n" + 
				"?acqrole purl:BFO_0000052 ?scanner.     \n" + 
				"?acqrole purl:BFO_0000054 ?ctacq . \n" + 
				"?scanner ontomedirad:has_manufacturer_name ?manufacturer .\n" + 
				"?scanner ontomedirad:has_model_name ?model .\n" + 
				"}\n";
		
		return executeQuerry(sparql1 ,isReasoning);
		
	}
	
	public static boolean databaseContains(String test) {
	    for (database b : database.values()) {
	        if (b.name().equals(test)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	@RequestMapping( value = "/importDicomFileSetDescriptor", method = RequestMethod.POST, headers = "Accept=text/xml")
	public String importDicomData(@RequestBody DicomFileSetDescriptor dicomFileSetDescriptor,
			 @RequestParam(value = "db", required = false) String db) {
		logger.info("Dicom Import request received");
		if (dockerHost==null) {dockerHost = Application.dockerHost ;}
		if (starDogUrl==null) {starDogUrl = Application.starDogUrl ;}
		if (memory==null) {memory=Application.memory;}
		//String db = "test";
		OntologyPopulator.retrievePatientData(dicomFileSetDescriptor.getPatientDescriptor());

		for (DICOMStudyType study : dicomFileSetDescriptor.getDICOMStudy() ){
			for (DICOMSeriesType series : study.getDICOMSeries() ) {
				if(series.getDICOMSeriesDescriptor().getModality00080060().equals("SR")) {
					logger.info("SR referenced");
					studyInstanceUID = study.getDICOMStudyDescriptor().getStudyInstanceUID0020000D();
					seriesInstanceUID = series.getDICOMSeriesDescriptor().getSeriesInstanceUID0020000E();
					if (db==null)  {createAdminConnection(database.ontoMedirad);}
					else {createAdminConnection(db);}
					retrieveSR(studyInstanceUID, seriesInstanceUID);
					starDogConnection.close();

				} else if (series.getDICOMSeriesDescriptor().getModality00080060().equals("CT")) {
					logger.info("CT referenced");
					studyInstanceUID = study.getDICOMStudyDescriptor().getStudyInstanceUID0020000D();
					seriesInstanceUID = series.getDICOMSeriesDescriptor().getSeriesInstanceUID0020000E();
					if (db==null)  {createAdminConnection(database.ontoMedirad);}
					else {createAdminConnection(db);}
					retrieveCT(dicomFileSetDescriptor, studyInstanceUID, seriesInstanceUID);
					starDogConnection.close();

				} else {
					logger.warn("Unknown reference : "+series.getDICOMSeriesDescriptor().getModality00080060());
				}
			}
		}
		return "{\"res\": \"ImportDicomFileSetDescriptor Request received\"}";
	}
	
	@RequestMapping( value = "/importNonDicomFileSetDescriptor", method = RequestMethod.POST, headers = "Accept=text/xml")
	public String importNonDicomData(@RequestBody NonDicomFileSetDescriptor nonDicomFileSetDescriptor) {
		
		if (dockerHost==null) {dockerHost = Application.dockerHost ;}
		if (starDogUrl==null) {starDogUrl = Application.starDogUrl ;}		
		
		if (nonDicomFileSetDescriptor.wp2Subtask212WorkflowData!=null) {
			TranslateNonDicomData.retrieveSubtastk212(nonDicomFileSetDescriptor.wp2Subtask212WorkflowData.iterator());
		}

		logger.info("Retrieving NON Dicom FileSetDescriptor");
		z++;
		rdfName = "RdfBackup/nonDicom_study" + nonDicomFileSetDescriptor.referencedClinicalResearchStudy.clinicalResearchStudyID +"_"+z+".rdf";
		writingRDF(rdfName);
		createAdminConnection(database.ontoMedirad);
		setInStarDog(rdfName);
		starDogConnection.close();

		return "{\"res\": \"ImportNonDicomFileSetDescriptor Request received\"}";
	}
	
	public boolean retrieveSR(String studyInstanceUID, String seriesInstanceUID) {
		logger.warn("Retrieving SR StudyInstanceUID: " + studyInstanceUID+" SeriesInstanceUID: " + seriesInstanceUID);
		URL url;
		try {
			String targetURL = "http://"+dockerHost+"/dcm4chee-arc/aets/DCM4CHEE/rs/studies/" + studyInstanceUID + "/series/" + seriesInstanceUID;
			handle = "/pacs/studies/"+studyInstanceUID+"/series/"+seriesInstanceUID;
			
			//Create connection
			url = new URL(targetURL);
			connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "multipart/related; type=application/dicom;");

			String boundary = "";
			String boundaryHeader = "boundary=";
			String contenttype = connection.getHeaderField("Content-Type");
			int bufsize = connection.getContentLength();

			// retrieving boundary within Content-Type
			String[] contenttypeArray = contenttype.split(";");
			for ( String content : contenttypeArray ){
				if(content.contains(boundaryHeader)) {
					boundary = content.substring(content.indexOf("=")+1);
					logger.debug("Boundary found: " + boundary);
				}
			}

			logger.debug("bufsize : "+bufsize);
			if (bufsize<=0) { // correction when bufsize = -1
				bufsize=50; logger.debug("bufsize corrected to  "+bufsize);
			}

			MultipartStream multipartStream = new MultipartStream(connection.getInputStream(), boundary.getBytes(), bufsize , null);

			// Write SR (DCM file)
			String srFilename = "SR.dcm";
			logger.debug("Writing SR in : "+srFilename);
			File file = new File(srFilename);
			FileOutputStream out = new FileOutputStream(file.getAbsolutePath());
			boolean nextPart = multipartStream.skipPreamble();

			if (nextPart) {
				multipartStream.readHeaders();
				multipartStream.readBodyData(out);
			}

			out.close();

			logger.info("Reading SR (local file)");	
			File f = new File(srFilename);			
			AttributeList liste = new AttributeList();
			
			try {liste.read(f);
			SR = new StructuredReport(liste);
			} catch (IOException e) {
				logger.debug("Reading SR file : IOException");
				logger.debug(e.getMessage());
			} catch (DicomException e) {
				logger.debug("Reading SR file : DicomException");			
				logger.debug(e.getMessage());
			} finally {TranslateDicomData.readingSR((ContentItem) SR.getRoot());}

			rdfName = "RdfBackup/SR_study" + studyInstanceUID+"_series_" + seriesInstanceUID+".rdf";
			writingRDF(rdfName);

			setInStarDog(rdfName);

		} catch (ProtocolException e) {
			logger.debug("Retrieving SR : ProtocolException");
			logger.debug(e.getMessage());
		} catch (MalformedURLException e) {
			logger.debug("Retrieving SR : MalformedURLException");
			logger.debug(e.getMessage());
		} catch (FileUploadIOException e) {
			logger.debug("Retrieving SR : FileUploadIOException");
			logger.debug(e.getMessage());
		} catch (MalformedStreamException e) {
			logger.debug("Retrieving SR : MalformedStreamException");
			logger.debug(e.getMessage());
		} catch (IOException e) {
			logger.debug("Retrieving SR : IOException");
			logger.debug(e.getMessage());
		} finally {
			logger.debug("Retrieving SR : No exception catched");
			if(connection != null) {connection.disconnect(); }
		}
		return true;
	}
	
	public boolean retrieveCT(DicomFileSetDescriptor DicomFileSetDescriptorContent, String studyInstanceUID, String seriesInstanceUID) {
		logger.info("Retrieving CT"+" StudyInstanceUID: " + studyInstanceUID+" SeriesInstanceUID: " + seriesInstanceUID);
		Iterator<DICOMStudyType> iterFileSet = DicomFileSetDescriptorContent.dicomStudy.iterator();
		TranslateDicomData.readingCT(iterFileSet, studyInstanceUID, seriesInstanceUID);
		rdfName = "RdfBackup/CT_study" + studyInstanceUID+"_series_" + seriesInstanceUID+".rdf";
		writingRDF(rdfName);
		setInStarDog(rdfName);
		return true;
	}
	
	public void createAdminConnection(database db, boolean paramreasoner) {
		if (dockerHost==null) {dockerHost = Application.dockerHost ;}
		if (starDogUrl==null) {starDogUrl = Application.starDogUrl ;}		
		logger.debug("Creation of the StarDog connection (Database : "+db.toString()+") at "+starDogUrl);
		ConnectionConfiguration connectionConfig = ConnectionConfiguration
				.to(db.toString()).server(starDogUrl).credentials(username, password).reasoning(paramreasoner);;

		logger.debug("StarDog connection (reasoning : "+connectionConfig.REASONING_ENABLED.toString()+")");
				 
				// create the Stardog connection 
		ConnectionPool connectionPool = createConnectionPool(connectionConfig);
		starDogConnection = connectionPool.obtain();
		logger.debug("Sucessfully created the StarDog connection (Database : "+db.toString()+")");
	}
	
	public void createAdminConnection(database db) {createAdminConnection(db, reasoningDefault);}
	
	public void createAdminConnection(String db) {
	    for (database b : database.values()) {
	        if (b.name().equals(db)) {
	        	createAdminConnection(b, reasoningDefault); return;
	        }
	    }
	    createAdminConnection(database.ontoMedirad, reasoningDefault);
	}
	
	public void createAdminConnection(String db, boolean paramreasoner) {
	    for (database b : database.values()) {
	        if (b.name().equals(db)) {
	        	createAdminConnection(b, paramreasoner); return;
	        }
	    }
	    createAdminConnection(database.ontoMedirad, paramreasoner);
	}

	private ConnectionPool createConnectionPool (ConnectionConfiguration connectionConfig) {
		ConnectionPoolConfig poolConfig = ConnectionPoolConfig.using(connectionConfig)
				.minPool(0).maxPool(50).expiration(30, TimeUnit.MINUTES).blockAtCapacity(1, TimeUnit.MINUTES);
		return poolConfig.create();
	}

	public void writingRDF(String pathOut) {
		logger.info("Writing RDF file in "+pathOut);		
		try {
			FileOutputStream sortie = new FileOutputStream(pathOut);
			OntologyPopulator.populateModel.write(sortie, "RDF/XML-ABBREV");
		} catch (FileNotFoundException e) {
			logger.debug("Writing RDF file : FileNotFoundException");	
			logger.debug(e.getMessage());
		} catch (UnknownTransactionException e) {
			logger.debug("Writing RDF file : UnknownTransactionException");	
			logger.debug(e.getMessage());
		} finally {
			logger.info("Writing RDF file Sucessfull");	
		}
	}

	public void setInStarDog(String path) {
		logger.info("Transfer to stardog...");
		starDogConnection.begin();
		try {starDogConnection.add().io().format(RDFFormat.RDFXML).stream(new FileInputStream(path)); 
		} catch (StardogException e) {
			logger.debug("StardogException");
			logger.debug(e.getMessage());
		} catch (FileNotFoundException e) {
			logger.debug("StardogException");
			logger.debug(e.getMessage());
		}

		starDogConnection.commit();
		logger.info("Transfer to stardog : Complete");
	}
}