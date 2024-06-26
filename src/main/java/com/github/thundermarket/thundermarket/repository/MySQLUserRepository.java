package com.github.thundermarket.thundermarket.repository;

import com.github.thundermarket.thundermarket.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MySQLUserRepository implements UserRepository {

    private final DataSource dataSource;

    public MySQLUserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }

    private void releaseConnection(Connection conn) {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }

    @Override
    public User save(User user) {
        String sql = "INSERT INTO users (email, password) VALUES (?, ?)";
        Connection conn = null;

        try {
            conn = getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getPassword());
                int affectedRows = ps.executeUpdate();

                if (affectedRows == 0) {
                    throw new RuntimeException("Create user failed, no affectedRows");
                }

                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (!generatedKeys.next()) {
                        throw new RuntimeException("Create user failed, no generatedKeys");
                    }
                    user.setId(generatedKeys.getLong(1));
                }
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Create user failed", e);
        } finally {
            releaseConnection(conn);
        }
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        Connection conn = null;

        try {
            conn = getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ResultSet resultSet = ps.executeQuery();
                List<User> users = new ArrayList<>();

                while (resultSet.next()) {
                    users.add(new User.Builder()
                            .withId(resultSet.getLong("id"))
                            .withEmail(resultSet.getString("email"))
                            .withPassword(resultSet.getString("password"))
                            .build()
                    );
                }
                return users;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Find all users failed", e);
        } finally {
            releaseConnection(conn);
        }
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        Connection conn = null;

        try {
            conn = getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, email);
                ps.setString(2, password);

                try (ResultSet resultSet = ps.executeQuery()) {
                    if (resultSet.next()) {
                        User user = new User();
                        user.setId(resultSet.getLong("id"));
                        user.setEmail(resultSet.getString("email"));
                        user.setPassword(resultSet.getString("password"));

                        return user;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Find by email and password failed", e);
        } finally {
            releaseConnection(conn);
        }
        return null;
    }
}
