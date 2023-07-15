package ua.com.tarvic.javaspring.hw2.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ua.com.tarvic.javaspring.hw2.models.Car;
import ua.com.tarvic.javaspring.hw2.models.dto.CarDTO;

@Component
@AllArgsConstructor
public class CarUtil {

    public Car convertDtoToEntity(CarDTO carDTO) {
        Car car = new Car();
        car.setModel(carDTO.getModel());
        car.setProducer(carDTO.getProducer());
        car.setPower(carDTO.getPower());
        return car;
    }

    public CarDTO convertEntityToDto(Car car) {
        return new CarDTO(car.getModel(), car.getProducer(), car.getPower());
    }
}
