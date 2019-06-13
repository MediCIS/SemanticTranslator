package querries;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;

import au.com.bytecode.opencsv.CSVReader;

public class ListQuerries {
	
	private ArrayList<Querry> ListQuerry; 
	//private String fileName = "hgdhgd.csv";
	private String fileName = "RequestList.csv";

	
	public ListQuerries() throws IOException {													// Constructor (by reading querries in an excel file) 
		ListQuerry = new ArrayList<Querry>();
		CSVReader readerCSV = null;
		InputStream fileStream;
        Boolean requestReasoning;
        fileStream = new ClassPathResource(fileName).getInputStream();
		readerCSV = new CSVReader(new BufferedReader(new InputStreamReader(fileStream)), ';');
		String [] nextLine;
        while ((nextLine = readerCSV.readNext()) != null) {
        	String requestNum = nextLine[0];
			//System.out.println("requestNum : "+requestNum);
			
			String requestTitle = nextLine[1];
			//System.out.println("requestTitle : "+requestTitle);

			String requestDescription = nextLine[2];
			//System.out.println("requestDescription : "+requestDescription);
			
			String requestReasoningString = nextLine[3];
			if (requestReasoningString.contains("FALSE")) {
				requestReasoning = false;
			} else {
				requestReasoning = true;
			}
			
			//System.out.println("requestReasoning (csv) : "+requestReasoningString);
			//System.out.println("requestReasoning : "+requestReasoning);

			String request = nextLine[4];
			//System.out.println("request : "+request);

			addRequest(requestNum, requestTitle, request, requestDescription, requestReasoning);
        }
        fileStream.close();
        readerCSV.close();
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
	
	public void addRequest(String name, String label, String request, String description, boolean reasoning) {
		ListQuerry.add(new Querry(name, label, request, description, reasoning));		// Create a querry Object and add it to the list
	}
	
	public void deleteRequest(String nameOrLabel) {
		Iterator<Querry> iter = ListQuerry.iterator();
		Querry requestToDelete = null;
		while (iter.hasNext()) {
			Querry request = iter.next();

			if (request.getLabel().trim().contains(nameOrLabel.trim()) || request.getId().trim().contains(nameOrLabel.trim())) {
				requestToDelete = request;
			}
		}
		if (requestToDelete!=null) {
			ListQuerry.remove(requestToDelete);
		}
	}
	
	public JSONArray getJsonString() throws JSONException {				// Return Querry in JSON format
		JSONArray listeJSON = new JSONArray();								// Create an array in JSON format
		Iterator<Querry> iter = ListQuerry.iterator();
		while (iter.hasNext()) {
			JSONObject jsonQuerry = new JSONObject(iter.next().getJSON());
			listeJSON.put(jsonQuerry);	
		}
		return listeJSON;										// Return the JSON list
	}
	
	public String getRequestListsinCSV() {
		String csv = "";
		Iterator<Querry> iter = ListQuerry.iterator();
		while (iter.hasNext()) {
			csv+=iter.next().toString()+"\n";
		}
		System.out.println("csv : "+csv);
		return csv;
	}
	
}