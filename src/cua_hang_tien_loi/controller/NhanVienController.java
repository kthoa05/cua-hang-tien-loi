package cua_hang_tien_loi.controller;

import cua_hang_tien_loi.dao.DAO_NhanVien;
import cua_hang_tien_loi.entity.NhanVien;

public class NhanVienController {

	private DAO_NhanVien daoNV;

	public boolean themNhanVien(NhanVien nv) {
		return daoNV.addNhanVien(nv);
	}
}
