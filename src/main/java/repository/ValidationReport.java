package repository;

import org.json.JSONException;
import org.json.JSONObject;

public class ValidationReport {
    private final boolean isValid;
    private final String message;

    public ValidationReport(boolean isValid, String message) {
        this.isValid = isValid;
        this.message = message;
    }

    public boolean isValid() {return isValid;}

    public String getMessage() {return message;}
    
    public String getJson() {
		JSONObject obj = new JSONObject();
		
		try {
			obj.put("isValid", isValid);
			obj.put("message", message);
		} catch (JSONException e) {e.printStackTrace();}

		return obj.toString();
    }
}