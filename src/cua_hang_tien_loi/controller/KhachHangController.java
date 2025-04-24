package cua_hang_tien_loi.controller;

import cua_hang_tien_loi.dao.DAO_KhachHang;
import cua_hang_tien_loi.entity.KhachHang;

public class KhachHangController {

	private DAO_KhachHang daoKH;

	public KhachHang getKhachHang(String id) {
		return daoKH.getKhachHangById(id);
	}
}
