package querries;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import querries.Querry;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.json.JSONArray;
import org.springframework.core.io.ClassPathResource;

public class ListQuerries {
	
	private ArrayList<Querry> ListQuerry; 
	
	public ListQuerries() {													// Constructor (by reading querries in an excel file) 
		ListQuerry = new ArrayList<Querry>(); 								// List for store the Querry Objects
		
		ClassPathResource resource = 										// Excel file containing the querries list
				new ClassPathResource("/RequestList.xlsx");					// Stored as a ressource 
			    
		try {
			InputStream i = resource.getInputStream();						// Stream to read the file
			Workbook workbook = WorkbookFactory.create(i);							// Convert the file content as a "workbook"
	        Sheet sheet = workbook.getSheetAt(0); 							// Getting the first Sheet (at index zero)
	        DataFormatter dataFormatter = new DataFormatter();  			// Create a DataFormatter to format and get each cell's value as String 
	        String id; String description; String requete; String label; 	// Empty String for store values read in the file
	        
	        for (Row row: sheet) { 											// Use a for-each loop to iterate over the rows and columns
	        	id = dataFormatter.formatCellValue(row.getCell(0)); 		// Get ID value in the first cell
	        	label = dataFormatter.formatCellValue(row.getCell(1));		
	        	description = dataFormatter.formatCellValue(row.getCell(2));
	        	requete = dataFormatter.formatCellValue(row.getCell(3));

	        	ListQuerry.add(new Querry(id, label, requete, description)); // Generate a object Querry and add it to the list
	        }

	        workbook.close(); 	        									 // Closing the workbook
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public Querry getRequest(String nameRequest) {							// Return a Querry as a Java Object
		Iterator<Querry> iter = ListQuerry.iterator();						// Iterator on the querry list
		while (iter.hasNext()) {
			Querry req = iter.next();										// Get the querry
			if (req.getId().equalsIgnoreCase(nameRequest)) {				// Compare the querry's name and name provided
				return req;													// If names are equal return the req
			}
		}
		return null;														// If no request matches return null
	}
	
	public void addRequest(String name, String label, String request, String description) {
		ListQuerry.add(new Querry(name, label, request, description));		// Create a querry Object and add it to the list
	}
	
	public String getJsonString() {											// Return Querry in JSON format
		JSONArray listeJSON = new JSONArray();								// Create an array in JSON format
		for (int i=0; i<ListQuerry.size(); i++) {							// Iterate on the querry's list
			listeJSON.put(ListQuerry.get(i).getJSON());						// Add the querry to the JSON list
		}
		return listeJSON.toString();										// Return the JSON list
	}
}