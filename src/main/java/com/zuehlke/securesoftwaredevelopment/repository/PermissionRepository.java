package com.zuehlke.securesoftwaredevelopment.repository;

import com.zuehlke.securesoftwaredevelopment.domain.Permission;
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
public class PermissionRepository {

    private static final Logger LOG = LoggerFactory.getLogger(PermissionRepository.class);

    private final DataSource dataSource;

    public PermissionRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Permission> findByRoleId(int roleId) {
        List<Permission> permissions = new ArrayList<>();
        String query = "SELECT id, name FROM permissions WHERE id IN (SELECT permissionId FROM role_to_permissions WHERE roleId=" + roleId + ")";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                permissions.add(new Permission(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return permissions;
    }
}
