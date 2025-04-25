package cua_hang_tien_loi.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
				Date ngayLap = rs.getDate("ngayLap");

				HoaDon hd = new HoaDon(maHD, kh, nv, ngayLap);
				danhSachHoaDon.add(hd);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return danhSachHoaDon;
	}

	// get hd by date
	public ArrayList<HoaDon> getHoaDonByDate(Date ngayBatDau, Date ngayKetThuc) {
		ArrayList<HoaDon> listHoaDon = new ArrayList<>();
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();

		try {
			String sql = "SELECT * FROM HoaDon WHERE ngayLapHD between ? and ?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dateBatDau = sdf.format(ngayBatDau);
			String dateNgayKetThuc = sdf.format(ngayKetThuc);

			stmt.setString(1, dateBatDau);
			stmt.setString(2, dateNgayKetThuc);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String maHoaDon = rs.getString(1);
				Date ngayLap = rs.getDate(2);
				NhanVien nhanVien = nvController.getNhanVien(rs.getString(3));
				KhachHang khachHang = khController.getKhachHang(rs.getString(4));
				HoaDon hoaDon = new HoaDon(maHoaDon, khachHang, nhanVien, ngayLap);
				System.out.println(hoaDon);
				listHoaDon.add(hoaDon);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return listHoaDon;
	}

	// add hoa don
	public int addHoaDon(HoaDon hoaDon) {
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		try {
			String sql = "insert into HoaDon(maHoaDon, ngaylap, maNhanVien, maKhachHang) values (?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareCall(sql);
			stmt.setString(1, hoaDon.getMaHD());
			stmt.setDate(2, hoaDon.getNgayLapHD());
			stmt.setString(3, hoaDon.getNv().getMaNV());
			stmt.setString(4, hoaDon.getKh().getMaKH());
			return stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return -1;
	}

	// tong tien hd
	public int tongTienHoaDon(String maHoaDon) {
		int tongTien = 0;
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		try {
			String sql = "SELECT SUM(ChiTietHoaDon.thanhTien) as tongTien\n"
					+ "FROM            ChiTietHoaDon INNER JOIN\n"
					+ "                         HoaDon ON ChiTietHoaDon.maHD = HoaDon.maHD\n"
					+ "where			HoaDon.maHD = ?";
			PreparedStatement stmt = conn.prepareCall(sql);
			stmt.setString(1, maHoaDon);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				tongTien = rs.getInt(1);
				return tongTien;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	// tra cuu hoa don
	public List<HoaDon> tracuuHoaDon(String tenKH, String maHD, String maKH, String ngayLapHD, String nhanVien,
			String sdt) {
		List<HoaDon> hoaDons = new ArrayList<>();
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		String sql = "SELECT h.maHD, h.maKH, h.ngayLapHD, kh.tenKH, nv.hoTen AS nhanVien, kh.sdt, SUM(cthd.thanhTien) AS tongTien "
				+ "FROM HoaDon h " + "JOIN KhachHang kh ON h.maKH = kh.maKH " + "JOIN NhanVien nv ON h.maNV = nv.maNV "
				+ "JOIN ChiTietHoaDon cthd ON h.maHD = cthd.maHD "
				+ "WHERE (kh.tenKH LIKE ? OR h.maHD LIKE ? OR h.maKH LIKE ? OR h.ngayLapHD LIKE ? OR nv.hoTen LIKE ? OR kh.sdt LIKE ?) "
				+ "GROUP BY h.maHD, h.maKH, h.ngayLapHD, kh.tenKH, nv.hoTen, kh.sdt " + "ORDER BY h.ngayLapHD";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, "%" + tenKH + "%");
			ps.setString(2, "%" + maHD + "%");
			ps.setString(3, "%" + maKH + "%");
			ps.setString(4, "%" + ngayLapHD + "%");
			ps.setString(5, "%" + nhanVien + "%");
			ps.setString(6, "%" + sdt + "%");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String maHDResult = rs.getString("maHD");
				String maKHResult = rs.getString("maKH");
				String tenKHResult = rs.getString("tenKH");
				String nhanVienResult = rs.getString("nhanVien");
				String sdtResult = rs.getString("sdt");
				Date ngayLapHDResult = rs.getDate("ngayLapHD");
				double tongTien = rs.getDouble("tongTien");

				KhachHang kh = new KhachHang(maKHResult, tenKHResult, sdtResult);
				NhanVien nv = new NhanVien(nhanVienResult);

				HoaDon hoaDon = new HoaDon(maHDResult, kh, nv, ngayLapHDResult, tongTien);
				hoaDons.add(hoaDon);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return hoaDons;
	}

	// get mahd
	public List<String> getMaHD() {
		List<String> ds = new ArrayList<String>();
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		String sql = "SELECT maHD FROM HoaDon";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ds.add(rs.getString("maHD"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ds;
	}

}
