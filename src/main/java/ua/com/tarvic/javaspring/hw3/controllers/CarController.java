package ua.com.tarvic.javaspring.hw3.controllers;

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

/*
Беремо проєкт з автомобілями, котрий робили до цього моменту
1. Виносимо логіку у сервісний прошарок.
2. Додаємо відправку листа на пошту з повідомленням реєстрації нової автівки
*/


import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ua.com.tarvic.javaspring.hw3.models.Car;
import ua.com.tarvic.javaspring.hw3.models.dto.CarDTO;
import ua.com.tarvic.javaspring.hw3.models.views.Views;
import ua.com.tarvic.javaspring.hw3.services.CarService;
import ua.com.tarvic.javaspring.hw3.utils.CarUtil;

import java.util.List;

@RestController
@RequestMapping(value = "/cars")
public class CarController {
    private final CarService carService;
    private final CarUtil carUtil;

    public CarController(
            @Qualifier("CarService.hw3.v1")
            CarService carService,
            CarUtil carUtil
    ) {
        this.carService = carService;
        this.carUtil = carUtil;
    }

    //get /cars
    @GetMapping()
    @JsonView(Views.Public.class)
    public ResponseEntity<List<Car>> findAll() {
        return new ResponseEntity<>(carService.findAll(), HttpStatus.OK);
    }

    //get /cars/{id}
    @GetMapping("/{id}")
    @JsonView(Views.Public.class)
    public ResponseEntity<Car> findById(@PathVariable int id) {
        Car car = carService.findById(id);
        HttpStatus status = car == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(car, status);
    }

    //post /cars
    @PostMapping()
    @JsonView(Views.Public.class)
    public ResponseEntity<Car> save(@RequestBody @Valid CarDTO carDTO) {
        System.out.println(carDTO);

        Car car = carUtil.convertDtoToEntity(carDTO);
        Car saved = carService.save(car);

        return new ResponseEntity<>(saved,
            carDTO.getId() == 0 ? HttpStatus.CREATED : HttpStatus.OK);
    }

    //delete /cars/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        Boolean deleted = carService.deleteById(id);
        HttpStatus status = deleted ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(status.getReasonPhrase(), status);
    }

    //get cars/power/{value} (знайти всі по потужності) ()
    @GetMapping("/power/{value}")
    @JsonView(Views.Public.class)
    public ResponseEntity<List<Car>> findByPower(@PathVariable int value) {
        return new ResponseEntity<>(carService.findByPower(value), HttpStatus.OK);
    }

    //get cars/producer/{value} (знайти всі по виробнику)
    @GetMapping("/producer/{value}")
    @JsonView(Views.Public.class)
    public ResponseEntity<List<Car>> findByProducer(@PathVariable String value) {
        return new ResponseEntity<>(carService.findByProducer(value), HttpStatus.OK);
    }

}
