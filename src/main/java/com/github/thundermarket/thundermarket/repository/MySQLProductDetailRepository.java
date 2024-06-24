package com.github.thundermarket.thundermarket.repository;

import com.github.thundermarket.thundermarket.domain.ProductDetail;
import com.github.thundermarket.thundermarket.domain.User;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
public class MySQLProductDetailRepository implements ProductDetailRepository {

    private final DataSource dataSource;

    public MySQLProductDetailRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }

    private void releaseConnection(Connection conn) {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }

    @Override
    public ProductDetail save(ProductDetail productDetail) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ProductDetail findById(Long id) {
        String sql = "SELECT * FROM productDetails WHERE id = ?";
        Connection conn = null;

        try {
            conn = getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setLong(1, id);

                try (ResultSet resultSet = ps.executeQuery()) {
                    if (resultSet.next()) {
                        String color = resultSet.getString("color");
                        String productCondition = resultSet.getString("productCondition");
                        String batteryCondition = resultSet.getString("batteryCondition");
                        String cameraCondition = resultSet.getString("cameraCondition");
                        String accessories = resultSet.getString("accessories");
                        String purchaseDate = resultSet.getString("purchaseDate");
                        String warrantyDuration = resultSet.getString("warrantyDuration");
                        String tradeLocation = resultSet.getString("tradeLocation");
                        int deliveryFee = resultSet.getInt("deliveryFee");

                        return new ProductDetail(color, productCondition, batteryCondition, cameraCondition, accessories, purchaseDate, warrantyDuration, tradeLocation, deliveryFee);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Find by id failed", e);
        } finally {
            releaseConnection(conn);
        }
        return null;
    }
}
