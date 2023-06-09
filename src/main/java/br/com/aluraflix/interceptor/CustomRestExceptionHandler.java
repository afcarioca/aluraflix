package br.com.aluraflix.interceptor;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


@ControllerAdvice
public class CustomRestExceptionHandler{


    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex) {
        
      
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        ApiError apiErrorMessage = new ApiError(HttpStatus.BAD_REQUEST, errors);
        return new ResponseEntity<>(apiErrorMessage, apiErrorMessage.getStatus());
    }

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<Object> handleNoSuchElement(
        NoSuchElementException ex) {
        
        ApiError apiErrorMessage = new ApiError(HttpStatus.NO_CONTENT,ex.getMessage());
        return new ResponseEntity<>(apiErrorMessage, apiErrorMessage.getStatus());
    }
    
    @ExceptionHandler(VideoNotRemoveException.class)
    protected ResponseEntity<Object> handleVideoNotRemove(
        VideoNotRemoveException ex) {
        
        ApiError apiErrorMessage = new ApiError(HttpStatus.NOT_FOUND,ex.getMessage());
        return new ResponseEntity<>(apiErrorMessage, apiErrorMessage.getStatus());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(
        MethodArgumentTypeMismatchException ex) {
        
        String error = "Parâmetro do "+ex.getName()+" inválido";
       
        ApiError apiErrorMessage = new ApiError(HttpStatus.BAD_REQUEST, error);
        return new ResponseEntity<>(apiErrorMessage, apiErrorMessage.getStatus());
    }
    @ExceptionHandler(SQLException.class)
    protected ResponseEntity<Object> handleSQL(
        SQLException ex) {
        
        String erro = ex.getMessage()+": "+ex.getErrorCode();
        ApiError apiErrorMessage = new ApiError(HttpStatus.BAD_REQUEST, erro);
        return new ResponseEntity<>(apiErrorMessage, apiErrorMessage.getStatus());
    }


    @ExceptionHandler(VideoExistenceException.class)
    protected ResponseEntity<Object> handleVideoExistence(
        VideoExistenceException ex) {
        
        ApiError apiErrorMessage = new ApiError(HttpStatus.CONFLICT, "Vídeo já cadastrado");
        return new ResponseEntity<>(apiErrorMessage, apiErrorMessage.getStatus());
    }
}
