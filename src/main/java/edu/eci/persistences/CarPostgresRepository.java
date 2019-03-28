package edu.eci.persistences;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import edu.eci.models.Car;
import edu.eci.models.User;
import edu.eci.persistences.repositories.ICarRepository;

@Component
@Qualifier("CarPostgresRepository")
public class CarPostgresRepository implements ICarRepository{

	@Autowired
	private DataBaseConfig db;
	
	@Override
	public List<Car> findAll() {
		String query = "SELECT * FROM cars";
        List<Car> cars=new ArrayList<>();

        try(Connection connection = db.getDataSource().getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Car car= new Car();
                car.setBrand(rs.getString("brand"));
                car.setLicencePlate(rs.getString("licence"));
                cars.add(car);
            }
            return cars;
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
	}

	@Override
	public Car find(String licence) {
		String query = "SELECT * FROM cars WHERE licence='"+licence+"'";
        Car car= new Car();

        try(Connection connection = db.getDataSource().getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                car.setBrand(rs.getString("brand"));
                car.setLicencePlate(rs.getString("licence"));
                return car;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
	}

	@Override
	public String save(Car entity) {
		String sentece= "INSERT INTO cars VALUES ('"+entity.getBrand()+"','"+entity.getLicencePlate()+"');";
    	try(Connection connection = db.getDataSource().getConnection()){
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sentece);
            
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    	return entity.getLicencePlate();
	}

	@Override
	public void update(Car entity) {
		String sentece="update cars set brand='"+entity.getBrand()+"' where licence='"+entity.getLicencePlate()+"';";
    	try(Connection connection = db.getDataSource().getConnection()){
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sentece);
            
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
		
	}

	@Override
	public void delete(String o) {
		String sentece="delete from cars where licence='"+o+"';";
    	try(Connection connection = db.getDataSource().getConnection()){
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sentece);
            
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
		
	}

	@Override
	public void remove(String id) {
		String sentece="delete from cars where licence='"+id+"';";
    	try(Connection connection = db.getDataSource().getConnection()){
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sentece);
            
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
		
	}

}
