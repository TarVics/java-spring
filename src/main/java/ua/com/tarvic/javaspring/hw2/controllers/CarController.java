package ua.com.tarvic.javaspring.hw2.controllers;

/*
Створити модель
Car
    id
    model
    producer
    power

реалізувати запити
get /cars
get /cars/{id}
post /cars
delete /cars/{id}
get cars/power/{value} (знайти всі по потужності) ()
get cars/producer/{value} (знайти всі по виробнику)


Зробити валідацію полів power (power > 0 && power < 1100) і відповідні оробники
Переробити всі методи контролера, щоб повертати response entity з відповідними статусами

Зробити 3 рівня відображення
Level1 - id model producer power (для endpoint /cars/{id})
Level2 - model producer power ( для endpoint /cars/power, /cars/producer)
Level3 - model producer (для endpoint /cars)

*/

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ua.com.tarvic.javaspring.hw2.models.Car;
import ua.com.tarvic.javaspring.hw2.models.dto.CarDTO;
import ua.com.tarvic.javaspring.hw2.models.views.Views;
import ua.com.tarvic.javaspring.hw2.services.CarService;

import java.util.List;

@RestController
@RequestMapping(value = "/cars")
@AllArgsConstructor
public class CarController {
    private CarService carService;

    //get /cars
    @GetMapping()
    @JsonView(Views.Level3.class)
    public ResponseEntity<List<Car>> findAll() {
        return new ResponseEntity<>(carService.findAll(), HttpStatus.OK);
    }

    //get /cars/{id}
    @GetMapping("/{id}")
    @JsonView(Views.Level1.class)
    public ResponseEntity<Car> findById(@PathVariable int id) {
        Car car = carService.findById(id);
        HttpStatus status = car == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(car, status);
    }

    //post /cars
    @PostMapping()
    public ResponseEntity<Car> save(@RequestBody @Valid CarDTO carDTO) {
        return new ResponseEntity<>(carService.save(carDTO), HttpStatus.CREATED);
    }

    //delete /cars/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        Car car = carService.deleteById(id);
        HttpStatus status = car == null ? HttpStatus.NOT_FOUND : HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(status.getReasonPhrase(), status);
    }

    //get cars/power/{value} (знайти всі по потужності) ()
    @GetMapping("/power/{value}")
    @JsonView(Views.Level2.class)
    public ResponseEntity<List<Car>> findByPower(@PathVariable int value) {
        return new ResponseEntity<>(carService.findByPower(value), HttpStatus.OK);
    }

    //get cars/producer/{value} (знайти всі по виробнику)
    @GetMapping("/producer/{value}")
    @JsonView(Views.Level2.class)
    public ResponseEntity<List<Car>> findByProducer(@PathVariable String value) {
        return new ResponseEntity<>(carService.findByProducer(value), HttpStatus.OK);
    }

}
