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
	
	public ListQuerries() {		
		ListQuerry = new ArrayList<Querry>(); 
		
		ClassPathResource resource = new ClassPathResource("/RequestList.xlsx");
			    
        Workbook workbook;
		try {
			InputStream i = resource.getInputStream();
			workbook = WorkbookFactory.create(i);
	        Sheet sheet = workbook.getSheetAt(0); 							// Getting the first Sheet (at index zero)
	        DataFormatter dataFormatter = new DataFormatter();  			// Create a DataFormatter to format and get each cell's value as String 
	        String id; String description; String requete; String label; 
	        
	        for (Row row: sheet) { 											// use a for-each loop to iterate over the rows and columns
	        	id = dataFormatter.formatCellValue(row.getCell(0)); 		// get value
	        	label = dataFormatter.formatCellValue(row.getCell(1));
	        	description = dataFormatter.formatCellValue(row.getCell(2));
	        	requete = dataFormatter.formatCellValue(row.getCell(3));

	        	ListQuerry.add(new Querry(id, label, requete, description)); // generate a object Querry and add it to the list
	        }

	        workbook.close(); 	        									 // Closing the workbook
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public Querry getRequest(String nameRequest) {							// Return Querry as a Java Object
		Iterator<Querry> iter = ListQuerry.iterator();
		while (iter.hasNext()) {
			Querry req = iter.next();
			if (req.getId().equalsIgnoreCase(nameRequest)) {
				return req;
			}
		}
		return null;
	}
	
	public void addRequest(String name, String label, String request, String description) {
		ListQuerry.add(new Querry(name, label, request, description));
	}
	
	public String getJsonString() {											// Return Querry in JSON format
		JSONArray listeJSON = new JSONArray();
		for (int i=0; i<ListQuerry.size(); i++) {
			listeJSON.put(ListQuerry.get(i).getJSON());
		}
		return listeJSON.toString();
	}
}