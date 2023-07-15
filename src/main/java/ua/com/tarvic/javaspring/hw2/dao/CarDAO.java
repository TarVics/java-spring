package ua.com.tarvic.javaspring.hw2.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.com.tarvic.javaspring.hw2.models.Car;

import java.util.List;

public interface CarDAO extends JpaRepository<Car, Integer> {
    List<Car> findCarsByProducer(String producer);
    List<Car> findCarsByPower(int power);
}
