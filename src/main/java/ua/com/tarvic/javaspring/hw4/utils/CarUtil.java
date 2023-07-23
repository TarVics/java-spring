package ua.com.tarvic.javaspring.hw4.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ua.com.tarvic.javaspring.hw4.models.Car;
import ua.com.tarvic.javaspring.hw4.models.dto.CarDTO;

@Component
@AllArgsConstructor
public class CarUtil {

    public Car convertDtoToEntity(CarDTO carDTO) {
        Car car = new Car();
        String id = carDTO.getId();
        if (id != null && id.length() > 0) car.setId(id);
        car.setModel(carDTO.getModel());
        car.setProducer(carDTO.getProducer());
        car.setPower(carDTO.getPower());
        return car;
    }

    public CarDTO convertEntityToDto(Car car) {
        CarDTO carDTO = new CarDTO();
        if (car != null) {
            String id = car.getId();
            if (id != null && id.length() > 0) carDTO.setId(id);
            carDTO.setModel(car.getModel());
            carDTO.setProducer(car.getProducer());
            carDTO.setPower(car.getPower());
        }
        return carDTO;
    }
}
