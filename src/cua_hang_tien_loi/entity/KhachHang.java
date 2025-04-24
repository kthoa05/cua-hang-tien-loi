package cua_hang_tien_loi.entity;

import cua_hang_tien_loi.dao.DAO_KhachHang;

public class KhachHang {
	private String maKH;
	private String tenKH;
	private boolean phai;
	private String sdt;

	public KhachHang() {
		super();
	}

	private String auto_ID() {
		DAO_KhachHang daoKH = new DAO_KhachHang();
		String idPrefix = "KH";
		int length = daoKH.getAllKhachHang().size();
		String finalId = idPrefix + String.format("%04d", length + 1);
		return finalId;
	}

	public KhachHang(String maKH, String tenKH, boolean phai, String sdt) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.phai = phai;
		this.sdt = sdt;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public boolean isPhai() {
		return phai;
	}

	public void setPhai(boolean phai) {
		this.phai = phai;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", tenKH=" + tenKH + ", phai=" + phai + ", sdt=" + sdt + "]";
	}

}
