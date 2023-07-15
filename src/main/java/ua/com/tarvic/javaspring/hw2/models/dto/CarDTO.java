package ua.com.tarvic.javaspring.hw2.models.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CarDTO {
    @NotBlank(message = "value is required")
    private String model;

    @NotBlank(message = "value is required")
    private String producer;

    // Зробити валідацію полів power (power > 0 && power < 1100) і відповідні оробники
    @NotNull(message = "value is required")
    @Min(value = 1, message = "value must be greater then 0")
    @Max(value = 1099, message = "value must be less then 1100")
    private int power;
}
