package ua.com.tarvic.javaspring.security.jwt.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseError {
   private int code;
   private String error;
   private String message;
   private String path;
}
