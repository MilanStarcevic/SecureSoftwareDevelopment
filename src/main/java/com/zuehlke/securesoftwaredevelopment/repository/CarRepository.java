package com.zuehlke.securesoftwaredevelopment.repository;

import com.zuehlke.securesoftwaredevelopment.config.AuditLogger;
import com.zuehlke.securesoftwaredevelopment.domain.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CarRepository {

    private static final Logger LOG = LoggerFactory.getLogger(CarRepository.class);
    private static final AuditLogger auditLogger = AuditLogger.getAuditLogger(PersonRepository.class);

    private static final String CARS_TABLE = "cars";
    private DataSource dataSource;

    public CarRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Car findById(String id) {
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

    public void update(int id, Car carUpdate) throws SQLException {
        Car carFromDb = findById(String.valueOf(id));
        String manufacturer = carUpdate.getManufacturer() != null ? carUpdate.getManufacturer() : carFromDb.getManufacturer();

        String sqlQuery = "UPDATE cars SET price = ?, wholesalePrice = ?, model = ?, manufacturer='" + manufacturer + "' WHERE id=" + id;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            Double price = carUpdate.getPrice() != null ? carUpdate.getPrice() : carFromDb.getPrice();
            Double wholesalePrice = carUpdate.getWholesalePrice() != null ? carUpdate.getWholesalePrice() : carFromDb.getWholesalePrice();
            String model = carUpdate.getModel() != null ? carUpdate.getModel() : carFromDb.getModel();
            statement.setDouble(1, price);
            statement.setDouble(2, wholesalePrice);
            statement.setString(3, model);
            statement.executeUpdate();
        }
    }

    /**
     * @param searchQuery model or manufacturer
     * @return list of cars or empty in case of exception
     */
    public List<Car> search(String searchQuery) throws SQLException {
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
        }
        return cars;
    }

    public List<Car> getAll() {
        List<Car> cars = new ArrayList<>();
        String sqlQuery = "SELECT id, price, wholesalePrice, model, manufacturer FROM " + CARS_TABLE;
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
