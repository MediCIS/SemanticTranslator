package repository;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.commons.io.IOUtils;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.tomcat.util.http.fileupload.MultipartStream;
import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Sequence;
import org.dcm4che3.data.Tag;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.complexible.stardog.StardogException;
import com.complexible.stardog.api.Exporter;
import com.pixelmed.dicom.AttributeList;
import com.pixelmed.dicom.ContentItem;
import com.pixelmed.dicom.DicomException;
import com.pixelmed.dicom.StructuredReport;

import javaXSDclass.NonDicomFileSetDescriptor;
import queries.Querry;

@Controller
@RestController

public class ServiceController extends CommonFunctions {	
	public enum database {ontoMedirad}; 	// StarDog Database list ()

	String rdfName;								// Name of the RDF file that will be writen before transfer to StarDog (for backup)
	int z = 0; 									// Used for names RDF files and avoid overwriting
	int compteurCT = 0;

	static String studyInstanceUID;

	static Memory memory = Application.memory;
	
	String lastXML = null;
	
	static Boolean testValidDicom = true;

	private final static Logger logger = LoggerFactory.getLogger(ServiceController.class); 	

	
	// Principaux Services
	@RequestMapping(value = "/importDicomMetadata", method = RequestMethod.POST)
	public String importDicomMetadata(@RequestBody String kosString) throws IOException, JSONException, DicomException {
		// function to import metadatas from a dicom file
		// Dicom file are referenced in a Dicom KOS file
		logger.info("importDicomMetadata");

		logger.info("kosString : "+kosString);
		JSONObject kosDescriptor = new JSONObject(kosString);           // Parse received string as a JSON object

		String KOSFhirId = kosDescriptor.getString("KOSFhirId");		// Extract values from JSONobject
		String ClinicalResearchStudyId = kosDescriptor.getString("clinicalResearchStudyId");

		logger.debug(kosDescriptor.toString());	 

		ArrayList<String> listCleCT = new ArrayList<String>();         // For multiple CTs from a same series, only one will be read

		if (fhirUrl==null) {fhirUrl=Application.fhirUrl;}
		if (pacsUrl==null) {pacsUrl=Application.pacsUrl;}

		String KosURL = fhirUrl+"/baseDstu3/Binary/"+KOSFhirId;  		// URL in FHiR
		URL url = new URL(KosURL);
		logger.debug(KosURL);

		HttpURLConnection fhirConnection = (HttpURLConnection) url.openConnection(); // Connection to retrieve dicom KOS file in Fhir
		fhirConnection.setRequestMethod("GET");
		fhirConnection.setRequestProperty("Accept", "type=application/dicom;"); 

		org.dcm4che3.io.DicomInputStream dis = new org.dcm4che3.io.DicomInputStream(fhirConnection.getInputStream());
				// Read KOS file as a dicom stream
		Attributes attr  = dis.readDataset(-1, -1);                    // Get Attributes tree

		Sequence mainSeq = attr.getSequence(Tag.CurrentRequestedProcedureEvidenceSequence); 
		Iterator<Attributes> itr = mainSeq.iterator(); // Iterator for iter belong CurrentRequestedProcedureEvidenceSequence

		while(itr.hasNext()) { // iter on KOS content study by study

			Attributes element = itr.next();
			Sequence referencedSeriesSeq = element.getSequence(Tag.ReferencedSeriesSequence);

			String studyId = element.getString(Tag.StudyInstanceUID); 
			studyInstanceUID = studyId;

			Iterator<Attributes> seriesItr = referencedSeriesSeq.iterator();

			while(seriesItr.hasNext()) { // iter on KOS content series by series inside a study
				Attributes seriesItem = seriesItr.next();
				Sequence referencedSOPSeq = seriesItem.getSequence(Tag.ReferencedSOPSequence);			
				String seriesUID = seriesItem.getString(Tag.SeriesInstanceUID);

				Iterator<Attributes> sopItr = referencedSOPSeq.iterator();

				while(sopItr.hasNext()) {
					Attributes sopItem = sopItr.next();

					String ReferencedSOPClassUID = sopItem.getString(Tag.ReferencedSOPClassUID);

					if (ReferencedSOPClassUID.contains("1.2.840.10008.5.1.4.1.1.88")) { // If Dicom file referenced is a SR
						retrieveDicomFile(studyId, seriesUID, ClinicalResearchStudyId); // read meta datas
						retrieveSR(studyId, seriesUID);
					} else if (ReferencedSOPClassUID.equals("1.2.840.10008.5.1.4.1.1.2")) { // If Dicom file referenced is a CT
						String cleCT = studyId + seriesUID; 
						boolean testCleCt = true;
						Iterator<String> iterCleCt = listCleCT.iterator();
						while (iterCleCt.hasNext()) {
							if (iterCleCt.next().contentEquals(cleCT)) {
								testCleCt = false;
							}
						}
						if (testCleCt == true) { // If any CT with these seriesUID and these studyId has ever been read
							retrieveDicomFile(studyId, seriesUID, ClinicalResearchStudyId);
							listCleCT.add(cleCT);
						}
					} else { // If Dicom file referenced is not a CT and not an SR
						retrieveDicomFile(studyId, seriesUID, ClinicalResearchStudyId);
					}

				}   
			}
		}
		
		dis.close();												   // Close Stream
		fhirConnection.disconnect();                                   // Close coonection to fhir

		return "{\"res\":\"importDicomMetadata Request received\"}";
	}
	
