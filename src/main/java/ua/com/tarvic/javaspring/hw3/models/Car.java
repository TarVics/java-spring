package ua.com.tarvic.javaspring.hw3.models;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ua.com.tarvic.javaspring.hw3.models.views.Views;


@Entity
@NoArgsConstructor
@Data
public class Car {
    @JsonView({Views.Public.class})
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonView({Views.Public.class})
    private String model;

    @JsonView({Views.Public.class})
    private String producer;

    @JsonView({Views.Public.class})
    private int power;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_USER"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonView({Views.Public.class})
    private User user;
}
