package online.exceptions;

import online.db.model.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleMethodArgumentNotValidException(RuntimeException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST);
        exceptionResponse.setMessage(e.getMessage());
        return exceptionResponse;
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleAnyExceptions(Exception e) {
        e.printStackTrace();
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        exceptionResponse.setMessage(e.getMessage());
        return exceptionResponse;
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ExceptionResponse handleBadCredentialsException(BadCredentialsException badCredentialsException) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setStatus(HttpStatus.UNAUTHORIZED);
        exceptionResponse.setMessage(badCredentialsException.getMessage());
        return exceptionResponse;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ExceptionResponse handleBadCredentialsException(ConstraintViolationException constraintViolationException) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST);
        exceptionResponse.setMessage(constraintViolationException.getMessage());
        return exceptionResponse;
    }
}
