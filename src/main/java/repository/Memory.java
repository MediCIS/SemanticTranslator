package repository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import org.apache.jena.ontology.Individual;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.api.ConnectionPool;
import com.complexible.stardog.api.ConnectionPoolConfig;
import com.complexible.stardog.api.SelectQuery;
import com.stardog.stark.query.SelectQueryResult;
import com.stardog.stark.query.io.QueryResultFormats;
import com.stardog.stark.query.io.QueryResultWriters;
import com.stardog.stark.query.io.ResultWritingFailed;

import repository.ImportController.database;


public class Memory extends OntologyPopulator {

	Connection starDogConnection; 													// Object for store the StardogConnection
	String starDogUrl = Application.starDogUrl;										// Retreive stardogURL from Application
	private final static Logger logger = LoggerFactory.getLogger(Memory.class);		// Logger

	private LinkedList<String> listSeriesctDataset;									
	private LinkedList<String> listStudyctDataset;
	private LinkedList<Individual> listIRIctDataset;

	private LinkedList<String> listSeriesPatient;
	private LinkedList<String> listStudyPatient;
	private LinkedList<Individual> listIRIPatient;

	private LinkedList<String> listInstitutions;
	private LinkedList<Individual> listIRIinstitutions;
	private LinkedList<Individual> listIRIroleOfResponsibleOrganization;

	private LinkedList<String> listSoftware;
	private LinkedList<Individual> listIRIsoftware;

	private LinkedList<String> listMcMethod;
	private LinkedList<Individual> listIRIMcMethod;

	private LinkedList<String> listIDsHuman;
	private LinkedList<Individual> listIRIHuman;

	private LinkedList<String> listDicomUID;
	private LinkedList<Individual> listIRIimagingStudies;

	private Hashtable<String, Individual> tablePatientNames;
	private Hashtable<String, Individual> tablePatientID;

	private Hashtable<String, Individual> tableStudyInstance;

	private Hashtable<String, Individual> tableAuthorName;

	private Hashtable<String, Individual> tableCalibrationIDs;
	
	private LinkedList<String> tableDicomIDs;



	public Memory() throws  IOException, InvocationTargetException, ResultWritingFailed, JSONException {
		initVoidMemory();
		requestSoftware();
		requestMCMethod();
		requestInstit();
		requestPatientCTImageDS();
		requestHuman();
	}

	public synchronized void initVoidMemory() {
		System.out.println("initVoidMemory");
		listSeriesctDataset = new LinkedList<String>();
		listStudyctDataset = new LinkedList<String>();
		listIRIctDataset = new LinkedList<Individual>();	

		listSeriesPatient = new LinkedList<String>();
		listStudyPatient = new LinkedList<String>();
		listIRIPatient = new LinkedList<Individual>();

		listInstitutions = new LinkedList<String>();
		listIRIinstitutions = new LinkedList<Individual>();
		listIRIroleOfResponsibleOrganization = new LinkedList<Individual>();

		listSoftware = new LinkedList<String>();
		listIRIsoftware = new LinkedList<Individual>();

		listMcMethod = new LinkedList<String>();
		listIRIMcMethod = new LinkedList<Individual>();

		listIDsHuman = new LinkedList<String>();
		listIRIHuman = new LinkedList<Individual>();

		listDicomUID = new LinkedList<String>();
		listIRIimagingStudies = new LinkedList<Individual>();

		tablePatientNames = new Hashtable<String, Individual>();
		tablePatientID = new Hashtable<String, Individual>();

		tableStudyInstance = new Hashtable<String, Individual>();

		tableAuthorName  = new Hashtable<String, Individual>();

		tableCalibrationIDs  = new Hashtable<String, Individual>();
		
		tableDicomIDs  = new LinkedList<String>();
	}

	public void initDicomIDsList() throws IOException, JSONException {
		String request = "SELECT ?a ?seriesUID ?studyUID WHERE {\n" + 
				"	?a ontomedirad:has_DICOM_series_instance_UID ?seriesUID .\n" + 
				" 	?a ontomedirad:has_DICOM_study_instance_UID ?studyUID .\n" + 
				"} ORDER BY ?seriesUID";
		
		String resultats = executeRequest(request);	
		String seriesUID; String studyUID; 
		
		JSONArray jsonResults = new JSONObject(resultats).getJSONObject("results").getJSONArray("bindings");
		for (int i=0; i<jsonResults.length(); i++) {
			seriesUID = jsonResults.getJSONObject(i).getJSONObject("seriesUID").getString("value");
			studyUID = jsonResults.getJSONObject(i).getJSONObject("studyUID").getString("value");

			tableDicomIDs.add(seriesUID+"_"+studyUID);
		}
	
		starDogConnection.close();
	}
	
