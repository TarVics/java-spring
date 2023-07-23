package ua.com.tarvic.javaspring.hw4.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.tarvic.javaspring.hw4.models.Car;

public interface CarDAO extends MongoRepository<Car, String> {
}
