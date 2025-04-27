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

public class DAO_NhanVien {

	private Connection connection;

	public DAO_NhanVien() {
		this.connection = ConnectDB.getInstance().getConnection();
	}

	private String maNV;

	// them nhan vien
	public boolean addNhanVien(NhanVien nv) {
		String sql = "INSERT INTO NhanVien (maNV, hoTen, phai, ngaySinh, sdt, email, cmnd, mk, isAdmin, trangThaiLamViec, imgPath) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = ConnectDB.getConnection(); PreparedStatement s = conn.prepareStatement(sql)) {

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
			int rowsAffected = s.executeUpdate();

			// Move the print statement inside the method
			System.out.println("Cập nhật NV với mã: " + nv.getMaNV());

			if (rowsAffected > 0) {
				return true;
			} else {
				System.out.println("Cập nhật không thành công, không có hàng nào bị ảnh hưởng.");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// update nhan vien dua tren ma nv
	public boolean updateNhanVien(NhanVien nv) {
		String sql = "UPDATE NhanVien SET hoTen = ?, phai = ?, ngaySinh = ?, sdt = ?, email = ?, cmnd = ?, mk = ?, isAdmin = ?, trangThaiLamViec = ?, imgPath = ? WHERE sdt = ? AND cmnd = ?";
		try (Connection conn = ConnectDB.getConnection(); PreparedStatement s = conn.prepareStatement(sql)) {
			s.setString(1, nv.getHoTen());
			s.setBoolean(2, nv.isPhai());
			s.setDate(3, nv.getNgaySinh() != null ? Date.valueOf(nv.getNgaySinh()) : null);
			s.setString(4, nv.getSdt());
			s.setString(5, nv.getEmail());
			s.setString(6, nv.getCmnd());
			s.setString(7, nv.getMk());
			s.setBoolean(8, nv.isAdmin());
			s.setBoolean(9, nv.isTrangThaiLamViec());
			s.setString(10, nv.getImgPath());
			s.setString(11, nv.getSdt()); // Sử dụng sdt và cmnd để xác định nhân viên
			s.setString(12, nv.getCmnd());

			int rowsAffected = s.executeUpdate();
			System.out.println("Cập nhật NV với SĐT: " + nv.getSdt() + " và CCCD: " + nv.getCmnd());

			if (rowsAffected > 0) {
				return true;
			} else {
				System.out.println("Cập nhật không thành công, không có hàng nào bị ảnh hưởng.");
				return false;
			}
		} catch (SQLException e) {
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
		String sql = "{call findNhanVienPhanTraCuu(?, ?, ?, ?, ?)}";
		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, maNV != null && !maNV.isEmpty() ? maNV : null);
			cs.setString(2, hoTen != null && !hoTen.isEmpty() ? hoTen : null);
			cs.setString(3, sdt != null && !sdt.isEmpty() ? sdt : null);
			cs.setBoolean(4, gioiTinh);
			cs.setString(5, cmnd != null && !cmnd.isEmpty() ? cmnd : null);

			// Log các tham số
			System.out.println("Tham số tìm kiếm:");
			System.out.println("maNV: " + maNV);
			System.out.println("hoTen: " + hoTen);
			System.out.println("sdt: " + sdt);
			System.out.println("gioiTinh: " + gioiTinh);
			System.out.println("cmnd: " + cmnd);

			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				NhanVien nv = new NhanVien(rs.getString("maNV"), rs.getString("hoTen"), rs.getBoolean("phai"),
						rs.getDate("ngaySinh").toLocalDate(), rs.getString("sdt"), rs.getString("email"),
						rs.getString("cmnd"), rs.getString("mk"), rs.getBoolean("isAdmin"),
						rs.getBoolean("trangThaiLamViec"), rs.getString("imgPath"));
				dsnv.add(nv);
			}

			// Log danh sách nhân viên trả về
			System.out.println("Danh sách nhân viên trả về: " + dsnv);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsnv;
	}

	// tim nhan vien cho phan tra cuu dua theo manv, tennv, sdt, gioitinh, cmnd
	public List<NhanVien> findNhanVienPhanTraCuu(String maNV, String hoTen) {
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		List<NhanVien> dsnv = new ArrayList<>();
		String sql = "{call findNhanVienPhanTraCuu1(?, ?)}";
		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, maNV != null && !maNV.isEmpty() ? maNV : null);
			cs.setString(2, hoTen != null && !hoTen.isEmpty() ? hoTen : null);

			// Log các tham số
			System.out.println("Tham số tìm kiếm:");
			System.out.println("maNV: " + maNV);
			System.out.println("hoTen: " + hoTen);

			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				System.out.println("jump");
				NhanVien nv = new NhanVien(rs.getString("maNV"), rs.getString("hoTen"), rs.getBoolean("phai"),
						rs.getDate("ngaySinh").toLocalDate(), rs.getString("sdt"), rs.getString("email"),
						rs.getString("cmnd"), rs.getString("mk"), rs.getBoolean("isAdmin"),
						rs.getBoolean("trangThaiLamViec"), rs.getString("imgPath"));
				dsnv.add(nv);
			}

