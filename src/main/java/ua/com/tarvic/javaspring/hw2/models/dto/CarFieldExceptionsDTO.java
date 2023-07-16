package ua.com.tarvic.javaspring.hw2.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class CarFieldExceptionsDTO {
    private int code;
    private Map<String, String> errors;
}
