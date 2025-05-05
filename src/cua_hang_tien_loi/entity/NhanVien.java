package cua_hang_tien_loi.entity;

import java.time.LocalDate;

import cua_hang_tien_loi.dao.DAO_NhanVien;

public class NhanVien {
	private String maNV;
	private String hoTen;
	private boolean phai;
	private LocalDate ngaySinh;
	private String sdt;
	private String email;
	private String cmnd;
	private String mk;
	private boolean isAdmin;
	private boolean trangThaiLamViec;
	private String imgPath;

	public String auto_ID() {
		DAO_NhanVien daoNV = new DAO_NhanVien();
		int length = daoNV.getAllNhanVien().size();
		String finalId = String.format("%03d", length + 1);
		return finalId;
	}

	public NhanVien() {
		super();
	}

	public NhanVien(String maNV, String hoTen, boolean phai, String sdt, String email, String cmnd, String mk,
			boolean trangThaiLamViec) {
		super();
		this.maNV = maNV;
		this.hoTen = hoTen;
		this.phai = phai;
		this.sdt = sdt;
		this.email = email;
		this.cmnd = cmnd;
		this.mk = mk;
		this.trangThaiLamViec = trangThaiLamViec;
	}

	public NhanVien(String maNV, String hoTen, boolean phai, LocalDate ngaySinh, String sdt, String email, String cmnd,
			String mk, boolean isAdmin, boolean trangThaiLamViec, String imgPath) {
		super();
		this.maNV = maNV;
		this.hoTen = hoTen;
		this.phai = phai;
		this.ngaySinh = ngaySinh;
		this.sdt = sdt;
		this.email = email;
		this.cmnd = cmnd;
		this.mk = mk;
		this.isAdmin = isAdmin;
		this.setTrangThaiLamViec(trangThaiLamViec);
		this.imgPath = imgPath;
	}

	public NhanVien(String hoTen, boolean phai, String sdt, String cmnd, boolean trangThaiLamViec, String ten,
			String gioiTinh, String cccd, String ttlv) {
		super();
		this.hoTen = hoTen;
		this.phai = phai;
		this.sdt = sdt;
		this.cmnd = cmnd;
		this.trangThaiLamViec = trangThaiLamViec;
	}

	public NhanVien(String hoTen) {
		super();
		this.hoTen = hoTen;
	}


	public NhanVien(String maNV2, Object hoTen2) {
		// TODO Auto-generated constructor stub
		this.maNV = maNV2;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public boolean isPhai() {
		return phai;
	}

	public void setPhai(boolean phai) {
		this.phai = phai;
	}

	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public String getMk() {
		return mk;
	}

	public void setMk(String mk) {
		this.mk = mk;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", hoTen=" + hoTen + ", phai=" + phai + ", ngaySinh=" + ngaySinh + ", sdt="
				+ sdt + ", email=" + email + ", cmnd=" + cmnd + ", mk=" + mk + ", isAdmin=" + isAdmin + "]";
	}

	public boolean isTrangThaiLamViec() {
		return trangThaiLamViec;
	}

	public void setTrangThaiLamViec(boolean trangThaiLamViec) {
		this.trangThaiLamViec = trangThaiLamViec;
	}

}
