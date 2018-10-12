package repository;


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

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {

	protected static final Logger logger = LoggerFactory.getLogger(repository.OntologyPopulator.class);	//logger
	
    @ExceptionHandler(StardogException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void StarDogExceptionHandler() {
    	logger.error("StardogException");
    }
    
    @ExceptionHandler(TupleQueryResultHandlerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void TupleQueryResultHandlerExceptionHandler() {
    	logger.error("TupleQueryResultHandlerException");
    }
    
    @ExceptionHandler(QueryEvaluationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void QueryEvaluationExceptionHandler() {
    	logger.error("QueryEvaluationException");
    }
    
    @ExceptionHandler(UnsupportedQueryResultFormatException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void UnsupportedQueryResultFormatExceptionHandler() {
    	logger.error("UnsupportedQueryResultFormatException");
    }
    
    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void IOExceptionHandler() {
    	logger.error("IOException");
    }
   
    @ExceptionHandler(DicomException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void DicomExceptionHandler() {
    	logger.error("DicomException");
    }
    
    @ExceptionHandler(FileNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void FileNotFoundExceptionHandler() {
    	logger.error("FileNotFoundException");
    }
    
    @ExceptionHandler(JAXBException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String JAXBExceptionHandler() {
    	logger.error("JAXBException");
    	return new ValidationReport(false, "JAXBException").getJson();
    }
    
    @ExceptionHandler(SAXException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String SAXExceptionHandler() {
    	logger.error("SAXException");
    	return new ValidationReport(false, "SAXException").getJson();    }
}