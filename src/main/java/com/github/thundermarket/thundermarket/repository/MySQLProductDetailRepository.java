package com.github.thundermarket.thundermarket.repository;

import com.github.thundermarket.thundermarket.domain.ProductDetail;
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
        String sql = "INSERT INTO productDetails (color, productCondition, batteryCondition, cameraCondition, accessories, purchaseDate, warrantyDuration, tradeLocation, deliveryFee, videoFilePath, thumbnailFilePath) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        long generatedKey = 0L;

        try {
            conn = getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, productDetail.getColor());
                ps.setString(2, productDetail.getProductCondition());
                ps.setString(3, productDetail.getBatteryCondition());
                ps.setString(4, productDetail.getCameraCondition());
                ps.setString(5, productDetail.getAccessories());
                ps.setString(6, productDetail.getPurchaseDate());
                ps.setString(7, productDetail.getWarrantyDuration());
                ps.setString(8, productDetail.getTradeLocation());
                ps.setInt(9, productDetail.getDeliveryFee());
                ps.setString(10, productDetail.getVideoFilePath());
                ps.setString(11, productDetail.getThumbnailFilePath());
                int affectedRows = ps.executeUpdate();

                if (affectedRows == 0) {
                    throw new RuntimeException("Create productDetails failed, no affectedRows");
                }

                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (!generatedKeys.next()) {
                        throw new RuntimeException("Create productDetails failed, no generatedKeys");
                    }
                    generatedKey = generatedKeys.getLong(1);
                }
                return new ProductDetail.Builder(productDetail)
                        .withId(generatedKey)
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Create productDetails failed", e);
        } finally {
            releaseConnection(conn);
        }
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
                                .withVideo(resultSet.getString("videoFilePath"))
                                .withThumbnailFilePath(resultSet.getString("thumbnailFilePath"))
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
