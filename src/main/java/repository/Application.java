package repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.TupleQueryResultHandlerException;
import org.openrdf.query.resultio.UnsupportedQueryResultFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import querries.ListQuerries;


@SpringBootApplication
public class Application { 

	static Memory memory;												 // Memory will store a few objects without querrying the stardog
	
	public static String pathOntology; 									 // Settings that will be read from a file 
	public static String pacsUrl;
	public static String starDogUrl;
	public static String fhirUrl;

	public static OntModel model;										 // Model will store the ontology 
	public static ListQuerries listQuerries;							 // Object to store the querries list
				
	public static boolean hideLogs = true; 								 // Will hide most of the logs because loading ontology provide too many logs

	private final static Logger logger = 								 // Object to make logs
			LoggerFactory.getLogger(Application.class);
	
	public static OntModel dataModel;
	
	public static List<String> listeOntologyFiles = Arrays.asList("CHEBI_for_OntoMEDIRAD.owl",
			"bfo.owl","FMA_for_OntoMEDIRAD.owl","IAO_for_OntoSPM.owl",
			"MEDIRADClinicalResearchStudies.owl","ontoMedirad.owl",
			"PATO_for_OntoMEDIRAD.owl","Radionuclides_for_OntoMEDIRAD.owl",
			"radiopharmaceuticals.owl","skos.rdf","UO_for_OntoMEDIRAD.owl");
		
    public static void main(String[] args) throws IOException, TupleQueryResultHandlerException, QueryEvaluationException, UnsupportedQueryResultFormatException, InvocationTargetException {    			
		
    	int nMinutes = 10;

      	if (args.length>=1) {
     		if (args[0]=="express") {nMinutes = 0;}
     		else {nMinutes = Integer.parseInt(args[0]);}
     	}
      	
    	System.out.println("Hello World !");

    	loadProperties();									     // Load some settings from a text file (pathOntology, dockerHost, starDogUrl)

		listQuerries = new ListQuerries(); 							// Init a querry list (read from the excel file)
    	loadOntology(pathOntology);
    	
        System.out.println("Wait for Stardog"); 	
    	try {
			TimeUnit.MINUTES.sleep(nMinutes);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        System.out.println("Wait end"); 	
		
        memory = new Memory();										// Going to request to get usefull object inside semantic database    	
            	
        SpringApplication.run(Application.class, args);					 // Spring Boot

        hideLogs = false; 									 			 // Will allow logs to be show
        
        System.out.println("\n"); 
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||"); 
        logger.info("Semantic Translator is ready");
		System.out.println("model : "+model);
        System.out.println("\n\nSemantic Translator is ready\n\n"); 	 // Now server is ready to receive commands
        
    }
    
    public static void loadOntology(String pathOntology) throws IOException {
    	logger.debug("Loading Ontology ...");
		model = ModelFactory.createOntologyModel(); 					 // Create empty graph for the ontology
		InputStream in;      	 
		for (String file : listeOntologyFiles) {
			in = new FileInputStream("OntoMedirad/"+file);
			//in = new ClassPathResource("OntoMedirad/"+file).getInputStream();
			model.read(in, null); 											 // Read the ontology files (it takes about 4 minutes)
		}
		System.out.println("\nOntology has been Imported Sucesfully\n");
		System.out.println(model.size());
	}
	
	public static OntModel getModel() {return model;}					// Used to provide the ontology to other classes
    
    public static void loadProperties() throws IOException {								 // Load some settings from a text file for configure sever 
    	Properties prop = new Properties();                   		     // Object to store the properties
    	InputStream input = null;										 // Stream for read the file
		input = new FileInputStream("config.properties");   		 // Load file as a stream
		prop.load(input);								    		 // Extract properties
		logger.info("pacsUrl : "+prop.getProperty("pacsUrl")); // Log the property read in the file
		pacsUrl = prop.getProperty("pacsUrl");				 // Set the property
		logger.info("starDogUrl : "+prop.getProperty("starDogUrl"));
		starDogUrl = prop.getProperty("starDogUrl");
		logger.info("pathOntology : "+prop.getProperty("pathOntology"));
		pathOntology = prop.getProperty("pathOntology");
		logger.info("fhirUrl : "+prop.getProperty("fhirUrl"));
		fhirUrl = prop.getProperty("fhirUrl");
		input.close();										 // Close the stream used to read the file
    }
    
}