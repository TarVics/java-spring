package ua.com.tarvic.javaspring.hw2.services;

import org.springframework.stereotype.Service;
import ua.com.tarvic.javaspring.hw2.models.Car;
import ua.com.tarvic.javaspring.hw2.models.dto.CarDTO;

import java.util.List;

@Service
public interface CarService {
    List<Car> findAll();

    Car findById(int id);

    Car save(CarDTO carDTO);

    Car deleteById(int id) ;

    List<Car> findByPower(int value);

    List<Car> findByProducer(String value);
}
