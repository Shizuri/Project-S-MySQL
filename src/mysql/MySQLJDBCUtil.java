package mysql;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLJDBCUtil {

	/**
	 * Get database connection
	 *
	 * @return a Connection object
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			// db parameters
			String url = "jdbc:mysql://localhost:3306/project_s";
			String user = "root";
			String password = "test1";

			// create a connection to the database
			conn = DriverManager.getConnection(url, user, password);
			// more processing here
			// ...
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}

		return conn;
	}

//    public static Connection getConnection() throws SQLException {
//        Connection conn = null;
// 
//        try (FileInputStream f = new FileInputStream("db.properties")) {
// 
//            // load the properties file
//            Properties pros = new Properties();
//            pros.load(f);
// 
//            // assign db parameters
//            String url = pros.getProperty("url");
//            String user = pros.getProperty("user");
//            String password = pros.getProperty("password");
//            
//            // create a connection to the database
//            conn = DriverManager.getConnection(url, user, password);
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//        return conn;
//    }

}