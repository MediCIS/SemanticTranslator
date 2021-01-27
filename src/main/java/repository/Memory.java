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

import repository.ServiceController.database;


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

	private Hashtable<String, Individual> tableTemplateOfSR;

	// added Bernard 15-06-2020
	public LinkedList<Individual> listIRIInternalRadiotherapy;
	public LinkedList<Individual> listIRIRadiopharmaceuticalAdministration;
	public LinkedList<String> listRadiopharmaceuticalAdministrationId;
	public LinkedList<String> listHumanIdOfInternalRadiotherapy ;

	public LinkedList<Individual> listIRITimePoint;
	public LinkedList<String> listHumanIdOfTimePoint;
	public LinkedList<String> listTimePointId;

	// added Bernard 31-07-2020
	public LinkedList<Individual> listIRISPECTCalibration;
	// public LinkedList<Individual> listIRIIsotopeOfSPECTCalibration;
	public LinkedList<Individual> listIRISPECTRecoveryCoefficientCurve;
	public LinkedList<Individual> listIRISPECTCalibrationCoefficient;
	public LinkedList<String> listSPECTReferenceCalibrationDate;
	public LinkedList<String> listIdOfSPECTCalibration; // date+labelisotope

	public LinkedList<Individual> listIRICTCalibration;
	public LinkedList<Individual> listIRICTNumberCalibrationCurve;
	public LinkedList<String> listCTReferenceCalibrationDate;


	public Memory() throws  IOException, InvocationTargetException, ResultWritingFailed, JSONException {
		initVoidMemory();
		requestSoftware();
		requestMCMethod();
		requestInstit();
		requestDicomDatasets();
		requestHuman();
		requestTemplateOfSR();
		requestStudyInstanceUID();
		requestInternalRadiotherapy();
		requestTimePoint();
		requestSPECTCalibration();
		requestCTCalibration();
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
		
		tableDicomIDs = new LinkedList<String>();
		
		tableTemplateOfSR = new Hashtable<String, Individual>();

		//added by Bernard 15-06-2020

		listIRIInternalRadiotherapy = new LinkedList<Individual>();

		listIRIRadiopharmaceuticalAdministration = new LinkedList<Individual>();

		listRadiopharmaceuticalAdministrationId = new LinkedList<String>();

		listHumanIdOfInternalRadiotherapy = new LinkedList<String>();

		listIRITimePoint  = new LinkedList<Individual>();

		listHumanIdOfTimePoint = new LinkedList<String>();

		listTimePointId = new LinkedList<String>();

		//added by Bernard 31-07-2020

		listIRISPECTCalibration = new LinkedList<Individual>();
		//listIRIIsotopeOfSPECTCalibration = new LinkedList<Individual>();
		listIRISPECTRecoveryCoefficientCurve = new LinkedList<Individual>();
		listIRISPECTCalibrationCoefficient = new LinkedList<Individual>();
		listSPECTReferenceCalibrationDate = new LinkedList<String>();
		listIdOfSPECTCalibration = new LinkedList<String>();
		listIRICTCalibration = new LinkedList<Individual>();
		listIRICTNumberCalibrationCurve = new LinkedList<Individual>();
		listCTReferenceCalibrationDate = new LinkedList<String>();

	}

	public Individual getTemplateOfSR(String protocolName, String protocolID) {
		if (tableTemplateOfSR.containsKey(protocolName)) {
			return tableTemplateOfSR.get(protocolName);
		} else {
			Individual template = createIndiv(generateName("template_of_structured_report"), model.getResource(racineURI+"template_of_structured_report"));
			addDataProperty(template, racineURI+"has_name", protocolName);
			if (protocolID != null) {
				addDataProperty(template, racineURI+"has_id", protocolID);
			} 
			return template;
		}
	}
	
	public synchronized void requestTemplateOfSR() throws IOException, JSONException  {
		logger.debug("requestTemplateOfSR");
		starDogUrl = Application.starDogUrl ;	
		if (model==null) {model=Application.model;}

		String sparql = "SELECT DISTINCT ?template ?name WHERE {\n" + 
				"				?template rdf:type ontomedirad:template_of_structured_report .\n" + 
				"				?template ontomedirad:has_name ?name .\n" + 
				"		} ORDER BY ?template";

		String resultats = executeRequest(sparql);			
		String name; String template;
		JSONArray jsonResults = new JSONObject(resultats).getJSONObject("results").getJSONArray("bindings");
		for (int i=0; i<jsonResults.length(); i++) {
			template = jsonResults.getJSONObject(i).getJSONObject("template").getString("value");
			name = jsonResults.getJSONObject(i).getJSONObject("name").getString("value");
//			tableTemplateOfSR.put(name, createIndiv(template, model.createResource(racineURI+"template_of_structured_report")));
			tableTemplateOfSR.put(name, model.createIndividual(template, model.getResource(racineURI+"template_of_structured_report")));
		}

		starDogConnection.close();

		logger.debug("requestTemplateOfSR OK");
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
			//System.out.println(listIDsHuman.get(i)+" : "+listIRIHuman.get(i));

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
		logger.debug("requestHuman");
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
			//logger.debug("memory requestHumans id "+id.trim());
			listIDsHuman.add(id.trim());
			//logger.debug("memory requestHumans human"+model.getIndividual(human.trim()));
			//listIRIHuman.add(model.getIndividual(human.trim()));
			listIRIHuman.add(model.createIndividual(human.trim(), model.getResource(racineURI+"human"))) ;
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
		logger.debug("requestStudyInstanceUID");
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

		logger.debug("requestStudyInstanceUID OK");
	}

	//public synchronized void setStudyInstance(String UID, Individual studyInstance) {
	//	tableStudyInstance.put(UID, studyInstance);
	//}

	public synchronized Individual getStudyInstanceByUID(String UID) {
		Individual study = tableStudyInstance.get(UID);	
		return study;
	}

	public synchronized void requestPatientNameIDs() throws IOException, InvocationTargetException, JSONException {
		logger.debug("requestPatientNameIDs");
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

		logger.debug("requestPatientNameIDs OK");
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
		if (tablePatientID.containsKey(Id)) {
			return tablePatientID.get(Id);
		} else {
			Individual human = createIndiv(generateName("human"), model.getResource(racineURI+"human"));
			addDataProperty(human, racineURI+"has_id", Id);
			setPatientId(Id,human);
			return human;
		}
		
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

	public synchronized void setHuman(String SeriesID, String StudyID, Individual patient) {
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

	public synchronized void setDataset(String SeriesID, String StudyID, Individual CtDataSet) {
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

	public synchronized void requestDicomDatasets() throws ResultWritingFailed, IOException, JSONException {
		logger.debug("requestDicomDatasets");
		starDogUrl = Application.starDogUrl ;	
				
		String sparql = "SELECT DISTINCT ?human ?dataset ?handle ?datasetclass WHERE {" + 
				"	?patient rdf:type ontomedirad:patient ." + 
				"   ?human purl:BFO_0000087 ?patient ." +
				"  	?patient purl:BFO_0000054 ?acquisition ." + 
				"   ?dataset ontomedirad:is_specified_output_of ?acquisition ." + 
				"  	?dataset ontomedirad:has_IRDBB_WADO_handle ?handle ." + 
				"  	?dataset rdf:type ?datasetclass ." + 
				"} ";
	
		String resultats = executeRequest(sparql);	
		String humanIRI; String datasetIRI; String handle; String datasetclass;
		
		JSONArray jsonResults = new JSONObject(resultats).getJSONObject("results").getJSONArray("bindings");
		for (int i=0; i<jsonResults.length(); i++) {
			humanIRI = jsonResults.getJSONObject(i).getJSONObject("human").getString("value");
			datasetIRI = jsonResults.getJSONObject(i).getJSONObject("dataset").getString("value");			
			handle = jsonResults.getJSONObject(i).getJSONObject("handle").getString("value");	
			datasetclass = jsonResults.getJSONObject(i).getJSONObject("datasetclass").getString("value");						

			if (handle.contains("/studies/")) {
				handle = handle.split("/studies/")[1];
			}
			
			if (handle.contains("/series/")) {
				String study = handle.split("/series/")[0];
				String series = handle.split("/series/")[1];
				if (model==null) {model=Application.model;}
				
				setHuman(series, study, model.createIndividual(humanIRI.trim(), model.getResource(racineURI+"human")));
				setDataset(series, study, model.createIndividual(datasetIRI.trim(), model.getResource(datasetclass)));
			}
			logger.debug("requestDicomDatasets humanIRI"+ humanIRI );
			logger.debug("requestDicomDatasets datasetIRI"+ datasetIRI );
			logger.debug("requestDicomDatasets handle"+ handle );
		}
		
		starDogConnection.close();
		logger.debug("requestDicomDatasets OK");
	}

	// added by Bernard 15-06-2020
	public synchronized void requestInternalRadiotherapy() throws IOException, InvocationTargetException, JSONException {
		logger.debug("requestInternalRadiotherapy");
		starDogUrl = Application.starDogUrl ;	
		if (model==null) {model=Application.model;}

		String sparql = "SELECT DISTINCT ?PatientId ?InternalRadiotherapy ?RadiopharmaceuticalAdmin ?RadiopharmaceuticalAdminId WHERE {\n" + 
				"				?InternalRadiotherapy rdf:type ontomedirad:internal_radiotherapy .\n" + 
				"				?InternalRadiotherapy ontomedirad:treats ?Human .\n" + 
				"				?Human ontomedirad:has_id ?PatientId .\n" +
				"				?RadiopharmaceuticalAdmin rdf:type ontomedirad:radiopharmaceutical_administration .\n" +
				"				?RadiopharmaceuticalAdmin purl:BFO_0000132 ?InternalRadiotherapy .\n" +
				"				?RadiopharmaceuticalAdmin ontomedirad:has_id ?RadiopharmaceuticalAdminId .\n" +
				"		} ORDER BY ?PatientId";

		String resultats = executeRequest(sparql);			
		String patientid; String internalradiotherapy; String radiopharmaceuticaladmin; String radiopharmaceuticaladminid;
		JSONArray jsonResults = new JSONObject(resultats).getJSONObject("results").getJSONArray("bindings");
		for (int i=0; i<jsonResults.length(); i++) {
			patientid = jsonResults.getJSONObject(i).getJSONObject("PatientId").getString("value");
			internalradiotherapy = jsonResults.getJSONObject(i).getJSONObject("InternalRadiotherapy").getString("value");
			radiopharmaceuticaladmin = jsonResults.getJSONObject(i).getJSONObject("RadiopharmaceuticalAdmin").getString("value");
			radiopharmaceuticaladminid = jsonResults.getJSONObject(i).getJSONObject("RadiopharmaceuticalAdminId").getString("value");
		    logger.debug("internal radiotherapy value  : "+internalradiotherapy);
			logger.debug("radiopharmaceuticaladmin  value  : "+radiopharmaceuticaladmin);
			logger.debug("radiopharmaceuticaladminid  value  : "+radiopharmaceuticaladminid);

			listIRIInternalRadiotherapy.add(model.createIndividual(internalradiotherapy.trim(), model.getResource(racineURI+"internal_radiotherapy"))) ;
			listIRIRadiopharmaceuticalAdministration.add(model.createIndividual(radiopharmaceuticaladmin.trim(), model.getResource(racineURI+"radiopharmaceutical_administration"))) ;

			listRadiopharmaceuticalAdministrationId.add(radiopharmaceuticaladminid);
			listHumanIdOfInternalRadiotherapy.add(patientid);
		}

		starDogConnection.close();

		logger.debug("requestInternalRadiotherapy OK");
	}

	public synchronized void requestTimePoint() throws IOException, InvocationTargetException, JSONException {
		logger.debug("requestTimePoint");
		starDogUrl = Application.starDogUrl ;	
		if (model==null) {model=Application.model;}

		String sparql = "SELECT DISTINCT ?TimePoint ?TimePointId ?HumanId ?TimePointClass  WHERE {\n" + 
				"				?TimePoint rdf:type ?TimePointClass .\n" + 
				"				?TimePoint ontomedirad:has_id ?TimePointId .\n" + 
				"				?TimePointClass rdfs:subClassOf* ontomedirad:timepoint_of_internal_radiotherapy .\n" +
				"				?TimePoint ontomedirad:has_patient ?Human .\n" +
				"				?Human ontomedirad:has_id ?HumanId .\n" +
				"				?Human rdf:type ontomedirad:human .\n" +
				"		} ORDER BY ?HumanId";

		String resultats = executeRequest(sparql);			
		String timepoint; String timepointid; String humanid; String timepointclass;
		JSONArray jsonResults = new JSONObject(resultats).getJSONObject("results").getJSONArray("bindings");
		for (int i=0; i<jsonResults.length(); i++) {
			timepoint = jsonResults.getJSONObject(i).getJSONObject("TimePoint").getString("value");
			timepointid = jsonResults.getJSONObject(i).getJSONObject("TimePointId").getString("value");
			humanid = jsonResults.getJSONObject(i).getJSONObject("HumanId").getString("value");
			timepointclass = jsonResults.getJSONObject(i).getJSONObject("TimePointClass").getString("value");
		    logger.debug("timepoint value  : "+timepoint);
			logger.debug("timepointid  value  : "+timepointid);
			logger.debug("humanid  value  : "+humanid);
			logger.debug("timepointclass  value  : "+timepointclass);
			listIRITimePoint.add(model.createIndividual(timepoint.trim(), model.getResource(timepointclass)));


			listHumanIdOfTimePoint.add(humanid);
			listTimePointId.add(timepointid);
		}

		starDogConnection.close();

		logger.debug("requestTimePoint OK");
	}

	// added by Bernard 31-07-2020

	public synchronized void requestSPECTCalibration() throws IOException, InvocationTargetException, JSONException {
		logger.debug("requestSPECTCalibration");
		starDogUrl = Application.starDogUrl ;	
		if (model==null) {model=Application.model;}

		String sparql = "SELECT DISTINCT ?SPECTCalibration ?ReferenceCalibrationDate ?Radionuclide ?RadionuclideLabel ?CalibrationCoefficient ?RecoveryCoefficientCurve ?IdOfSPECTCalibration  WHERE {\n" + 
				"				?SPECTCalibration rdf:type ontomedirad:SPECT_CT_calibration .\n" + 
				"				?CalibrationCoefficient rdf:type ontomedirad:SPECT_calibration_coefficient .\n" + 
				"				?CalibrationCoefficient ontomedirad:is_specified_output_of ?CalibrationCoefficientCalculation .\n" +
				"				?CalibrationCoefficientCalculation rdf:type ontomedirad:SPECT_calibration_coefficient_calculation .\n" +
				"				?CalibrationCoefficientCalculation purl:BFO_0000132 ?SPECTCalibration .\n" +
				"				?CalibrationCoefficient ontomedirad:has_reference_calibration_date ?ReferenceCalibrationDate .\n" + 
				"				?CalibrationCoefficient ontomedirad:is_about ?Radionuclide .\n" + 
				"				?RecoveryCoefficientCurve rdf:type ontomedirad:SPECT_recovery_coefficient_curve .\n" +
				"				?RecoveryCoefficientCurve ontomedirad:is_specified_output_of ?RecoveryCoefficientCurveCalculation .\n" +
				"				?RecoveryCoefficientCurveCalculation rdf:type ontomedirad:SPECT_recovery_coefficient_curve_calculation .\n" +
				"				?RecoveryCoefficientCurveCalculation purl:BFO_0000132 ?SPECTCalibration .\n" +
				"				?RecoveryCoefficientCurve ontomedirad:has_reference_calibration_date ?ReferenceCalibrationDate .\n" + 
				"				?RecoveryCoefficientCurve ontomedirad:is_about ?Radionuclide .\n" + 
				"				?Radionuclide rdf:type ?RadionuclideClass .\n" + 
				"				?RadionuclideClass rdfs:subClassOf* radionuclides:radionuclide .\n" + 
				"				?RadionuclideClass rdfs:label ?RadionuclideLabel .\n" + 
				"				OPTIONAL {?SPECTCalibration ontomedirad:has_id ?IdOfSPECTCalibration } } \n";

		String resultats = executeRequest(sparql);			
		String spectCalibration; String referenceCalibrationDate; String radionuclide; String radionuclideLabel; String calibrationCoefficient; String recoveryCoefficientCurve; String idOfSPECTCalibration;
		JSONArray jsonResults = new JSONObject(resultats).getJSONObject("results").getJSONArray("bindings");
		for (int i=0; i<jsonResults.length(); i++) {
			spectCalibration = jsonResults.getJSONObject(i).getJSONObject("SPECTCalibration").getString("value");
			referenceCalibrationDate = jsonResults.getJSONObject(i).getJSONObject("ReferenceCalibrationDate").getString("value");
			radionuclide = jsonResults.getJSONObject(i).getJSONObject("Radionuclide").getString("value");
			radionuclideLabel = jsonResults.getJSONObject(i).getJSONObject("RadionuclideLabel").getString("value");
			calibrationCoefficient = jsonResults.getJSONObject(i).getJSONObject("CalibrationCoefficient").getString("value");
			recoveryCoefficientCurve = jsonResults.getJSONObject(i).getJSONObject("RecoveryCoefficientCurve").getString("value");
			idOfSPECTCalibration = jsonResults.getJSONObject(i).getJSONObject("IdOfSPECTCalibration").getString("value");

		    	logger.debug("spectCalibration value  : "+ spectCalibration);
		    	logger.debug("referenceCalibrationDate value  : "+referenceCalibrationDate);
		    	logger.debug("radionuclide value  : "+ radionuclide);
		    	logger.debug("radionuclideLabel value  : "+ radionuclideLabel);
		    	logger.debug("calibrationCoefficient value  : "+ calibrationCoefficient);
		    	logger.debug("recoveryCoefficientCurve value  : "+ recoveryCoefficientCurve);
		    	logger.debug("idOfSPECTCalibration value  : "+ idOfSPECTCalibration);

			listIRISPECTCalibration.add(model.createIndividual(spectCalibration.trim(), model.getResource(racineURI+"SPECT_CT_calibration"))) ;
			listSPECTReferenceCalibrationDate.add(referenceCalibrationDate);
			listIRISPECTRecoveryCoefficientCurve.add(model.createIndividual(recoveryCoefficientCurve.trim(), model.getResource(racineURI+"SPECT_recovery_coefficient_curve"))) ;
			listIRISPECTCalibrationCoefficient.add(model.createIndividual(calibrationCoefficient.trim(), model.getResource(racineURI+"SPECT_calibration_coefficient"))) ;
			listIdOfSPECTCalibration.add(idOfSPECTCalibration);
			//listIdOfSPECTCalibration.add("toto");

		}

		starDogConnection.close();

		logger.debug("requestSPECTCalibration OK");
	}

	public synchronized void requestCTCalibration() throws IOException, InvocationTargetException, JSONException {
		logger.debug("requestCTCalibration");
		starDogUrl = Application.starDogUrl ;	
		if (model==null) {model=Application.model;}

		String sparql = "SELECT DISTINCT ?CTCalibration ?ReferenceCalibrationDate ?CTNumberCalibrationCurve WHERE {\n" + 
				"				?CTCalibration rdf:type ontomedirad:CT_calibration .\n" + 
				"				?CTNumberCalibrationCurve rdf:type ontomedirad:CT_number_calibration_curve .\n" + 
				"				?CTNumberCalibrationCurve ontomedirad:is_specified_output_of ?CTNumberCalibrationCurveCalculation .\n" +
				"				?CTNumberCalibrationCurveCalculation rdf:type ontomedirad:calculation_of_CT_number_calibration_curve .\n" +
				"				?CTNumberCalibrationCurveCalculation purl:BFO_0000132 ?CTCalibration .\n" +
				"				?CTNumberCalibrationCurve ontomedirad:has_reference_calibration_date ?ReferenceCalibrationDate .\n" + 		
				"				 } \n";

		String resultats = executeRequest(sparql);			
		String ctCalibration; String referenceCalibrationDate; String ctNumberCalibrationCurve; 
		JSONArray jsonResults = new JSONObject(resultats).getJSONObject("results").getJSONArray("bindings");
		for (int i=0; i<jsonResults.length(); i++) {
			ctCalibration = jsonResults.getJSONObject(i).getJSONObject("CTCalibration").getString("value");
			referenceCalibrationDate = jsonResults.getJSONObject(i).getJSONObject("ReferenceCalibrationDate").getString("value");
			ctNumberCalibrationCurve = jsonResults.getJSONObject(i).getJSONObject("CTNumberCalibrationCurve").getString("value");

		    	logger.debug("ctCalibration value  : "+ ctCalibration);
		    	logger.debug("referenceCalibrationDate value  : "+referenceCalibrationDate);
		    	logger.debug("ctNumberCalibrationCurve value  : "+ ctNumberCalibrationCurve);

			listIRICTCalibration.add(model.createIndividual(ctCalibration.trim(), model.getResource(racineURI+"CT_calibration"))) ;
			listCTReferenceCalibrationDate.add(referenceCalibrationDate);
			listIRICTNumberCalibrationCurve.add(model.createIndividual(ctNumberCalibrationCurve.trim(), model.getResource(racineURI+"CT_number_calibration_curve"))) ;
		}

		starDogConnection.close();

		logger.debug("requestCTCalibration OK");
	}
	public synchronized LinkedList<Individual> getlistIRIInternalRadiotherapy() {
	return listIRIInternalRadiotherapy;
	}

	public synchronized LinkedList<Individual> getlistIRIRadiopharmaceuticalAdministration() {
	return listIRIRadiopharmaceuticalAdministration;
	}

	public synchronized LinkedList<String> getlistRadiopharmaceuticalAdministrationId() {
	return listRadiopharmaceuticalAdministrationId;
	}

	public synchronized LinkedList<String> getlistHumanIdOfInternalRadiotherapy() {
	return listHumanIdOfInternalRadiotherapy;
	}

	public synchronized LinkedList<Individual> getlistIRITimePoint() {
	return listIRITimePoint;
	}

	public synchronized LinkedList<String> getlistHumanIdOfTimePoint() {
	return listHumanIdOfTimePoint;
	}

	public synchronized LinkedList<String> getlistTimePointId() {
	return listTimePointId;
	}

	// added by Bernard 31-07-2020

		/* public synchronized LinkedList<Individual> getlistIRIIsotopeOfSPECTCalibration() {
	return listIRIIsotopeOfSPECTCalibration;
	}*/

	public synchronized LinkedList<Individual> getlistIRISPECTCalibration() {
	return listIRISPECTCalibration;
	}
	public synchronized LinkedList<Individual> getlistIRISPECTRecoveryCoefficientCurve() {
	return listIRISPECTRecoveryCoefficientCurve;
	}
	public synchronized LinkedList<Individual> getlistIRISPECTCalibrationCoefficient() {
	return listIRISPECTCalibrationCoefficient;
	}
	public synchronized LinkedList<String> getlistSPECTReferenceCalibrationDate() {
	return listSPECTReferenceCalibrationDate;
	}
	public synchronized LinkedList<String> getlistIdOfSPECTCalibration() {
	return listIdOfSPECTCalibration;
	}
	public synchronized LinkedList<Individual> getlistIRICTCalibration() {
		logger.debug("memory : getlistIRICTCalibration  OK");
	return listIRICTCalibration;
	}
	public synchronized LinkedList<Individual> getlistIRICTNumberCalibrationCurve() {
		logger.debug("memory : getlistIRICTNumberCalibrationCurve  OK");
	return listIRICTNumberCalibrationCurve;
	}
	public synchronized LinkedList<String> getlistCTReferenceCalibrationDate() {
			logger.debug("memory : getlistCTReferenceCalibrationDate  OK");
	return listCTReferenceCalibrationDate;
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
				.to(db.toString()).server(starDogUrl).reasoning(false).credentials(ServiceController.username, ServiceController.password);
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