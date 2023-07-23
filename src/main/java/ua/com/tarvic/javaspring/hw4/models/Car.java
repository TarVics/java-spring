package ua.com.tarvic.javaspring.hw4.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@NoArgsConstructor
@Data
public class Car {
    @Id
    private String id;

    private String model;

    private String producer;

    private int power;
}
