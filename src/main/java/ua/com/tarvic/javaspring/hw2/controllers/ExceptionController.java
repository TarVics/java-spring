package ua.com.tarvic.javaspring.hw2.controllers;

import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ua.com.tarvic.javaspring.hw2.models.dto.CarFieldExceptionsDTO;


@AllArgsConstructor
@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CarFieldExceptionsDTO fieldException(MethodArgumentNotValidException ex) {
//        System.out.println(ex);
        //return ex.getMessage();
        //return ex.getFieldError().getDefaultMessage();
        //ex.getBody().getDetail();

        Map<String, String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField, fieldError -> {
                            String message = fieldError.getDefaultMessage();
                            return message != null ? message : "";
                        })
                );

        return new CarFieldExceptionsDTO(ex.getBody().getStatus(), errors);
    }
}
