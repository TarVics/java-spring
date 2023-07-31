package ua.com.tarvic.javaspring.security.jwt.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ua.com.tarvic.javaspring.security.jwt.models.ExceptionDTO;

import java.sql.SQLException;


@AllArgsConstructor
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ExceptionDTO> fieldException(SQLException ex) {
//        System.out.println(ex);
        //return ex.getMessage();
        //return ex.getFieldError().getDefaultMessage();
        //ex.getBody().getDetail();
        return new ResponseEntity<>(new ExceptionDTO(ex.getErrorCode(), ex.getMessage()), HttpStatus.FORBIDDEN);
    }
}
