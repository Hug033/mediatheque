package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLController {
    private static final String url = "jdbc:postgresql://localhost/bd_mediatheque";
    private static final String user = "user_media";
    private static final String password = "tux";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
}
