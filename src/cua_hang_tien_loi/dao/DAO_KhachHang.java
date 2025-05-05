package cua_hang_tien_loi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cua_hang_tien_loi.connectDB.ConnectDB;
import cua_hang_tien_loi.entity.KhachHang;

public class DAO_KhachHang {

    // Lấy khách hàng theo mã
    public KhachHang getKhachHangById(String id) {
        ConnectDB.getInstance();
        Connection conn = ConnectDB.getConnection();
        KhachHang kh = new KhachHang();
        try {
            String sql = "SELECT * FROM KhachHang WHERE maKH = ?";
            PreparedStatement stmt = conn.prepareStatement(sql); 
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                kh.setMaKH(rs.getString(1));
                kh.setTenKH(rs.getString(2));
                kh.setPhai(rs.getBoolean(3));
                kh.setSdt(rs.getString(4));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return kh;
    }

    // Tìm khách hàng cho TraCuuKhachHangQuanLy
    public ArrayList<KhachHang> getKhachHang(String ma, String ten, String sdt) {
        ArrayList<KhachHang> listKhachHang = new ArrayList<>();
        ConnectDB.getInstance();
        Connection conn = ConnectDB.getConnection();
        try {
            String sql = "SELECT * FROM KhachHang WHERE maKH = ? AND tenKH = ? AND sdt = ?";
            PreparedStatement stmt = conn.prepareStatement(sql); // Sử dụng prepareStatement
            stmt.setString(1, ma);
            stmt.setString(2, ten);
            stmt.setString(3, sdt);
            ResultSet rs = stmt.executeQuery(); // Không truyền lại chuỗi SQL
            while (rs.next()) {
                String maKhachHang = rs.getString(1);
                String hoVaTen = rs.getString(2);
                boolean gioiTinh = rs.getBoolean(3);
                String sdtKH = rs.getString(4);
                KhachHang kh = new KhachHang(maKhachHang, hoVaTen, gioiTinh, sdtKH);
                listKhachHang.add(kh);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listKhachHang;
    }

    // Lấy tất cả khách hàng
    public ArrayList<KhachHang> getAllKhachHang() {
        ArrayList<KhachHang> listKhachHang = new ArrayList<>();
        ConnectDB.getInstance();
        Connection conn = ConnectDB.getConnection();
        try {
            String sql = "SELECT * FROM KhachHang";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String maKhachHang = rs.getString(1);
                String hoVaTen = rs.getString(2);
                boolean gioiTinh = rs.getBoolean(3);
                String sdt = rs.getString(4);
                KhachHang kh = new KhachHang(maKhachHang, hoVaTen, gioiTinh, sdt);
                listKhachHang.add(kh);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listKhachHang;
    }

    // Lấy mã khách hàng
    public List<String> getMaKH() {
        List<String> ds = new ArrayList<String>();
        ConnectDB.getInstance();
        Connection conn = ConnectDB.getConnection();
        String sql = "SELECT maKH FROM KhachHang";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ds.add(rs.getString("maKH"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ds;
    }
}
