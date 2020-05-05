package com.zuehlke.securesoftwaredevelopment.repository;

import com.zuehlke.securesoftwaredevelopment.domain.Service;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            int columnCount = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                List<String> properties = new ArrayList<>();
                int id = rs.getInt(1);
                for (int i = 2; i <= columnCount; i++) {
                    String value = rs.getString(i);
                    properties.add(value);
                }
                services.add(new Service(id, properties));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return services;
    }
}
