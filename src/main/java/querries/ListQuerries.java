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
import org.springframework.core.io.ClassPathResource;

public class ListQuerries {
	
	private ArrayList<Querry> ListQuerry; 
	private String csvFileName = "RequestList.csv";
	private String tmpFileName = "/Users/marinebrenet/Documents/metadata-repository/src/main/resources/requestList.ser";
	private String fileName = "requestList.ser";
	
	public ListQuerries() {													// Constructor (by reading querries in an excel file) 
		//ListQuerry = new ArrayList<Querry>();
		
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
		
		//Iterator<Querry> iter = ListQuerry.iterator();
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
		
		/*
		Iterator<Querry> iter = ListQuerry.iterator();
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("/Users/marinebrenet/Documents/metadata-repository/src/main/resources/requestList.ser");
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);

			while (iter.hasNext()) {
				Querry q = iter.next();
				out.writeObject(q);
			}
			out.close();
	        fileOut.close();
	        
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
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
			if (request.getLabel()==nameOrLabel || request.getId()==nameOrLabel) {
				requestToDelete = request;
			}
		}
		if (requestToDelete!=null) {
			ListQuerry.remove(requestToDelete);
		}
		serializeRequests();
	}
	
	public String getJsonString() {											// Return Querry in JSON format
		JSONArray listeJSON = new JSONArray();								// Create an array in JSON format
		for (int i=0; i<ListQuerry.size(); i++) {							// Iterate on the querry's list
			listeJSON.put(ListQuerry.get(i).getJSON());						// Add the querry to the JSON list
		}
		return listeJSON.toString();										// Return the JSON list
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