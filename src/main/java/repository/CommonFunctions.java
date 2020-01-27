package repository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.query.TupleQueryResultHandlerException;
import org.openrdf.query.resultio.QueryResultIO;
import org.openrdf.query.resultio.TupleQueryResultFormat;
import org.openrdf.query.resultio.UnsupportedQueryResultFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.api.ConnectionPool;
import com.complexible.stardog.api.ConnectionPoolConfig;
import com.complexible.stardog.api.SelectQuery;

import repository.ImportController.database;

public abstract class CommonFunctions {

	boolean reasoningDefault = false; 			// by defaut request will not use reasoning

	static String username = "admin"; 			// Credentials for StarDog
	static String password = "admin";
	
	String fhirUrl = Application.fhirUrl ;
	String pacsUrl = Application.pacsUrl ;
	String starDogUrl = Application.starDogUrl; 
	Connection starDogConnection;				// Connection to Stardog (will be activated only when necessary)
	ConnectionPool connectionPool;
	
	private final static Logger logger = LoggerFactory.getLogger(ImportController.class); 


	public static boolean GateKeeper(String request) {	   				// Security Check for request sent to StarDog
		// All request that will degrade data in stardog will be blocked
		if (request.contains("CONSTRUCT")) {return false;} 				// Unsecure command because Construct command can degrade data
		return true;									   				// Commands seems to be secure
	}



	public synchronized ResponseEntity<String> executeQuerry(String request, String isReasoning) { // Execute a querry (querry is passed as a string)
		if (GateKeeper(request)==false) {	
			logger.error("Request refused for Security Reason");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request refused for Security Reason");
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

		logger.debug("request : ");
		logger.debug(request);

		SelectQuery aQuery = starDogConnection.select(request);         // Put the request to the StarDog

		TupleQueryResult aResult = null;							    // Create an object to receive the result

		aResult = aQuery.execute();										// Execute Request (StarDog Exception)

		ByteArrayOutputStream out = new ByteArrayOutputStream();	    // Create an OuputStream to receive result
		try {
			QueryResultIO.writeTuple(aResult,                           // Write the request result in the ByteArrayOutputStream
					TupleQueryResultFormat.JSON, out);
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

		starDogConnection.close();										// Close The Connection to Stardog (despite the errors)
		aResult.close();												// Close The Object (despite the errors)

		logger.debug("result : ");
		logger.debug(out.toString());


		return ResponseEntity.status(HttpStatus.OK).body(out.toString()); // Convert the ByteArrayOutputStream as a string and return it
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
		
		if (connectionPool != null) {connectionPool.shutdown();}
		
		connectionPool = createConnectionPool(connectionConfig);								// Create the Stardog connection 
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
	
	public void closeAdminConnection() {
		starDogConnection.close();
		connectionPool.release(starDogConnection);
	}

	private ConnectionPool createConnectionPool (ConnectionConfiguration connectionConfig) {				// Create the connection pool
		ConnectionPoolConfig poolConfig = ConnectionPoolConfig.using(connectionConfig)
				.minPool(0).maxPool(50)
				.expiration(30, TimeUnit.MINUTES)
				.blockAtCapacity(1, TimeUnit.MINUTES);
		return poolConfig.create();
	}

}
