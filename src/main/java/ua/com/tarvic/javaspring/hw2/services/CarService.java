package ua.com.tarvic.javaspring.hw2.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import ua.com.tarvic.javaspring.hw2.dao.CarDAO;
import ua.com.tarvic.javaspring.hw2.models.Car;
import ua.com.tarvic.javaspring.hw2.models.dto.CarDTO;
import ua.com.tarvic.javaspring.hw2.utils.CarUtil;

import java.util.List;

@Service
@AllArgsConstructor
public class CarService {
    private CarDAO carDAO;
    private CarUtil carUtil;

    public List<Car> findAll() {
        return carDAO.findAll();
    }

    public Car findById(int id) {
        return carDAO.findById(id).orElse(null);
    }

    public Car save(CarDTO carDTO) {
        Car car = carUtil.convertDtoToEntity(carDTO);
        return carDAO.save(car);
    }

    public Car deleteById(int id) {
        Car car = findById(id);
        if (car != null) carDAO.deleteById(id);
        return car;
    }

    public List<Car> findByPower(int value) {
        return carDAO.findCarsByPower(value);
    }

    public List<Car> findByProducer(String value) {
        return carDAO.findCarsByProducer(value);
    }

}
