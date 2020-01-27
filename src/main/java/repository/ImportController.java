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
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;
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
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.complexible.stardog.StardogException;
import com.complexible.stardog.api.Exporter;
import com.complexible.stardog.api.SelectQuery;
import com.pixelmed.dicom.AttributeList;
import com.pixelmed.dicom.ContentItem;
import com.pixelmed.dicom.DicomException;
import com.pixelmed.dicom.StructuredReport;

import javaXSDclass.NonDicomFileSetDescriptor;
import querries.Querry;

import org.dcm4che3.data.*;

@Controller
@RestController

public class ImportController extends CommonFunctions {	
	public enum database {ontoMedirad, test}; 	// StarDog Database list ()

	String rdfName;								// Name of the RDF file that wille writen before transfer to StarDog (for backup)
	int z = 0; 									// Used for names RDF files and avoid overwriting
	int compteurCT = 0;

	static String studyInstanceUID;

	Memory memory = Application.memory;
	
	String lastXML = null;

	private final static Logger logger = LoggerFactory.getLogger(ImportController.class); 	

	@RequestMapping (value = "/testXML", method = RequestMethod.POST)
	public String testXML(@RequestBody NonDicomFileSetDescriptor nonDicomFileSetDescriptor)  {      
		TranslateNonDicomData.translateNonDicomData(nonDicomFileSetDescriptor);
		return "Tipoui !\n";
	}
	
	
	@RequestMapping (value = "/testMetadatas", method = RequestMethod.GET)
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

		int nIter = 1000;
		
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
			
