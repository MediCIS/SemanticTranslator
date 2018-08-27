package repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import querries.ListQuerries;

@SpringBootApplication
public class Application { //TEST
		
	static String testStudy;
	static String testSerie;
	
	static Memory memory;
	
	public static String pathOntology; 
	public static String dockerHost;
	public static String starDogUrl;
	public static OntModel model;
	public static ListQuerries listQuerries;
	
	public static boolean hideLogs = true; 						//will hide most of the logs because loading ontology provide too many logs
	private final static Logger logger = LoggerFactory.getLogger(Application.class);
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        loadProperties();
        listQuerries = new ListQuerries(); 						// Init a querry list (read from the excel file)
        loadOntology(pathOntology); 							// load the ontlogy from file (it takes about 4 minutes)
        memory = new Memory(); 									// Going to request to get usefull object inside semanti database
        hideLogs = false; 										// will allow logs to be show
        System.out.println("ready"); 							// now server is ready to receive commands
    }
    
    public static void loadOntology(String pathOntology) {
		System.out.println("Loading Ontology ...");
		model = ModelFactory.createOntologyModel(); 			//Create empty graph for the ontology
		InputStream in = FileManager.get().open(pathOntology);  //get the main file
		model.read(in, null); 									//read the ontology files (it takes about 4 minutes)
		System.out.println("Ontology has been Imported Sucesfully\n");
	}
	
	public static OntModel getModel() {
		return model;
	}
    
    public static void loadProperties() {						// load some settings from a text file for configure sever 
    	Properties prop = new Properties();
    	InputStream input = null;
    	try {
    		input = new FileInputStream("config.properties");  // load file as a stream
    		prop.load(input);								   // extract properties
    		logger.info("dockerHost : "+prop.getProperty("dockerHost")); // log the property read in the file
    		dockerHost = prop.getProperty("dockerHost");				 // set the property
    		logger.info("starDogUrl : "+prop.getProperty("starDogUrl"));
    		starDogUrl = prop.getProperty("starDogUrl");
    		logger.info("pathOntology : "+prop.getProperty("pathOntology"));
    		pathOntology = prop.getProperty("pathOntology");
    	} catch (IOException ex) {ex.printStackTrace();}
    	finally {
    		if (input != null) {
    			try {
    				input.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
    	}
    }
}