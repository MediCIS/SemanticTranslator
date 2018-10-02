package querries;

import org.json.JSONException;
import org.json.JSONObject;

public class Querry implements java.io.Serializable {

	private String id; 
	private String request;
	private String label;
	private String description; 

	public Querry(String pid, String plabel,String prequest, String pdescription) { // Querry constructor
		id=pid; label=plabel; description=pdescription; request=prequest;
	}
	
	public String getJSON() {						//return JSON informations of the querry
		JSONObject obj = new JSONObject();
		try {
			obj.put("id", id);
			obj.put("label", label);
			obj.put("description", description);
		} catch (JSONException e) {e.printStackTrace();}
		return obj.toString();
	}
	
	public String getId() {return id;}
	
	public String getRequest() {return request;}
}