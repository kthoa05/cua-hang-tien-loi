package cua_hang_tien_loi.entity;

public class ChiTietHoaDon {
	private HoaDon hd;
	private SanPham sp;
	private int soLuong;
	private long thanhTien;

	public ChiTietHoaDon() {
		super();
	}

	public ChiTietHoaDon(HoaDon hd, SanPham sp, int soLuong, long thanhTien) {
		super();
		this.hd = hd;
		this.sp = sp;
		this.soLuong = soLuong;
		this.thanhTien = thanhTien;
	}

	public HoaDon getHd() {
		return hd;
	}

	public void setHd(HoaDon hd) {
		this.hd = hd;
	}

	public SanPham getSp() {
		return sp;
	}

	public void setSp(SanPham sp) {
		this.sp = sp;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public long getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(long thanhTien) {
		this.thanhTien = thanhTien;
	}

	@Override
	public String toString() {
		return "ChiTietHoaDon [hd=" + hd + ", sp=" + sp + ", soLuong=" + soLuong + ", thanhTien=" + thanhTien + "]";
	}

}
