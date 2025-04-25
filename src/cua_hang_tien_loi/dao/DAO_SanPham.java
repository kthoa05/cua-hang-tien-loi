package cua_hang_tien_loi.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cua_hang_tien_loi.connectDB.ConnectDB;
import cua_hang_tien_loi.entity.SanPham;

public class DAO_SanPham {

	// get all sp
	public List<SanPham> getAllSanPham() {
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		String sql = "SELECT * FROM SanPham";
		PreparedStatement s = null;
		SanPham sp = new SanPham();
		List<SanPham> listSP = new ArrayList<SanPham>();
		try {
			s = conn.prepareStatement(sql);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				sp = new SanPham(rs.getString("imgPath"), rs.getString("maSP"), rs.getString("tenSP"),
						rs.getString("loaiSP"), rs.getBoolean("ttkd"), rs.getDouble("donGia"),
						rs.getString("chatLieu"));
				listSP.add(sp);

			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listSP;
	}

	// get sp by masp
	public SanPham getSanPham(String id) {
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		String sql = "SELECT * FROM SanPham WHERE maSP = ?";
		PreparedStatement s = null;
		SanPham sp = new SanPham();
		try {
			s = conn.prepareStatement(sql);
			s.setString(0, id);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				sp = new SanPham(rs.getString("imgPath"), rs.getString("maSP"), rs.getString("tenSP"),
						rs.getString("loaiSP"), rs.getBoolean("ttkd"), rs.getDouble("donGia"),
						rs.getString("chatLieu"));

			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return sp;
	}

	public boolean addSanPham(SanPham sp) {
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		String sql = "INSERT INTO SanPham VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement s = null;
		try {
			s = conn.prepareStatement(sql);
			s.setString(1, sp.getImgPath());
			s.setString(2, sp.getMaSP());
			s.setString(3, sp.getTenSP());
			s.setString(4, sp.getLoaiSP());
			s.setBoolean(5, sp.isTTKD());
			s.setDouble(6, sp.getDonGia());
			s.setString(7, sp.getChatLieu());
			s.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateSanPham(SanPham sp) {
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		String sql = "UPDATE NhanVien SET tenSP = ?, loaiSP = ?, ttkd = ?, donGia = ?, chatLieu = ?, phanTramKM = ?, ngayBDKM = ?, ngayKTKM = ? where MaSP = ?";
		PreparedStatement s = null;
		try {
			s = conn.prepareStatement(sql);
			s.setString(1, sp.getTenSP());
			s.setString(2, sp.getLoaiSP());
			s.setBoolean(3, sp.isTTKD());
			s.setString(4, sp.getChatLieu());
			s.setString(5, sp.getMaSP());
			s.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// get for TraCuuSanPham dua tren 4 field masp, tensp, loai, ttkd
	public List<SanPham> findSanPham(String maSP, String tenSP, String loai, boolean ttkd) {
		List<SanPham> ds = new ArrayList<>();
		String sql = "{call findSanPham(?, ?, ?, ?)}";

		try (Connection conn = ConnectDB.getConnection(); CallableStatement cs = conn.prepareCall(sql)) {
			cs.setString(1, maSP != null && !maSP.isEmpty() ? maSP : null);
			cs.setString(2, tenSP != null && !tenSP.isEmpty() ? tenSP : null);
			cs.setString(3, loai != null && !loai.isEmpty() ? loai : null);
			String ttkdStr = ttkd ? "Kinh doanh" : "Ngừng kinh doanh";
			cs.setString(4, ttkdStr);

			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				SanPham sp = new SanPham(rs.getString("imgPath"), rs.getString("maSP"), rs.getString("tenSP"),
						rs.getString("loaiSP"), rs.getBoolean("ttkd"), rs.getDouble("donGia"),
						rs.getString("chatLieu"));
				ds.add(sp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ds;
	}

	// select loai len
	public List<String> getLoaiSP() {
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		String sql = "SELECT DISTINCT loai FROM SanPham";
		PreparedStatement s = null;
		List<String> ds = new ArrayList<>();
		ResultSet rs = null;
		try {
			s = conn.prepareStatement(sql);
			rs = s.executeQuery();
			while (rs.next()) {
				String loai = rs.getString("loai");
				ds.add(loai);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	// select ttkd
	public List<String> getDSTinhTrangKD() {
		List<String> ds = new ArrayList<>();
		Connection conn = ConnectDB.getConnection();
		String sql = "SELECT ttkd FROM SanPham";

		try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				boolean ttkd = rs.getBoolean("ttkd");
				String tinhTrang = ttkd ? "Kinh doanh" : "Không kinh doanh";
				ds.add(tinhTrang);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

}
