package edu.eci.persistences;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.eci.models.User;
import edu.eci.persistences.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Qualifier("UserPostgresRepository")
public class UserPostgresRepository implements IUserRepository {

    private String dbUrl = null;

    @Autowired
    private DataBaseConfig db;

    @Override
    public User getUserByUserName(String userName) {
        return null;
    }

    @Override
    public List<User> findAll() {
        String query = "SELECT * FROM users";
        List<User> users=new ArrayList<>();

        try(Connection connection = db.getDataSource().getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                User user = new User();
                user.setName(rs.getString("name"));
                user.setId(UUID.fromString(rs.getString("id")));
                users.add(user);
            }
            return users;
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public User find(UUID id) {
    	String query = "SELECT * FROM users WHERE id='"+id+"'";
        User users= new User();

        try(Connection connection = db.getDataSource().getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                User user = new User();
                user.setName(rs.getString("name"));
                user.setId(UUID.fromString(rs.getString("id")));
                return users;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public UUID save(User entity) {
    	String sentece= "INSERT INTO users VALUES ('"+entity.getId()+"','"+entity.getName()+"');";
    	try(Connection connection = db.getDataSource().getConnection()){
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sentece);
            
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    	return entity.getId();
    }

    @Override
    public void update(User entity) {
    	String sentece="update users set name='"+entity.getName()+"' where id='"+entity.getId()+"';";
    	try(Connection connection = db.getDataSource().getConnection()){
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sentece);
            
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(UUID o) {
    	String sentece="delete from users where id='"+o+"';";
    	try(Connection connection = db.getDataSource().getConnection()){
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sentece);
            
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(UUID id) {
    	String sentece="delete from users where id='"+id+"';";
    	try(Connection connection = db.getDataSource().getConnection()){
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sentece);
            
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }


}
