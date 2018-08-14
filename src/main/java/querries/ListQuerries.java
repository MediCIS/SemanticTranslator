package querries;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import querries.Querry;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;

public class ListQuerries {
	
	private ArrayList<Querry> ListQuerry; 
	
	public ListQuerries() {		
		ListQuerry = new ArrayList<Querry>(); 
		
		ClassPathResource resource = new ClassPathResource("/RequestList.xlsx");
		
		//URL fileQuerriesUrl = this.getClass().getResource("classpath:/request/RequestList.xlsx");
	    //System.out.println("fileQuerriesUrl : "+fileQuerriesUrl);	 
	    
        Workbook workbook;
		try {
			InputStream i = resource.getInputStream();
			workbook = WorkbookFactory.create(i);

	        // Getting the Sheet at index zero
	        Sheet sheet = workbook.getSheetAt(0);

	        // Create a DataFormatter to format and get each cell's value as String
	        DataFormatter dataFormatter = new DataFormatter();

	        // 2. Or you can use a for-each loop to iterate over the rows and columns
	        String id; String description; String requete; String label;
	        for (Row row: sheet) {
	        	id = dataFormatter.formatCellValue(row.getCell(0));
	        	label = dataFormatter.formatCellValue(row.getCell(1));
	        	description = dataFormatter.formatCellValue(row.getCell(2));
	        	requete = dataFormatter.formatCellValue(row.getCell(3));

	        	ListQuerry.add(new Querry(id, label, requete, description));
	        }

	        // Closing the workbook
	        workbook.close();
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public Querry getRequest(String nameRequest) {
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
	
	public String getJsonString() {
		//JSONObject obj = new JSONObject();
		JSONArray listeJSON = new JSONArray();
		for (int i=0; i<ListQuerry.size(); i++) {
			//obj.put("Request"+i, ListQuerry.get(i).getJSON());
			listeJSON.put(ListQuerry.get(i).getJSON());
		}
		
		return listeJSON.toString();
	}
}