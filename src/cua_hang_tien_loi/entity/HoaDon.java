package cua_hang_tien_loi.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import cua_hang_tien_loi.dao.DAO_ChiTietHoaDon;
import cua_hang_tien_loi.dao.DAO_HoaDon;

public class HoaDon {
	private String maHD;
	private KhachHang kh;
	private NhanVien nv;
	private Date ngayLapHD;
	private double tongTien;

	private String auto_IDHoaDon() {
		// auto gen id hóa đơn dạng HDXXXXXX
		DAO_HoaDon hoaDon_DAO = new DAO_HoaDon();
		String idPrefix = "HD";
		int length = hoaDon_DAO.getAllHoaDon().size();
		String finalId = idPrefix + String.format("%05d", length + 1);
		return finalId;

	}

	public HoaDon(String maHD, KhachHang kh, NhanVien nv, Date ngayLapHD) {
		super();
		this.maHD = maHD;
		this.kh = kh;
		this.nv = nv;
		this.ngayLapHD = ngayLapHD;
	}

	public HoaDon(String maHD, KhachHang kh, NhanVien nv, Date ngayLapHD, double tongTien) {
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

	public Date getNgayLapHD() {
		return ngayLapHD;
	}

	public void setNgayLapHD(Date ngayLapHD) {
		this.ngayLapHD = ngayLapHD;
	}

	@Override
	public String toString() {
		return "HoaDon [maHD=" + maHD + ", kh=" + kh + ", nv=" + nv + ", ngayLapHD=" + ngayLapHD + ", tongTien=";
	}

	public double tongTien() {
		long tongTien = 0;
		DAO_ChiTietHoaDon daoCTHD = new DAO_ChiTietHoaDon();
		ArrayList<ChiTietHoaDon> listChiTietHoaDon = daoCTHD.getAllCTHDByHoaDon(this);
		for (ChiTietHoaDon cthd : listChiTietHoaDon) {
			tongTien += cthd.getSoLuong();
		}

		return tongTien;
	}

}
