package cua_hang_tien_loi.entity;

import java.time.LocalDate;

import cua_hang_tien_loi.dao.DAO_HoaDon;

public class HoaDon {
	private String maHD;
	private KhachHang kh;
	private NhanVien nv;
	private LocalDate ngayLapHD;
	private double tongTien;

	private String auto_IDHoaDon() {
		// auto gen id hóa đơn dạng HDXXXXXX
		DAO_HoaDon hoaDon_DAO = new DAO_HoaDon();
		String idPrefix = "HD";
		int length = hoaDon_DAO.getAllHoaDon().size();
		String finalId = idPrefix + String.format("%05d", length + 1);
		return finalId;

	}

	public HoaDon(String maHD, KhachHang kh, NhanVien nv, LocalDate ngayLapHD, double tongTien) {
		super();
		this.maHD = maHD;
		this.kh = kh;
		this.nv = nv;
		this.ngayLapHD = ngayLapHD;
		this.tongTien = tongTien;
	}

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public KhachHang getKh() {
		return kh;
	}

	public void setKh(KhachHang kh) {
		this.kh = kh;
	}

	public NhanVien getNv() {
		return nv;
	}

	public void setNv(NhanVien nv) {
		this.nv = nv;
	}

	public LocalDate getNgayLapHD() {
		return ngayLapHD;
	}

	public void setNgayLapHD(LocalDate ngayLapHD) {
		this.ngayLapHD = ngayLapHD;
	}

	public double getTongTien() {
		return tongTien;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

	@Override
	public String toString() {
		return "HoaDon [maHD=" + maHD + ", kh=" + kh + ", nv=" + nv + ", ngayLapHD=" + ngayLapHD + ", tongTien="
				+ tongTien + "]";
	}

}
