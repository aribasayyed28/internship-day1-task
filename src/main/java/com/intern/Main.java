package com.intern;

import java.sql.*;

public class Main {

    private static final String URL = "jdbc:postgresql://localhost:5432/internship_day1";
    private static final String USER = "postgres";  
    private static final String PASSWORD = "12345";


    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connected successfully!");

            
            String countQuery = "SELECT COUNT(*) FROM students";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(countQuery)) {
                if (rs.next()) {
                    System.out.println("Total students: " + rs.getInt(1));
                }
            }

            
            String insertQuery =
                "INSERT INTO students (name, email, dept_id, enrollment_year) VALUES (?, ?, ?, ?)";

            try (PreparedStatement ps = conn.prepareStatement(insertQuery)) {
                ps.setString(1, "JDBC Student");
                ps.setString(2, "jdbc@example.com");
                ps.setInt(3, 1);
                ps.setInt(4, 2025);

                int rows = ps.executeUpdate();
                System.out.println("Inserted rows: " + rows);
            }

            
            String updateQuery = "UPDATE students SET name = 'JDBC Updated' WHERE email = 'jdbc@example.com'";
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(updateQuery);
                System.out.println("Updated student");
            }

            
            String deleteQuery = "DELETE FROM students WHERE email = 'jdbc@example.com'";
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(deleteQuery);
                System.out.println("Deleted student");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
