package ua.com.tarvic.javaspring.hw4.controllers;

/*
Створити модель та реалізувати операції використовуючи mongodb
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

*/


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ua.com.tarvic.javaspring.hw4.models.Car;
import ua.com.tarvic.javaspring.hw4.models.dto.CarDTO;
import ua.com.tarvic.javaspring.hw4.services.CarService;
import ua.com.tarvic.javaspring.hw4.utils.CarUtil;

import java.util.List;

@RestController
@RequestMapping(value = "/cars")
public class CarController {
    private final CarService carService;
    private final CarUtil carUtil;

    public CarController(
            @Qualifier("CarService.hw4.v1")
            CarService carService,
            CarUtil carUtil
    ) {
        this.carService = carService;
        this.carUtil = carUtil;
    }

    //get /cars
    @GetMapping()
    public ResponseEntity<List<Car>> findAll() {
        return new ResponseEntity<>(carService.findAll(), HttpStatus.OK);
    }

    //get /cars/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Car> findById(@PathVariable String id) {
        Car car = carService.findById(id);
        HttpStatus status = car == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(car, status);
    }

    //post /cars
    @PostMapping()
    public ResponseEntity<Car> save(@RequestBody @Valid CarDTO carDTO) {
        System.out.println(carDTO);

        Car car = carUtil.convertDtoToEntity(carDTO);
        Car saved = carService.save(car);

        String id = carDTO.getId();
        System.out.println(id == null);

        return new ResponseEntity<>(saved,
            id == null || id.length() == 0 ? HttpStatus.CREATED : HttpStatus.OK);
    }

    //delete /cars/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        Boolean deleted = carService.deleteById(id);
        HttpStatus status = deleted ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(status.getReasonPhrase(), status);
    }

}
