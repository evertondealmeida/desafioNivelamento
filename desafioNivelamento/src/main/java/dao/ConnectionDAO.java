package persistence;

import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

public class ConnectionDAO {
	public Statement statement;
	public ResultSet resultset;
	public PreparedStatement prep;

	public Connection getConnection() {
		String url = "jdbc:sqlite:allShop.db";
		try {
			return DriverManager.getConnection(url);
		} catch (SQLException ex) {
			Logger.getLogger(ConnectionDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
}
