package ua.com.tarvic.javaspring.hw2.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ua.com.tarvic.javaspring.hw2.models.dto.CarFieldErrorDTO;
import ua.com.tarvic.javaspring.hw2.models.dto.CarFieldExceptionsDTO;

import java.util.List;

@AllArgsConstructor
@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CarFieldExceptionsDTO fieldException(MethodArgumentNotValidException ex) {
//        System.out.println(ex);
        //return ex.getMessage();
        //return ex.getFieldError().getDefaultMessage();
        //ex.getBody().getDetail();
        List<CarFieldErrorDTO> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(error -> new CarFieldErrorDTO(
                        error.getField(),
                        error.getDefaultMessage())).toList();

        return new CarFieldExceptionsDTO(ex.getBody().getStatus(), errors);
    }
}
