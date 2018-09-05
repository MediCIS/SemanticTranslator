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

	static String username = "admin"; 			// Credentials for StarDog
	static String password = "admin";

	boolean reasoningDefault = false; 			// by defaut request will not use reasoning

	String dockerHost = Application.dockerHost ;
	String starDogUrl = Application.starDogUrl;
	Memory memory = Application.memory;

	HttpURLConnection SRconnection;				// Connection to retrieve the SR
	StructuredReport SR;						// For store SR document

	public enum database {ontoMedirad, test}; 	// StarDog Database list ()

	String rdfName;								// Name of the RDF file that wille writen before transfer to StarDog (for backup)
	int z = 0; 									// Used for names RDF files and avoid overwriting

	Connection starDogConnection;				// Connection to Stardog (will be activated only when necessary)

	static Individual patient;							// Will store the ontologic entity of the patient
	static Individual clinicalResearchStudy;

	static String handle;						// Handle is at top for being transmitted to the Import Controller
	static String studyInstanceUID; static String seriesInstanceUID; 

	private final static Logger logger = LoggerFactory.getLogger(ImportController.class); 

	@RequestMapping(value = "/test")			// for my own use (will be removed at the end)
	public String test(@RequestParam Map<String,String> requestParams) throws Exception{
		String p1=requestParams.get("p1");
		String p2=requestParams.get("p2");
		return "Hello "+p1+" "+p2+"\n";
	}

	@RequestMapping(value = "/isReady")			// for test with jenkins
	public String isReady() {
		//return Application.ready ? "YES" : "NO";
		if (Application.ready) {
			return "YES";
		} else {
			return "NO";
		}
	}
	
	@RequestMapping (value = "/getMimeTypeDataFormat", method = RequestMethod.GET, headers = "Accept=text/xml", produces = {"application/json"})
	public String getMimeTypeDataFormat(@RequestParam("nonDICOMDataFormat") String nonDICOMDataFormat) {  
		System.out.println("getMimeTypeDataFormat");
		return executeQuerry("  SELECT DISTINCT  ?label ?class\n" + 
				"           WHERE {\n" + 
				"        ?class rdf:type owl:Class .\n" + 
				"        ?class ontomedirad:has_MIME_type ?label .\n" + 
				"        ?class skos:prefLabel ?classlabel .\n" + 
				"        FILTER (?classlabel = <"+nonDICOMDataFormat+"@en>) .\n" + 
				"        }" , "false" ); 	  
	} 
	

	@RequestMapping (value = "/getResearchStudies", method = RequestMethod.GET, headers = "Accept=text/xml", produces = {"application/json"})
	public String getResearchStudies() {   
		return executeQuerry("SELECT DISTINCT ?study ?id ?name ?description\n" + 
				"          WHERE {\n" + 
				"      ?study rdf:type ontomedirad:clinical_research_study .\n" + 
				"      ?study ontomedirad:has_id ?id .\n" + 
				"      ?study ontomedirad:has_name ?name .\n" + 
				"      ?study ontomedirad:has_description ?description .\n" + 
				"      }" , "false" ); 	  
	} 

	@RequestMapping ( value = "/getRequestList", method = RequestMethod.GET, headers = "Accept=text/xml", produces = {"application/json"})
	public String getRequestList() {return Application.listQuerries.getJsonString();} // return request list in JSON

	@RequestMapping ( value = "/validateDicomFileSetDescriptor", method = RequestMethod.POST, headers = "Accept=text/xml", produces = {"application/json"})
	public String validateDicomFileSetDescriptor(@RequestBody String filesetDescriptorString) {  // validate request list in JSON

		logger.info("Validating DicomFileSetDescriptor");

		try {
			String tmpFilePath = "tmp.xml";		// XML content will be written in a temporary file (will be overwriten each time)
			JAXBContext jc = JAXBContext.newInstance("repository");

			PrintWriter out = new PrintWriter(tmpFilePath); 
			out.println(filesetDescriptorString); // write XML content to be validated in the file
			out.close();

			SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema"); // Structure for XML schema

			Schema schema = factory.newSchema(new StreamSource(new ClassPathResource("/xsd/dicomFileSetDescriptor.xsd").getInputStream())); 
			// read the XML schema

			Unmarshaller unmarshaller = jc.createUnmarshaller();
			unmarshaller.setSchema(schema);     // store the schema

			DicomFileSetDescriptor fileSetDescriptor = (DicomFileSetDescriptor) unmarshaller.unmarshal(new File(tmpFilePath)); 
			// import the XML file and check if XML is valid with the schema

		} catch (UnmarshalException  e) {
			String msg;
			if (e.getCause() != null ) {
				msg =  e.getCause().getMessage(); // get the cause why the XML file is invalid
			} else {
				msg =  e.toString(); 			  // get the error message
			}
			return new ValidationReport(false, msg).getJson().toString(); // return the error message as a JSON object
		} catch (Exception  e) {
			return new ValidationReport(false, e.toString()).getJson().toString(); // return the error message as a JSON object
		}
		return new ValidationReport(true, "").getJson(); // return the message (valid) as a JSON object
	}

	@RequestMapping ( value = "/validateNonDicomFileSetDescriptor", method = RequestMethod.POST, headers = "Accept=text/xml", produces = "application/json")
	public String validateNonDicomFileSetDescriptor(@RequestBody String filesetDescriptorString) {

		logger.info("Validating NonDicomFileSetDescriptor");

		try {
			String tmpFilePath = "tmp.xml";		// XML content will be written in a temporary file (will be overwriten each time)
			JAXBContext jc = JAXBContext.newInstance("repository");

			PrintWriter out = new PrintWriter(tmpFilePath);
			out.println(filesetDescriptorString);// write XML content to be validated in the file
			out.close();

			SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema"); // Structure for XML schema

			Schema schema = factory.newSchema(new StreamSource(new ClassPathResource("/xsd/nonDicomFileSetDescriptor.xsd").getInputStream()));
			// read the XML schema

			Unmarshaller unmarshaller = jc.createUnmarshaller();
			unmarshaller.setSchema(schema);     // store the schema

			NonDicomFileSetDescriptor fileSetDescriptor = (NonDicomFileSetDescriptor) unmarshaller.unmarshal(new File(tmpFilePath));
			// import the XML file and check if XML is valid with the schema

		} catch (UnmarshalException  e) {
			String msg;
			if (e.getCause() != null ) {		  // if XML is invalid 
				msg =  e.getCause().getMessage(); // get the cause why the XML file is invalid
			} else {							  // if there is an error and no cause 
				msg =  e.toString(); 			  // get the error message
			}
			return new ValidationReport(false, msg).getJson().toString(); // return the error message as a JSON object
		} catch (Exception  e) {											//if there is an error
			return new ValidationReport(false, e.toString()).getJson().toString(); // return the error message as a JSON object
		}
		return new ValidationReport(true, "").getJson(); // return the message (valid) as a JSON object
	}

	public static boolean GateKeeper(String request) {	   // Security Check for request sent to StarDog
		// All request that will degrade data in stardog will be blocked
		if (request.contains("CONSTRUCT")) {return false;} // Unsecure command because Construct command can degrade data
		return true;									   // Commands seems to be secure
	}

	public String executeQuerry(String request, String isReasoning) { // Execute a querry (querry is passed as a string)

		if (GateKeeper(request)==false) {							  // Security test
			return "Request refused for Security Reason";             // Stop the function if the request is not secured
		} 

		switch(isReasoning) {										  // Will create a stardog connection 
		case "true": 
			createAdminConnection(database.ontoMedirad, true); 		  // Create a connection to a stardog database with reasoning
			break;
		case "false":
			createAdminConnection(database.ontoMedirad, false);  	  // Create a connection to a stardog database WITHOUT reasoning
			break;
		default:														  	// Defaut case because for alwatys create a connection to stardog
			createAdminConnection(database.ontoMedirad, reasoningDefault);  // Create a connection to a stardog database with reasoning as defaut value
			break;
		}

		SelectQuery aQuery = starDogConnection.select(request);        // Put the request to the StarDog

		TupleQueryResult aResult = null;							   // Create an object to receive the result

		try { aResult = aQuery.execute();}							   // Execute the request
		catch (StardogException e) {
			starDogConnection.close();								   // Close the connection to StarDog  if there was an error
			return ("{\"res\": \""+e.toString()+"\"}");				   // Return the error message in a JSON object
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();	   // Create an OuputStream to receive result

		try {
			QueryResultIO.writeTuple(aResult, TextTableQueryResultWriter.FORMAT.JSON, out); // Write the request result in the ByteArrayOutputStream
		} catch (TupleQueryResultHandlerException | QueryEvaluationException | UnsupportedQueryResultFormatException | IOException e) {
			return ("{\"res\": \""+e.toString()+"\"}");				   // Return the error message in a JSON object
		} finally {starDogConnection.close();aResult.close();}		   // Close The Object (despite the errors)

		return (out.toString());				 					   // Convert the ByteArrayOutputStream as a string and return it in a JSON object
	}

	@RequestMapping( value = "/requestFromList", method = RequestMethod.GET, headers = "Accept=text/xml", produces = "application/sparql-results+json")
	public String requestFromList(@RequestParam("id") String id) {     // Shortcut to execute a request from the request list
		String isReasoning = "false";
		Querry q = Application.listQuerries.getRequest(id);            // Retrieve request from the list (can be null if request name is unknown)

		if (q!=null) {											   	   // If request is NOT null
			String a = executeQuerry(q.getRequest(), isReasoning); 	   // Execute the querry and store the result as a string
			return a;											       // Return Querry Result
		} else {												       // If request is null
			return ("{\"res\": \"Error : Unknown request\"}");	       // Return an Error message
		}
	}

	@RequestMapping( value = "/testRequestControle", method = RequestMethod.GET, headers = "Accept=text/xml") // For test only
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

	@RequestMapping( value = "/testReturnReq", method = RequestMethod.GET, headers = "Accept=text/xml") // For test only
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

	public static boolean databaseContains(String test) { // function for check if Database name provided is a real database (in the enumeration)
		for (database b : database.values()) {
			if (b.name().equals(test)) {
				return true;
			}
		}
		return false;
	}

	@RequestMapping( value = "/importDicomFileSetDescriptor", method = RequestMethod.POST, headers = "Accept=text/xml")
	public String importDicomData(@RequestBody DicomFileSetDescriptor dicomFileSetDescriptor,  				// Import Dicom file
			@RequestParam(value = "db", required = false) String db) {						   				// (not required) name of the database
		logger.info("Dicom Import request received");
		if (dockerHost==null) {dockerHost = Application.dockerHost ;}
		if (starDogUrl==null) {starDogUrl = Application.starDogUrl ;}
		if (memory==null) {memory=Application.memory;}														// Memory is used for store some objects
		patient = OntologyPopulator.retrievePatientData(dicomFileSetDescriptor.getPatientDescriptor()); 	// Retrieve patient data

		Individual clinicalResearchStudy = OntologyPopulator.retrieveClinicalResearchStudy(dicomFileSetDescriptor.referencedClinicalStudy.clinicalStudyID);

		for (DICOMStudyType study : dicomFileSetDescriptor.getDICOMStudy()) {				   				// Iterate on the studies
			for (DICOMSeriesType series : study.getDICOMSeries() ) {						   				// Iterate on the series
				if(series.getDICOMSeriesDescriptor().getModality00080060().equals("SR")) {	   				// If there is a SR Structured Report
					logger.info("SR referenced");
					studyInstanceUID = study.getDICOMStudyDescriptor().getStudyInstanceUID0020000D();		// Get studyID
					seriesInstanceUID = series.getDICOMSeriesDescriptor().getSeriesInstanceUID0020000E();	// Get seriesID
					if (db==null)  {createAdminConnection(database.ontoMedirad);}							// If no DB provided create a connection to ontoMedirad
					else {createAdminConnection(db);}														// If DB provided create a connection these DB
					retrieveSR(studyInstanceUID, seriesInstanceUID);										// Translate SR
					starDogConnection.close();																// Close Stardog connection

				} else if (series.getDICOMSeriesDescriptor().getModality00080060().equals("CT")) {			// If there is a CT 
					logger.info("CT referenced");
					studyInstanceUID = study.getDICOMStudyDescriptor().getStudyInstanceUID0020000D();		// Get studyID
					seriesInstanceUID = series.getDICOMSeriesDescriptor().getSeriesInstanceUID0020000E();	// Get seriesID
					if (db==null)  {createAdminConnection(database.ontoMedirad);}							// If no DB provided create a connection to ontoMedirad
					else {createAdminConnection(db);}														// If DB provided create a connection these DB
					logger.info("Retrieving CT"+" StudyInstanceUID: " + studyInstanceUID+" SeriesInstanceUID: " + seriesInstanceUID);
					Iterator<DICOMStudyType> iterFileSet = dicomFileSetDescriptor.dicomStudy.iterator();
					TranslateDicomData.readingCT(iterFileSet, studyInstanceUID, seriesInstanceUID, clinicalResearchStudy);
					rdfName = "RdfBackup/CT_study" + studyInstanceUID+"_series_" + seriesInstanceUID+".rdf";
					writingRDF(rdfName);
					setInStarDog(rdfName);
					starDogConnection.close();																// Close Stardog connection

				} else {
					logger.warn("Unknown reference : "+series.getDICOMSeriesDescriptor().getModality00080060()); // Log a message if there is an unknown type
				}
			}
		}
		return "{\"res\": \"ImportDicomFileSetDescriptor Request received\"}";								// Return these message if there's no Error
	}

	@RequestMapping( value = "/importNonDicomFileSetDescriptor", method = RequestMethod.POST, headers = "Accept=text/xml")
	public String importNonDicomData(@RequestBody NonDicomFileSetDescriptor nonDicomFileSetDescriptor,  	// Import Non Dicom data XML valid file
			@RequestParam(value = "db", required = false) String db) {						    

		if (dockerHost==null) {dockerHost = Application.dockerHost ;}
		if (starDogUrl==null) {starDogUrl = Application.starDogUrl ;}		

		if (nonDicomFileSetDescriptor!=null) {									    // If there is a nonDicomFileSetDescriptor
			TranslateNonDicomData.translateNonDicomData(nonDicomFileSetDescriptor); // Translate these Data
		}

		logger.info("Retrieving NON Dicom FileSetDescriptor");
		z++;																								// Z is a number for the file name (will avoid overwriting)
		rdfName = "RdfBackup/nonDicom_study" + nonDicomFileSetDescriptor.referencedClinicalResearchStudy.clinicalResearchStudyID +"_"+z+".rdf";
		writingRDF(rdfName);																				// Write Semantic Graph in a RDF file
		if (db==null)  {createAdminConnection(database.ontoMedirad);}										// If no DB provided create a connection to ontoMedirad
		else {createAdminConnection(db);}																	// If DB provided create a connection these DB setInStarDog(rdfName);
		starDogConnection.close();																			// Close Stardog connection

		return "{\"res\": \"ImportNonDicomFileSetDescriptor Request received\"}";							// Return these message if there's no Error
	}

	public boolean retrieveSR(String studyInstanceUID, String seriesInstanceUID) {							// Treat SR (before and after translation)
		logger.warn("Retrieving SR StudyInstanceUID: " + studyInstanceUID+" SeriesInstanceUID: " + seriesInstanceUID);

		try {																								// Execution has many exceptions
			String targetURL = "http://"+dockerHost+"/dcm4chee-arc/aets/DCM4CHEE/rs/studies/" + studyInstanceUID + "/series/" + seriesInstanceUID;  // URL in DCM4CHEE
			handle = "/pacs/studies/"+studyInstanceUID+"/series/"+seriesInstanceUID;						// Handle to retrieve object in the Pacs

			URL url = new URL(targetURL);																	// Create URLfor the connection to Retrieve the SR in DCM4CHEE
			SRconnection = (HttpURLConnection) url.openConnection();										// Open Connection
			SRconnection.setRequestMethod("GET");
			SRconnection.setRequestProperty("Accept", "multipart/related; type=application/dicom;");

			String boundary = "";																			// Create object for retrieve SR as a text  
			String boundaryHeader = "boundary=";															// Same
			String contentType = SRconnection.getHeaderField("Content-Type");								// Get header at "Content-Type"
			int bufsize = SRconnection.getContentLength();													// Get Length of the SR as a text

			String[] contentTypeArray = contentType.split(";");												// Split the text received as a list of String
			for (String content : contentTypeArray ) {														 
				if (content.contains(boundaryHeader)) {
					boundary = content.substring(content.indexOf("=")+1);									// Extract the content
					logger.debug("Boundary found: " + boundary);
				}
			}

			logger.debug("bufsize : "+bufsize);
			if (bufsize<=0) { 																				// Correction when bufsize = -1
				bufsize=50;																					// Buffsize correction (50 is a random number enough big)
				logger.debug("bufsize corrected to  "+bufsize);
			}

			MultipartStream multipartStream = new MultipartStream(											// MultipartStream will receive the input stream containing the SR
					SRconnection.getInputStream(), boundary.getBytes(), bufsize, null);

			String srFilename = "SR.dcm";																	// Filename for write the SR and read it after
			logger.debug("Writing SR in : "+srFilename);
			File file = new File(srFilename);																// Create the file for write the SR
			FileOutputStream out = new FileOutputStream(file.getAbsolutePath());							// New stream to write in the file
			boolean nextPart = multipartStream.skipPreamble();

			if (nextPart) {																					// Iterate as a while (X.hasNext())
				multipartStream.readHeaders();																// Header of the part won't be written
				multipartStream.readBodyData(out);															// Write in the file
			}
			out.close();																					// Close the stream to the file

			logger.info("Reading SR (local file)");	
			File f = new File(srFilename);																	// SR file that will be read 
			AttributeList attributeList = new AttributeList();												// Crate an empty attribute that will receive SR Datas

			try {attributeList.read(f);																		// Read SR from file and put contain in attributeList
			SR = new StructuredReport(attributeList);														// Convert the attributeList in StructuredReport
			} catch (IOException e) {																		// Error when reading the file
				logger.debug("Reading SR file : IOException");
				logger.debug(e.getMessage()); return false;
			} catch (DicomException e) {																	// Error when reading DICOM
				logger.debug("Reading SR file : DicomException");			
				logger.debug(e.getMessage()); return false;
			} finally {
				TranslateDicomData.readingSR((ContentItem) SR.getRoot());									// Read and Translate the SR from the root
			}

			rdfName = "RdfBackup/SR_study" + studyInstanceUID+"_series_" + seriesInstanceUID+".rdf";		// Name for the RDF File (won't be overwritten)
			writingRDF(rdfName);																			// Write the populated graph in the RDF file
			setInStarDog(rdfName);																			// Transfer file to Stardog

		} catch (ProtocolException e) {
			logger.debug("Retrieving SR : ProtocolException");
			logger.debug(e.getMessage()); return false;
		} catch (MalformedURLException e) {
			logger.debug("Retrieving SR : MalformedURLException");
			logger.debug(e.getMessage()); return false;
		} catch (FileUploadIOException e) {
			logger.debug("Retrieving SR : FileUploadIOException");
			logger.debug(e.getMessage()); return false;
		} catch (MalformedStreamException e) {
			logger.debug("Retrieving SR : MalformedStreamException");
			logger.debug(e.getMessage()); return false;
		} catch (IOException e) {
			logger.debug("Retrieving SR : IOException");
			logger.debug(e.getMessage()); return false;
		} finally {
			logger.debug("Retrieving SR : No exception catched");
			if(SRconnection != null) {SRconnection.disconnect(); }											// Close the connection to the Stardog
		}
		return true;																						// End : everything was good
	}

	public void createAdminConnection(database db, boolean paramreasoner) {									// Main method to open a connection to a Stardog database
		if (dockerHost==null) {dockerHost = Application.dockerHost ;}
		if (starDogUrl==null) {starDogUrl = Application.starDogUrl ;}		
		logger.debug("Creation of the StarDog connection (Database : "+db.toString()+") at "+starDogUrl);
		ConnectionConfiguration connectionConfig = ConnectionConfiguration									// Configuration of the connection
				.to(db.toString())																			// Select Database (from the enumeration)
				.server(starDogUrl)																			// StarDog URL 
				.credentials(username, password)															// Login and Pasword of Stardog
				.reasoning(paramreasoner);																	// Will it use reasoning (boollean)

		logger.debug("StarDog connection (reasoning : "+connectionConfig.REASONING_ENABLED.toString()+")"); 

		ConnectionPool connectionPool = createConnectionPool(connectionConfig);								// Create the Stardog connection 
		starDogConnection = connectionPool.obtain();														// Return the Stardog connection as a java Object
		logger.debug("Sucessfully created the StarDog connection (Database : "+db.toString()+")");
	}

	public void createAdminConnection(database db) {														// Create a StardogConnection without provide reasoning setting
		createAdminConnection(db, reasoningDefault);														// Reasoning is set as the default value
	} 

	public void createAdminConnection(String db) {															// Same but when the database name is provided as a string
		for (database b : database.values()) {																// Will compare the string with values in the enumeration
			if (b.name().equals(db)) {
				createAdminConnection(b, reasoningDefault); return;
			}
		}
		createAdminConnection(database.ontoMedirad, reasoningDefault);										// If 
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