package edu.eci.services.contracts;

import java.util.List;
import java.util.UUID;

import edu.eci.models.Car;
import edu.eci.models.User;


public interface ICarServices {
	List<Car> list();
    Car create(Car car);
    Car getByLicence(String licence);
    Car get(String name);
    void delete(String car);
    void update(Car car);

}
