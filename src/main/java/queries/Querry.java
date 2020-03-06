package queries;

import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("serial")
public class Querry  implements java.io.Serializable  {

	private String id; 
	private String request;
	private String label;
	private String description; 
	private Boolean reasoning; 


	public Querry(String pid, String plabel,String prequest, String pdescription, Boolean preasoning) { // Querry constructor
		id=pid; label=plabel; description=pdescription; request=prequest; reasoning=preasoning;
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
	
	public String getLabel() {return label;}
	
	public Boolean isReasonong() {return reasoning;}
		
	public String toString() {
		return id+"\t"+label+"\t"+request+"\t"+description;
	}
}