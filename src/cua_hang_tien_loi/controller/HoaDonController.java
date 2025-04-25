package cua_hang_tien_loi.controller;

import java.util.List;

import cua_hang_tien_loi.dao.DAO_HoaDon;
import cua_hang_tien_loi.entity.HoaDon;

public class HoaDonController {

	private DAO_HoaDon daoHD;

	// tra cuu hoa don
	public List<HoaDon> traCuuHoaDon(String tenKH, String maHD, String maKH, String ngayLapHD, String nhanVien,
			String sdt) {
		return daoHD.tracuuHoaDon(tenKH, maHD, maKH, ngayLapHD, nhanVien, sdt);
	}

	// get maHD
	public List<String> getMaHoaDon() {
		return daoHD.getMaHD();
	}
}