	public boolean hasDicomUIDs(String seriesUID, String studyUID) {
		return tableDicomIDs.contains(seriesUID+"_"+studyUID);
	}

	
	public void testDicomUIDs() {
		System.out.println("testDicomUIDs");
		for (int i=0; i<tableDicomIDs.size(); i++) {
			System.out.println(tableDicomIDs.get(i));
		}
	}

	public synchronized Individual getHuman(String patientID) {
		System.out.println("getHuman");

		for (int i = 0; i<listIRIHuman.size(); i++) {
			System.out.println(listIDsHuman.get(i)+" : "+listIRIHuman.get(i));

			if (listIDsHuman.get(i).equalsIgnoreCase(patientID)) {
				System.out.println("return human : "+listIRIHuman.get(i));
				return (listIRIHuman.get(i));
			}
		}

		Individual patient = createIndiv(generateName("Human"), model.getResource(racineURI+"human"));	
		addDataProperty(patient, racineURI+"has_id", patientID);
		listIDsHuman.add(patientID.trim());
		listIRIHuman.add(patient);
		return patient;
	}

	public synchronized void requestHuman() throws IOException, JSONException  {
		logger.debug("requestHumans");
		starDogUrl = Application.starDogUrl ;	
		if (model==null) {model=Application.model;}

		String sparql = "SELECT DISTINCT ?human ?patientID WHERE {\n" + 
				"				?human rdf:type ontomedirad:human .\n" + 
				"				?human ontomedirad:has_id ?patientID .\n" + 
				"				} ORDER BY ?human";

		String resultats = executeRequest(sparql);			
		String id; String human;
		JSONArray jsonResults = new JSONObject(resultats).getJSONObject("results").getJSONArray("bindings");
		for (int i=0; i<jsonResults.length(); i++) {
			human = jsonResults.getJSONObject(i).getJSONObject("human").getString("value");
			id = jsonResults.getJSONObject(i).getJSONObject("patientID").getString("value");

			listIDsHuman.add(id.trim());
			listIRIHuman.add(model.getIndividual(human.trim()));
		}

		starDogConnection.close();

		logger.debug("requestHuman OK");
	}

	public synchronized Individual getAuthorByName(String name) {
		Individual author = tableAuthorName.get(name);
		if (author==null) {
			author = createIndiv(generateName("author"), model.getResource(racineURI+"author"));
			addDataProperty(author, racineURI+"has_name", name);
		}
		return author;
	} 

	public synchronized void requestStudyInstanceUID() throws IOException, InvocationTargetException, JSONException {
		logger.debug("requestHumans");
		starDogUrl = Application.starDogUrl ;	
		if (model==null) {model=Application.model;}

		String sparql = "SELECT DISTINCT ?imagingStudy ?StudyUID WHERE {\n" + 
				"  		?imagingStudy rdf:type ontomedirad:imaging_study .\n" + 
				"  		?imagingStudy ontomedirad:has_DICOM_study_instance_UID ?StudyUID .\n" + 
				"} ORDER BY ?imagingStudy" ;

		String resultats = executeRequest(sparql);	
		String StudyUID; String imagingStudy; 
		
		JSONArray jsonResults = new JSONObject(resultats).getJSONObject("results").getJSONArray("bindings");
		for (int i=0; i<jsonResults.length(); i++) {
			imagingStudy = jsonResults.getJSONObject(i).getJSONObject("imagingStudy").getString("value");
			StudyUID = jsonResults.getJSONObject(i).getJSONObject("StudyUID").getString("value");
			tableStudyInstance.put(StudyUID, model.createIndividual(imagingStudy, model.getResource(racineURI+"imaging_study")));
		}
	
		starDogConnection.close();

		logger.debug("requestHuman OK");
	}

	//public synchronized void setStudyInstance(String UID, Individual studyInstance) {
	//	tableStudyInstance.put(UID, studyInstance);
	//}

