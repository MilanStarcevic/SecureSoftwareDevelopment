package com.zuehlke.securesoftwaredevelopment.repository;

import com.zuehlke.securesoftwaredevelopment.domain.Service;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ServiceRepository {

    private final DataSource dataSource;

    public ServiceRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Service> getScheduled(String columns) {
        List<Service> services = new ArrayList<>();
        String sqlQuery = "SELECT ss.id, " + columns + " FROM scheduled_services ss INNER JOIN persons ON ss.personId = persons.id INNER JOIN cars ON ss.carId = cars.id";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet rs = statement.executeQuery();
            String[] columnNames = columns.split(",");
            while (rs.next()) {
                Map<String, String> properties = new HashMap<>();
                int id = rs.getInt(1);
                for (String column : columnNames) {
                    String value = rs.getString(column);
                    properties.put(column, value);
                }
                services.add(new Service(id, properties));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return services;
    }
}
