/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
