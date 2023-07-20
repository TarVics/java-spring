package ua.com.tarvic.javaspring.hw3.models.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {
    private int id;

    @NotBlank(message = "value is required")
    private String model;

    @NotBlank(message = "value is required")
    private String producer;

    // Зробити валідацію полів power (power > 0 && power < 1100) і відповідні оробники
    @NotNull(message = "value is required")
    @Min(value = 1, message = "value must be greater then 0")
    @Max(value = 1099, message = "value must be less then 1100")
    private int power;

    private int userId;
}
