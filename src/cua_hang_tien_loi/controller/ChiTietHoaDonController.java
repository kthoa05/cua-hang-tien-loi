package cua_hang_tien_loi.controller;

import cua_hang_tien_loi.dao.DAO_ChiTietHoaDon;
import cua_hang_tien_loi.entity.ChiTietHoaDon;

public class ChiTietHoaDonController {

	private DAO_ChiTietHoaDon daoCTHD;

	// insert hd
	public boolean themCTHD(ChiTietHoaDon cthd) {
		return daoCTHD.addChiTietHoaDon(cthd);
	}

	// get mahd
	public ChiTietHoaDon timChiTietTheoMa(String ma) {
		return daoCTHD.getChiTietHoaDonById(ma);
	}
}
