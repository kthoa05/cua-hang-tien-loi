package cua_hang_tien_loi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cua_hang_tien_loi.connectDB.ConnectDB;
import cua_hang_tien_loi.entity.KhachHang;

public class DAO_KhachHang {
	public KhachHang getKhachHangById(String id) {
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		KhachHang kh = new KhachHang();
		try {
			String sql = "SELECT * FROM KhachHang WHERE maKH = ?";
			PreparedStatement stmt = conn.prepareCall(sql);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				kh.setMaKH(rs.getString(1));
				kh.setTenKH(rs.getString(2));
				kh.setPhai(rs.getBoolean(3));
				kh.setSdt(rs.getString(4));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return kh;
	}
}
