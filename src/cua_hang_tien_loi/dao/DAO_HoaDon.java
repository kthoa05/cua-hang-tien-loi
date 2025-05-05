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
import cua_hang_tien_loi.entity.ChiTietHoaDon;
import cua_hang_tien_loi.entity.HoaDon;
import cua_hang_tien_loi.entity.KhachHang;
import cua_hang_tien_loi.entity.NhanVien;

public class DAO_HoaDon {

	private NhanVienController nvController;
	private KhachHangController khController;

	public DAO_HoaDon() {
		this.nvController = new NhanVienController();
		this.khController = new KhachHangController();
	}

	// get hoa don by maHD
	public HoaDon getHoaDonById(String maHD) {
		HoaDon hoaDon = new HoaDon();
		Connection conn = ConnectDB.getConnection();
		String query = "SELECT * FROM HoaDon WHERE maHD = ?";

		try (PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setString(1, maHD);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					String maHoaDon = rs.getString("maHD");
					Date ngayLap = rs.getDate("ngayLapHD");
					String maNV = rs.getString("maNV");
					hoaDon = new HoaDon(maHoaDon, new NhanVien(maNV), ngayLap);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return hoaDon;
	}

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
				if (kh == null) {
					System.out.println("KhachHang is null for maHD: " + maHD);
					continue;
				}
				Date ngayLapHD = rs.getDate("ngayLapHD");
				HoaDon hd = new HoaDon(maHD, kh, nv, ngayLapHD);
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
				Date ngayLapHD = rs.getDate(2);
				NhanVien nhanVien = nvController.getNhanVien(rs.getString(3));
				KhachHang khachHang = khController.getKhachHang(rs.getString(4));
				HoaDon hoaDon = new HoaDon(maHoaDon, khachHang, nhanVien, ngayLapHD);
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
			String sql = "insert into HoaDon(maHD, ngayLapHD, maNV, maKhachHang) values (?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
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
			PreparedStatement stmt = conn.prepareStatement(sql);
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
		String sql = "SELECT h.maHD, h.maKH, h.ngayLapHD, kh.tenKH, nv.hoTen, kh.sdt, SUM(cthd.thanhTien) AS tongTien "
				+ "FROM HoaDon h " + "JOIN KhachHang kh ON h.maKH = kh.maKH " + "JOIN NhanVien nv ON h.maNV = nv.maNV "
				+ "JOIN ChiTietHoaDon cthd ON h.maHD = cthd.maHD "
				+ "WHERE (kh.tenKH LIKE ? AND h.maHD LIKE ? AND h.maKH LIKE ? AND h.ngayLapHD LIKE ? AND nv.hoTen LIKE ? AND kh.sdt LIKE ?) "
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
				String nhanVienResult = rs.getString("hoTen");
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

	// insert hoa don
	public boolean themHoaDon(HoaDon hoaDon) {
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		String query = "INSERT INTO HoaDon (maHD, maKH, maNV, ngayLapHD) VALUES (?, ?, ?, ?)";
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, hoaDon.getMaHD());
			stmt.setString(2, hoaDon.getKh().getMaKH());
			stmt.setString(3, hoaDon.getNv().getMaNV());
			stmt.setDate(4, hoaDon.getNgayLapHD());
			int updateStatus = stmt.executeUpdate();
			return updateStatus > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<ChiTietHoaDon> getAllCTHDByHoaDon(HoaDon hoaDon) {
		List<ChiTietHoaDon> listChiTietHoaDon = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectDB.getConnection();
			String sql = "SELECT * FROM ChiTietHoaDon WHERE maHD = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, hoaDon.getMaHD());
			rs = stmt.executeQuery();
			while (rs.next()) {
				// Xử lý kết quả
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listChiTietHoaDon;
	}

	// lay ngay len cho combo box
	public List<Integer> getNgay() {
		List<Integer> ds = new ArrayList<>();
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		String sql = "SELECT DISTINCT DAY(ngayLapHD) FROM HoaDon"; // Sử dụng đúng tên cột
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ds.add(rs.getInt(1)); // Sử dụng chỉ số cột thay vì tên
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ds;
	}

	// Method to get months
	public List<Integer> getThang() {
		List<Integer> ds = new ArrayList<>();
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		String sql = "SELECT DISTINCT MONTH(ngayLapHD) FROM HoaDon"; // Sử dụng đúng tên cột
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ds.add(rs.getInt(1)); // Sử dụng chỉ số cột thay vì tên
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ds;
	}

	// lay ngay len cho combo box
	public List<Integer> getNam() {
		List<Integer> ds = new ArrayList<Integer>();
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		String sql = "SELECT DISTINCT YEAR(ngayLapHD) FROM HoaDon";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ds.add(rs.getInt(1));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ds;
	}

	// lay ngay thang nam cho thong ke
	public List<Object[]> thongKeChiTietHoaDon(int ngay, int thang, int nam) {
		List<Object[]> list = new ArrayList<>();
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		String sql = "SELECT hd.maHD, hd.ngayLapHD, nv.maNV, sp.maSP, cthd.soLuong, cthd.thanhTien " + "FROM HoaDon hd "
				+ "INNER JOIN NhanVien nv ON hd.maNV = nv.maNV " + "JOIN ChiTietHoaDon cthd ON hd.maHD = cthd.maHD "
				+ "INNER JOIN SanPham sp ON cthd.maSP = sp.maSP "
				+ "WHERE DAY(hd.ngayLapHD) = ? AND MONTH(hd.ngayLapHD) = ? AND YEAR(hd.ngayLapHD) = ?";
		System.out.println("sql: " + sql);
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, ngay);
			stmt.setInt(2, thang);
			stmt.setInt(3, nam);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Object[] row = { rs.getString("maHD"), rs.getDate("ngayLapHD"), rs.getString("maNV"),
						rs.getString("maSP"), rs.getInt("soLuong"), rs.getDouble("thanhTien") };
				System.out.println("row: " + row);
				list.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
