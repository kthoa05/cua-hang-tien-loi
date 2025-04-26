package cua_hang_tien_loi.controller;

import java.util.List;

import cua_hang_tien_loi.dao.DAO_HoaDon;
import cua_hang_tien_loi.dao.DAO_KhachHang;
import cua_hang_tien_loi.entity.HoaDon;

public class HoaDonController {
	
	private DAO_HoaDon daoHD;
	
	public HoaDonController() {
	    daoHD = new DAO_HoaDon(); // Khởi tạo ở đây
	}


	// tra cuu hoa don
	public List<HoaDon> traCuuHoaDon(String tenKH, String maHD, String maKH, String ngayLapHD, String nhanVien,
			String sdt) {
		return daoHD.tracuuHoaDon(tenKH, maHD, maKH, ngayLapHD, nhanVien, sdt);
	}

	// get maHD
	public List<String> getMaHoaDon() {
		return daoHD.getMaHD();
	}

	// insert hd
	public boolean themHoaDon(HoaDon hd) {
		return daoHD.themHoaDon(hd);
	}

	// lay ngay len
	public List<Integer> getNgay() {
		return daoHD.getNgay();
	}

	// lay thang len
	public List<Integer> getThang() {
		return daoHD.getThang();
	}

	// lay nam len
	public List<Integer> getNam() {
		return daoHD.getNgay();
	}

	// thong ke
	public List<Object[]> thongKeHoaDon(int ngay, int thang, int nam) {
		return daoHD.thongKeChiTietHoaDon(ngay, thang, nam);
	}

	// get hoa don by mahd
	public HoaDon timHoaDonTheoMa(String maHD) {
		return daoHD.getHoaDonById(maHD);
	}
}
