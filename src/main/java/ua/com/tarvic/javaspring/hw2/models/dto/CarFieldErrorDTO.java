package ua.com.tarvic.javaspring.hw2.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarFieldErrorDTO {
    private String field;
    private String error;
}
