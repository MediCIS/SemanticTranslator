package errors;


import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.TupleQueryResultHandlerException;
import org.openrdf.query.resultio.UnsupportedQueryResultFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.xml.sax.SAXException;

import com.complexible.stardog.StardogException;
import com.pixelmed.dicom.DicomException;

import repository.ValidationReport;

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {

	protected static final Logger logger = LoggerFactory.getLogger(repository.OntologyPopulator.class);	//logger
	
    @ExceptionHandler(StardogException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Stardog Error")
    public void StarDogExceptionHandler() {
    	logger.error("StardogException");
    }
    
    @ExceptionHandler(TupleQueryResultHandlerException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error Tuple Query Result Handler Exception (error when converting querry result is converted to String)")
    public void TupleQueryResultHandlerExceptionHandler() {
    	logger.error("TupleQueryResultHandlerException");
    }
    
    @ExceptionHandler(QueryEvaluationException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Query Evaluation Exception (error when converting querry result is converted to String)")
    public void QueryEvaluationExceptionHandler() {
    	logger.error("QueryEvaluationException");
    }
    
    @ExceptionHandler(UnsupportedQueryResultFormatException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Unsupported Query Result Format Exception (error when converting querry result is converted to String)")
    public void UnsupportedQueryResultFormatExceptionHandler() {
    	logger.error("UnsupportedQueryResultFormatException");
    }
    
    @ExceptionHandler(IOException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "IOException (Error when Reading or Writing a file)")
    public void IOExceptionHandler() {
    	logger.error("IOException");
    }
   
    @ExceptionHandler(DicomException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Dicom Exception (Error with Dicom datas)")
    public void DicomExceptionHandler() {
    	logger.error("DicomException");
    }
    
    @ExceptionHandler(FileNotFoundException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "File Not Found Exception")
    public void FileNotFoundExceptionHandler() {
    	logger.error("FileNotFoundException");
    }
    
    @ExceptionHandler(JAXBException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "JAXB Exception (XML is not valid)")
    public String JAXBExceptionHandler() {
    	logger.error("JAXBException");
    	return new ValidationReport(false, "JAXBException").getJson();
    }
    
    @ExceptionHandler(SAXException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "SAX Exception (XML is not valid)")
    public String SAXExceptionHandler() {
    	logger.error("SAXException");
    	return new ValidationReport(false, "SAXException").getJson();
    }
    
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Request refused for Security Reason")
    public void BadRequestHandler() {
    	logger.error("BadRequest");
    }
}