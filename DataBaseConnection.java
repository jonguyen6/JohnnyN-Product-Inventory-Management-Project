// Reusable final class connection for the DB.

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    // final variables since they will stay the same for the entirety of the time.
    // static since they belong to this class.
    static final String dbURL = "jdbc:mysql://localhost:3306/PIPDB";
    static final String userName = "root";
    static final String password = "1324";

    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(dbURL, userName, password);
        } catch (SQLException e) {
            e.printStackTrace(); // don't have to throw new exception, just print out the StackTrace instead.
        }
        return con;
    }
}
