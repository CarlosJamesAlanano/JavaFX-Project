package com.example.finalfx.last;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUtil {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/login_schema";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "password";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC driver not found", e);
        }
    }

    public static boolean validateLogin(String username, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND passwords = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Drivers> getDrivers() {
        String query = "SELECT name, vehicle FROM drivers";
        List<Drivers> drivers = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String vehicle = resultSet.getString("vehicle");
                drivers.add(new Drivers(name, vehicle));
            }
            return drivers;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean registerUser(String username, String password) {
        String query = "INSERT INTO users (email, passwords) VALUES (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
