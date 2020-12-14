package com.zuehlke.securesoftwaredevelopment.repository;

import com.zuehlke.securesoftwaredevelopment.domain.Voucher;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class VoucherRepository {
    private final DataSource dataSource;

    public VoucherRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Voucher findByCode(String code) {
        String query = "SELECT id, code, discountPercentage FROM vouchers WHERE code='" + code + "'";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            if (rs.next()) {
                int id = rs.getInt(1);
                String resultCode = rs.getString(2);
                int discountPercentage = rs.getInt(3);
                return new Voucher(id, resultCode, discountPercentage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Voucher findById(String id) {
        String query = "SELECT id, code, discountPercentage FROM vouchers WHERE id='" + id + "'";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            if (rs.next()) {
                int resultId = rs.getInt(1);
                String code = rs.getString(2);
                int discountPercentage = rs.getInt(3);
                return new Voucher(resultId, code, discountPercentage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
