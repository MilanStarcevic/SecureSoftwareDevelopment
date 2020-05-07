package com.zuehlke.securesoftwaredevelopment.repository;

import com.zuehlke.securesoftwaredevelopment.domain.ScheduleService;
import com.zuehlke.securesoftwaredevelopment.domain.Service;
import com.zuehlke.securesoftwaredevelopment.domain.ServiceTicket;
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

    public List<Service> getScheduled(String columns) {
        List<Service> services = new ArrayList<>();
        String sqlQuery = "SELECT ss.id, ss.personId, " + columns + " FROM scheduled_services ss INNER JOIN persons ON ss.personId = persons.id";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet rs = statement.executeQuery();
            int columnCount = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                List<String> properties = new ArrayList<>();
                int id = rs.getInt(1);
                int personId = rs.getInt(2);
                for (int i = 3; i <= columnCount; i++) {
                    String value = rs.getString(i);
                    properties.add(value);
                }
                services.add(new Service(id, personId, properties));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return services;
    }

    public void insertScheduledService(int userId, ScheduleService scheduleService, String tableName) throws SQLException {
        String sqlQuery = "insert into " + tableName + " (personId, carModel, date, remark) values (?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, userId);
            statement.setString(2, scheduleService.getCarModel());
            statement.setDate(3, Date.valueOf(scheduleService.getDate()));
            statement.setString(4, scheduleService.getRemark());
            statement.executeUpdate();
        }
    }

    public void insertScheduledService(int userId, ScheduleService scheduleService) throws SQLException {
        insertScheduledService(userId, scheduleService, "scheduled_services");
    }

    public void updateScheduledService(ServiceTicket serviceTicket) throws SQLException {
        String sqlQuery = "update scheduled_services set ticketNumber = ?, time = ? where id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, serviceTicket.getTicketNumber().toString());
            statement.setTime(2, Time.valueOf(serviceTicket.getTime() + ":00"));
            statement.setInt(3, serviceTicket.getId());
            statement.executeUpdate();
        }
    }
}
