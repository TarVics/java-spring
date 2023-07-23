package ua.com.tarvic.javaspring.hw4.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.tarvic.javaspring.hw4.dao.CarDAO;
import ua.com.tarvic.javaspring.hw4.models.Car;

import java.util.List;

@Service("CarService.hw4.v1")
@AllArgsConstructor
public class CarServiceImpl implements CarService {
    private CarDAO carDAO;

    public List<Car> findAll() {
        return carDAO.findAll();
    }

    public Car findById(String id) {
        return carDAO.findById(id).orElse(null);
    }

    public Car save(Car car) {
        return carDAO.save(car);
    }

    public Boolean deleteById(String id) {
        boolean exists = carDAO.existsById(id);
        if (exists) carDAO.deleteById(id);
        return exists;
    }

}
