package ua.com.tarvic.javaspring.hw3.models;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.com.tarvic.javaspring.hw3.models.views.Views;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Public.class)
    private int id;

    @Column(nullable = false)
    @JsonView(Views.Public.class)
    private String name;

    @Column(nullable = false)
    @JsonView(Views.Public.class)
    private String email;

    @JsonView(Views.Public.class)
    private String avatar;

    private boolean isActivated = false;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Car> cars;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}