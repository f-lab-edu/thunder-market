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
        String sql = "SELECT * FROM productsDetails WHERE id = ?";
        Connection conn = null;

        try {
            conn = getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setLong(1, id);

                try (ResultSet resultSet = ps.executeQuery()) {
                    if (resultSet.next()) {
                        ProductDetail productDetail = new ProductDetail();
                        productDetail.setColor(resultSet.getString("color"));
                        productDetail.setProductCondition(resultSet.getString("productCondition"));
                        productDetail.setBatteryCondition(resultSet.getString("batteryCondition"));
                        productDetail.setCameraCondition(resultSet.getString("cameraCondition"));
                        productDetail.setAccessories(resultSet.getString("accessories"));
                        productDetail.setPurchaseDate(resultSet.getString("purchaseDate"));
                        productDetail.setWarrantyDuration(resultSet.getString("warrantyDuration"));
                        productDetail.setTradeLocation(resultSet.getString("tradeLocation"));
                        productDetail.setDeliveryFee(resultSet.getInt("deliveryFee"));

                        return productDetail;
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
