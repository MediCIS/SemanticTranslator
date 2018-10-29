package repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
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
	public static String dockerHost;
	public static String starDogUrl;
	
	public static OntModel model;										 // Model will store the ontology 
	public static ListQuerries listQuerries;							 // Object to store the querries list
				
	public static boolean hideLogs = true; 								 // Will hide most of the logs because loading ontology provide too many logs
	private static boolean express = false;

	private final static Logger logger = 								 // Object to make logs
			LoggerFactory.getLogger(Application.class);
		
    public static void main(String[] args) throws TupleQueryResultHandlerException, QueryEvaluationException, UnsupportedQueryResultFormatException, IOException {
        SpringApplication.run(Application.class, args);					 // Spring Boot
    	loadProperties();											     // Load some settings from a text file (pathOntology, dockerHost, starDogUrl)
		System.out.println("Hello World !");

        for (int i = 0; i<args.length; i++) {							 // Iter on arguments
        	if (args[i].contains("express")) {express=true;}			 // Allow a quick boot (without ontology loading)
        }
    	listQuerries = new ListQuerries(); 							 	 // Init a querry list (read from the excel file)

        if (express==false) {
        	loadOntology(pathOntology); 								 // load the ontlogy from file (it takes about 3-4 minutes)
        	memory = new Memory(); 										 // Going to request to get usefull object inside semanti database
        }
        
        hideLogs = false; 									 			 // Will allow logs to be show
        System.out.println("\n"); 
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||"); 
        System.out.println("\n\nSemantic Translator is ready\n\n"); 	 // Now server is ready to receive commands
    }
    
    public static void loadOntology(String pathOntology) {
		System.out.println("Loading Ontology ...");
		model = ModelFactory.createOntologyModel(); 					 // Create empty graph for the ontology
		InputStream in = FileManager.get().open(pathOntology);      	 // Get the main file
		model.read(in, null); 											 // Read the ontology files (it takes about 4 minutes)
		System.out.println("\nOntology has been Imported Sucesfully\n");
	}
	
	public static OntModel getModel() {return model;}					// Used to provide the ontology to other classes
    
    public static void loadProperties() {								 // Load some settings from a text file for configure sever 
    	Properties prop = new Properties();                   		     // Object to store the properties
    	InputStream input = null;										 // Stream for read the file
    	try {
    		input = new FileInputStream("config.properties");   		 // Load file as a stream
    		prop.load(input);								    		 // Extract properties
    		logger.info("dockerHost : "+prop.getProperty("dockerHost")); // Log the property read in the file
    		dockerHost = prop.getProperty("dockerHost");				 // Set the property
    		logger.info("starDogUrl : "+prop.getProperty("starDogUrl"));
    		starDogUrl = prop.getProperty("starDogUrl");
    		logger.info("pathOntology : "+prop.getProperty("pathOntology"));
    		pathOntology = prop.getProperty("pathOntology");
    	} catch (IOException ex) {ex.printStackTrace();}				 // Catch read errors
    	finally {
    		if (input != null) {
    			try {
    				input.close();										 // Close the stream used to read the file
    			} catch (IOException e) {								 // Catch read errors
    				e.printStackTrace();
    			}
    		}
    	}
    }
    
}