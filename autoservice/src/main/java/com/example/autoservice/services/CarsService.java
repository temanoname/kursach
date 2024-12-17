package com.example.autoservice.services;

import com.example.autoservice.models.Cars;
import com.example.autoservice.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarsService {

    private final CarRepository carsRepository;

    public CarsService(CarRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    public List<Cars> findAll() {
        return carsRepository.findAll();
    }

    public Optional<Cars> findById(Long id) {
        return carsRepository.findById(id);
    }

    public Cars saveCar(Cars car) {
        return carsRepository.save(car);
    }

    public Cars updateCar(Long id, Cars updatedCar) {
        return carsRepository.findById(id)
                .map(car -> {
                    car.setBrand(updatedCar.getBrand());
                    car.setModel(updatedCar.getModel());
                    car.setYear(updatedCar.getYear());
                    car.setEngine_volume(updatedCar.getEngine_volume());
                    car.setMileage(updatedCar.getMileage());
                    car.setUsers(updatedCar.getUsers());
                    return carsRepository.save(car);
                }).orElseThrow(() -> new RuntimeException("Car not found with ID: " + id));
    }

    public void deleteCar(Long id) {
        carsRepository.deleteById(id);
    }
}
