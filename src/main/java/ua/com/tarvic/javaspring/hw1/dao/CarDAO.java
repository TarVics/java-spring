package ua.com.tarvic.javaspring.hw1.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.com.tarvic.javaspring.hw1.models.Car;


public interface CarDAO extends JpaRepository<Car, Integer> {

/*
    @Query(value = "select c.* from car c where c.power = :power", nativeQuery = true)
    List<Car> byPower(int power);
*/

    @Query(value = "select c from Car c where c.power = :power")
    List<Car> byPower(int power);

//    List<Car> findCarsByPower(@Param("power") int power_);

    // https://docs.spring.io/spring-data/jpa/docs/1.5.0.RELEASE/reference/html/jpa.repositories.html
    List<Car> findCarsByProducer(String value);
}
