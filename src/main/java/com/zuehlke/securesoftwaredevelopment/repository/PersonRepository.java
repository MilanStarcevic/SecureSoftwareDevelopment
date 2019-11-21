package com.zuehlke.securesoftwaredevelopment.repository;

import com.zuehlke.securesoftwaredevelopment.domain.Person;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepository {

    private DataSource dataSource;

    public PersonRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Person> getAll() {
        List<Person> personList = new ArrayList<>();
        String query = "SELECT name FROM person";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                String name = rs.getString(1);
                personList.add(new Person(name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personList;
    }
}
