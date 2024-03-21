package my_bank.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnect {
    public Connection createConnection() {
        try {
            return DriverManager.getConnection(
                    System.getenv("DB_URL"),
                    System.getenv("DB_USERNAME"),
                    System.getenv("DB_PASSWORD")
            );
        } catch (Exception exception) {
            System.err.println("Connection error :\n  > " + exception.getMessage());
            return null;
        }
    }
}
