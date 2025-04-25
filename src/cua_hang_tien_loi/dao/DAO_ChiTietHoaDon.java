package cua_hang_tien_loi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

	// add cthd
	public boolean addChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		try {
			String sql = "INSERT INTO ChiTietHoaDon (maHD, maSP, soLuong, thanhTien) VALUES (?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareCall(sql);
			stmt.setString(1, chiTietHoaDon.getSp().getMaSP());
			stmt.setString(2, chiTietHoaDon.getHd().getMaHD());
			stmt.setInt(3, chiTietHoaDon.getSoLuong());
			stmt.setLong(4, chiTietHoaDon.getThanhTien());

			int result = stmt.executeUpdate();
			return result > 0;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	// tong doanh thu
	public double getTongDoanhThu() {
		ConnectDB.getConnection();
		Connection conn = ConnectDB.getConnection();
		String sql = "SELECT SUM(thanhTien) FROM ChiTietHoaDon";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				return rs.getDouble(1);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	// tong tien hd
	public double tongTienHoaDon(String maHD) {
		ConnectDB.getConnection();
		Connection conn = ConnectDB.getConnection();
		String sql = "select ChiTietHoaDon.maHD,  tongTien = sum(ChiTietHoaDon.thanhTien) \n" + "from ChiTietHoaDon \n"
				+ "where maHD = ?\n" + "group by ChiTietHoaDon.maHD";

		try {
			PreparedStatement stmt = conn.prepareCall(sql);
			stmt.setString(1, maHD);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return rs.getDouble(2);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return -1;
	}

}