	@RequestMapping(value = "/importKheopsSR", method = RequestMethod.POST, produces = {"application/json"}, consumes= "text/plain")	
	public String importKheopsSR(@RequestBody String SRurl) throws IOException, JSONException, DicomException {	
		// Retrieve and translate a Kheops SR
		// These function is called by Kheops Software (only used on  Geneva server)
		retrieveSRKheopsFile(SRurl);
		return "{\"res\":\"importKheopsSR Request received\"}";
	}

	@RequestMapping( value = "/importNonDicomFileSetDescriptor", method = RequestMethod.POST, headers = "Accept=text/xml")
	public String importNonDicomData(@RequestBody NonDicomFileSetDescriptor nonDicomFileSetDescriptor,  	// Import Non Dicom data XML valid file
			@RequestParam(value = "db", required = false) String db) throws FileNotFoundException {						    
		// Import and translate a non dicom file
		// nonDicomFileSetDescriptor class (ands subclass) are created with JAXB anf follow the nonDicomFileSetDescriptor Schema
		if (pacsUrl==null) {pacsUrl = Application.pacsUrl ;}
		if (starDogUrl==null) {starDogUrl = Application.starDogUrl ;}	

		lastXML = nonDicomFileSetDescriptor.toString();
		logger.debug("PatientId : "+nonDicomFileSetDescriptor.getPatientId());
		TranslateNonDicomData.translateNonDicomData(nonDicomFileSetDescriptor); 						// Translate these Data

		logger.info("Retrieving NON Dicom FileSetDescriptor");
		z++;																								// Z is a number for the file name (will avoid overwriting)
		String studyID = nonDicomFileSetDescriptor.getReferencedClinicalResearchStudy().getClinicalResearchStudyID();
		rdfName = "RdfBackup/nonDicom_study" + studyID +"_"+z+".rdf";
		writingRDF(rdfName);																				// Write Semantic Graph in a RDF file
		if (db==null)  {createAdminConnection(database.ontoMedirad);}										// If no DB provided create a connection to ontoMedirad
		else {createAdminConnection(db);}																	// If DB provided create a connection these DB setInStarDog(rdfName);
		setInStarDog(rdfName);
		starDogConnection.close();																			// Close Stardog connection

		return "{\"res\": \"ImportNonDicomFileSetDescriptor Request received\"}";							// Return these message if there's no Error
	}

