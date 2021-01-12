package com.zuehlke.securesoftwaredevelopment.repository;

import com.zuehlke.securesoftwaredevelopment.domain.Voucher;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
public class VoucherRepository {
    private final DataSource dataSource;

    public VoucherRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Voucher findByCode(String code) throws SQLException {
        String query = "SELECT id, code, discountPercentage FROM vouchers WHERE code=?";
        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, code);
            ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    String resultCode = rs.getString(2);
                    int discountPercentage = rs.getInt(3);
                    return new Voucher(id, resultCode, discountPercentage);
                }
            }
        return null;
    }

    public Voucher findById(Integer id) throws SQLException {
        String query = "SELECT id, code, discountPercentage FROM vouchers WHERE id=" + id;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            if (rs.next()) {
                int resultId = rs.getInt(1);
                String code = rs.getString(2);
                int discountPercentage = rs.getInt(3);
                return new Voucher(resultId, code, discountPercentage);
            }
        }
        return null;
    }


    public void save(String code, int discountPercentage) throws SQLException {
        String query = "INSERT INTO vouchers(code, discountPercentage) VALUES ('" + code + "', " + discountPercentage + ")";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate(query);
        }
    }
}
