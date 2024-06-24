package com.github.thundermarket.thundermarket.repository;

import com.github.thundermarket.thundermarket.domain.Product;
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
                        return new ProductDetail.Builder()
                                .withColor(resultSet.getString("color"))
                                .withProductCondition(resultSet.getString("productCondition"))
                                .withBatteryCondition(resultSet.getString("batteryCondition"))
                                .withCameraCondition(resultSet.getString("cameraCondition"))
                                .withAccessories(resultSet.getString("accessories"))
                                .withPurchaseDate(resultSet.getString("purchaseDate"))
                                .withWarrantyDuration(resultSet.getString("warrantyDuration"))
                                .withTradeLocation(resultSet.getString("tradeLocation"))
                                .withDeliveryFee(resultSet.getInt("deliveryFee"))
                                .build();
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