	public synchronized Individual getStudyInstanceByUID(String UID) {
		Individual study = tableStudyInstance.get(UID);	
		return study;
	}

	public synchronized void requestPatientNameIDs() throws IOException, InvocationTargetException, JSONException {
		logger.debug("requestHumans");
		starDogUrl = Application.starDogUrl ;	
		if (model==null) {model=Application.model;}

		String sparql = "SELECT DISTINCT ?human ?patientID ?patientName WHERE {\n" + 
				"  ?human rdf:type ontomedirad:human .\n" + 
				"  ?human ontomedirad:has_id ?patientID .\n" + 
				"  ?human ontomedirad:has_name ?patientName .\n" + 
				"} ORDER BY ?human" ;

		String resultats = executeRequest(sparql);	
		String human; String patientID; String patientName;
		
		JSONArray jsonResults = new JSONObject(resultats).getJSONObject("results").getJSONArray("bindings");
		for (int i=0; i<jsonResults.length(); i++) {
			human = jsonResults.getJSONObject(i).getJSONObject("human").getString("value");
			patientID = jsonResults.getJSONObject(i).getJSONObject("patientID").getString("value");
			patientName = jsonResults.getJSONObject(i).getJSONObject("patientName").getString("value");

			tablePatientNames.put(patientName, model.createIndividual(human, model.getResource(racineURI+"human")));
			tablePatientID.put(patientID, model.createIndividual(human, model.getResource(racineURI+"human")));
		}
	
		starDogConnection.close();

		logger.debug("requestHuman OK");
	}

	public synchronized void setPatientName(String name, Individual human) {
		System.out.println("setPatientName");
		System.out.println("name : "+name);
		System.out.println("human : "+human);
		System.out.println("tablePatientNames : "+tablePatientNames);
		if (tablePatientNames.containsKey(name) == false && name != null && human != null) { 
			tablePatientNames.put(name, human);
		}
	}

	public synchronized Individual getPatientByName(String name) {
		return tablePatientNames.get(name);
	}

	public synchronized void setPatientId(String Id, Individual human) {
		System.out.println("setPatientId");
		System.out.println("Id : "+Id);
		System.out.println("human : "+human);
		System.out.println("tablePatientID : "+tablePatientID);
		if (tablePatientID.containsKey(Id) == false) {
			tablePatientID.put(Id, human);
		}
	}

	public synchronized Individual getPatientbyId(String Id) {
		return tablePatientID.get(Id);
	}

	public synchronized void setImagingStudy(String DicomUID, Individual imagingStudy) {
		System.out.println("setImagingStudy");
		int n = listDicomUID.size();
		listIRIimagingStudies.add(n, imagingStudy);
		listDicomUID.add(n, DicomUID);
		tableStudyInstance.put(DicomUID, imagingStudy);
	}

	public synchronized Individual getImagingStudy(String DicomUID) {
		System.out.println("getImagingStudy : "+DicomUID);
		for (int i = 0; i<listDicomUID.size(); i++) {
			System.out.println(listDicomUID.get(i));
			if (listDicomUID.get(i).trim().equals(DicomUID.trim())) {
				return listIRIimagingStudies.get(i);
			}
		}
		return null;
	}

	public synchronized void setPatient(String SeriesID, String StudyID, Individual patient) {
		for (int i = 0; i<listSeriesPatient.size(); i++) {
			if (listSeriesPatient.get(i).equalsIgnoreCase(SeriesID.trim()) && listStudyPatient.get(i).equalsIgnoreCase(StudyID.trim())) {
				listIRIPatient.add(i, patient); return;
			}
		}
		listSeriesPatient.add(SeriesID.trim());
		listStudyPatient.add(StudyID.trim());
		listIRIPatient.add(patient);
	}

	public synchronized Individual getPatient(String SeriesID, String StudyID) {
		for (int i = 0; i<listSeriesPatient.size(); i++) {
			if (listSeriesPatient.get(i).equalsIgnoreCase(SeriesID.trim()) && listStudyPatient.get(i).equalsIgnoreCase(StudyID.trim())) {
				return listIRIPatient.get(i);
			}
		}
		return null;
	}

