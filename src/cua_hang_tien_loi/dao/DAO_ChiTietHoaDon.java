package cua_hang_tien_loi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cua_hang_tien_loi.connectDB.ConnectDB;
import cua_hang_tien_loi.controller.SanPhamController;
import cua_hang_tien_loi.entity.ChiTietHoaDon;
import cua_hang_tien_loi.entity.HoaDon;
import cua_hang_tien_loi.entity.SanPham;

public class DAO_ChiTietHoaDon {
	private SanPhamController spController;

	public ArrayList<ChiTietHoaDon> getAllCTHDByHoaDon(HoaDon hoaDon) {
		ArrayList<ChiTietHoaDon> listChiTietHoaDon = new ArrayList<>();
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		try {
			String sql = "Select * from ChiTietHoaDon where maHoaDon = ?";
			PreparedStatement stmt = conn.prepareCall(sql);
			stmt.setString(1, hoaDon.getMaHD());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				SanPham sanPham = spController.getById(rs.getString(1));

				int soLuong = rs.getInt(3);
				long thanhTien = rs.getLong(4);

				ChiTietHoaDon CTHD = new ChiTietHoaDon(hoaDon, sanPham, soLuong, thanhTien);
				listChiTietHoaDon.add(CTHD);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return listChiTietHoaDon;
	}
}
