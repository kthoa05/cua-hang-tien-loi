package cua_hang_tien_loi.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cua_hang_tien_loi.connectDB.ConnectDB;
import cua_hang_tien_loi.entity.SanPham;

public class DAO_SanPham {
	public ArrayList<SanPham> getSanPham() {
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		String sql = "SELECT * FROM SanPham";
		PreparedStatement s = null;
		ArrayList<SanPham> list = new ArrayList<SanPham>();
		try {
			s = conn.prepareStatement(sql);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				list.add(new SanPham(rs.getString("imgPath"), rs.getString("maSP"), rs.getString("tenSP"),
						rs.getString("loaiSP"), rs.getBoolean("ttkd"), rs.getDouble("donGia"), rs.getString("chatLieu"),
						rs.getDouble("phanTramKM"), rs.getDate("ngayBDKM").toLocalDate(),
						rs.getDate("ngayKTKM").toLocalDate()));

			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
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
			s.setDouble(8, sp.getPhanTramKM());
			s.setDate(9, Date.valueOf(sp.getNgayBDKM()));
			s.setDate(10, Date.valueOf(sp.getNgayKTKM()));
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
			s.setDouble(5, sp.getPhanTramKM());
			s.setDate(6, Date.valueOf(sp.getNgayBDKM()));
			s.setDate(7, Date.valueOf(sp.getNgayKTKM()));
			s.setString(9, sp.getMaSP());
			s.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<SanPham> findSanPham(String maSP, String tenSP, boolean ttkd) {
		List<SanPham> ds = new ArrayList<>();
		String sql = "{call findSanPham(?, ?, ?)}";

		try (Connection conn = ConnectDB.getConnection(); CallableStatement cs = conn.prepareCall(sql)) {
			cs.setString(1, maSP != null && !maSP.isEmpty() ? maSP : null);
			cs.setString(2, tenSP != null && !tenSP.isEmpty() ? tenSP : null);
			String ttkdStr = ttkd ? "Kinh doanh" : "Ngừng kinh doanh";
			cs.setString(3, ttkdStr);

			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				SanPham sp = new SanPham(rs.getString("imgPath"), rs.getString("maSP"), rs.getString("tenSP"),
						rs.getString("loaiSP"), rs.getBoolean("ttkd"), rs.getDouble("donGia"), rs.getString("chatLieu"),
						rs.getDouble("phanTramKM"), rs.getDate("ngayBDKM").toLocalDate(),
						rs.getDate("ngayKTKM").toLocalDate());
				ds.add(sp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ds;
	}

}
