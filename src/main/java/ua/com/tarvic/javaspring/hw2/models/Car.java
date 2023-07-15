package ua.com.tarvic.javaspring.hw2.models;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import ua.com.tarvic.javaspring.hw2.models.views.Views;

@Entity
@NoArgsConstructor
@Data
public class Car {
    @JsonView(Views.Level1.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonView({Views.Level1.class, Views.Level2.class, Views.Level3.class})
    private String model;

    @JsonView({Views.Level1.class, Views.Level2.class, Views.Level3.class})
    private String producer;

    @JsonView({Views.Level1.class, Views.Level2.class})
    private int power;
}
