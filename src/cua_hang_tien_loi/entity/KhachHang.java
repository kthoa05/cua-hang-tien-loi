package cua_hang_tien_loi.entity;

public class KhachHang {
	private String maKH;
	private String tenKH;
	private boolean phai;
	private String sdt;

	public KhachHang() {
		super();
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
