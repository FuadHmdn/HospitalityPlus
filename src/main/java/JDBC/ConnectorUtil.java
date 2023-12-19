package JDBC;

import org.junit.jupiter.api.Assertions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorUtil {
    public static Connection getConnection() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/belajar";
        String username = "root";
        String password = "";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Berhasil konek database");
            return connection;
        } catch (SQLException exception) {
            Assertions.fail(exception);
            return null;
        }
    }
}
