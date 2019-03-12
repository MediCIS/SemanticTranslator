package repository;


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.commons.io.IOUtils;
import org.apache.jena.ontology.Individual;
import org.apache.tomcat.util.http.fileupload.MultipartStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.query.TupleQueryResultHandlerException;
import org.openrdf.query.resultio.QueryResultIO;
import org.openrdf.query.resultio.TupleQueryResultFormat;
import org.openrdf.query.resultio.UnsupportedQueryResultFormatException;
import org.openrdf.rio.RDFFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import com.complexible.stardog.StardogException;
import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.api.ConnectionPool;
import com.complexible.stardog.api.ConnectionPoolConfig;
import com.complexible.stardog.api.Exporter;
import com.complexible.stardog.api.SelectQuery;
import com.pixelmed.dicom.AttributeList;
import com.pixelmed.dicom.ContentItem;
import com.pixelmed.dicom.DicomException;
import com.pixelmed.dicom.StructuredReport;

import javaXSDclass.DICOMSeriesType;
import javaXSDclass.DICOMStudyType;
import javaXSDclass.DicomFileSetDescriptor;
import javaXSDclass.NonDicomFileSetDescriptor;
import querries.Querry;
import repository.TranslateDicomData.TypeDicom;

import org.dcm4che3.data.*;


@Controller
@RestController
public class ImportController {	

	static String username = "admin"; 			// Credentials for StarDog
	static String password = "admin";

	boolean reasoningDefault = false; 			// by defaut request will not use reasoning

	String fhirUrl = Application.fhirUrl ;
	String pacsUrl = Application.pacsUrl ;
	String starDogUrl = Application.starDogUrl;
	Memory memory = Application.memory;

	StructuredReport SR;						// For store SR document

	public enum database {ontoMedirad, test}; 	// StarDog Database list ()

	String rdfName;								// Name of the RDF file that wille writen before transfer to StarDog (for backup)
	int z = 0; 									// Used for names RDF files and avoid overwriting
	
	Connection starDogConnection;				// Connection to Stardog (will be activated only when necessary)

	private static Individual patient;			// Will store the ontologic entity of the patient
	static Individual clinicalResearchStudy;

	static String handle;						// Handle is at top for being transmitted to the Import Controller
	static String studyInstanceUID; static String seriesInstanceUID; 

	private final static Logger logger = LoggerFactory.getLogger(ImportController.class); 
	
