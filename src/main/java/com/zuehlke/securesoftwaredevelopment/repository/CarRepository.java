package com.zuehlke.securesoftwaredevelopment.repository;

import com.zuehlke.securesoftwaredevelopment.domain.Car;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CarRepository {

    private DataSource dataSource;

    public CarRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * @param searchQuery model or manufacturer
     * @return list of cars or empty in case of exception
     */
    public List<Car> search(String searchQuery) {
        List<Car> cars = new ArrayList<>();
        String sqlQuery =
                "SELECT id, price, wholesalePrice, model, manufacturer FROM cars WHERE UPPER(model) LIKE UPPER('%" + searchQuery + "%')" +
                        "OR UPPER(manufacturer) LIKE UPPER('%" + searchQuery + "%')";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sqlQuery)) {
            while (rs.next()) {
                int id = rs.getInt(1);
                double price = rs.getDouble(2);
                double wholesalePrice = rs.getDouble(3);
                String model = rs.getString(4);
                String manufacturer = rs.getString(5);
                cars.add(new Car(id, price, wholesalePrice, model, manufacturer));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }
}
