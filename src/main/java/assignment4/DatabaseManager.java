package assignment4;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/car_rental";
    private static final String USER = "postgres";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver not found", e);
        }
    }

    public List<Car> getAllVehiclesFromDb() {
        List<Car> cars = new ArrayList<>();
        String query = "SELECT id, brand, model, type, price, is_rented FROM vehicles";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                cars.add(new Car(
                        rs.getInt("id"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getString("type"),
                        rs.getInt("price"),
                        rs.getBoolean("is_rented")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    public boolean rentVehicleInDb(int id) {
        String sql = "UPDATE vehicles SET is_rented = true WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean returnVehicleInDb(int id) {
        String sql = "UPDATE vehicles SET is_rented = false WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean addVehicleToDb(Car car) {
        String sql = "INSERT INTO vehicles (brand, model, type, price, is_rented) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, car.getBrand());
            pstmt.setString(2, car.getModel());
            pstmt.setString(3, car.getType());
            pstmt.setInt(4, car.getPrice());
            pstmt.setBoolean(5, car.isRented());

            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.err.println("ПОЛНАЯ ОШИБКА SQL: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteVehicleFromDb(int id) {
        String sql = "DELETE FROM vehicles WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("SQL DELETE ERROR: " + e.getMessage());
            return false;
        }
    }

    public List<Car> getAllCarsFromDb() {
        return null;
    }
}