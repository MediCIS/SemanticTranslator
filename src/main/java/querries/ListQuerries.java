package querries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;

public class ListQuerries {
	
	private ArrayList<Querry> ListQuerry; 
	private String fileName = "requestList.ser";
	
	public ListQuerries() {													// Constructor (by reading querries in an excel file) 
		
		try {
			InputStream fileIn = new ClassPathResource(fileName).getInputStream();
	        ObjectInputStream in = new ObjectInputStream(fileIn);
	        ListQuerry = (ArrayList<Querry>) in.readObject();
	        in.close();
	        fileIn.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	public void serializeRequests() {
		try {
			ClassPathResource res = new ClassPathResource(fileName);
			FileOutputStream fos = new FileOutputStream(res.getFile());
	        ObjectOutputStream out = new ObjectOutputStream(fos);
	        out.writeObject(ListQuerry);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
		serializeRequests();
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
		serializeRequests();
	}
	
	public  JSONArray getJsonString() throws JSONException {											// Return Querry in JSON format
		JSONArray listeJSON = new JSONArray();								// Create an array in JSON format
		for (int i=0; i<ListQuerry.size(); i++) {							// Iterate on the querry's list
			String strJson = ListQuerry.get(i).getJSON();
			JSONObject jsonQuerry = null;
			jsonQuerry = new JSONObject(strJson);
			listeJSON.put(jsonQuerry);								// Add the querry to the JSON list
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