	@RequestMapping(value = "/validateNonDicomFileSetDescriptor", method = RequestMethod.POST, produces = {"application/json"}, consumes= "text/plain")
	public @ResponseBody String validateNonDicomFileSetDescriptor(@RequestBody String filesetDescriptorString) throws SAXException, IOException, ParserConfigurationException {
		// validate an XML received by the irdbb ui against the XSD schema
		logger.info("Validating NonDicomFileSetDescriptor");			// Log a message 
		/*
		if (validationDicomReferenced(filesetDescriptorString)==false) {
			logger.debug("Dicom Files referenced in XML were not imported");
			return new ValidationReport(false, "Dicom Files referenced in XML were not imported").getJson(); 
		}*/
		
		String tmpFilePath = "tmp.xml";  // path for write xml file
		InputStream xsdStream;
		Validator validator;
		try {
			PrintWriter out = new PrintWriter(tmpFilePath);				// Object for write xml inside a file
			out.println(filesetDescriptorString);						// Write XML content to be validated in the file
			out.close();												// Close the object
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI); // Initialize an empty object for store the schema
			xsdStream = new ClassPathResource("/xsd/nonDicomFileSetDescriptor.xsd").getInputStream(); // convert the XSD schema file as an object
			Source schemaSource = new StreamSource(xsdStream);
			Schema schema = factory.newSchema(schemaSource);
			validator = schema.newValidator();    // create an object for validate xml against XSD
			validator.validate(new StreamSource(new File(tmpFilePath))); // validate XMl against XSD
		} catch (IOException e) {
			logger.debug("IO Error when reading XML or XSD File");
			e.printStackTrace();
			return new ValidationReport(false, e.getMessage()).getJson(); // return a json object to answer to IRDBB UI with an error
		} catch (SAXException e) { // these error is not really an error it the XMl that is not valid
			logger.debug("NonDicomFileSetDescriptor is not Valid");
			e.printStackTrace();
			return new ValidationReport(false, e.getMessage()).getJson(); // return a json object to answer to IRDBB UI that XMl is not valid
		}
		logger.debug("NonDicomFileSetDescriptor is Valid"); // If there is no exception the XML is valid
		String test = new ValidationReport(true, "XML is Valid").getJson();// return a json object to answer to IRDBB UI that XMl is valid
		System.out.print(test);
		return test;
	}
	
	public Boolean validationDicomReferenced(String filesetDescriptorString) throws ParserConfigurationException, SAXException, IOException  {      
		testValidDicom = true;
		try {memory.initDicomIDsList();}
		catch (JSONException e) {e.printStackTrace();}
		String tmpFilePath = "tmp.xml";
		PrintWriter out = new PrintWriter(tmpFilePath);
		out.println(filesetDescriptorString);						// Write XML content to be validated in the file
		out.close();
	
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		File f = new File(tmpFilePath);		
		Document document = builder.parse(f);
		document.normalizeDocument();
		document.normalize();
		document.getDocumentElement().normalize();
		
		NodeList test = document.getChildNodes();
		visitChildNodes(test);
		
		return testValidDicom;
	}
	
	private static void visitChildNodes(NodeList nList) {
		//System.out.println("visitChildNodes");
		//System.out.println("nList.getLength() : "+nList.getLength());
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node node = nList.item(temp);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				//System.out.println("Node Name = " + node.getNodeName()); 
				if (node.getNodeName().contains("irdbb:DICOMData")) {
					readDicomData(node);
				} else if (node.hasChildNodes()) {
					visitChildNodes(node.getChildNodes());
				}
			}
		}
	}

	public static void readDicomData(Node nodeDicomdata) {
		NodeList nodesList = nodeDicomdata.getChildNodes();
		String DICOMStudyUID =  null;
		String DICOMSeriesUID =  null;
		for (int i = 0; i < nodesList.getLength(); i++) {
			Node node = nodesList.item(i);
			//System.out.println("Node Name = " + node.getNodeName() + "; Value = " + node.getTextContent());
			if (node.getNodeName().contains("DICOMStudyUID")) {
				DICOMStudyUID = node.getTextContent();
			}
			if (node.getNodeName().contains("DICOMSeriesUID")) {
				DICOMSeriesUID = node.getTextContent();
			}
		}
		
		if (memory.hasDicomUIDs(DICOMStudyUID, DICOMSeriesUID)==false) {
			testValidDicom=false;
		}
	}

	// Services Annexes
	@RequestMapping (value = "/getVersion", method = RequestMethod.GET)
	public String returnVersionNumber()  {
		// parse pom.xml to extract semantic translator version 
		// it was necessary to know which semantic translation is inside a docker 
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		Document doc = null;
		String version = "0";

		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {e.printStackTrace();}

		if (dBuilder!=null) {
			if ((new File("pom.xml")).exists()) {
				try {
					doc = dBuilder.parse(new File("pom.xml"));
				} catch (SAXException | IOException e) {e.printStackTrace();}
			} else {
				try {
					doc = dBuilder.parse(Application.class.getResourceAsStream("/META-INF/maven/org.springframework⁩/semanticTranslator⁩/pom.xml"));
				} catch (SAXException | IOException e) {e.printStackTrace();}
			}
			if (doc!=null) {
				doc.getDocumentElement().normalize();
				version = doc.getElementsByTagName("version").item(0).getTextContent() ;
			}
		}
		return version;
	}

	@RequestMapping (value = "/downloadDatasFromStarDog", method = RequestMethod.GET, headers = "Accept=text/rdf")
	public @ResponseBody FileSystemResource downloadStarDogDatabase(@RequestParam(value = "db", required = false) String db) throws FileNotFoundException {		
		// return as an RDF file the content of a Stardog Database
		String fileName; File file = null;
		if (db==null)  {
			createAdminConnection(database.ontoMedirad);				// If no DB provided create a connection to ontoMedirad
			fileName = "stardogData_ontoMedirad.rdf";
		} else {														// else connect to a specific database
			createAdminConnection(db);
			fileName = "stardogData_"+db.toString()+".rdf";
		}

		Exporter exporter = starDogConnection.export();   

		file = new File(fileName);
		FileOutputStream output = new FileOutputStream(file);
		exporter.to(output);

		starDogConnection.close();

		return new FileSystemResource(file); // return the RDF file
	}

	@RequestMapping (value = "/downloadRequests", method = RequestMethod.GET , headers = "Accept=text/csv")
	public String downloadRequests() {
		// return as a CSV file the request list (only for technical issues)
		return Application.listQuerries.getRequestListsinCSV();
	}

	@RequestMapping( value = "/requestFromList", method = RequestMethod.GET, headers = "Accept=text/xml", produces = "application/sparql-results+json")
	public ResponseEntity<String> requestFromList(@RequestParam("id") String id) {      
		// these function execute a request from the request list
		Querry q = Application.listQuerries.getRequest(id);             // Retrieve request from the list (can be null if request name is unknown)
		if (q!=null) {											   	    // If request is NOT null
			logger.debug("Request : "+q.getLabel());
			logger.debug("Reasoning : "+q.isReasonong());
			ResponseEntity<String> a = executeQuerry(q.getRequest().replaceAll("\"", ""), q.isReasonong().toString().toLowerCase()); 	    // Execute the querry and store the result as a string
			return a;											        // Return Querry Result
		} else {												        // If request is null
			logger.error("Unknown Request");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unknown Request");
		}
	}

	@RequestMapping (value = "/getMimeTypeDataFormat", method = RequestMethod.GET)
	public ResponseEntity<String> getMimeTypeDataFormat(@RequestParam("nonDICOMDataFormat") String nonDICOMDataFormat) {  
		// These function send a specific request for retreive mime type for a nonDICOMDataFormat
		// It is used to build the IRDBB UI interface so errors in these function can block IRDBB UI 
		ResponseEntity<String> res = null;
		res = executeQuerry("  SELECT DISTINCT  ?label ?class\n" + 
				"           WHERE {\n" + 
				"        ?class rdf:type owl:Class .\n" + 
				"        ?class ontomedirad:has_MIME_type ?label .\n" + 
				"        ?class skos:prefLabel ?classlabel .\n" + 
				"        FILTER (?classlabel = \""+nonDICOMDataFormat+"\"@en) \n" + 
				"        }" , "false" );
		return res;
	} 

	@RequestMapping (value = "/getResearchStudies", method = RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<String> getResearchStudies()  {   
		// These function send a specific request for retreive all research studies
		// It is used to build the IRDBB UI interface so errors in these function can block IRDBB UI 
		ResponseEntity<String> res = executeQuerry("SELECT DISTINCT ?study ?id ?name ?description\n" + 
				"          WHERE {\n" + 
				"      ?study rdf:type ontomedirad:clinical_research_study .\n" + 
				"      ?study ontomedirad:has_id ?id .\n" + 
				"      ?study ontomedirad:has_name ?name .\n" + 
				"      ?study ontomedirad:has_description ?description .\n" + 
				"      }" , "false" );
		return res;
	} 

	@RequestMapping (value = "/getXSDfilesName", method = RequestMethod.GET, produces = {"application/json"})
	public String getXSDfilesName() {  
		// The function provide the XSD file list to IRDBB UI 
		// It is used to build the IRDBB UI interface so errors in these function can block IRDBB UI 
		JSONArray listeJSON = new JSONArray(); // create an empty list for store XSD file information as JSON Object
		try {
			InputStream stream = new ClassPathResource("xsdTableau.txt").getInputStream(); // read file as a stream
			String line; String name; String fileName;  String description;
			BufferedReader br = new BufferedReader(new InputStreamReader(stream)); // convert stream to BufferedReader for read line by line
			while ((line = br.readLine()) != null) { // while has line
				name = line.split(";")[0];          // split line and extract values
				fileName = line.split(";")[1];
				description = line.split(";")[2];

				JSONObject obj = new JSONObject(); // create a new JSON Object for an xsd file
				try {
					obj.put("label", name);        // add values to the json object
					obj.put("fileName", fileName);
					obj.put("description", description);
					listeJSON.put(obj);				// add json object to a list
				} catch (JSONException e) {e.printStackTrace();}
			}

			br.close(); // close BufferedReader
			stream.close();  // close stream file

		} catch (IOException e) {
			e.printStackTrace();
		}

		return listeJSON.toString(); // export JSON list as a string
	} 

	@RequestMapping (value = "/getXSD", method = RequestMethod.GET, produces = {"text/xml"} )
	public String getXSD(@RequestParam("fileName") String fileName) { 
		// These function provide XML file asked
		// It is used to build the IRDBB UI interface so errors in these function can block IRDBB UI 
		String path = "/xsdSimple/"+fileName; // path to XSD
		try {
			ClassPathResource res = new ClassPathResource(path); // get XSD file as a ressource
			System.out.println(res.getFilename());
			InputStream stream = res.getInputStream();	// create stream to read XSD content
			StringWriter writer = new StringWriter();
			IOUtils.copy(stream, writer, "UTF-8"); // convertioon from stream...
			String theString = writer.toString();  // ... to string
			stream.close(); // close stream
			return(theString); //send XSD content as a string
		} catch (IOException e) {
			System.out.print("IOException");
			System.out.print(e.getMessage());
			e.printStackTrace();
			return null;
		}
	} 

	@RequestMapping(value = "/getRequestList", method = RequestMethod.GET, produces = {"application/json"})
	public String getRequestList()  throws JSONException  {
		// The function provide the request list to IRDBB UI 
		// It is used to build the IRDBB UI interface so errors in these function can block IRDBB UI 
		JSONArray res = Application.listQuerries.getJsonString();
		return res.toString();
	} // return request list in JSON

	// Fonctions locales
	public void retrieveDicomFile(String studyInstanceUID, String seriesInstanceUID, String ClinicalResearchStudyId) throws IOException {
		logger.info("Retrieving CT StudyInstanceUID: " + studyInstanceUID+" SeriesInstanceUID: " + seriesInstanceUID);
		if (pacsUrl==null) {pacsUrl=Application.pacsUrl;}

		String targetURL = pacsUrl+"/dcm4chee-arc/aets/DCM4CHEE/rs/studies/" + studyInstanceUID + "/series/" + seriesInstanceUID;  // URL in DCM4CHEE
		String handle = "/pacs/studies/"+studyInstanceUID+"/series/"+seriesInstanceUID;
		
		logger.debug(targetURL);

		HttpURLConnection pacsConnection; 
		URL url = new URL(targetURL);
		pacsConnection = (HttpURLConnection) url.openConnection();					// Open Connection
		pacsConnection.setRequestMethod("GET");
		pacsConnection.setRequestProperty("Accept", "multipart/related; type=\"application/dicom\";");

		String boundary = "";															// Create object for retrieve SR as a text  
		String boundaryHeader = "boundary=";															
		String contentType = pacsConnection.getHeaderField("Content-Type");				// Get header at "Content-Type"
		int bufsize = pacsConnection.getContentLength();	

		String[] contentTypeArray = contentType.split(";");								// Split the text received as a list of String
		for (String content : contentTypeArray ) {														 
			if (content.contains(boundaryHeader)) {
				boundary = content.substring(content.indexOf("=")+1);					// Extract the content
			}
		}

		if (bufsize<=0) { 																// Correction when bufsize = -1
			logger.warn("bufsize : "+bufsize);
			bufsize=50;																	// Buffsize correction (50 is a random number enough big)
			logger.warn("bufsize corrected to  "+bufsize);
		} else {
			logger.debug("bufsize : "+bufsize);
		}

		String srFilename = "CT"+compteurCT+".dcm";										// Filename for write the SR and read it after
		MultipartStream multipartStream;
		multipartStream = new MultipartStream(											// MultipartStream will receive the input stream containing the SR
				pacsConnection.getInputStream(), boundary.getBytes(), bufsize, null);
		logger.debug("Writing File in : "+srFilename);
		File file = new File(srFilename);												// Create the file for write the SR
		FileOutputStream out = new FileOutputStream(file.getAbsolutePath());			// New stream to write in the file
		boolean nextPart = multipartStream.skipPreamble();

		if (nextPart) {													// Iterate as a while (X.hasNext())
			multipartStream.readHeaders();								// Header of the part won't be written
			multipartStream.readBodyData(out);							// Write in the file
		}
		out.close();
		closeAdminConnection();											// Close the connection to the Stardog

		logger.debug("Reading SR (local file)");	
		File f = new File(srFilename);									// SR file that will be read 

		org.dcm4che3.io.DicomInputStream input;
		Attributes obj;
		input = new org.dcm4che3.io.DicomInputStream(f);				// open stream for read the dicom file
		obj = input.readDataset(-1, -1);
		TranslateDicomMetadatas.translateDicomMetaData(obj, ClinicalResearchStudyId, handle); // translate the dicom file from the stream
		input.close();													// close stream 						

		createAdminConnection(database.ontoMedirad);					// open connection to stardog

		rdfName = "RdfBackup/CT_metadata" + studyInstanceUID+"_series_" + seriesInstanceUID+".rdf";		// Name for the RDF File (won't be overwritten)
		writingRDF(rdfName);		// Write the populated graph in the RDF file
		setInStarDog(rdfName);      // Push file to Stardog
		
		starDogConnection.close(); //close connnection to stardog

		logger.debug("Retrieving File : No exception catched");
	}
	
	public void retrieveSRKheopsFile(String targetURL) throws IOException {
		logger.info("Retrieving SR Kheops : " + targetURL);
		if (pacsUrl==null) {pacsUrl=Application.pacsUrl;}

		logger.debug(targetURL);

		HttpURLConnection pacsConnection; 
		URL url = new URL(targetURL);
		pacsConnection = (HttpURLConnection) url.openConnection();					    // Open Connection with server
		pacsConnection.setRequestMethod("GET");											// Request Parameters for http connection
		pacsConnection.setRequestProperty("Accept", "multipart/related; type=\"application/dicom\";"); // Request Parameters for http connection

		String boundary = "";															// Create object for retrieve SR as a text  
		String boundaryHeader = "boundary=";															
		String contentType = pacsConnection.getHeaderField("Content-Type");				// Get header at "Content-Type"
		int bufsize = pacsConnection.getContentLength();	

		String[] contentTypeArray = contentType.split(";");								// Split the text received as a list of String
		for (String content : contentTypeArray ) {														 
			if (content.contains(boundaryHeader)) {
				boundary = content.substring(content.indexOf("=")+1);					// Extract the content
			}
		}

		if (bufsize<=0) { 																// Correction when bufsize = -1
			logger.warn("bufsize : "+bufsize);
			bufsize=50;																	// Buffsize correction (50 is a random number enough big)
			logger.warn("bufsize corrected to  "+bufsize);
		} else {
			logger.debug("bufsize : "+bufsize);
		}

		String srFilename = "CT"+compteurCT+".dcm";										// Filename for write the CT and read it after
		MultipartStream multipartStream;
		multipartStream = new MultipartStream(											// MultipartStream will receive the input stream containing the SR
				pacsConnection.getInputStream(), boundary.getBytes(), bufsize, null);
		logger.debug("Writing File in : "+srFilename);
		File file = new File(srFilename);												// Create the file for write the SR
		FileOutputStream out = new FileOutputStream(file.getAbsolutePath());			// New stream to write in the file
		boolean nextPart = multipartStream.skipPreamble();

		if (nextPart) {																	// Iterate as a while (X.hasNext())
			multipartStream.readHeaders();												// Header of the part won't be written
			multipartStream.readBodyData(out);											// Write in the file
		}
		out.close();
		closeAdminConnection();										// Close the connection to the Stardog

		logger.debug("Reading SR (local file)");	
		File f = new File(srFilename);								// SR file that will be read 

		org.dcm4che3.io.DicomInputStream input;
		Attributes obj;
		input = new org.dcm4che3.io.DicomInputStream(f);
		obj = input.readDataset(-1, -1);
		input.close();

		TranslateDicomMetadatas.translateSRmaienz(obj);

		createAdminConnection(database.ontoMedirad);

		rdfName = "RdfBackup/SR_Kheops.rdf";						// Name for the RDF File (will be overwritten)
		writingRDF(rdfName);										// Write the populated graph in the RDF file
		setInStarDog(rdfName);
		
		starDogConnection.close();

		logger.debug("Retrieving File : No exception catched");
	}

	public static boolean databaseContains(String test) {
		// Function for check if Database name provided is a real database (in the enumeration)
		// These function was built for allow export in a stardog with multiples databases
		// Because our stardog has only one database these function is not usefull
		// It is kept for allow new development with multiple databases
		
		for (database b : database.values()) {							// Iterate the iteration
			if (b.name().equals(test)) {								// Compare name provided with name in the iterartion
				return true;											// Return True if name provided is in the iterartion
			}
		}
		return false;													// Return False if name provided is NOT in the iterartion
	}

	public boolean retrieveSR(String studyInstanceUID, String seriesInstanceUID) throws DicomException, IOException {							
		// Import SR from DCM4CHEE, call translation function and push graph to Stardog
		logger.info("Retrieving SR StudyInstanceUID: " + studyInstanceUID+" SeriesInstanceUID: " + seriesInstanceUID);
		if (pacsUrl==null) {pacsUrl=Application.pacsUrl;}
		String targetURL = pacsUrl+"/dcm4chee-arc/aets/DCM4CHEE/rs/studies/" + studyInstanceUID + "/series/" + seriesInstanceUID;  // URL in DCM4CHEE

		String handle = "/pacs/studies/"+studyInstanceUID+"/series/"+seriesInstanceUID;						// Handle to retrieve object in the Pacs
		OntologyPopulator.handle = handle;

		logger.debug("targetURL : "+targetURL);

		HttpURLConnection pacsConnection; 
		URL url = new URL(targetURL);
		pacsConnection = (HttpURLConnection) url.openConnection();					// Open Connection
		pacsConnection.setRequestMethod("GET");										// Request Parameters for http connection
		pacsConnection.setRequestProperty("Accept",  "multipart/related; type=application/dicom;"); // Request Parameters for http connection

		String boundary = "";									// Create object for retrieve SR as a text  
		String boundaryHeader = "boundary=";								
		String contentType = pacsConnection.getHeaderField("Content-Type");	// Get header at "Content-Type"
		int bufsize = pacsConnection.getContentLength();	

		if (contentType!=null ) {
			String[] contentTypeArray = contentType.split(";");			// Split the text received as a list of String
			for (String content : contentTypeArray ) {														 
				if (content.contains(boundaryHeader)) {
					boundary = content.substring(content.indexOf("=")+1);// Extract the content
				}
			}
		}
		if (bufsize<=0) { 						// Correction when bufsize = -1
			logger.warn("bufsize : "+bufsize);
			bufsize=100;						// Buffsize correction (50 is a random number enough big)
			logger.warn("bufsize corrected to  "+bufsize);
		} else {
			logger.debug("bufsize : "+bufsize);
		}

		String srFilename = "SR.dcm";		// Filename for write the SR and read it after
		MultipartStream multipartStream;
		multipartStream = new MultipartStream(	// MultipartStream will receive the input stream containing the SR
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

		org.dcm4che3.io.DicomInputStream input;
		Attributes obj;
		input = new org.dcm4che3.io.DicomInputStream(file);				   // open the SR for extract patientID
		obj = input.readDataset(-1, -1);
		String PatientID = obj.getString(Tag.PatientID);                   // extract PatientId
		input.close();													   // close the SR

		logger.debug("Reading SR (local file)");	
		File f = new File(srFilename);										// SR file that will be read 																	// SR file that will be read 
		AttributeList attributeList = new AttributeList();					// Crate an empty attribute that will receive SR Datas

		attributeList.read(f);												// Read SR from file and put contain in attributeList
		StructuredReport SR = new StructuredReport(attributeList);			// Convert the attributeList in StructuredReport
		TranslateDicomSR.readingSR((ContentItem) SR.getRoot(), PatientID);	// Read and Translate the SR from the root

		createAdminConnection(database.ontoMedirad);
		rdfName = "RdfBackup/SR_study" + studyInstanceUID+"_series_" + seriesInstanceUID+".rdf";		
		// Name for the RDF File (won't be overwritten)
		writingRDF(rdfName);	// Write the populated graph in the RDF file
		setInStarDog(rdfName);

		logger.debug("Retrieving SR : No exception catched");
		closeAdminConnection();	// Close the connection to the Stardog

		return true;			// End : everything was good
	}

	public void writingRDF(String pathOut) throws FileNotFoundException  {		
		// Write in a RDF file the graph created (populateModel)
		logger.info("Writing RDF file in "+pathOut);		

		FileOutputStream sortie = new FileOutputStream(pathOut);
		OntologyPopulator.populateModel.write(sortie, "RDF/XML", null);					

		logger.info("Writing RDF file Sucessfull");
		OntologyPopulator.populateModel = ModelFactory.createOntologyModel();
		OntologyPopulator.imagingStudy = null;
	}

	public void setInStarDog(String path) throws StardogException, FileNotFoundException  {	
		// Import a RDF file in stardog 
		logger.info("Transfer to stardog...");
		starDogConnection.begin();						// Begin the import action 
		Path p = Paths.get(path);
		starDogConnection.add().io().file(p);

		starDogConnection.commit();						// End of the import action (without commit the import is not valid)
		logger.info("Transfer to stardog : Complete");
	}


	
	
	
	
	@RequestMapping (value = "/testXML", method = RequestMethod.POST, produces = {"application/json"},consumes= "text/xml")
	public String testXML(@RequestBody NonDicomFileSetDescriptor nonDicomFileSetDescriptor) throws FileNotFoundException  {   
		// read XML file content and translate it in RDF
		TranslateNonDicomData.translateNonDicomData(nonDicomFileSetDescriptor);
		rdfName = "testXML.rdf";	
		writingRDF(rdfName);
		createAdminConnection(database.ontoMedirad);

		setInStarDog(rdfName);
		
		closeAdminConnection();
		return "Tipoui !\n";
	}
	
	//@RequestMapping (value = "/testDICOMuids", method = RequestMethod.POST)
	public String testDICOMuids()  {  
		try {
			memory.initDicomIDsList();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		memory.testDicomUIDs();
		return "Tipoui !\n";
	}
	
	//@RequestMapping (value = "/testSR", method = RequestMethod.GET)
	public String testSR() throws IOException, DicomException {    

		List<String> listeSR = new ArrayList<String>();
		listeSR.add("srPhilips1.3.46.670589.33.1.63639248000522907300001.4948198664952878862_5000_000001_1503644282031e.dcm");
		listeSR.add("SRd.1.3.12.2.1107.5.1.4.64144.30000018031207153259200003616.dcm");
		listeSR.add("SR.dcm");
		listeSR.add("SR_IM-0001-0003.dcm");
		listeSR.add("SR_d.1.3.12.2.1107.5.1.4.64144.30000018031207153259200003616.dcm");
		listeSR.add("SR_Philips1.3.46.670589.33.1.63639248000522907300001.4948198664952878862_5000_000001_1503644282031e.dcm");
		
		studyInstanceUID="test";

		
		Iterator<String> iteratorSR = listeSR.iterator();
		
		while (iteratorSR.hasNext()) {
			String srFilename = iteratorSR.next();
			System.out.println("srFilename : "+srFilename);
			
			File file = new File("uploadFiles/SR/"+srFilename);																// Create the file for write the SR

			
			org.dcm4che3.io.DicomInputStream input;
			Attributes obj;
			input = new org.dcm4che3.io.DicomInputStream(file);
			obj = input.readDataset(-1, -1);
			String PatientID = obj.getString(Tag.PatientID);
			input.close();

			logger.debug("Reading SR (local file)");	
			File f = new File("uploadFiles/SR/"+srFilename);																	// SR file that will be read 																	// SR file that will be read 
			AttributeList attributeList = new AttributeList();												// Crate an empty attribute that will receive SR Datas

			attributeList.read(f);																			// Read SR from file and put contain in attributeList
			StructuredReport SR = new StructuredReport(attributeList);														// Convert the attributeList in StructuredReport
			TranslateDicomSR.readingSR((ContentItem) SR.getRoot(), PatientID);										// Read and Translate the SR from the root

			createAdminConnection(database.ontoMedirad);
			rdfName = "RdfBackup/"+srFilename.replace("dcm", "rdf");		// Name for the RDF File (won't be overwritten)
			writingRDF(rdfName);																			// Write the populated graph in the RDF file
			setInStarDog(rdfName);

			logger.debug("Retrieving SR : No exception catched");
		}
		
		return "Tipoui !\n";
	}
	
	//@RequestMapping (value = "/testSRkheops", method = RequestMethod.GET)
	public String testSRkheops() throws IOException {    
		String fileName = "uploadFiles/SR_Kheops _IM-0001-0001.dcm";
				
		File f = new File(fileName);																	// SR file that will be read 

		org.dcm4che3.io.DicomInputStream input;
		Attributes obj;
		input = new org.dcm4che3.io.DicomInputStream(f);
		obj = input.readDataset(-1, -1);
		input.close();

		TranslateDicomMetadatas.translateSRmaienz(obj);

		createAdminConnection(database.ontoMedirad);

		rdfName = "RdfBackup/SR_Kheops.rdf";		// Name for the RDF File (won't be overwritten)
		writingRDF(rdfName);																			// Write the populated graph in the RDF file
		setInStarDog(rdfName);
		
		starDogConnection.close();
		
		return "Tipoui !\n";
	}
	
	//@RequestMapping (value = "/testMetadatas", method = RequestMethod.GET)
	public String testMetadatas() throws IOException, DicomException {      
		List<String> listeRDF = Stream.of(
				"NM_00000631.dcm",
				"1.2.392.200036.9116.2.5.1.37.2424156756.1562828030.518650.dcm",
				"NM_royal_1.3.12.2.1107.5.6.1.0.30800119042309091780800000006.dcm",
				"CTlocalizer 0000003.dcm",
				"CTlocalizer 000000.dcm",
				"CTlocalizer 000001.dcm",
				"pet 66863 000000.dcm",
				"pet 69357 000000.dcm",
				"pet ct 13979 000000.dcm",
				"pet ct 33633 000000.dcm",
				"pet ct 77667 000000.dcm",
				"NM_1.2.826.0.1.3680043.2.1143.9044577508240762692299637257578126775.dcm",
				"NM_1.2.826.0.1.3680043.2.1143.4166221461035278595477829423829815483.dcm",
				"NM_1.2.826.0.1.3680043.2.1143.5291241776818564084362627452114139907.dcm",
				"NM_1.2.826.0.1.3680043.2.1143.4439810664766913333893527940924912402.dcm",
				"NM_1.2.840.113619.2.281.3562.103051.1493996811.123212500.dcm",
				"NM_22739480.dcm",
				"CT_1.2.840.113619.2.281.3562.103051.1493996663.372826500.dcm",
				"CT_1.2.840.113619.2.281.3562.103051.1493996667.372868700.dcm",
				"CT_1.2.840.113619.2.281.3562.103051.1493996672.372829600.dcm",
				"CT 96821 000000.dcm",
				"CT 11200 000000.dcm",
				"CTenhanced0011.dcm",
				"CTenhanced0050.dcm",
				"CTenhanced0053.dcm",
				"CTenhanced0070.dcm",
				"CTenhanced0011.dcm",
				"CTenhanced0050.dcm",
				"CTenhanced0053.dcm",
				"CTenhanced0070.dcm",
				"SR_Maienz_Report-15-2_sr.xml.dcm",
				"SR_Maienz_Report-79538-1_sr.xml.dcm",
				"SR_Maienz_Report-81000-1_sr.xml.dcm",
				"SR_Maienz_Report-81223-1_sr.xml.dcm",
				"SR_Maienz_Report-81322-1_sr.xml.dcm",
				"SR_Maienz_Report-84044-1_sr.xml.dcm"
				).collect(Collectors.toList());

		int nIter = 1;
		
		for (int i = 0; i<nIter; i++) {
			System.out.println("\tIteration"+i);
			Iterator<String> RDFIter = listeRDF.iterator();
			while (RDFIter.hasNext()) {
				String ClinicalResearchStudyId = "2.1.2";
				String fileName = RDFIter.next();
				File f = new File("uploadFiles/Dicom/"+fileName);																	// SR file that will be read 
	
				org.dcm4che3.io.DicomInputStream input;
				Attributes obj = null;
				try {
					input = new org.dcm4che3.io.DicomInputStream(f);
					obj = input.readDataset(-1, -1);
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	
				System.out.println("\n\n-------------------------------------------------------------------------------------");
	
				System.out.println("\t"+fileName);
				if (obj!=null) {
					TranslateDicomMetadatas.translateDicomMetaData(obj, ClinicalResearchStudyId, "exampleHandle");
				}
	
				rdfName = fileName.replace(".dcm", ".rdf");	
				writingRDF(rdfName);
				createAdminConnection(database.ontoMedirad);
	
				setInStarDog(rdfName);
				
				closeAdminConnection();
				System.out.println("\n\n-------------------------------------------------------------------------------------");
			}
		}
		return "Tipoui !\n";
	}

	
	
	
	
}