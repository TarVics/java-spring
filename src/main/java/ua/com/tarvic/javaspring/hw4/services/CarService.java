package ua.com.tarvic.javaspring.hw4.services;

import org.springframework.stereotype.Service;
import ua.com.tarvic.javaspring.hw4.models.Car;

import java.util.List;

@Service
public interface CarService {
    List<Car> findAll();

    Car findById(String id);

    Car save(Car car);

    Boolean deleteById(String id) ;

}
