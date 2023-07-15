package ua.com.tarvic.javaspring.hw1.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.tarvic.javaspring.hw1.dao.CarDAO;
import ua.com.tarvic.javaspring.hw1.models.Car;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cars")
@AllArgsConstructor
public class CarController {

    private CarDAO carDAO;
//    private CarExDAO carExDAO;

    //get /cars
    @GetMapping()
    public ResponseEntity<List<Car>> findAll() {
        return new ResponseEntity<>(carDAO.findAll(), HttpStatus.OK);
    }

    //get /cars/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Car> findById(@PathVariable int id) {
        Optional<Car> car = carDAO.findById(id);
        return new ResponseEntity<>(
                car.orElse(null),
                car.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    //post /cars
    @PostMapping()
    public ResponseEntity<Car> save(@RequestBody Car car) {
        return new ResponseEntity<>(carDAO.save(car), HttpStatus.CREATED);
    }

    //delete /cars/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id) {
        carDAO.deleteById(id);
    }

    //get cars/power/{value} (знайти всі по потужності)
    @GetMapping("/power/{value}")
    public ResponseEntity<List<Car>> findCarsByPower(@PathVariable int value) {
        return new ResponseEntity<>(carDAO.byPower(value), HttpStatus.OK);
//        return new ResponseEntity<>(carDAO.findCarsByPower(value), HttpStatus.OK);
//        return new ResponseEntity<>(carExDAO.findCarsByPower(value), HttpStatus.OK);
    }

    //get cars/producer/{value} (знайти всі по виробнику)
    @GetMapping("/producer/{value}")
    public ResponseEntity<List<Car>> findCarsByProducer(@PathVariable String value) {
        return new ResponseEntity<>(carDAO.findCarsByProducer(value), HttpStatus.OK);
    }
}
