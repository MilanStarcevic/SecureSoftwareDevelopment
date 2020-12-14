package com.zuehlke.securesoftwaredevelopment.repository;

import com.zuehlke.securesoftwaredevelopment.domain.ScheduleService;
import com.zuehlke.securesoftwaredevelopment.domain.Service;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ServiceRepository {

    private final DataSource dataSource;

    public ServiceRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Service> getScheduled() {
        List<Service> services = new ArrayList<>();
        String sqlQuery = "SELECT ss.id, ss.personId, firstName, lastName, carModel, date, email, voucherId FROM scheduled_services ss INNER JOIN persons ON ss.personId = persons.id";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                services.add(new Service(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return services;
    }

    public void insertScheduledService(int userId, ScheduleService scheduleService, Integer voucherId, String tableName) throws SQLException {
        String sqlQuery = "insert into " + tableName + " (personId, carModel, date, email, voucherId) values (?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, userId);
            statement.setString(2, scheduleService.getCarModel());
            statement.setDate(3, Date.valueOf(scheduleService.getDate()));
            statement.setString(4, scheduleService.getEmail());
            statement.setInt(5, voucherId);
            statement.executeUpdate();
        }
    }

    public void insertScheduledService(int userId, ScheduleService scheduleService, Integer voucherId) throws SQLException {
        insertScheduledService(userId, scheduleService, voucherId, "scheduled_services");
    }
}
