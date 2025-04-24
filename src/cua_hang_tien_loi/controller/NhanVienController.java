package cua_hang_tien_loi.controller;

import java.util.List;

import cua_hang_tien_loi.dao.DAO_NhanVien;
import cua_hang_tien_loi.entity.NhanVien;

public class NhanVienController {

	private DAO_NhanVien daoNV;

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

	// get for TraCuuNhanVien
	public List<NhanVien> getNV(String ma, String ten, boolean phai, String sdt, String cccd) {
		return daoNV.findNhanVien(ma, ten, phai, sdt, cccd);
	}
}
