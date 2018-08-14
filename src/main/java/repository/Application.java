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
public class Application {
	
	static String testStudy;
	static String testSerie;
	
	static Memory memory;
	
	public static String pathOntology; 
	public static String dockerHost;
	public static String starDogUrl;
	public static OntModel model;
	public static ListQuerries listQuerries;
	
	public static boolean ontologyLoaded = false;
	private final static Logger logger = LoggerFactory.getLogger(Application.class);
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        loadProperties();
        listQuerries = new ListQuerries();
        loadOntology(pathOntology); 
        memory = new Memory();
        ontologyLoaded = true;
        System.out.println("ready");
    }
    
    public static void loadOntology(String pathOntology) {
		System.out.println("Loading Ontology ...");
		model = ModelFactory.createOntologyModel();
		InputStream in = FileManager.get().open(pathOntology);
		model.read(in, null);
		System.out.println("Ontology has been Imported Sucesfully\n");
	}
	
	public static OntModel getModel() {
		return model;
	}
    
    public static void loadProperties() {
    	Properties prop = new Properties();
    	InputStream input = null;
    	try {
    		input = new FileInputStream("config.properties");
    		prop.load(input);
    		logger.info("dockerHost : "+prop.getProperty("dockerHost"));
    		dockerHost = prop.getProperty("dockerHost");
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