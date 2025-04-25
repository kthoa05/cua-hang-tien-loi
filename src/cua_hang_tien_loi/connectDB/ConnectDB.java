package cua_hang_tien_loi.connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB{

	public static Connection con = null;
	public static ConnectDB instance = new ConnectDB();

	public void connect() throws SQLException {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=CHTL_QuanLy";
		String user = "sa";
		String password = "sapassword";
		con = DriverManager.getConnection(url, user, password);
	}

	public void disconnect() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static Connection getCon() {
		return con;
	}

	public static ConnectDB getInstance() {
		return instance;
	}

	public static Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}

}