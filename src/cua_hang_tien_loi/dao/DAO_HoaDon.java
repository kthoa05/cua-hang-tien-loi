package cua_hang_tien_loi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import cua_hang_tien_loi.connectDB.ConnectDB;
import cua_hang_tien_loi.controller.KhachHangController;
import cua_hang_tien_loi.controller.NhanVienController;
import cua_hang_tien_loi.entity.HoaDon;
import cua_hang_tien_loi.entity.KhachHang;
import cua_hang_tien_loi.entity.NhanVien;

public class DAO_HoaDon {

	private NhanVienController nvController;
	private KhachHangController khController;

	public List<HoaDon> getAllHoaDon() {
		List<HoaDon> danhSachHoaDon = new ArrayList<>();
		Connection conn = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT * FROM HoaDon";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				String maHD = rs.getString("maHD");
				NhanVien nv = nvController.getNhanVien(rs.getString("maNV"));
				KhachHang kh = khController.getKhachHang(rs.getString("maKH"));
				LocalDate ngayLap = rs.getDate("ngayLap").toLocalDate();

				HoaDon hd = new HoaDon(maHD, kh, nv, ngayLap);
				danhSachHoaDon.add(hd);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return danhSachHoaDon;
	}

}
