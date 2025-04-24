package cua_hang_tien_loi.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import cua_hang_tien_loi.connectDB.ConnectDB;
import cua_hang_tien_loi.entity.NhanVien;
import cua_hang_tien_loi.entity.SanPham;

public class DAO_NhanVien {

	// them nhan vien
	public boolean addNhanVien(NhanVien nv) {
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		String sql = "INSERT INTO NhanVien VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement s = null;
		try {
			s = conn.prepareStatement(sql);
			s.setString(1, nv.getMaNV());
			s.setString(2, nv.getHoTen());
			s.setBoolean(3, nv.isPhai());
			s.setDate(4, Date.valueOf(nv.getNgaySinh()));
			s.setString(5, nv.getSdt());
			s.setString(6, nv.getEmail());
			s.setString(7, nv.getCmnd());
			s.setString(8, nv.getMk());
			s.setBoolean(9, nv.isAdmin());
			s.setBoolean(10, nv.isTrangThaiLamViec());
			s.setString(11, nv.getImgPath());
			s.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// update nhan vien dua tren ma nv
	public boolean updateNhanVien(NhanVien nv) {
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		String sql = "UPDATE NhanVien SET phai = ?, ngaySinh = ?, sdt = ?, email = ?, cmnd = ?, mk = ?, isAdmin = ? WHERE maNV = ?";
		PreparedStatement s = null;
		try {
			s = conn.prepareStatement(sql);
			s.setBoolean(1, nv.isPhai());
			s.setDate(2, Date.valueOf(nv.getNgaySinh()));
			s.setString(3, nv.getSdt());
			s.setString(4, nv.getEmail());
			s.setString(5, nv.getCmnd());
			s.setString(6, nv.getMk());
			s.setBoolean(7, nv.isAdmin());
			// where
			s.setString(8, nv.getMaNV());
			s.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// tim nhan vien dua tren ma, ten, tt cho phan update
	public List<NhanVien> findNhanVienTheoDieuKien(String maNV, String hoTen, boolean status) {
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		List<NhanVien> dsnv = new ArrayList<>();
		String sql = "{call findNhanVienTheoNhieuDieuKien(?, ?, ?)}";

		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, maNV != null && !maNV.isEmpty() ? maNV : null);
			cs.setString(2, hoTen != null && !hoTen.isEmpty() ? hoTen : null);
			cs.setBoolean(3, status);

			// duyet
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				NhanVien nv = new NhanVien(rs.getString("maNV"), rs.getString("hoTen"), rs.getBoolean("phai"),
						rs.getDate("nagySinh").toLocalDate(), rs.getString("sdt"), rs.getString("email"),
						rs.getString("cmnd"), rs.getString("mk"), rs.getBoolean("isAdmin"),
						rs.getBoolean("trangThaiLamViec"), rs.getString("imgPath"));
				dsnv.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dsnv;
	}

	// tim nhan vien cho phan tra cuu dua theo manv, tennv, sdt, gioitinh, cmnd
	public List<NhanVien> findNhanVienPhanTraCuu(String maNV, String hoTen, String sdt, boolean gioiTinh, String cmnd) {
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		List<NhanVien> dsnv = new ArrayList<>();
		String sql = "{call findNhanVienTheoNhieuDieuKien(?, ?, ?, ?, ?)}";

		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, maNV != null && !maNV.isEmpty() ? maNV : null);
			cs.setString(2, hoTen != null && !hoTen.isEmpty() ? hoTen : null);
			cs.setString(3, sdt != null && !sdt.isEmpty() ? sdt : null);
			cs.setBoolean(4, gioiTinh);
			cs.setString(5, cmnd != null && !cmnd.isEmpty() ? cmnd : null);

			// duyet
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				NhanVien nv = new NhanVien(rs.getString("maNV"), rs.getString("hoTen"), rs.getBoolean("phai"),
						rs.getDate("nagySinh").toLocalDate(), rs.getString("sdt"), rs.getString("email"),
						rs.getString("cmnd"), rs.getString("mk"), rs.getBoolean("isAdmin"),
						rs.getBoolean("trangThaiLamViec"), rs.getString("imgPath"));
				dsnv.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dsnv;
	}

	// tao autoId
	public ArrayList<NhanVien> getAllNhanVien() {
		ArrayList<NhanVien> listNhanVien = new ArrayList<>();
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();

		try {
			String sql = "SELECT * FROM NhanVien";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String maNhanVien = rs.getString(1);
				String hoVaTen = rs.getString(2);
				boolean gioiTinh = rs.getBoolean(3);
				LocalDate ngaySinh = rs.getDate(4).toLocalDate();
				String sdt = rs.getString(5);
				String email = rs.getString(6);
				String cmnd = rs.getString(7);
				String mk = rs.getString(8);
				boolean isAdmin = rs.getBoolean(9);
				boolean ttlv = rs.getBoolean(10);
				String imgPath = rs.getString(11);
				NhanVien nv = new NhanVien(maNhanVien, hoVaTen, gioiTinh, ngaySinh, sdt, email, cmnd, mk, isAdmin, ttlv,
						imgPath);
				listNhanVien.add(nv);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return listNhanVien;
	}

	// auth
	public NhanVien getTaiKhoan(String sdt, String mk) {
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		NhanVien nv = null;
		String sql = "SELECT * FROM NhanVien WHERE sdt = ? AND mk = ?";
		PreparedStatement s = null;
		try {
			s = conn.prepareStatement(sql);
			s.setString(1, sdt);
			s.setString(2, mk);
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				String maNhanVien = rs.getString(1);
				String hoVaTen = rs.getString(2);
				boolean gioiTinh = rs.getBoolean(3);
				LocalDate ngaySinh = rs.getDate(4).toLocalDate();
				String soDT = rs.getString(5);
				String email = rs.getString(6);
				String cmnd = rs.getString(7);
				String matKhau = rs.getString(8);
				boolean isAdmin = rs.getBoolean(9);
				boolean ttlv = rs.getBoolean(10);
				String imgPath = rs.getString(11);
				nv = new NhanVien(maNhanVien, hoVaTen, gioiTinh, ngaySinh, soDT, email, cmnd, matKhau, isAdmin, ttlv,
						imgPath);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return nv;
	}

	// get gioi tinh
	public List<String> getPhai() {
		List<String> ds = new ArrayList<>();
		Connection conn = ConnectDB.getConnection();
		String sql = "SELECT phai FROM NhanVien";

		try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				boolean phai = rs.getBoolean("phai");
				String phaiStr = phai ? "Nữ" : "Nam";
				ds.add(phaiStr);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	//get trang thau lam viec
	public List<String> getTrangThaiLV() {
		List<String> ds = new ArrayList<>();
		Connection conn = ConnectDB.getConnection();
		String sql = "SELECT ttlv FROM NhanVien";

		try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				boolean ttlv = rs.getBoolean("ttlv");
				String ttlvStr = ttlv ? "Đang làm việc" : "Ngưng làm việc";
				ds.add(ttlvStr);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	// get List NV for TraCuuNhanVien dua tren 4 field maNV, tenNV, phai, sdt, cccd
	public List<NhanVien> findNhanVien(String ma, String ten, Boolean gt, String sdt, String cccd) {
		List<NhanVien> ds = new ArrayList<>();
		String sql = "{call findNhanVien(?, ?, ?, ?, ?)}";
		Connection conn = ConnectDB.getConnection();

		try (CallableStatement cs = conn.prepareCall(sql)) {
			cs.setString(1, ma != null && !ma.isEmpty() ? ma : null);
			cs.setString(2, ten != null && !ten.isEmpty() ? ten : null);
			cs.setBoolean(3, gt);
			cs.setString(4, sdt != null && !sdt.isEmpty() ? sdt : null);
			cs.setString(5, cccd != null && !cccd.isEmpty() ? cccd : null);

			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				NhanVien nv = new NhanVien(rs.getString("maNV"), rs.getString("tenNV"), rs.getBoolean("phai"),
						rs.getString("cccd"), rs.getString("mk"), rs.getString("sdt"), rs.getString("email"),
						rs.getBoolean("ttkd"));
				ds.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ds;
	}

}
