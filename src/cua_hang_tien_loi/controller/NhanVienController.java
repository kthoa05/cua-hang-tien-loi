package cua_hang_tien_loi.controller;

import java.util.List;

import cua_hang_tien_loi.dao.DAO_NhanVien;
import cua_hang_tien_loi.entity.NhanVien;

public class NhanVienController {

    private DAO_NhanVien daoNV;

    // Constructor để khởi tạo đối tượng daoNV
    public NhanVienController() {
        this.daoNV = new DAO_NhanVien();
    }

    public boolean themNhanVien(NhanVien nv) {
        return daoNV.addNhanVien(nv);
    }

    // auth
    public boolean isAdmin(String sdt, String mk) {
        NhanVien nv = daoNV.getTaiKhoan(sdt, mk);
        return nv.isAdmin();
    }

    // get all for class ThongTinTaiKhoanQuanLy
    public NhanVien getThongTinTaiKhoan(String sdt, String mk) {
        return daoNV.getTaiKhoan(sdt, mk);
    }

    // get phai
    public List<String> getPhai() {
        return daoNV.getPhai();
    }
    
    public String getImg(String maNV,String hoTen) {
    	return daoNV.getImg(maNV, hoTen);
    }

    // get ttlv
    public List<String> getTrangThaiLamViec() {
        return daoNV.getTrangThaiLV();
    }

    // get for TraCuuNhanVien
    public List<NhanVien> getNV(String maNV, String hoTen, String sdt, boolean gioiTinh, String cmnd) {
        return daoNV.findNhanVienPhanTraCuu(maNV, hoTen, sdt, gioiTinh, cmnd);
    }
    
    //get for CapNhatNhanVien
    public List<NhanVien> getNV(String maNV, String hoTen) {
        return daoNV.findNhanVienPhanTraCuu(maNV, hoTen);
    }
    
    //get ma for CapNhatNhanVien'
    public NhanVien getNV(String maNV) {
        return daoNV.findNhanVienPhanTraCuu(maNV);
    }

    // cap nhat
    public boolean capNhatNhanVien(NhanVien nv) {
        return daoNV.updateNhanVien(nv);
    }

    // get all nv
    public NhanVien getNhanVien(String id) {
        return daoNV.getNhanVienByID(id);
    }

	
}
