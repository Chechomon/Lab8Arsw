package edu.eci.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import edu.eci.models.Car;
import edu.eci.persistences.repositories.ICarRepository;
import edu.eci.persistences.repositories.IUserRepository;
import edu.eci.services.contracts.ICarServices;

@Component
public class CarServices implements ICarServices {
	
	 @Autowired
	 @Qualifier("CarPostgresRepository")
	 private ICarRepository carRepository;

	@Override
	public List<Car> list() {
		return carRepository.findAll();		
	}

	@Override
	public Car create(Car car) {
		if(null == car.getLicencePlate())
            throw new RuntimeException("Id invalid");
        else if(carRepository.find(car.getLicencePlate()) != null)
            throw new RuntimeException("The car exists");
        else
            carRepository.save(car);
        return car;
	}

	@Override
	public Car getByLicence(String id) {
		return carRepository.find(id);
	}

	@Override
	public Car get(String name) {
		return carRepository.find(name);
	}

	@Override
	public void delete(String car) {
		carRepository.delete(car);
		
	}

	@Override
	public void update(Car car) {
		carRepository.update(car);
	}

}