	@RequestMapping (value = "/testAskomics", method = RequestMethod.GET)
	public String testAbstractionAskomics()  {
		
		File f = new File("testAskomics.ttl");
		FileOutputStream outStream;
		try {
			outStream = new FileOutputStream(f);
			Application.dataModel.write(outStream, "Turtle");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return "Tipoui !\n";
	}
	
	@RequestMapping (value = "/test", method = RequestMethod.POST)
	public String test() {
		
		logger.info("Info");
		logger.debug("Debug");
		logger.warn("Warn");
		logger.error("Error");
		
		return "Tipoui !";
	}
	
	@RequestMapping(value = "/importDicomMetadata", method = RequestMethod.POST)	//, headers = "Accept=application/json"
	public ResponseEntity<String> importDicomMetadata(@RequestBody String kosString) {	
		System.out.println("importDicomMetadata");
		
		String clinicalResearchStudyId = null; String KOSFhirId = null;
		JSONObject kosDescriptor = null;
		try {
			kosDescriptor = new JSONObject(kosString);
			clinicalResearchStudyId = kosDescriptor.getString("clinicalResearchStudyId");
			KOSFhirId = kosDescriptor.getString("KOSFhirId");
		} catch (JSONException e1) {
			e1.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("JSON Exception"); 
		}
		
		System.out.println("kosDescriptor : ");
		System.out.println(kosDescriptor);
		
		System.out.println("clinicalResearchStudyId : "+clinicalResearchStudyId);
		System.out.println("KOSFhirId : "+KOSFhirId);		 
		
		ArrayList<String> listCleCT = new ArrayList<String>();
		
		if (fhirUrl==null) {fhirUrl=Application.fhirUrl;}
		if (pacsUrl==null) {pacsUrl=Application.pacsUrl;}
		
		String KosURL = fhirUrl+"/baseDstu3/Binary/" + KOSFhirId;  							// URL in FHiR
		System.out.println(KosURL);
		
		URL url;
		try {
			url = new URL(KosURL);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Malformed URL : "+KosURL); 
		}		
		
		HttpURLConnection fhirConnection = null;
		
		int code = -1;
		try {
			fhirConnection = (HttpURLConnection) url.openConnection();
			fhirConnection.setRequestMethod("GET");
			fhirConnection.setRequestProperty("Accept",  "type=application/dicom;"); //multipart/related;
			code = fhirConnection.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
			if (code==-1) {
				return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Error when connecting to Fhir Server. No response code returned"); 
			} else {
				return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Error when connecting to Fhir Server. response code :"+code); 
			}
		}
		
		System.out.println("DicomInputStream");
		org.dcm4che3.io.DicomInputStream dis = null;
		Attributes attr = null;

		try {
			dis = new org.dcm4che3.io.DicomInputStream(fhirConnection.getInputStream());
			attr = dis.readDataset(-1, -1);
			dis.close();
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("IO Error when reading KOS"); 
		} 

		Sequence mainSeq = attr.getSequence(Tag.CurrentRequestedProcedureEvidenceSequence);

		//TranslateDicomData.translateDicomMetaData(attr);
	
		Iterator<Attributes> itr = mainSeq.iterator();

		while(itr.hasNext()) {
			
			Attributes element = itr.next();
			Sequence referencedSeriesSeq = element.getSequence(Tag.ReferencedSeriesSequence);
			
			String PatientPseudo = element.getString(Tag.PatientName);
			System.out.println("PatientPseudo : "+PatientPseudo);
			
			String PatientID = element.getString(Tag.PatientID);
			System.out.println("PatientID : "+PatientID);
			
			String studyId = element.getString(Tag.StudyInstanceUID);

			Iterator<Attributes> seriesItr = referencedSeriesSeq.iterator();
			
			while(seriesItr.hasNext()) {
				Attributes seriesItem = seriesItr.next();
				Sequence referencedSOPSeq = seriesItem.getSequence(Tag.ReferencedSOPSequence);			
				String seriesUID = seriesItem.getString(Tag.SeriesInstanceUID);
				
				Iterator<Attributes> sopItr = referencedSOPSeq.iterator();

				while(sopItr.hasNext()) {
					Attributes sopItem = sopItr.next();
						
					//String referencedSOPInstancesUID = sopItem.getString(Tag.ReferencedSOPInstanceUID);
					String ReferencedSOPClassUID = sopItem.getString(Tag.ReferencedSOPClassUID);
					
					if (ReferencedSOPClassUID.equals("1.2.840.10008.5.1.4.1.1.2")) { // CT
						String cleCT = studyInstanceUID + seriesInstanceUID;
						boolean testCleCt = true;
						Iterator<String> iterCleCt = listCleCT.iterator();
						while (iterCleCt.hasNext()) {
							if (iterCleCt.next().contentEquals(cleCT)) {
								testCleCt = false;
							}
						}
						if (testCleCt == true) {
							
							try {
								retrieveDicomFile(studyId, seriesUID);
							} catch (IOException e) {
								e.printStackTrace();
								return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("IO Error when reading CT"); 

							}
							listCleCT.add(cleCT);
							
						}
						
					} else if (ReferencedSOPClassUID.contains("1.2.840.10008.5.1.4.1.1.88")) { // SR
						try {
							retrieveSR(studyId, seriesUID);
						} catch (DicomException e) {
							e.printStackTrace();
							return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Dicom Error when reading SR"); 
						} catch (IOException e) {
							e.printStackTrace();
							return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("IO Error when reading SR"); 

						}
					
//						1.2.840.10008.5.1.4.1.1.88.11	Basic Text SR	 
//						1.2.840.10008.5.1.4.1.1.88.22	Enhanced SR	 
//						1.2.840.10008.5.1.4.1.1.88.33	Comprehensive SR	 
//						1.2.840.10008.5.1.4.1.1.88.40	Procedure Log Storage	 
//						1.2.840.10008.5.1.4.1.1.88.50	Mammography CAD SR	 
//						1.2.840.10008.5.1.4.1.1.88.59	Key Object Selection Document	 
//						1.2.840.10008.5.1.4.1.1.88.65	Chest CAD SR	 
//						1.2.840.10008.5.1.4.1.1.88.67	X-Ray Radiation Dose SR

					} else {
						try {
							retrieveDicomFile(studyId, seriesUID);
						} catch (IOException e) {
							e.printStackTrace();
							return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("IO Error when reading CT"); 

						}
					}
					
				}   
			}
		}
		try {
			dis.close();
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("IO Error when reading KOS"); 
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Tipoui !"); 
	}
		
	int compteurCT = 0;
	
	public void retrieveDicomFile(String studyInstanceUID, String seriesInstanceUID) throws IOException {
		logger.info("Retrieving CT StudyInstanceUID: " + studyInstanceUID+" SeriesInstanceUID: " + seriesInstanceUID);
		if (pacsUrl==null) {pacsUrl=Application.pacsUrl;}
		
		String targetURL = "http://"+pacsUrl+"/dcm4chee-arc/aets/DCM4CHEE/rs/studies/" + studyInstanceUID + "/series/" + seriesInstanceUID;  // URL in DCM4CHEE
		handle = "/pacs/studies/"+studyInstanceUID+"/series/"+seriesInstanceUID;						// Handle to retrieve object in the Pacs
		
		System.out.println(targetURL);
		logger.debug(targetURL);
		
		HttpURLConnection pacsConnection; 
		URL url = new URL(targetURL);
		pacsConnection = (HttpURLConnection) url.openConnection();					// Open Connection
		pacsConnection.setRequestMethod("GET");
		pacsConnection.setRequestProperty("Accept",  "multipart/related; type=application/dicom;");
		
		String boundary = "";																			// Create object for retrieve SR as a text  
		String boundaryHeader = "boundary=";															// Same
		String contentType = pacsConnection.getHeaderField("Content-Type");								// Get header at "Content-Type"
		int bufsize = pacsConnection.getContentLength();	
		
		String[] contentTypeArray = contentType.split(";");												// Split the text received as a list of String
		for (String content : contentTypeArray ) {														 
			if (content.contains(boundaryHeader)) {
				boundary = content.substring(content.indexOf("=")+1);									// Extract the content
			}
		}
		
		if (bufsize<=0) { 																				// Correction when bufsize = -1
			logger.warn("bufsize : "+bufsize+"bufsize corrected to 50");
			bufsize=50;																					// Buffsize correction (50 is a random number enough big)
		} else {
			logger.debug("bufsize : "+bufsize);
		}

		String srFilename = "CT"+compteurCT+".dcm";																	// Filename for write the SR and read it after
		MultipartStream multipartStream;
		multipartStream = new MultipartStream(											// MultipartStream will receive the input stream containing the SR
				pacsConnection.getInputStream(), boundary.getBytes(), bufsize, null);
		logger.debug("Writing SR in : "+srFilename);
		File file = new File(srFilename);																// Create the file for write the SR
		FileOutputStream out = new FileOutputStream(file.getAbsolutePath());							// New stream to write in the file
		boolean nextPart = multipartStream.skipPreamble();

		if (nextPart) {																					// Iterate as a while (X.hasNext())
			multipartStream.readHeaders();																// Header of the part won't be written
			multipartStream.readBodyData(out);															// Write in the file
		}
		out.close();
		logger.debug("Reading SR (local file)");	
		File f = new File(srFilename);																	// SR file that will be read 
		
		org.dcm4che3.io.DicomInputStream input = new org.dcm4che3.io.DicomInputStream(f);
		Attributes obj = input.readDataset(-1, -1);
		input.close();
		
		TranslateDicomData.translateDicomMetaData(obj, TypeDicom.CT);

		createAdminConnection(database.ontoMedirad);
		
		rdfName = "RdfBackup/CT_metadata" + studyInstanceUID+"_series_" + seriesInstanceUID+".rdf";		// Name for the RDF File (won't be overwritten)
		writingRDF(rdfName);																			// Write the populated graph in the RDF file
		setInStarDog(rdfName);
		
		logger.debug("Retrieving SR : No exception catched");
		if(pacsConnection != null) {pacsConnection.disconnect(); }											// Close the connection to the Stardog
	}
	
	@RequestMapping (value = "/getMimeTypeDataFormat", method = RequestMethod.GET)
	public ResponseEntity<String> getMimeTypeDataFormat(@RequestParam("nonDICOMDataFormat") String nonDICOMDataFormat) {  
		String res = null;
		try {
			res = executeQuerry("  SELECT DISTINCT  ?label ?class\n" + 
					"           WHERE {\n" + 
					"        ?class rdf:type owl:Class .\n" + 
					"        ?class ontomedirad:has_MIME_type ?label .\n" + 
					"        ?class skos:prefLabel ?classlabel .\n" + 
					"        FILTER (?classlabel = \""+nonDICOMDataFormat+"\"@en) \n" + 
					"        }" , "false" );
		} catch (TupleQueryResultHandlerException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("TupleQueryResultHandlerException"); 				
		} catch (QueryEvaluationException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("QueryEvaluationException"); 				
		} catch (UnsupportedQueryResultFormatException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("UnsupportedQueryResultFormatException"); 				
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("IOException"); 				
		}
		return ResponseEntity.status(HttpStatus.OK).body(res); 				

	} 

	@RequestMapping (value = "/getResearchStudies", method = RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<String> getResearchStudies() {   
		String res=null;
		try {
			res = executeQuerry("SELECT DISTINCT ?study ?id ?name ?description\n" + 
					"          WHERE {\n" + 
					"      ?study rdf:type ontomedirad:clinical_research_study .\n" + 
					"      ?study ontomedirad:has_id ?id .\n" + 
					"      ?study ontomedirad:has_name ?name .\n" + 
					"      ?study ontomedirad:has_description ?description .\n" + 
					"      }" , "false" );
		} catch (TupleQueryResultHandlerException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("TupleQueryResultHandlerException"); 				
		} catch (QueryEvaluationException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("QueryEvaluationException"); 				
		} catch (UnsupportedQueryResultFormatException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("UnsupportedQueryResultFormatException"); 				
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("IOException"); 				
		}
		return ResponseEntity.status(HttpStatus.OK).body(res); 			
	} 
	
	List<String> listXSDnames = Stream.of("2D-DosimetryWorkflow.xsd", "3D-DosimetrySlide1Workflow.xsd", "3D-DosimetrySlide2Workflow.xsd", 
			"3D-DosimetryWorkflow.xsd", "calibrationWorkflow.xsd", "Hybrid-DosimetryWorkflow.xsd","WP2subtask212WorkflowData.xsd"
			).collect(Collectors.toList());
					
	@RequestMapping (value = "/getXSDfilesName", method = RequestMethod.GET, produces = {"application/json"})
	public String getXSDfilesName() {  
		JSONArray listeJSON = new JSONArray();
		try {
			InputStream stream = new ClassPathResource("xsdTableau.txt").getInputStream();
			String line; String name; String fileName;  String description;
			BufferedReader br = new BufferedReader(new InputStreamReader(stream));
			while ((line = br.readLine()) != null) {
				name = line.split(";")[0];
				fileName = line.split(";")[1];
				description = line.split(";")[2];
				
				JSONObject obj = new JSONObject();
				try {
					obj.put("label", name);
					obj.put("fileName", fileName);
					obj.put("description", description);
					listeJSON.put(obj);		
				} catch (JSONException e) {e.printStackTrace();}
			}
			
			stream.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (int i=0; i<listXSDnames.size(); i++) {							// Iterate on the querry's list
							        // Add the querry to the JSON list
		}
		return listeJSON.toString();	
	} 
	
	@RequestMapping (value = "/getXSD", method = RequestMethod.GET, produces = {"text/xml"} )
	public String getXSD(@RequestParam("fileName") String fileName) {  
		System.out.println("getXSD");
		String path = "/xsdSimple/"+fileName;
		try {
			ClassPathResource res = new ClassPathResource(path);
			System.out.println(res.getFilename());
			InputStream stream = res.getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(stream, writer, "UTF-8");
			String theString = writer.toString();
			return(theString);
		} catch (IOException e) {
			System.out.print("IOException");
			System.out.print(e.getMessage());
			e.printStackTrace();
			return null;
		}
		//return null;
	} 
	
	@RequestMapping(value = "/getRequestList", method = RequestMethod.GET, produces = {"application/json"})
	public String getRequestList()  throws JSONException  {
		JSONArray res = Application.listQuerries.getJsonString();
		return res.toString();
	} // return request list in JSON

	@RequestMapping(value = "/validateDicomFileSetDescriptor", method = RequestMethod.POST, headers = "Accept=text/xml", produces = "application/json")
	public ResponseEntity<String> validateDicomFileSetDescriptor(@RequestBody String filesetDescriptorString)  {  // validate request list in JSON
		logger.info("Validating DicomFileSetDescriptor");			// Log a message 
		String tmpFilePath = "tmp.xml";
		InputStream xsdStream;
		Validator validator;
		try {
			PrintWriter out = new PrintWriter(tmpFilePath);
			out.println(filesetDescriptorString);						// Write XML content to be validated in the file
			out.close();
			SchemaFactory factory =  SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			xsdStream = new ClassPathResource("/xsd/dicomFileSetDescriptor.xsd").getInputStream();
			Source schemaSource = new StreamSource(xsdStream);
	        Schema schema = factory.newSchema(schemaSource);
	        validator = schema.newValidator();
	        validator.validate(new StreamSource(new File(tmpFilePath)));
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("IO Error when reading XML or XSD File : "+e.getMessage()); 
		} catch (SAXException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("XML doesn't match with XSD file : "+e.getMessage()); 
		}
		return ResponseEntity.status(HttpStatus.OK).body(""); 				
	}

	@RequestMapping(value = "/validateNonDicomFileSetDescriptor", method = RequestMethod.POST, headers = "Accept=text/xml", produces = "application/json")
	public @ResponseBody ResponseEntity<String> validateNonDicomFileSetDescriptor(@RequestBody String filesetDescriptorString) throws SAXException, IOException {
		logger.info("Validating NonDicomFileSetDescriptor");			// Log a message 
		String tmpFilePath = "tmp.xml";
		InputStream xsdStream;
		Validator validator;
		try {
			PrintWriter out = new PrintWriter(tmpFilePath);
			out.println(filesetDescriptorString);						// Write XML content to be validated in the file
			out.close();
			SchemaFactory factory =  SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			xsdStream = new ClassPathResource("/xsd/nonDicomFileSetDescriptor.xsd").getInputStream();
			Source schemaSource = new StreamSource(xsdStream);
	        Schema schema = factory.newSchema(schemaSource);
	        validator = schema.newValidator();
	        validator.validate(new StreamSource(new File(tmpFilePath)));
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("IO Error when reading XML or XSD File : "+e.getMessage()); 
		} catch (SAXException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("XML doesn't match with XSD file : "+e.getMessage()); 
		}
		return ResponseEntity.status(HttpStatus.OK).body(""); 				
	}

	public static boolean GateKeeper(String request) {	   				// Security Check for request sent to StarDog
		// All request that will degrade data in stardog will be blocked
		if (request.contains("CONSTRUCT")) {return false;} 				// Unsecure command because Construct command can degrade data
		return true;									   				// Commands seems to be secure
	}
	
	public synchronized String executeQuerry(String request, String isReasoning) throws TupleQueryResultHandlerException, QueryEvaluationException, UnsupportedQueryResultFormatException, IOException { // Execute a querry (querry is passed as a string)
		if (GateKeeper(request)==false) {	
			logger.error("Request refused for Security Reason");
			return null;
		} 
		
		switch(isReasoning) {										  	// Will create a stardog connection 
		case "true": 
			createAdminConnection(database.ontoMedirad, true); 		  	// Create a connection to a stardog database with reasoning
			break;
		case "false":
			createAdminConnection(database.ontoMedirad, false);  	  	// Create a connection to a stardog database WITHOUT reasoning
			break;
		default:														// Defaut case because for always create a connection to stardog
			createAdminConnection(database.ontoMedirad, reasoningDefault);// Create a connection to a stardog database with reasoning as defaut value
			break;
		}

		SelectQuery aQuery = starDogConnection.select(request);         // Put the request to the StarDog

		TupleQueryResult aResult = null;							    // Create an object to receive the result

		aResult = aQuery.execute();										// Execute Request (StarDog Exception)

		ByteArrayOutputStream out = new ByteArrayOutputStream();	    // Create an OuputStream to receive result
		QueryResultIO.writeTuple(aResult,                           // Write the request result in the ByteArrayOutputStream
				TupleQueryResultFormat.JSON, out);
		starDogConnection.close();										// Close The Connection to Stardog (despite the errors)
		aResult.close();												// Close The Object (despite the errors)
											
		return out.toString(); // Convert the ByteArrayOutputStream as a string and return it
	}

	@RequestMapping( value = "/requestFromList", method = RequestMethod.GET, headers = "Accept=text/xml", produces = "application/sparql-results+json")
	public ResponseEntity<String> requestFromList(@RequestParam("id") String id)        // Shortcut to execute a request from the request list
			throws TupleQueryResultHandlerException, QueryEvaluationException, StardogException, UnsupportedQueryResultFormatException, IOException {    
		String isReasoning = "false";
		Querry q = Application.listQuerries.getRequest(id);             // Retrieve request from the list (can be null if request name is unknown)

		if (q!=null) {											   	    // If request is NOT null
			String a = executeQuerry(q.getRequest().replaceAll("\"", ""), isReasoning); 	    // Execute the querry and store the result as a string
			return ResponseEntity.status(HttpStatus.OK).body(a);											        // Return Querry Result
		} else {												        // If request is null
			logger.error("Unknown Request");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unknown Request");
		}
	}

	@RequestMapping( value = "/testRequestControle", method = RequestMethod.GET, headers = "Accept=text/xml") // For test only
	public String TestRequestControle(@RequestParam Map<String,String> requestParams) throws TupleQueryResultHandlerException, QueryEvaluationException, UnsupportedQueryResultFormatException, IOException {
		System.out.println(requestParams);
		String name = requestParams.get("name");
		int valueRef = Integer.parseInt(requestParams.get("valueRef"));
		String request = "SELECT (COUNT(DISTINCT ?entity) AS ?count) WHERE {?entity rdf:type "+name+" . }";
		System.out.println(request);
		String res = null;
		res = executeQuerry(request,"false");

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
	public ResponseEntity<String> RequeteTestReturn(@RequestParam("isReasoning") String isReasoning) {
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

		@SuppressWarnings("unused")
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
		@SuppressWarnings("unused")
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
		String res = null;
		try {
			 res = executeQuerry(sparql1 ,isReasoning);
		} catch (TupleQueryResultHandlerException e) {
			e.printStackTrace();
		} catch (QueryEvaluationException e) {
			e.printStackTrace();
		} catch (UnsupportedQueryResultFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK).body(res);

	}

	public static boolean databaseContains(String test) { 				// Function for check if Database name provided is a real database (in the enumeration)
		for (database b : database.values()) {							// Iterate the iteration
			if (b.name().equals(test)) {								// Compare name provided with name in the iterartion
				return true;											// Return True if name provided is in the iterartion
			}
		}
		return false;													// Return False if name provided is NOT in the iterartion
	}
	
	public static Individual getPatient() {
		return patient;
	}

	@RequestMapping(value = "/importDicomFileSetDescriptor", method = RequestMethod.POST, headers = "Accept=text/xml")
	public ResponseEntity<String> importDicomData(@RequestBody DicomFileSetDescriptor dicomFileSetDescriptor,  				// Import Dicom file
			@RequestParam(value = "db", required = false) String db)  {						   				// (not required) name of the database
		logger.info("Dicom Import request received");
		if (pacsUrl==null) {pacsUrl = Application.pacsUrl ;}
		if (starDogUrl==null) {starDogUrl = Application.starDogUrl ;}
		if (memory==null) {memory=Application.memory;}														// Memory is used for store some objects

		Individual clinicalResearchStudy = OntologyPopulator.
				retrieveClinicalResearchStudy(dicomFileSetDescriptor.getReferencedClinicalStudy().getClinicalStudyID());

		for (DICOMStudyType study : dicomFileSetDescriptor.getDICOMStudy()) {				   				// Iterate on the studies
			for (DICOMSeriesType series : study.getDICOMSeries() ) {						   				// Iterate on the series
				studyInstanceUID = study.getDICOMStudyDescriptor().getStudyInstanceUID0020000D();		// Get studyID
				seriesInstanceUID = series.getDICOMSeriesDescriptor().getSeriesInstanceUID0020000E();	// Get seriesID
				if(series.getDICOMSeriesDescriptor().getModality00080060().equals("SR")) {	   				// If there is a SR Structured Report
					logger.info("SR referenced");
					if (db==null)  {createAdminConnection(database.ontoMedirad);}							// If no DB provided create a connection to ontoMedirad
					else {createAdminConnection(db);}														// If DB provided create a connection these DB
					try {
						retrieveSR(studyInstanceUID, seriesInstanceUID);
					} catch (DicomException e) {
						e.printStackTrace();
						return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("DicomException when reading SR");
					} catch (IOException e) {
						e.printStackTrace();
						return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("IOException when reading SR");
					}										
					starDogConnection.close();																// Close Stardog connection

				} else if (series.getDICOMSeriesDescriptor().getModality00080060().equals("CT")) {			// If there is a CT 
					logger.info("CT referenced");
					if (db==null)  {createAdminConnection(database.ontoMedirad);}							// If no DB provided create a connection to ontoMedirad
					else {createAdminConnection(db);}														// If DB provided create a connection these DB
					logger.info("Retrieving CT"+" StudyInstanceUID: " + studyInstanceUID+" SeriesInstanceUID: " + seriesInstanceUID);
					Iterator<DICOMStudyType> iterFileSet = dicomFileSetDescriptor.getDICOMStudy().iterator();// Iterator on the root of the Dicom Study XML
					TranslateDicomData.readingCT(iterFileSet, 												// Read and translate the CT
							studyInstanceUID, seriesInstanceUID, 
							clinicalResearchStudy, dicomFileSetDescriptor.getPatientDescriptor());
					rdfName = "RdfBackup/CT_study" + studyInstanceUID+"_series_" + seriesInstanceUID+".rdf";// Name for the RDF file
					try {
						writingRDF(rdfName);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
						return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("FileNotFoundException when writing RDF file");
					}																	// Write RDF in a file
					
					try {
						setInStarDog(rdfName);
					} catch (StardogException e) {
						e.printStackTrace();
						return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Error encountered in StarDog");
					} catch (FileNotFoundException e) {
						e.printStackTrace();
						return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("FileNotFoundException when push RDF file to Stardog");
					}																	// Push RDF file to Stardog
					starDogConnection.close();																// Close Stardog connection
				} else {
					logger.warn("Unknown reference : "														// Log a message if there is an unknown type
								+series.getDICOMSeriesDescriptor().getModality00080060()); 									
				}
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body("ImportDicomFileSetDescriptor Request received");
	}

	@RequestMapping( value = "/importNonDicomFileSetDescriptor", method = RequestMethod.POST, headers = "Accept=text/xml")
	public ResponseEntity<String> importNonDicomData(@RequestBody NonDicomFileSetDescriptor nonDicomFileSetDescriptor,  	// Import Non Dicom data XML valid file
			@RequestParam(value = "db", required = false) String db) {						    

		if (pacsUrl==null) {pacsUrl = Application.pacsUrl ;}
		if (starDogUrl==null) {starDogUrl = Application.starDogUrl ;}	
		
		if (nonDicomFileSetDescriptor!=null) {									    						// If there is a nonDicomFileSetDescriptor
			TranslateNonDicomData.translateNonDicomData(nonDicomFileSetDescriptor); 						// Translate these Data
		}

		logger.info("Retrieving NON Dicom FileSetDescriptor");
		z++;																								// Z is a number for the file name (will avoid overwriting)
		rdfName = "RdfBackup/nonDicom_study" + nonDicomFileSetDescriptor.getReferencedClinicalResearchStudy().getClinicalResearchStudyID() +"_"+z+".rdf";
		try {
			writingRDF(rdfName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("FileNotFoundException when writing RDF file");
		}																				// Write Semantic Graph in a RDF file
		if (db==null)  {createAdminConnection(database.ontoMedirad);}										// If no DB provided create a connection to ontoMedirad
		else {createAdminConnection(db);}																	// If DB provided create a connection these DB setInStarDog(rdfName);
		try {
			setInStarDog(rdfName);
		} catch (StardogException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Error encountered in StarDog");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("FileNotFoundException when push RDF file to Stardog");
		}
		starDogConnection.close();																			// Close Stardog connection

		return ResponseEntity.status(HttpStatus.OK).body("ImportNonDicomFileSetDescriptor Request received");
	}
	
	public boolean retrieveSR(String studyInstanceUID, String seriesInstanceUID) throws DicomException, IOException {							// Treat SR (before and after translation)
		logger.info("Retrieving SR StudyInstanceUID: " + studyInstanceUID+" SeriesInstanceUID: " + seriesInstanceUID);
		// Execution has many exceptions
		String targetURL = "http://"+pacsUrl+"/dcm4chee-arc/aets/DCM4CHEE/rs/studies/" + studyInstanceUID + "/series/" + seriesInstanceUID;  // URL in DCM4CHEE
		handle = "/pacs/studies/"+studyInstanceUID+"/series/"+seriesInstanceUID;						// Handle to retrieve object in the Pacs

		URL url = new URL(targetURL);																	// Create URLfor the connection to Retrieve the SR in DCM4CHEE
		HttpURLConnection pacsConnection = (HttpURLConnection) url.openConnection();					// Open Connection
		pacsConnection.setRequestMethod("GET");
		pacsConnection.setRequestProperty("Accept", "multipart/related; type=application/dicom;");

		String boundary = "";																			// Create object for retrieve SR as a text  
		String boundaryHeader = "boundary=";															// Same
		String contentType = pacsConnection.getHeaderField("Content-Type");								// Get header at "Content-Type"
		int bufsize = pacsConnection.getContentLength();												// Get Length of the SR as a text

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
				pacsConnection.getInputStream(), boundary.getBytes(), bufsize, null);

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

		attributeList.read(f);																			// Read SR from file and put contain in attributeList
		SR = new StructuredReport(attributeList);														// Convert the attributeList in StructuredReport
		TranslateDicomData.readingSR((ContentItem) SR.getRoot());										// Read and Translate the SR from the root
		
		createAdminConnection(database.ontoMedirad);
		rdfName = "RdfBackup/SR_study" + studyInstanceUID+"_series_" + seriesInstanceUID+".rdf";		// Name for the RDF File (won't be overwritten)
		writingRDF(rdfName);																			// Write the populated graph in the RDF file
		setInStarDog(rdfName);
		
		logger.debug("Retrieving SR : No exception catched");
		if(pacsConnection != null) {pacsConnection.disconnect(); }											// Close the connection to the Stardog
		
		return true;																						// End : everything was good
	}

	public void createAdminConnection(database db, boolean paramreasoner) {									// Main method to open a connection to a Stardog database
		if (pacsUrl==null) {pacsUrl = Application.pacsUrl ;}
		if (starDogUrl==null) {starDogUrl = Application.starDogUrl ;}		
		logger.debug("Creation of the StarDog connection (Database : "+db.toString()+") at "+starDogUrl);
		ConnectionConfiguration connectionConfig = ConnectionConfiguration									// Configuration of the connection
				.to(db.toString())																			// Select Database (from the enumeration)
				.server(starDogUrl)																			// StarDog URL 
				.credentials(username, password)															// Login and Pasword of Stardog
				.reasoning(paramreasoner);																	// Will it use reasoning (boollean)

		logger.debug("StarDog connection (reasoning : "+ConnectionConfiguration.REASONING_ENABLED.toString()+")"); 

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
		createAdminConnection(database.ontoMedirad, reasoningDefault);										// If the dataBase is not in the enumeration
	}																										// Create a connection by default

	public void createAdminConnection(String db, boolean paramreasoner) {
		for (database b : database.values()) {
			if (b.name().equals(db)) {
				createAdminConnection(b, paramreasoner); return;
			}
		}
		createAdminConnection(database.ontoMedirad, paramreasoner);
	}

	private ConnectionPool createConnectionPool (ConnectionConfiguration connectionConfig) {				// Create the connection pool
		ConnectionPoolConfig poolConfig = ConnectionPoolConfig.using(connectionConfig)
				.minPool(0).maxPool(50)
				.expiration(30, TimeUnit.MINUTES)
				.blockAtCapacity(1, TimeUnit.MINUTES);
		return poolConfig.create();
	}

	public void writingRDF(String pathOut) throws FileNotFoundException  {																// Write the RDF 
		logger.info("Writing RDF file in "+pathOut);		

		FileOutputStream sortie = new FileOutputStream(pathOut);
		OntologyPopulator.populateModel.write(sortie, "RDF/XML-ABBREV");					

		logger.info("Writing RDF file Sucessfull");
	
	}

	public void setInStarDog(String path) throws StardogException, FileNotFoundException  {																	// Import a RDF file in stardog
		logger.info("Transfer to stardog...");
		System.out.println(starDogConnection);
		starDogConnection.begin();																			// Begin the import action 
		
		starDogConnection.add().io().format(RDFFormat.RDFXML).stream(new FileInputStream(path)); 

		starDogConnection.commit();																			// End of the import action (without commit the import is not valid)
		logger.info("Transfer to stardog : Complete");
	}
	
	@RequestMapping (value = "/downloadDatasFromStarDog", method = RequestMethod.GET, headers = "Accept=text/rdf")
	public @ResponseBody FileSystemResource downloadStarDogDatabase(@RequestParam(value = "db", required = false) String db) throws FileNotFoundException {		
		
		String fileName; File file = null;
		if (db==null)  {
			createAdminConnection(database.ontoMedirad);										// If no DB provided create a connection to ontoMedirad
			fileName = "stardogData_ontoMedirad.rdf";
		} else {
			createAdminConnection(db);
			fileName = "stardogData_"+db.toString()+".rdf";
		}
		
		Exporter exporter = starDogConnection.export();
		exporter.format(org.openrdf.rio.RDFFormat.RDFXML);
		
		file = new File(fileName);
		FileOutputStream output = new FileOutputStream(file);
		exporter.to(output);
		
		starDogConnection.close();
		
		return new FileSystemResource(file); 
	}
	
	@RequestMapping (value = "/shutDownServer", method = RequestMethod.GET , headers = "Accept=text/xml")
	public void shutDownServer() {
		logger.info("Shutting Down Server");
		System.out.println("Good Bye Friend !");
		System.exit(0);
	}
	
	@RequestMapping (value = "/addRequest", method = RequestMethod.GET , headers = "Accept=text/xml")
	public void addRequest(@RequestParam("name") String name,
			@RequestParam("label") String label,
			@RequestParam("request") String request,
			@RequestParam("description") String description) {
		Application.listQuerries.addRequest(name, label, request, description);
	}
	
	@RequestMapping (value = "/deleteRequest", method = RequestMethod.GET , headers = "Accept=text/xml")
	public void deleteRequest(@RequestParam("name") String name) {
		Application.listQuerries.deleteRequest(name);
	}
	
	@RequestMapping (value = "/downloadRequests", method = RequestMethod.GET , headers = "Accept=text/xml")
	public String downloadRequests() {
		return Application.listQuerries.getRequestListsinCSV();
	}
	
}