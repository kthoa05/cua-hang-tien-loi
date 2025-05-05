package cua_hang_tien_loi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import cua_hang_tien_loi.connectDB.ConnectDB;
import cua_hang_tien_loi.controller.HoaDonController;
import cua_hang_tien_loi.controller.SanPhamController;
import cua_hang_tien_loi.entity.ChiTietHoaDon;
import cua_hang_tien_loi.entity.HoaDon;
import cua_hang_tien_loi.entity.SanPham;

public class DAO_ChiTietHoaDon {
	private HoaDonController hoaDonController;
	private HoaDonController hdController;
	private SanPhamController spController;

	public DAO_ChiTietHoaDon() {
		this.hoaDonController = new HoaDonController();
		this.spController = new SanPhamController();
		this.hdController = new HoaDonController();
	}

	// get cthd by mahd
	public ChiTietHoaDon getChiTietHoaDonById(String maHD) {
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
		String query = "SELECT * FROM ChiTietHoaDon WHERE maHD = ?";

		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, maHD);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					String maHoaDon = rs.getString("maHD");
					String maSP = rs.getString("maSP");
					int soLuong = rs.getInt("soLuong");
					double thanhTien = rs.getDouble("thanhTien");

					HoaDon hd = hdController.timHoaDonTheoMa(maHoaDon);
					SanPham sp = spController.getById(maSP);
					chiTietHoaDon = new ChiTietHoaDon(hd, sp, soLuong, thanhTien);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return chiTietHoaDon;
	}

	
	public ArrayList<ChiTietHoaDon> getAllCTHDByHoaDon(HoaDon hoaDon) {
		ArrayList<ChiTietHoaDon> listChiTietHoaDon = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectDB.getConnection();
			if (conn == null || conn.isClosed()) {
				conn = ConnectDB.getConnection();
			}

			String sql = "SELECT * FROM ChiTietHoaDon WHERE maHD = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, hoaDon.getMaHD());
			rs = stmt.executeQuery();

			while (rs.next()) {
				SanPham sanPham = spController.getById(rs.getString("maSP"));
				int soLuong = rs.getInt("soLuong");
				long thanhTien = rs.getLong("thanhTien");

				ChiTietHoaDon cthd = new ChiTietHoaDon(hoaDon, sanPham, soLuong, thanhTien);
				listChiTietHoaDon.add(cthd);
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
			stmt.setString(1, chiTietHoaDon.getHd().getMaHD());
			stmt.setString(2, chiTietHoaDon.getSp().getMaSP());
			stmt.setInt(3, chiTietHoaDon.getSoLuong());
			stmt.setDouble(4, chiTietHoaDon.getThanhTien());

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
