package ua.com.tarvic.javaspring.hw3.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ua.com.tarvic.javaspring.hw3.dao.UserDAO;
import ua.com.tarvic.javaspring.hw3.models.Car;
import ua.com.tarvic.javaspring.hw3.models.User;
import ua.com.tarvic.javaspring.hw3.models.dto.CarDTO;

@Component
@AllArgsConstructor
public class CarUtil {

    private UserDAO userDAO;

    public Car convertDtoToEntity(CarDTO carDTO) {
        Car car = new Car();
        int id = carDTO.getId();
        if (id > 0) car.setId(id);
        car.setModel(carDTO.getModel());
        car.setProducer(carDTO.getProducer());
        car.setPower(carDTO.getPower());
        int userId = carDTO.getUserId();
        if (userId > 0) {
            car.setUser(userDAO.findById(userId).orElse(null));
        }
        return car;
    }

    public CarDTO convertEntityToDto(Car car) {
        CarDTO carDTO = new CarDTO();
        if (car != null) {
            int id = car.getId();
            if (id > 0) carDTO.setId(id);
            carDTO.setModel(car.getModel());
            carDTO.setProducer(car.getProducer());
            carDTO.setPower(car.getPower());
            User user = car.getUser();
            if (user != null) carDTO.setUserId(user.getId());
        }
        return carDTO;
    }
}
