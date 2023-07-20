package ua.com.tarvic.javaspring.hw3.controllers;

import lombok.AllArgsConstructor;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ua.com.tarvic.javaspring.hw3.models.dto.FieldExceptionsDTO;

import java.util.Map;
import java.util.stream.Collectors;


@AllArgsConstructor
@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public FieldExceptionsDTO fieldException(MethodArgumentNotValidException ex) {
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

        return new FieldExceptionsDTO(ex.getBody().getStatus(), errors);
    }
}
