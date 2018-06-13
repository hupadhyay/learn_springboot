package in.himtech.boot.learn.exp;

import java.sql.Timestamp;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This class is used to send customized exception message/validation message to client.
 * @author Himanshu
 *
 */
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionMessage> handleAllException(Exception exp, WebRequest request) {
		
		ExceptionMessage expMessage = new ExceptionMessage(new Timestamp(System.currentTimeMillis()), "Bharat Mata Ki Jai", request.getDescription(false), exp.getMessage());
		
		return new ResponseEntity<ExceptionMessage>(expMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(EntityNotFound.class)
	public ResponseEntity<ExceptionMessage> handleNotFoundException(Exception exp, WebRequest request) {
		
		ExceptionMessage expMessage = new ExceptionMessage(new Timestamp(System.currentTimeMillis()), "Bharat Mata Ki Jai", request.getDescription(false), exp.getMessage());
		
		return new ResponseEntity<ExceptionMessage>(expMessage, HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Customizing validation message 
	 * @Valid ...
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionMessage expMessage = new ExceptionMessage(new Timestamp(System.currentTimeMillis()), "Validation Violation", request.getDescription(false), ex.getBindingResult().toString());
		
		return new ResponseEntity<Object>(expMessage, HttpStatus.BAD_REQUEST);
	}
	

}