	public synchronized void setCtDataSet(String SeriesID, String StudyID, Individual CtDataSet) {
		for (int i = 0; i<listSeriesctDataset.size(); i++) {
			if (listSeriesctDataset.get(i).equalsIgnoreCase(SeriesID.trim()) && listStudyctDataset.get(i).equalsIgnoreCase(StudyID.trim())) {
				listIRIctDataset.add(i, CtDataSet); return;
			}
		}
		listSeriesctDataset.add(SeriesID.trim());
		listStudyctDataset.add(StudyID.trim());
		listIRIctDataset.add(CtDataSet);
	}

	public synchronized Individual getCtDataSet(String SeriesID, String StudyID) {
		for (int i = 0; i<listSeriesctDataset.size(); i++) {
			if (listSeriesctDataset.get(i).equalsIgnoreCase(SeriesID.trim())) {
				if (listStudyctDataset.get(i).equalsIgnoreCase(StudyID.trim())) {
					return listIRIctDataset.get(i);
				}
			}
		}
		return null;
	}

	public synchronized Individual getInstitution(String name) {
		for (int i = 0; i<listInstitutions.size(); i++) {
			if (listInstitutions.get(i).equalsIgnoreCase(name)) {
				return (listIRIinstitutions.get(i));
			}
		}
		Individual instit = createIndiv(generateName("institution"), Application.model.getResource("http://medicis.univ-rennes1.fr/ontologies/ontospm/OntoMEDIRAD.owl#institution"));
		addDataProperty(instit, racineURI+"has_name", name);
		Individual role = createIndiv(generateName("role_of_responsible_organization"), Application.model.getResource(racineURI+"role_of_responsible_organization"));
		listInstitutions.add(name);
		listIRIinstitutions.add(instit);
		listIRIroleOfResponsibleOrganization.add(role);
		return instit;
	}

	public synchronized Individual getRoleOfResponsibleOrganization(String name) {
		for (int i = 0; i<listInstitutions.size(); i++) {
			if (listInstitutions.get(i).equalsIgnoreCase(name)) {
				return (listIRIroleOfResponsibleOrganization.get(i));
			}
		}
		return null;
	}

	public synchronized Individual getSoftware(String name) {
		for (int i = 0; i<listSoftware.size(); i++) {
			if (listSoftware.get(i).equalsIgnoreCase(name)) {
				return (listIRIsoftware.get(i));
			}
		}

		Individual soft = createIndiv(generateName("Software"), Application.model.getResource(racineURI+"software"));
		addDataProperty(soft, racineURI+"has_name", name);
		listSoftware.add(name);
		listIRIsoftware.add(soft);
		return soft;
	}

	public synchronized Individual getMCmethod(String name) {
		for (int i = 0; i<listMcMethod.size(); i++) {
			if (listMcMethod.get(i).equalsIgnoreCase(name)) {
				return (listIRIMcMethod.get(i));
			}
		}
		Individual MCmethod = createIndiv(generateName("Monte_Carlo_CT_dosimetry_method"), Application.model.getResource(racineURI+"Monte_Carlo_CT_dosimetry_method"));
		addDataProperty(MCmethod, racineURI+"has_name", name);
		listMcMethod.add(name);
		listIRIMcMethod.add(MCmethod);
		return MCmethod;
	}

	public synchronized void requestSoftware() throws ResultWritingFailed, IOException, JSONException {
		logger.debug("requestSoftware");
		starDogUrl = Application.starDogUrl ;	

		String sparql = "SELECT DISTINCT ?software ?nameSoftware WHERE {\n" + 
				"?software rdf:type ontomedirad:software .\n" + 
				"?software ontomedirad:has_name ?nameSoftware .\n" + 
				"} ORDER BY ?software";
		
		String resultats = executeRequest(sparql);	
		String iri; String name;
		
		JSONArray jsonResults = new JSONObject(resultats).getJSONObject("results").getJSONArray("bindings");
		for (int i=0; i<jsonResults.length(); i++) {
			name = jsonResults.getJSONObject(i).getJSONObject("nameSoftware").getString("value");
			iri = jsonResults.getJSONObject(i).getJSONObject("software").getString("value");

			setSoftware(name, iri);
		}
		
		starDogConnection.close();
		logger.debug("requestSoftware OK");
	}
	
