package cua_hang_tien_loi.entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.jdi.connect.spi.Connection;

import cua_hang_tien_loi.connectDB.ConnectDB;
import cua_hang_tien_loi.dao.DAO_SanPham;

public class SanPham {
	public SanPham(String maSP) {
		super();
		this.maSP = maSP;
	}

	private String imgPath;
	private String maSP;
	private String tenSP;
	private String loaiSP;
	private boolean TTKD;
	private double donGia;
	private String chatLieu;

	public String auto_ID() {
		DAO_SanPham daoSP = new DAO_SanPham();
		String idPrefix = "SP";
		int length = daoSP.getAllSanPham().size();
		String finalId = idPrefix + String.format("%04d", length + 1);
		return finalId;
	}

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
		this.TTKD = tTKD;
	}

	public String getTTKDString() {
		return TTKD ? "Kinh doanh" : "Không kinh doanh";
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