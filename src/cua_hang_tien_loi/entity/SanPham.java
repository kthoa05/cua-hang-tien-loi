package cua_hang_tien_loi.entity;

public class SanPham {
	private String imgPath;
	private String maSP;
	private String tenSP;
	private String loaiSP;
	private boolean TTKD;
	private double donGia;
	private String chatLieu;

	public SanPham() {
		// TODO Auto-generated constructor stub
	}

	public SanPham(String imgPath, String maSP, String tenSP, String loaiSP, boolean tTKD, double donGia,
			String chatLieu) {
		super();
		this.imgPath = imgPath;
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.loaiSP = loaiSP;
		TTKD = tTKD;
		this.donGia = donGia;
		this.chatLieu = chatLieu;

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

	@Override
	public String toString() {
		return "SanPham [maSP=" + maSP + ", tenSP=" + tenSP + ", loaiSP=" + loaiSP + ", TTKD=" + TTKD + ", donGia="
				+ donGia + ", chatLieu=" + chatLieu;
	}

}