			Application.memory.initVoidMemory();
		}
		return "Tipoui !\n";
	}

	// Principaux Services
	@RequestMapping(value = "/importDicomMetadata", method = RequestMethod.POST)	//, headers = "Accept=application/json"
	public String importDicomMetadata(@RequestBody String kosString) throws IOException, JSONException, DicomException {	
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
		fhirConnection.setRequestProperty("Accept",  "type=application/dicom;"); 

		org.dcm4che3.io.DicomInputStream dis = new org.dcm4che3.io.DicomInputStream(fhirConnection.getInputStream());
				// Read dicom file as a dicom stream
		Attributes attr  = dis.readDataset(-1, -1);                    // Get Attributes tree

		Sequence mainSeq = attr.getSequence(Tag.CurrentRequestedProcedureEvidenceSequence); 
		Iterator<Attributes> itr = mainSeq.iterator(); // Iterator for iter belong CurrentRequestedProcedureEvidenceSequence

		while(itr.hasNext()) {

			Attributes element = itr.next();
			Sequence referencedSeriesSeq = element.getSequence(Tag.ReferencedSeriesSequence);

			String studyId = element.getString(Tag.StudyInstanceUID);
			studyInstanceUID = studyId;

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

					if (ReferencedSOPClassUID.contains("1.2.840.10008.5.1.4.1.1.88")) { // SR
						retrieveDicomFile(studyId, seriesUID, ClinicalResearchStudyId);
						retrieveSR(studyId, seriesUID);
						//						1.2.840.10008.5.1.4.1.1.88.11	Basic Text SR	 
						//						1.2.840.10008.5.1.4.1.1.88.22	Enhanced SR	 
						//						1.2.840.10008.5.1.4.1.1.88.33	Comprehensive SR	 
						//						1.2.840.10008.5.1.4.1.1.88.40	Procedure Log Storage	 
						//						1.2.840.10008.5.1.4.1.1.88.50	Mammography CAD SR	 
						//						1.2.840.10008.5.1.4.1.1.88.59	Key Object Selection Document	 
						//						1.2.840.10008.5.1.4.1.1.88.65	Chest CAD SR	 
						//						1.2.840.10008.5.1.4.1.1.88.67	X-Ray Radiation Dose SR
					} else if (ReferencedSOPClassUID.equals("1.2.840.10008.5.1.4.1.1.2")) { // CT
						String cleCT = studyId + seriesUID;
						boolean testCleCt = true;
						Iterator<String> iterCleCt = listCleCT.iterator();
						while (iterCleCt.hasNext()) {
							if (iterCleCt.next().contentEquals(cleCT)) {
								testCleCt = false;
							}
						}
						if (testCleCt == true) {						
							retrieveDicomFile(studyId, seriesUID, ClinicalResearchStudyId);
							listCleCT.add(cleCT);
						}

					} else {
						retrieveDicomFile(studyId, seriesUID, ClinicalResearchStudyId);
					}

				}   
			}
		}
		
		dis.close();												   // Close Stream
		fhirConnection.disconnect();                                   // Close coonection to fhir

		return "{\"res\":\"importDicomMetadata Request received\"}";

	}
	
	
	@RequestMapping(value = "/importKheopsSR", method = RequestMethod.POST, produces = {"application/json"},consumes= "text/plain")	
	public String importKheopsSR(@RequestBody String SRurl) throws IOException, JSONException, DicomException {	
		//
		
		retrieveDicomFile(SRurl);
		
		return "{\"res\":\"importKheopsSR Request received\"}";
	}

	/*
	@RequestMapping(value = "/importDicomFileSetDescriptor", method = RequestMethod.POST, headers = "Accept=text/xml")
	public String importDicomData(@RequestBody DicomFileSetDescriptor dicomFileSetDescriptor,  				// Import Dicom file
			@RequestParam(value = "db", required = false) String db)  {						   				// (not required) name of the database
		logger.info("Dicom Import request received");
		if (pacsUrl==null) {pacsUrl = Application.pacsUrl ;}
		if (starDogUrl==null) {starDogUrl = Application.starDogUrl ;}
		if (memory==null) {memory=Application.memory;}														// Memory is used for store some objects

		//Individual clinicalResearchStudy = OntologyPopulator.
		//		retrieveClinicalResearchStudy(dicomFileSetDescriptor.getReferencedClinicalStudy().getClinicalStudyID());

		for (DICOMStudyType study : dicomFileSetDescriptor.getDICOMStudy()) {				   				// Iterate on the studies
			for (DICOMSeriesType series : study.getDICOMSeries() ) {						   				// Iterate on the series
				String studyInstanceUID = study.getDICOMStudyDescriptor().getStudyInstanceUID0020000D();		// Get studyID
				String seriesInstanceUID = series.getDICOMSeriesDescriptor().getSeriesInstanceUID0020000E();	// Get seriesID
				if(series.getDICOMSeriesDescriptor().getModality00080060().equals("SR")) {	   				// If there is a SR Structured Report
					logger.info("SR referenced");
					if (db==null)  {createAdminConnection(database.ontoMedirad);}							// If no DB provided create a connection to ontoMedirad
					else {createAdminConnection(db);}														// If DB provided create a connection these DB
					try {
						retrieveSR(studyInstanceUID, seriesInstanceUID);
					} catch (DicomException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}										// Translate SR
					starDogConnection.close();																// Close Stardog connection

				} else if (series.getDICOMSeriesDescriptor().getModality00080060().equals("CT")) {			// If there is a CT 
					logger.info("CT referenced");
					if (db==null)  {createAdminConnection(database.ontoMedirad);}							// If no DB provided create a connection to ontoMedirad
					else {createAdminConnection(db);}														// If DB provided create a connection these DB
					logger.info("Retrieving CT"+" StudyInstanceUID: " + studyInstanceUID+" SeriesInstanceUID: " + seriesInstanceUID);
					Iterator<DICOMStudyType> iterFileSet = dicomFileSetDescriptor.getDICOMStudy().iterator();// Iterator on the root of the Dicom Study XML
					Individual clinicalResearchStudy = OntologyPopulator.retrieveClinicalResearchStudy(dicomFileSetDescriptor.getReferencedClinicalStudy().getClinicalStudyID());
					TranslateDicomCT.readingCT(iterFileSet, 												// Read and translate the CT
							studyInstanceUID, seriesInstanceUID, 
							clinicalResearchStudy , 
							dicomFileSetDescriptor.getPatientDescriptor());
					rdfName = "RdfBackup/CT_study" + studyInstanceUID+"_series_" + seriesInstanceUID+".rdf";// Name for the RDF file
					try {
						writingRDF(rdfName);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}																	// Write RDF in a file
					try {
						setInStarDog(rdfName);
					} catch (StardogException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}																	// Push RDF file to Stardog
					starDogConnection.close();																// Close Stardog connection
				} else {
					logger.warn("Unknown reference : "														// Log a message if there is an unknown type
							+series.getDICOMSeriesDescriptor().getModality00080060()); 									
				}
			}
		}
		return "{\"res\":\"ImportDicomFileSetDescriptor Request received\"}";
	}*/

	@RequestMapping( value = "/importNonDicomFileSetDescriptor", method = RequestMethod.POST, headers = "Accept=text/xml")
	public String importNonDicomData(@RequestBody NonDicomFileSetDescriptor nonDicomFileSetDescriptor,  	// Import Non Dicom data XML valid file
			@RequestParam(value = "db", required = false) String db) throws FileNotFoundException {						    

		if (pacsUrl==null) {pacsUrl = Application.pacsUrl ;}
		if (starDogUrl==null) {starDogUrl = Application.starDogUrl ;}	

		if (nonDicomFileSetDescriptor!=null) {									    						// If there is a nonDicomFileSetDescriptor
			
			lastXML = nonDicomFileSetDescriptor.toString();
			
			TranslateNonDicomData.translateNonDicomData(nonDicomFileSetDescriptor); 						// Translate these Data
		}

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

	/*
	@RequestMapping(value = "/validateDicomFileSetDescriptor", method = RequestMethod.POST, headers = "Accept=text/xml", produces = "application/json")
	public String validateDicomFileSetDescriptor(@RequestBody String filesetDescriptorString)  {  // validate request list in JSON
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
		} catch (SAXException | IOException e) {
			logger.debug("DicomFileSetDescriptor is not Valid");
			e.printStackTrace();
			return new ValidationReport(false, e.getCause().toString()).getJson();
		}
		logger.debug("DicomFileSetDescriptor is Valid");
		return new ValidationReport(true, "XML is Valid").getJson();
	}
*/
	
	@RequestMapping(value = "/validateNonDicomFileSetDescriptor", method = RequestMethod.POST, headers = "Accept=text/xml", produces = "application/json")
	public @ResponseBody String validateNonDicomFileSetDescriptor(@RequestBody String filesetDescriptorString) throws SAXException, IOException {
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
			validator.validate(new StreamSource(new File(tmpFilePath))); // TODO eviter NullPointeresxception
		} catch (IOException e) {
			logger.debug("IO Error when reading XML or XSD File");
			e.printStackTrace();
			return new ValidationReport(false, e.getMessage()).getJson();
		} catch (SAXException e) {
			logger.debug("NonDicomFileSetDescriptor is not Valid");
			e.printStackTrace();
			return new ValidationReport(false, e.getMessage()).getJson();
		}
		logger.debug("NonDicomFileSetDescriptor is Valid");
		String test = new ValidationReport(true, "XML is Valid").getJson();
		System.out.print(test);
		return test;
	}

	// Services Annexes
	@RequestMapping (value = "/getVersion", method = RequestMethod.GET)
	public String returnVersionNumber()  {
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

	/*
	@RequestMapping (value = "/downloadOntology", method = RequestMethod.GET, headers = "Accept=application/zip")
	public void downloadOntology(HttpServletResponse response) throws IOException {
		String zipFileName = "ontoMedirad.zip";
		FileOutputStream fos = new FileOutputStream(zipFileName);
		ZipOutputStream zipOut = new ZipOutputStream(fos);
		for (String srcFile : Application.listeOntologyFiles) {
			InputStream fis = new ClassPathResource("/OntoMedirad/"+srcFile).getInputStream();
			ZipEntry zipEntry = new ZipEntry(srcFile);
			zipOut.putNextEntry(zipEntry);
			byte[] bytes = new byte[1024];
			int length;
			while((length = fis.read(bytes)) >= 0) {
				zipOut.write(bytes, 0, length);
			}
			fis.close();
		} 
		zipOut.close();
		fos.close();

		File zipFile = new File(zipFileName);
		Path p = java.nio.file.Paths.get(zipFile.getCanonicalPath().replace(zipFileName, ""), zipFileName);
		response.setContentType("application/zip");
		response.addHeader("Content-Disposition", "attachment; filename="+zipFileName);
		Files.copy(p, response.getOutputStream());
		response.getOutputStream().flush();
	}*/

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

	@RequestMapping (value = "/downloadRequests", method = RequestMethod.GET , headers = "Accept=text/csv")
	public String downloadRequests() {
		return Application.listQuerries.getRequestListsinCSV();
	}

	@RequestMapping (value = "/verifRequest", method = RequestMethod.GET)
	public String verifRequest() {

		String request = "SELECT DISTINCT ?exam ?studydescr ?human ?spectacq ?spectacqclass ?radionucl ?protocolname ?protocoldescr\n" + 
				"	WHERE {\n" + 
				"\n" + 
				" ?spectacq purl:BFO_0000132 ?exam .\n" + 
				" ?exam ontomedirad:has_description ?studydescr .\n" + 
				" ?spectacq rdf:type ?spectacqclass .\n" + 
				" ?spectacqclass rdfs:subClassOf* ontomedirad:SPECT_data_acquisition .\n" + 
				" ?spectacq ontomedirad:has_protocol ?protocol .\n" + 
				" ?protocol ontomedirad:has_name ?protocolname .\n" + 
				" ?protocol ontomedirad:has_description ?protocoldescr .\n" + 
				" OPTIONAL {?spectacq ontomedirad:has_target_radionuclide ?radionucl}\n" + 
				"} \n" + 
				"";

		createAdminConnection(database.ontoMedirad, true); 		  	// Create a connection to a stardog database with reasoning

		SelectQuery aQuery = starDogConnection.select(request);         // Put the request to the StarDog

		TupleQueryResult aResult = aQuery.execute();							    

		ByteArrayOutputStream out = new ByteArrayOutputStream();	    // Create an OuputStream to receive result
		try {
			QueryResultIO.writeTuple(aResult, TupleQueryResultFormat.JSON, out);
		} catch (TupleQueryResultHandlerException | QueryEvaluationException | UnsupportedQueryResultFormatException
				| IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String result = out.toString();

		logger.trace(result);

		return "Tipoui !\n";

	}

	@RequestMapping( value = "/requestFromList", method = RequestMethod.GET, headers = "Accept=text/xml", produces = "application/sparql-results+json")
	public ResponseEntity<String> requestFromList(@RequestParam("id") String id)        // Shortcut to execute a request from the request list
			throws TupleQueryResultHandlerException, QueryEvaluationException, StardogException, UnsupportedQueryResultFormatException, IOException {    
		//String isReasoning = "false";
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
	public ResponseEntity<String> getResearchStudies() throws TupleQueryResultHandlerException, QueryEvaluationException, StardogException, UnsupportedQueryResultFormatException, IOException {   
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

		return listeJSON.toString();	
	} 

	@RequestMapping (value = "/getXSD", method = RequestMethod.GET, produces = {"text/xml"} )
	public String getXSD(@RequestParam("fileName") String fileName) {  
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
	} 

	@RequestMapping(value = "/getRequestList", method = RequestMethod.GET, produces = {"application/json"})
	public String getRequestList()  throws JSONException  {
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
			logger.warn("bufsize : "+bufsize);
			bufsize=50;																					// Buffsize correction (50 is a random number enough big)
			logger.warn("bufsize corrected to  "+bufsize);
		} else {
			logger.debug("bufsize : "+bufsize);
		}

		String srFilename = "CT"+compteurCT+".dcm";																	// Filename for write the SR and read it after
		MultipartStream multipartStream;
		multipartStream = new MultipartStream(											// MultipartStream will receive the input stream containing the SR
				pacsConnection.getInputStream(), boundary.getBytes(), bufsize, null);
		logger.debug("Writing File in : "+srFilename);
		File file = new File(srFilename);																// Create the file for write the SR
		FileOutputStream out = new FileOutputStream(file.getAbsolutePath());							// New stream to write in the file
		boolean nextPart = multipartStream.skipPreamble();

		if (nextPart) {																					// Iterate as a while (X.hasNext())
			multipartStream.readHeaders();																// Header of the part won't be written
			multipartStream.readBodyData(out);															// Write in the file
		}
		out.close();
		if(pacsConnection != null) {pacsConnection.disconnect();}											// Close the connection to the Stardog

		logger.debug("Reading SR (local file)");	
		File f = new File(srFilename);																	// SR file that will be read 

		org.dcm4che3.io.DicomInputStream input;
		Attributes obj;
		input = new org.dcm4che3.io.DicomInputStream(f);
		obj = input.readDataset(-1, -1);
		input.close();

		TranslateDicomMetadatas.translateDicomMetaData(obj, ClinicalResearchStudyId, handle);

		createAdminConnection(database.ontoMedirad);

		rdfName = "RdfBackup/CT_metadata" + studyInstanceUID+"_series_" + seriesInstanceUID+".rdf";		// Name for the RDF File (won't be overwritten)
		writingRDF(rdfName);																			// Write the populated graph in the RDF file
		setInStarDog(rdfName);
		
		starDogConnection.close();

		logger.debug("Retrieving File : No exception catched");
	}
	
	public void retrieveDicomFile(String targetURL) throws IOException {
		logger.info("Retrieving SR Kheops : " + targetURL);
		if (pacsUrl==null) {pacsUrl=Application.pacsUrl;}

		
		logger.debug(targetURL);

		HttpURLConnection pacsConnection; 
		URL url = new URL(targetURL);
		pacsConnection = (HttpURLConnection) url.openConnection();					// Open Connection
		pacsConnection.setRequestMethod("GET");
		pacsConnection.setRequestProperty("Accept", "multipart/related; type=\"application/dicom\";");

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
			logger.warn("bufsize : "+bufsize);
			bufsize=50;																					// Buffsize correction (50 is a random number enough big)
			logger.warn("bufsize corrected to  "+bufsize);
		} else {
			logger.debug("bufsize : "+bufsize);
		}

		String srFilename = "CT"+compteurCT+".dcm";																	// Filename for write the SR and read it after
		MultipartStream multipartStream;
		multipartStream = new MultipartStream(											// MultipartStream will receive the input stream containing the SR
				pacsConnection.getInputStream(), boundary.getBytes(), bufsize, null);
		logger.debug("Writing File in : "+srFilename);
		File file = new File(srFilename);																// Create the file for write the SR
		FileOutputStream out = new FileOutputStream(file.getAbsolutePath());							// New stream to write in the file
		boolean nextPart = multipartStream.skipPreamble();

		if (nextPart) {																					// Iterate as a while (X.hasNext())
			multipartStream.readHeaders();																// Header of the part won't be written
			multipartStream.readBodyData(out);															// Write in the file
		}
		out.close();
		if(pacsConnection != null) {pacsConnection.disconnect();}											// Close the connection to the Stardog

		logger.debug("Reading SR (local file)");	
		File f = new File(srFilename);																	// SR file that will be read 

		org.dcm4che3.io.DicomInputStream input;
		Attributes obj;
		input = new org.dcm4che3.io.DicomInputStream(f);
		obj = input.readDataset(-1, -1);
		input.close();
		
		String handle = "example handle";
		String ClinicalResearchStudyId = "example ClinicalResearchStudyId";

		TranslateDicomMetadatas.translateDicomMetaData(obj, ClinicalResearchStudyId, handle);

		createAdminConnection(database.ontoMedirad);

		rdfName = "RdfBackup/SR_Kheops.rdf";		// Name for the RDF File (won't be overwritten)
		writingRDF(rdfName);																			// Write the populated graph in the RDF file
		setInStarDog(rdfName);
		
		starDogConnection.close();

		logger.debug("Retrieving File : No exception catched");
	}

	public static boolean databaseContains(String test) { 				// Function for check if Database name provided is a real database (in the enumeration)
		for (database b : database.values()) {							// Iterate the iteration
			if (b.name().equals(test)) {								// Compare name provided with name in the iterartion
				return true;											// Return True if name provided is in the iterartion
			}
		}
		return false;													// Return False if name provided is NOT in the iterartion
	}

	public boolean retrieveSR(String studyInstanceUID, String seriesInstanceUID) throws DicomException, IOException {							// Treat SR (before and after translation)
		logger.info("Retrieving SR StudyInstanceUID: " + studyInstanceUID+" SeriesInstanceUID: " + seriesInstanceUID);
		if (pacsUrl==null) {pacsUrl=Application.pacsUrl;}
		String targetURL = pacsUrl+"/dcm4chee-arc/aets/DCM4CHEE/rs/studies/" + studyInstanceUID + "/series/" + seriesInstanceUID;  // URL in DCM4CHEE

		//String handle = "/pacs/studies/"+studyInstanceUID+"/series/"+seriesInstanceUID;						// Handle to retrieve object in the Pacs

		logger.debug("targetURL : "+targetURL);

		HttpURLConnection pacsConnection; 
		URL url = new URL(targetURL);
		pacsConnection = (HttpURLConnection) url.openConnection();					// Open Connection
		pacsConnection.setRequestMethod("GET");
		pacsConnection.setRequestProperty("Accept",  "multipart/related; type=application/dicom;");

		String boundary = "";																			// Create object for retrieve SR as a text  
		String boundaryHeader = "boundary=";															// Same
		String contentType = pacsConnection.getHeaderField("Content-Type");								// Get header at "Content-Type"
		int bufsize = pacsConnection.getContentLength();	

		if (contentType!=null ) {
			String[] contentTypeArray = contentType.split(";");											// Split the text received as a list of String
			for (String content : contentTypeArray ) {														 
				if (content.contains(boundaryHeader)) {
					boundary = content.substring(content.indexOf("=")+1);								// Extract the content
				}
			}
		}
		if (bufsize<=0) { 																			// Correction when bufsize = -1
			logger.warn("bufsize : "+bufsize);
			bufsize=100;																				// Buffsize correction (50 is a random number enough big)
			logger.warn("bufsize corrected to  "+bufsize);
		} else {
			logger.debug("bufsize : "+bufsize);
		}

		String srFilename = "SR.dcm";																	// Filename for write the SR and read it after
		MultipartStream multipartStream;
		multipartStream = new MultipartStream(											     // MultipartStream will receive the input stream containing the SR
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
		input = new org.dcm4che3.io.DicomInputStream(file);
		obj = input.readDataset(-1, -1);
		String PatientID = obj.getString(Tag.PatientID);
		input.close();

		logger.debug("Reading SR (local file)");	
		File f = new File(srFilename);																	// SR file that will be read 																	// SR file that will be read 
		AttributeList attributeList = new AttributeList();												// Crate an empty attribute that will receive SR Datas

		attributeList.read(f);																			// Read SR from file and put contain in attributeList
		StructuredReport SR = new StructuredReport(attributeList);														// Convert the attributeList in StructuredReport
		TranslateDicomSR.readingSR((ContentItem) SR.getRoot(), PatientID);										// Read and Translate the SR from the root

		createAdminConnection(database.ontoMedirad);
		rdfName = "RdfBackup/SR_study" + studyInstanceUID+"_series_" + seriesInstanceUID+".rdf";		// Name for the RDF File (won't be overwritten)
		writingRDF(rdfName);																			// Write the populated graph in the RDF file
		setInStarDog(rdfName);

		logger.debug("Retrieving SR : No exception catched");
		if(pacsConnection != null) {pacsConnection.disconnect(); }											// Close the connection to the Stardog

		return true;																						// End : everything was good
	}

	public void writingRDF(String pathOut) throws FileNotFoundException  {									// Write the RDF 
		logger.info("Writing RDF file in "+pathOut);		

		FileOutputStream sortie = new FileOutputStream(pathOut);
		OntologyPopulator.populateModel.write(sortie, "RDF/XML", null);					

		logger.info("Writing RDF file Sucessfull");
		OntologyPopulator.populateModel = ModelFactory.createOntologyModel();
		OntologyPopulator.imagingStudy = null;
	}

	public void setInStarDog(String path) throws StardogException, FileNotFoundException  {					// Import a RDF file in stardog
		logger.info("Transfer to stardog...");
		starDogConnection.begin();																			// Begin the import action 

		starDogConnection.add().io().format(RDFFormat.RDFXML).stream(new FileInputStream(path)); 

		starDogConnection.commit();																			// End of the import action (without commit the import is not valid)
		logger.info("Transfer to stardog : Complete");
	}

	/*
	@RequestMapping (value = "/shutDownServer", method = RequestMethod.GET , headers = "Accept=text/xml")
	public void shutDownServer() {
		logger.info("Shutting Down Server");
		System.out.println("Good Bye Friend !");
		System.exit(0);
	}*/

}