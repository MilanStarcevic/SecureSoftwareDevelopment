package com.zuehlke.securesoftwaredevelopment.repository;

import com.zuehlke.securesoftwaredevelopment.domain.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleRepository {

    private static final Logger LOG = LoggerFactory.getLogger(RoleRepository.class);

    private final DataSource dataSource;

    public RoleRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Role> findByUserId(int userId) {
        List<Role> roles = new ArrayList<>();
        String query = "SELECT id, name FROM roles WHERE id IN (SELECT roleId FROM user_to_roles WHERE userId=" + userId + ")";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                roles.add(new Role(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }
}
