package ua.com.tarvic.javaspring.security.jwt.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionDTO {
    private int code;
    private String error;
}
