package cua_hang_tien_loi.connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

	private static Connection con = null;
	private static final ConnectDB instance = new ConnectDB();

	private final String URL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLy_CHTL";
	private final String USER = "u12";
	private final String PASS = "u12";

	private ConnectDB() {
		// Constructor private để đảm bảo Singleton
	}

	public static ConnectDB getInstance() {
		return instance;
	}

	public static Connection getConnection() {
		try {
			if (con == null || con.isClosed()) {
				instance.connect(); // tự động kết nối lại nếu bị mất
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public void connect() throws SQLException {
		con = DriverManager.getConnection(URL, USER, PASS);
		System.out.println("✅ Đã kết nối lại CSDL.");
	}

	public void disconnect() {
		if (con != null) {
			try {
				con.close();
				System.out.println("🛑 Đã đóng kết nối CSDL.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}