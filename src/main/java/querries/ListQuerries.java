package querries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;

public class ListQuerries implements java.io.Serializable {
	
	private ArrayList<Querry> ListQuerry; 
	private String filename = "src/main/resources/requestList.ser";
	
	public ListQuerries() {													// Constructor (by reading querries in an excel file) 
		ListQuerry = new ArrayList<Querry>(); 								// List for store the Querry Objects
		
		try {
			FileInputStream fileIn = new FileInputStream(filename);
	        ObjectInputStream in = new ObjectInputStream(fileIn);
	        ListQuerry = (ArrayList<Querry>) in.readObject();
	        in.close();
	        fileIn.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	public void serializeListQuerries() {
		try {
	         FileOutputStream fileOut = new FileOutputStream(filename);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(ListQuerry);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in "+filename);
	      } catch (IOException i) {
	         i.printStackTrace();
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