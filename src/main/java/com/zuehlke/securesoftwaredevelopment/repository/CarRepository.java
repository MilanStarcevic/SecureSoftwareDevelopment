package com.zuehlke.securesoftwaredevelopment.repository;

import com.zuehlke.securesoftwaredevelopment.domain.Car;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CarRepository {

    private DataSource dataSource;

    public CarRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Car findById(int id) {
        String sqlQuery = "SELECT id, price, wholesalePrice, model, manufacturer FROM cars WHERE id=" + id;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sqlQuery)) {
            if (rs.next()) {
                return createCar(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(int id, Car car) {
        String sqlQuery = "UPDATE cars SET price = ?, wholesalePrice = ?, model = ?, manufacturer = ? WHERE id=" + id;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setDouble(1, car.getPrice());
            statement.setDouble(2, car.getWholesalePrice());
            statement.setString(3, car.getModel());
            statement.setString(4, car.getManufacturer());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                cars.add(createCar(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    public List<Car> getAll() {
        List<Car> cars = new ArrayList<>();
        String sqlQuery = "SELECT id, price, wholesalePrice, model, manufacturer FROM cars";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sqlQuery)) {
            while (rs.next()) {
                cars.add(createCar(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    private Car createCar(ResultSet rs) throws SQLException {
        int id = rs.getInt(1);
        double price = rs.getDouble(2);
        double wholesalePrice = rs.getDouble(3);
        String model = rs.getString(4);
        String manufacturer = rs.getString(5);
        return new Car(id, price, wholesalePrice, model, manufacturer);
    }
}
