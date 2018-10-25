package errors;

public class SAXException extends Exception {
	public SAXException(String message) {
		super(message);
	}
	
	public SAXException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
