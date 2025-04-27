package cua_hang_tien_loi.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cua_hang_tien_loi.connectDB.ConnectDB;
import cua_hang_tien_loi.entity.SanPham;

public class DAO_SanPham {

	private Connection conn;
	private Connection Connection;

	// get img NV for tim kiem o CapNhatSP:
	public String getImg(String maSP,String tenSP, String loaiSP, String ttkdStatus) {
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		String sql = "SELECT imgPath FROM SanPham WHERE maSP = ? AND tenSP = ? AND loaiSP = ? AND ttkd = ?";
		PreparedStatement s = null;
		String str = null;
		try {
			s = conn.prepareStatement(sql);
			s.setString(1, maSP);
			s.setString(2, tenSP);
			s.setString(3, loaiSP);
			s.setString(4, ttkdStatus);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				str = rs.getString("imgPath");

			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return str;
	}

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

	public SanPham getSanPham(String id) {
		SanPham sp = null;
		String sql = "SELECT * FROM SanPham WHERE maSP = ?";
		try (Connection conn = ConnectDB.getConnection(); PreparedStatement s = conn.prepareStatement(sql)) {
			s.setString(1, id);
			ResultSet rs = s.executeQuery();
			if (rs.next()) {
				sp = new SanPham(rs.getString("imgPath"), rs.getString("maSP"), rs.getString("tenSP"),
						rs.getString("loaiSP"), rs.getBoolean("ttkd"), rs.getDouble("donGia"),
						rs.getString("chatLieu"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sp;
	}

	// get sp by masp
	public boolean addSanPham(SanPham sp) {
		try (Connection conn = ConnectDB.getConnection()) {
			String sql = "INSERT INTO SanPham (imgPath, tenSP, loaiSP, ttkd, donGia, chatLieu, maSP) VALUES (?, ?, ?, ?, ?, ?, ?)";
			try (PreparedStatement s = conn.prepareStatement(sql)) {
				String imgPath = sp.getImgPath();
				// System.out.println("Giá trị imgPath trước khi thêm: " + imgPath);
				if (imgPath == null || imgPath.isEmpty()) {
					imgPath = "default_image.jpg"; // Thiết lập giá trị mặc định nếu imgPath bị null hoặc rỗng
				}

				s.setString(1, imgPath);
				s.setString(2, sp.getTenSP());
				s.setString(3, sp.getLoaiSP());
				String ttkdStr = sp.isTTKD() ? "Kinh doanh" : "Ngừng kinh doanh";
				System.out.println("ttkd: " + ttkdStr);
				s.setString(4, ttkdStr);
				s.setDouble(5, sp.getDonGia());
				s.setString(6, sp.getChatLieu());
				s.setString(7, sp.getMaSP());
				int affectedRows = s.executeUpdate();
				if (affectedRows > 0) {
					System.out.println("Thêm sản phẩm thành công!");
					return true;
				}
			}
		} catch (SQLException e) {
			System.out.println("SQL Error: " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateSanPham(SanPham sp) {
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		String sql = "UPDATE SanPham SET tenSP = ?, loaiSP = ?, ttkd = ?, donGia = ?, chatLieu = ?,imgPath = ? WHERE MaSP = ?";
		PreparedStatement s = null;
		try {
			conn.setAutoCommit(false);

			// Ghi log để kiểm tra giá trị trước khi cập nhật
			System.out.println("Cập nhật sản phẩm: " + sp.getMaSP());
			System.out.println("TTKD: " + (sp.isTTKD() ? "Không kinh doanh" : "Kinh doanh"));
			s = conn.prepareStatement(sql);
			s.setString(1, sp.getTenSP());
			s.setString(2, sp.getLoaiSP());
			String ttkdStr = sp.isTTKD() ? "Kinh doanh" : "Ngừng kinh doanh";
			s.setString(3, ttkdStr);
			s.setDouble(4, sp.getDonGia());
			s.setString(5, sp.getChatLieu());
			String imgPath = sp.getImgPath() != null ? sp.getImgPath() : "default_image.jpg"; // Nếu null, dùng chuỗi
																								// rỗng
			s.setString(6, imgPath);
			s.setString(7, sp.getMaSP());

			int affectedRows = s.executeUpdate();
			if (affectedRows > 0) {
				conn.commit(); // Cam kết thay đổi
				System.out.println("Cập nhật thành công!");
				return true;
			} else {
				conn.rollback(); // Quay lại nếu không có dòng nào bị ảnh hưởng
				System.out.println("Không có dòng nào bị ảnh hưởng, rollback.");
			}
		} catch (SQLException e) {
			// Nếu có lỗi, rollback giao dịch
			try {
				if (conn != null) {
					conn.rollback();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			// Đặt lại chế độ auto-commit cho kết nối
			try {
				if (conn != null) {
					conn.setAutoCommit(true); // Đặt lại chế độ auto-commit về true
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false; // Cập nhật thất bại
	}

	// get for TraCuuSanPham dua tren 4 field masp, tensp, loai, ttkd
	public List<SanPham> findSanPham(String maSP, String tenSP, String loai, boolean ttkd) {
		List<SanPham> ds = new ArrayList<>();
		String sql = "{call findSanPham(?, ?, ?, ?)}";
		try (Connection conn = ConnectDB.getConnection(); CallableStatement cs = conn.prepareCall(sql)) {
			cs.setString(1, maSP != null && !maSP.isEmpty() ? maSP : null);
			cs.setString(2, tenSP != null && !tenSP.isEmpty() ? tenSP : null);
			cs.setString(3, loai != null && !loai.isEmpty() ? loai : null);
			String ttkdStr = ttkd ? "Kinh doanh" : "Không kinh doanh";
			cs.setString(4, ttkdStr);
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				boolean ttkdValue = rs.getBoolean("ttkd");
				SanPham sp = new SanPham(rs.getString("imgPath"), rs.getString("maSP"), rs.getString("tenSP"),
						rs.getString("loaiSP"), ttkdValue, rs.getDouble("donGia"), rs.getString("chatLieu"));
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
		String sql = "SELECT DISTINCT loaiSP FROM SanPham";
		PreparedStatement s = null;
		List<String> ds = new ArrayList<>();
		ResultSet rs = null;
		try {
			s = conn.prepareStatement(sql);
			rs = s.executeQuery();
			while (rs.next()) {
				String loai = rs.getString("loaiSP");

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
		ds.add("Kinh doanh");
		ds.add("Không kinh doanh");
		return ds;
	}

}