package org.excercise.Exception;

	
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {
 	/* @ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> exceptionHandler() {		
		ExceptionResponse error = new ExceptionResponse("CofeeShopServicesApi","Invalid attribute name or invalid attribute case");
		return new ResponseEntity<ExceptionResponse>(error, HttpStatus.PARTIAL_CONTENT);
	} */
 	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFound(ResourceNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setCode("Not Found");
        response.setMessage(ex.getMessage());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }
 	 @ExceptionHandler(MethodArgumentNotValidException.class)
     public ResponseEntity<ExceptionResponse> invalidInput(MethodArgumentNotValidException ex) {
         ExceptionResponse response = new ExceptionResponse();
         response.setCode("Validation Error");
         response.setMessage(ex.getMessage());
         return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
     }

}
