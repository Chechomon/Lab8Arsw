/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.persistences;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

import org.apache.commons.dbcp2.*;
import org.springframework.stereotype.Component;

/**
 *
 * @author Sergio
 */
public class DataBaseConfig {

    private static BasicDataSource ds = new BasicDataSource();

    static {
        ds.setUrl("jdbc:postgresql://ec2-23-23-241-119.compute-1.amazonaws.com/daa7990odoqvqh?sslmode=require&user=flcglxtsqalmfn&password=712fc5dbbc3fe4bb670d8a2b397246b7e77b02bf6d905e36e6a9ddc5311001ea");
        ds.setUsername("flcglxtsqalmfn");
        ds.setPassword("712fc5dbbc3fe4bb670d8a2b397246b7e77b02bf6d905e36e6a9ddc5311001ea");
        ds.setMinIdle(1);
        ds.setMaxIdle(5);
    }

    public BasicDataSource getDataSource() {
        return ds;
    }
}