	public synchronized void requestMCMethod() throws ResultWritingFailed, IOException, JSONException {
		logger.debug("requestMCMethod");
		starDogUrl = Application.starDogUrl ;	
				
		String sparql = "SELECT DISTINCT ?method ?nameMethod WHERE {" + 
				"	?method rdf:type ontomedirad:Monte_Carlo_CT_dosimetry_method ." + 
				"	?method ontomedirad:has_name ?nameMethod ." + 
				"} ORDER BY ?method";
		String resultats = executeRequest(sparql);	
		String iri; String name;
		
		JSONArray jsonResults = new JSONObject(resultats).getJSONObject("results").getJSONArray("bindings");
		for (int i=0; i<jsonResults.length(); i++) {
			name = jsonResults.getJSONObject(i).getJSONObject("nameMethod").getString("value");
			iri = jsonResults.getJSONObject(i).getJSONObject("method").getString("value");	
			setMCMethod(name, iri);
		}
		
		starDogConnection.close();
		logger.debug("requestMCMethod OK");
	}

	public synchronized void requestInstit() throws ResultWritingFailed, IOException, JSONException{
		logger.debug("requestInstit");
		starDogUrl = Application.starDogUrl ;	
				
		String sparql = "SELECT DISTINCT ?institution ?nameInstit ?roleInstit WHERE {" + 
				"	?institution rdf:type ontomedirad:institution ." + 
				"	?institution ontomedirad:has_name ?nameInstit ." + 
				"    ?institution purl:BFO_0000161 ?roleInstit ." + 
				"} ORDER BY ?institution";
	
		String resultats = executeRequest(sparql);	
		
		String iriRole;
		String iriInstit; String nameInstit;
		
		JSONArray jsonResults = new JSONObject(resultats).getJSONObject("results").getJSONArray("bindings");
		for (int i=0; i<jsonResults.length(); i++) {
			nameInstit = jsonResults.getJSONObject(i).getJSONObject("nameInstit").getString("value");
			iriInstit = jsonResults.getJSONObject(i).getJSONObject("institution").getString("value");			
			iriRole = jsonResults.getJSONObject(i).getJSONObject("roleInstit").getString("value");			
			setInstit(nameInstit, iriInstit, iriRole);
		}
		
		starDogConnection.close();
		logger.debug("requestInstit OK");
	}

	public synchronized void requestPatientCTImageDS() throws ResultWritingFailed, IOException, JSONException{
		logger.debug("requestPatientCTImageDS");
		starDogUrl = Application.starDogUrl ;	
				
		String sparql = "SELECT DISTINCT ?patient ?CT_ImageDataSet ?handle WHERE {" + 
				"	?patient rdf:type ontomedirad:patient ." + 
				"  	?patient purl:BFO_0000054 ?CT_Acquisition ." + 
				"   ?CT_ImageDataSet ontomedirad:is_specified_output_of ?CT_Acquisition ." + 
				"  	?CT_ImageDataSet ontomedirad:has_IRDBB_WADO_handle ?handle ." + 
				"} ORDER BY ?software";
	
		String resultats = executeRequest(sparql);	
		String patientIRI; String CTImageDsIRI; String handle;
		
		JSONArray jsonResults = new JSONObject(resultats).getJSONObject("results").getJSONArray("bindings");
		for (int i=0; i<jsonResults.length(); i++) {
			patientIRI = jsonResults.getJSONObject(i).getJSONObject("patient").getString("value");
			CTImageDsIRI = jsonResults.getJSONObject(i).getJSONObject("CT_ImageDataSet").getString("value");			
			handle = jsonResults.getJSONObject(i).getJSONObject("handle").getString("value");			

			if (handle.contains("/studies/")) {
				handle = handle.split("/studies/")[1];
			}
			
			if (handle.contains("/series/")) {
				String study = handle.split("/series/")[0];
				String series = handle.split("/series/")[1];
				if (model==null) {model=Application.model;}
				
				setPatient(series, study, model.createIndividual(patientIRI, model.getResource(racineURI+"human")));
				setCtDataSet(series, study, model.createIndividual(CTImageDsIRI, model.getResource(racineURI+"CT_image_dataset")));

			}
		}
		
		starDogConnection.close();
		logger.debug("requestPatientCTImageDS OK");
	}

	public synchronized void setMCMethod(String nameMethod, String iri) {
		logger.debug("setMCMethod IRI : "+iri+" name : "+nameMethod);
		if (model==null) {model=Application.model;}
		Individual res = model.createIndividual(iri, model.getResource(racineURI+"Monte_Carlo_CT_dosimetry_method"));

		listMcMethod.add(nameMethod.trim());
		listIRIMcMethod.add(res);
	}

