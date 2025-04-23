package cua_hang_tien_loi.entity;

import java.time.LocalDate;

public class SanPham {
	private String imgPath;
	private String maSP;
	private String tenSP;
	private String loaiSP;
	private boolean TTKD;
	private double donGia;
	private String chatLieu;
	private double phanTramKM;
	private LocalDate ngayBDKM;
	private LocalDate ngayKTKM;

	public SanPham(String imgPath, String maSP, String tenSP, String loaiSP, boolean tTKD, double donGia,
			String chatLieu, double phanTramKM, LocalDate ngayBDKM, LocalDate ngayKTKM) {
		super();
		this.imgPath = imgPath;
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.loaiSP = loaiSP;
		TTKD = tTKD;
		this.donGia = donGia;
		this.chatLieu = chatLieu;
		this.phanTramKM = phanTramKM;
		this.ngayBDKM = ngayBDKM;
		this.ngayKTKM = ngayKTKM;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getMaSP() {
		return maSP;
	}

	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public String getLoaiSP() {
		return loaiSP;
	}

	public void setLoaiSP(String loaiSP) {
		this.loaiSP = loaiSP;
	}

	public boolean isTTKD() {
		return TTKD;
	}

	public void setTTKD(boolean tTKD) {
		TTKD = tTKD;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	public String getChatLieu() {
		return chatLieu;
	}

	public void setChatLieu(String chatLieu) {
		this.chatLieu = chatLieu;
	}

	public double getPhanTramKM() {
		return phanTramKM;
	}

	public void setPhanTramKM(double phanTramKM) {
		this.phanTramKM = phanTramKM;
	}

	public LocalDate getNgayBDKM() {
		return ngayBDKM;
	}

	public void setNgayBDKM(LocalDate ngayBDKM) {
		this.ngayBDKM = ngayBDKM;
	}

	public LocalDate getNgayKTKM() {
		return ngayKTKM;
	}

	public void setNgayKTKM(LocalDate ngayKTKM) {
		this.ngayKTKM = ngayKTKM;
	}

	@Override
	public String toString() {
		return "SanPham [imgPath=" + imgPath + ", maSP=" + maSP + ", tenSP=" + tenSP + ", loaiSP=" + loaiSP + ", TTKD="
				+ TTKD + ", donGia=" + donGia + ", chatLieu=" + chatLieu + ", phanTramKM=" + phanTramKM + ", ngayBDKM="
				+ ngayBDKM + ", ngayKTKM=" + ngayKTKM + "]";
	}

}
