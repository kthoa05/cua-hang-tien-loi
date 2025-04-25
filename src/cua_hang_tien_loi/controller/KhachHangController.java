package cua_hang_tien_loi.controller;

import java.util.List;

import cua_hang_tien_loi.dao.DAO_KhachHang;
import cua_hang_tien_loi.entity.KhachHang;

public class KhachHangController {

	private DAO_KhachHang daoKH;

	public KhachHang getKhachHang(String id) {
		return daoKH.getKhachHangById(id);
	}

	// get khachHang for TraCuuKhachHangQuanLy
	public List<KhachHang> getKhachHangForTraCuu(String ma, String ten, String sdt) {
		return daoKH.getKhachHang(ma, ten, sdt);
	}

	// get ma kh
	public List<String> getMaKH() {
		return daoKH.getMaKH();
	}
}