			// Log danh sách nhân viên trả về
			System.out.println("Danh sách nhân viên trả về: " + dsnv);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsnv;
	}
	
	//lay NhanVien len by maNV
	public NhanVien findNhanVienPhanTraCuu(String maNV) {
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		NhanVien nv = new NhanVien();
		String sql = "{call findNhanVienPhanTraCuu2(?)}";
		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, maNV != null && !maNV.isEmpty() ? maNV : null);

			// Log các tham số
			System.out.println("Tham số tìm kiếm:");
			System.out.println("maNV: " + maNV);

			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				System.out.println("jump");
				nv = new NhanVien(rs.getString("maNV"), rs.getString("hoTen"), rs.getBoolean("phai"),
						rs.getDate("ngaySinh").toLocalDate(), rs.getString("sdt"), rs.getString("email"),
						rs.getString("cmnd"), rs.getString("mk"), rs.getBoolean("isAdmin"),
						rs.getBoolean("trangThaiLamViec"), rs.getString("imgPath"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nv;
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
			ResultSet rs = s.executeQuery();
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

	// get trang thau lam viec
	public List<String> getTrangThaiLV() {
		List<String> ds = new ArrayList<>();
		Connection conn = ConnectDB.getConnection();
		String sql = "SELECT trangThaiLamViec FROM NhanVien"; // Sửa tên cột thành 'trangThaiLamViec'
		try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				boolean ttlv = rs.getBoolean("trangThaiLamViec"); // Sửa tên cột thành 'trangThaiLamViec'
				String ttlvStr = ttlv ? "Đang làm việc" : "Ngưng làm việc";
				ds.add(ttlvStr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	// get img NV for tim kiem o CapNhatNhanVien:
	public String getImg(String ma, String ten) {
	    Connection conn = ConnectDB.getConnection();
	    String sql = "SELECT imgPath FROM NhanVien WHERE maNV = ? OR hoTen = ?";
	    String pathImg = "null";
	    try (PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, ma != null && !ma.isEmpty() ? ma : null);
	        ps.setString(2, ten != null && !ten.isEmpty() ? ten : null);

	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            pathImg = rs.getString("imgPath");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return pathImg;
	}


	// get List NV for TraCuuNhanVien dua tren 4 field maNV, tenNV, phai, sdt, cccd
	public List<NhanVien> findNhanVien(String ma, String ten, Boolean gt, String sdt, String cccd) {
		Connection conn = ConnectDB.getConnection();
		if (conn == null) {
			System.out.println("Lỗi: Không thể kết nối cơ sở dữ liệu.");
			return new ArrayList<>();
		}
		System.out.println("Tìm kiếm với các tham số:");
		System.out.println("maNV: " + ma);
		System.out.println("HoTen: " + ten);
		System.out.println("gioiTinh: " + gt);
		System.out.println("sdt: " + sdt);
		System.out.println("cccd: " + cccd);

		List<NhanVien> ds = new ArrayList<>();
		String sql = "{call findNhanVien(?, ?, ?, ?, ?)}";

		try (CallableStatement cs = conn.prepareCall(sql)) {
			cs.setString(1, ma != null && !ma.isEmpty() ? ma : null);
			cs.setString(2, ten != null && !ten.isEmpty() ? ten : null);
			if (gt == null) {
				cs.setNull(3, java.sql.Types.BOOLEAN); // Gán giá trị null nếu gt là null
			} else {
				cs.setBoolean(3, gt);
			}
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

	// get nhan vien by id
	public NhanVien getNhanVienByID(String maNV) {
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		NhanVien nv = null;

		String sql = "SELECT * FROM NhanVien WHERE maNV = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, maNV);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				// Nếu có bản ghi, trả về thông tin nhân viên
				nv = new NhanVien(rs.getString("maNV"), rs.getString("hoTen"), rs.getBoolean("phai"),
						rs.getDate("ngaySinh").toLocalDate(), rs.getString("sdt"), rs.getString("email"),
						rs.getString("cmnd"), rs.getString("mk"), rs.getBoolean("isAdmin"),
						rs.getBoolean("trangThaiLamViec"), rs.getString("imgPath"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return nv;
	}

}
