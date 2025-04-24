package cua_hang_tien_loi.controller;

import java.util.List;

import cua_hang_tien_loi.dao.DAO_SanPham;
import cua_hang_tien_loi.entity.SanPham;

public class SanPhamController {

	private DAO_SanPham daoSp;

	//add sp
	public boolean themSanPham(SanPham sp) {
		return daoSp.addSanPham(sp);
	}

	public boolean capNhatSanPham(SanPham sp) {
		return daoSp.updateSanPham(sp);
	}

	// cho phan TraCuuSP
	public List<SanPham> timKiemSanPham(String maSp, String tenSp, String loai, boolean ttkd) {
		return daoSp.findSanPham(maSp, tenSp, loai, ttkd);
	}

	// loai sp
	public List<String> getLoaiSP() {
		return daoSp.getLoaiSP();
	}

	// ttkd
	public List<String> getTTKD() {
		return daoSp.getDSTinhTrangKD();
	}

	// get by id
	public SanPham getById(String id) {
		return daoSp.getSanPham(id);
	}
}
