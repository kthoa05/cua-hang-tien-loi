package cua_hang_tien_loi.controller;

import java.util.List;

import cua_hang_tien_loi.dao.DAO_SanPham;
import cua_hang_tien_loi.entity.SanPham;

public class SanPhamController {

    private DAO_SanPham daoSp;

    // Constructor để khởi tạo daoSp
    public SanPhamController() {
        daoSp = new DAO_SanPham(); // Khởi tạo đối tượng DAO_SanPham
    }

    // Thêm sản phẩm
    public boolean themSanPham(SanPham sp) {
        System.out.println("Thêm sản phẩm với Tên: " + sp.getTenSP() + ", Loại: " + sp.getLoaiSP());
        return daoSp.addSanPham(sp);
    }

    // Cập nhật sản phẩm
    public boolean capNhatSanPham(SanPham sp) {
        return daoSp.updateSanPham(sp);
    }

    // Tìm kiếm sản phẩm
    public List<SanPham> timKiemSanPham(String maSp, String tenSp, String loai, boolean ttkd) {
        return daoSp.findSanPham(maSp, tenSp, loai, ttkd);
    }

    // Lấy danh sách loại sản phẩm
    public List<String> getLoaiSP() {
        return daoSp.getLoaiSP();
    }

    // Lấy danh sách tình trạng kinh doanh
    public List<String> getTTKD() {
        return daoSp.getDSTinhTrangKD();
    }

    // Lấy sản phẩm theo ID
    public SanPham getById(String id) {
        return daoSp.getSanPham(id);
    }
}