package ua.com.tarvic.javaspring.hw3.models.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int id;

    @NotBlank(message = "value is required")
    @Size(min = 3, message = "value must be at least 3 chars")
    @Size(max = 255, message = "value is too long")
    private String name;

    @NotBlank(message = "value is required")
    @Email(message = "invalid value format")
    private String email;

    private MultipartFile avatar;
}