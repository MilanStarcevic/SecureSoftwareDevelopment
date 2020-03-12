package com.zuehlke.securesoftwaredevelopment.repository;

import com.zuehlke.securesoftwaredevelopment.domain.Comment;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentRepository {
    private DataSource dataSource;

    public CommentRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void create(Comment comment) {
        String query = "insert into comments(carId, userId, comment) " +
                "values (" + comment.getCarId() + ", " + comment.getUserId() + ", '" + comment.getComment() + "')";
        String prep_query = "insert into comments(carId, userId, comment) " +
                "values (?,?,?)";

        try (Connection connection = dataSource.getConnection();
             //Statement statement = connection.createStatement();
        ) {
            PreparedStatement ps = connection.prepareStatement(prep_query);
            ps.setInt(1, comment.getCarId());
            ps.setInt(2, comment.getUserId());
            ps.setString(3, comment.getComment());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Comment> getAll(String carId) {
        List<Comment> commentList = new ArrayList<>();
        String query = "SELECT carId, userId, comment FROM comments WHERE carId = " + carId;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                commentList.add(new Comment(rs.getInt(1), rs.getInt(2), rs.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commentList;
    }
}