	public synchronized void setSoftware(String nameSoftware, String iri) {
		logger.debug("setSoftware IRI : "+iri+" name : "+nameSoftware);
		if (model==null) {model=Application.model;}
		Individual res = model.createIndividual(iri, model.getResource(racineURI+"software"));

		listSoftware.add(nameSoftware.trim());
		listIRIsoftware.add(res);
	}

	public synchronized void setInstit(String nameInstit, String iriInstit, String iriRole) {
		logger.debug("setInstit IRI : "+iriInstit+" name : "+nameInstit);
		if (model==null) {model=Application.model;}

		Individual instit = model.createIndividual(iriInstit, model.getResource(racineURI+"institution"));
		Individual roleInstit = model.createIndividual(iriRole, model.getResource(racineURI+"role_of_responsible_organization"));

		listInstitutions.add(nameInstit.trim());
		listIRIinstitutions.add(instit);
		listIRIroleOfResponsibleOrganization.add(roleInstit);
	}

	private synchronized String executeRequest(String request) throws  IOException {
		
		createAdminConnection(database.ontoMedirad);
		logger.debug("Request "+request);
	
		SelectQuery aQuery = starDogConnection.select(request);
		
		SelectQueryResult aResult=null; ByteArrayOutputStream out=null;

		aResult = aQuery.execute();
		out = new ByteArrayOutputStream();

		QueryResultWriters.write(aResult, out, QueryResultFormats.JSON);

		String resultats = out.toString();

		return resultats;
	}

	public synchronized String toString() {
		String str = "Tableau Patient : \n";
		for (int i = 0; i<listSeriesPatient.size();i++) {
			str+=listSeriesPatient.get(i);
			str+=";"+listStudyPatient.get(i);
			if (listIRIPatient.get(i)!=null) {str+=";"+listIRIPatient.get(i);}
			else {str+=";"+"NULL";}
			str+="\n";
		}
		str += "\nTableau CTImageDataSet : \n";
		for (int i = 0; i<listSeriesctDataset.size();i++) {
			str+=listSeriesctDataset.get(i);
			str+=";"+listStudyctDataset.get(i);
			if (listIRIctDataset.get(i)!=null) {str+=";"+listIRIctDataset.get(i);}
			else {str+=";"+"NULL";}
			str+="\n";
		}
		str += "\nTableau Institutions : \n";
		for (int i = 0; i<listInstitutions.size();i++) {
			str+=listInstitutions.get(i);
			str+=";"+listIRIinstitutions.get(i);
			str+=";"+listIRIroleOfResponsibleOrganization.get(i);
			str+="\n";
		}
		str += "\nTableau Software : \n";
		for (int i = 0; i<listSoftware.size();i++) {
			str+=listSoftware.get(i);
			str+=";"+listIRIsoftware.get(i);
			str+="\n";
		}
		str += "\nTableau McMethod : \n";
		for (int i = 0; i<listMcMethod.size();i++) {
			str+=listMcMethod.get(i);
			str+=";"+listIRIMcMethod.get(i);
			str+="\n";
		}
		return str;
	}


	private synchronized void createAdminConnection(database db) {
		if (starDogUrl==null) {starDogUrl=Application.starDogUrl;}
		logger.debug("Creation of the StarDog connection (Database : "+db.toString()+") at "+starDogUrl);
		ConnectionConfiguration connectionConfig = ConnectionConfiguration
				.to(db.toString()).server(starDogUrl).reasoning(true).credentials(ImportController.username, ImportController.password);
		// create the Stardog connection 
		ConnectionPool connectionPool = createConnectionPool(connectionConfig);
		starDogConnection = connectionPool.obtain();
		logger.debug("Sucessfully created the StarDog connection (Database : "+db.toString()+")");
	}

	private synchronized ConnectionPool createConnectionPool (ConnectionConfiguration connectionConfig) {
		ConnectionPoolConfig poolConfig = ConnectionPoolConfig.using(connectionConfig)
				.minPool(0).maxPool(50).expiration(30, TimeUnit.MINUTES).blockAtCapacity(1, TimeUnit.MINUTES);
		return poolConfig.create();
	}

}