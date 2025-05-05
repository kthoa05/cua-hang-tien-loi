package cua_hang_tien_loi;

import java.sql.SQLException;

import cua_hang_tien_loi.connectDB.ConnectDB;
import cua_hang_tien_loi.ui.DangNhap;

public class Main {
	public static void main(String[] args) {
		try {
			ConnectDB.getInstance().connect(); //connect db
			new DangNhap().setVisible(true);   // chay ui
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Kết nối cơ sở dữ liệu thất bại.");
		}
	}
}