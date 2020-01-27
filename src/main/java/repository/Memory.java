package repository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.Hashtable;

import org.apache.jena.ontology.Individual;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.query.TupleQueryResultHandlerException;
import org.openrdf.query.resultio.QueryResultIO;
import org.openrdf.query.resultio.TupleQueryResultFormat;
import org.openrdf.query.resultio.UnsupportedQueryResultFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.complexible.stardog.StardogException;
import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.api.ConnectionPool;
import com.complexible.stardog.api.ConnectionPoolConfig;
import com.complexible.stardog.api.SelectQuery;

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

	
	public Memory() throws TupleQueryResultHandlerException, QueryEvaluationException, UnsupportedQueryResultFormatException, IOException, InvocationTargetException {
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

	public synchronized void requestHuman() throws TupleQueryResultHandlerException, QueryEvaluationException, 
	UnsupportedQueryResultFormatException, IOException, InvocationTargetException {
		logger.debug("requestHumans");
		starDogUrl = Application.starDogUrl ;	
		if (model==null) {model=Application.model;}

		String sparql = "SELECT DISTINCT ?human ?patientID WHERE {\n" + 
				"				?human rdf:type ontomedirad:human .\n" + 
				"				?human ontomedirad:has_id ?patientID .\n" + 
				"				} ORDER BY ?human";

		String[] resultats = executeRequest(sparql);	
		String[] ContenuLignes; String id; String human;

		for (int i=1; i<resultats.length; i++) {
			ContenuLignes = resultats[i].split(",");
			human = ContenuLignes[0];
			id = ContenuLignes[1];
			
			System.out.println("id : "+id);
			System.out.println("human : "+model.createIndividual(human.trim(), model.getResource(racineURI+"human")));

			model.createIndividual(human.trim(), model.getResource(racineURI+"human"));
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
	
	public synchronized void requestStudyInstanceUID() throws TupleQueryResultHandlerException, QueryEvaluationException, 
	UnsupportedQueryResultFormatException, IOException, InvocationTargetException {
		logger.debug("requestHumans");
		starDogUrl = Application.starDogUrl ;	
		if (model==null) {model=Application.model;}

		String sparql = "SELECT DISTINCT ?imagingStudy ?StudyUID WHERE {\n" + 
				"  		?imagingStudy rdf:type ontomedirad:imaging_study .\n" + 
				"  		?imagingStudy ontomedirad:has_DICOM_study_instance_UID ?StudyUID .\n" + 
				"} ORDER BY ?imagingStudy" ;

		String[] resultats = executeRequest(sparql);	
		String[] ContenuLignes; String imagingStudyIri; String StudyUID; 

		for (int i=1; i<resultats.length; i++) {
			ContenuLignes = resultats[i].split(",");
			imagingStudyIri = ContenuLignes[0];
			StudyUID = ContenuLignes[1];

			tableStudyInstance.put(StudyUID, model.createIndividual(imagingStudyIri, model.getResource(racineURI+"imaging_study")));
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
	
	public synchronized void requestPatientNameIDs() throws TupleQueryResultHandlerException, QueryEvaluationException, 
	UnsupportedQueryResultFormatException, IOException, InvocationTargetException {
		logger.debug("requestHumans");
		starDogUrl = Application.starDogUrl ;	
		if (model==null) {model=Application.model;}

		String sparql = "SELECT DISTINCT ?human ?patientID ?patientName WHERE {\n" + 
				"  ?human rdf:type ontomedirad:human .\n" + 
				"  ?human ontomedirad:has_id ?patientID .\n" + 
				"  ?human ontomedirad:has_name ?patientName .\n" + 
				"} ORDER BY ?human" ;

		String[] resultats = executeRequest(sparql);	
		String[] ContenuLignes; String name; String human; String Id;

		for (int i=1; i<resultats.length; i++) {
			ContenuLignes = resultats[i].split(",");
			human = ContenuLignes[0];
			Id = ContenuLignes[1];
			name = ContenuLignes[2];

			tablePatientNames.put(name, model.createIndividual(human, model.getResource(racineURI+"human")));
			tablePatientID.put(Id, model.createIndividual(human, model.getResource(racineURI+"human")));
		}

		starDogConnection.close();

		logger.debug("requestHuman OK");
	}
	
	public synchronized void setPatientName(String name, Individual human) {
		System.out.println("setPatientName");
		System.out.println("name : "+name);
		System.out.println("human : "+human);
		System.out.println("tablePatientNames : "+tablePatientNames);
		if (tablePatientNames.containsKey(name) == false) { 
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
	
	public synchronized void requestSoftware() throws TupleQueryResultHandlerException, QueryEvaluationException, 
			UnsupportedQueryResultFormatException, IOException, InvocationTargetException {
		logger.debug("requestSoftware");
		starDogUrl = Application.starDogUrl ;	

		String sparql = "SELECT DISTINCT ?software ?nameSoftware WHERE {\n" + 
				"?software rdf:type ontomedirad:software .\n" + 
				"?software ontomedirad:has_name ?nameSoftware .\n" + 
				"} ORDER BY ?software";
		
		String[] resultats = executeRequest(sparql);	
		String[] ContenuLignes; String iri; String name;
		
		for (int i=1; i<resultats.length; i++) {
			
			ContenuLignes = resultats[i].split(",");
			iri = ContenuLignes[0];
			name = ContenuLignes[1];
						
			setSoftware(name, iri);
		}
		starDogConnection.close();
		logger.debug("requestSoftware OK");
	}
	
	public synchronized void requestMCMethod() throws TupleQueryResultHandlerException, QueryEvaluationException, UnsupportedQueryResultFormatException, IOException {
		logger.debug("requestMCMethod");
		starDogUrl = Application.starDogUrl ;	
				
		String sparql = "SELECT DISTINCT ?method ?nameMethod WHERE {" + 
				"	?method rdf:type ontomedirad:Monte_Carlo_CT_dosimetry_method ." + 
				"	?method ontomedirad:has_name ?nameMethod ." + 
				"} ORDER BY ?method";
		String[] resultats = executeRequest(sparql);	
		String[] ContenuLignes; String iri; String name;
		
		for (int i=1; i<resultats.length; i++) {
			ContenuLignes = resultats[i].split(",");
			iri = ContenuLignes[0];
			name = ContenuLignes[1];		
			setMCMethod(name, iri);
		}
		
		starDogConnection.close();
		logger.debug("requestMCMethod OK");
	}
	
	public synchronized void requestInstit() throws TupleQueryResultHandlerException, QueryEvaluationException, UnsupportedQueryResultFormatException, IOException {
		logger.debug("requestInstit");
		starDogUrl = Application.starDogUrl ;	
				
		String sparql = "SELECT DISTINCT ?institution ?nameInstit ?roleInstit WHERE {" + 
				"	?institution rdf:type ontomedirad:institution ." + 
				"	?institution ontomedirad:has_name ?nameInstit ." + 
				"    ?institution purl:BFO_0000161 ?roleInstit ." + 
				"} ORDER BY ?institution";
	
		String[] resultats = executeRequest(sparql);	
		
		String[] ContenuLignes; String iriRole;
		String iriInstit; String nameInstit;
		
		for (int i=1; i<resultats.length; i++) {
			ContenuLignes = resultats[i].split(",");
			iriInstit = ContenuLignes[0];
			nameInstit = ContenuLignes[1];
			iriRole = ContenuLignes[2];						
			setInstit(nameInstit, iriInstit, iriRole);
		}
		
		starDogConnection.close();
		logger.debug("requestInstit OK");
	}
	
	public synchronized void requestPatientCTImageDS() throws TupleQueryResultHandlerException, QueryEvaluationException, UnsupportedQueryResultFormatException, IOException {
		logger.debug("requestPatientCTImageDS");
		starDogUrl = Application.starDogUrl ;	
				
		String sparql = "SELECT DISTINCT ?patient ?CT_ImageDataSet ?handle WHERE {" + 
				"	?patient rdf:type ontomedirad:patient ." + 
				"  	?patient purl:BFO_0000054 ?CT_Acquisition ." + 
				"   ?CT_ImageDataSet ontomedirad:is_specified_output_of ?CT_Acquisition ." + 
				"  	?CT_ImageDataSet ontomedirad:has_IRDBB_WADO_handle ?handle ." + 
				"} ORDER BY ?software";
	
		String[] resultats = executeRequest(sparql);	
		String[] ContenuLignes; String patientIRI; String CTImageDsIRI; String handle;
		
		for (int i=1; i<resultats.length; i++) {
			
			ContenuLignes = resultats[i].split(",");
			patientIRI = ContenuLignes[0];
			CTImageDsIRI = ContenuLignes[1];
			handle = ContenuLignes[2]; 
			System.out.println(handle);
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
	
	private synchronized String[] executeRequest(String request) throws TupleQueryResultHandlerException, QueryEvaluationException, 
	UnsupportedQueryResultFormatException, IOException, StardogException {

createAdminConnection(database.ontoMedirad);
logger.debug("Request "+request);

SelectQuery aQuery = starDogConnection.select(request);

TupleQueryResult aResult=null; ByteArrayOutputStream out=null;

aResult = aQuery.execute();
out = new ByteArrayOutputStream();
QueryResultIO.writeTuple(aResult, TupleQueryResultFormat.CSV, out);
String[] resultats = out.toString().split("\n");

if (aResult!=null) {aResult.close();}
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