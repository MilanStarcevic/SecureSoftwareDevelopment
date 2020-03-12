package com.zuehlke.securesoftwaredevelopment.repository;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
public class UserRepository {

    private DataSource dataSource;

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean validCredentials(String username, String password) {
        String query = "SELECT username FROM users WHERE username='" + username + "' AND password='" + password + "'";
        String prep_query = "SELECT username FROM users WHERE username= ? AND password= ? ";

        try (Connection connection = dataSource.getConnection())
             {
                 PreparedStatement preparedStatement = connection.prepareStatement(prep_query);
                 preparedStatement.setString(1, username);
                 preparedStatement.setString(2, password);
                 ResultSet rs = preparedStatement.executeQuery();

                 return rs.next();
             } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void delete(int userId) {
        String query = "DELETE FROM users WHERE id = " + userId;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
