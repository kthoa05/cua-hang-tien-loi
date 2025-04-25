CREATE DATABASE QuanLy_CHTL

USE QuanLy_CHTL;

CREATE TABLE KhachHang(
    maKH VARCHAR(10) PRIMARY KEY NOT NULL,
    tenKH NVARCHAR(100) NOT NULL,
    phai BIT NOT NULL,  -- Giới tính phải là 0 hoặc 1
    sdt VARCHAR(15) NOT NULL   -- Số điện thoại phải có định dạng hợp lệ
);

CREATE TABLE NhanVien (
    maNV VARCHAR(10) PRIMARY KEY NOT NULL,
    hoTen NVARCHAR(100) NOT NULL,
    phai BIT NOT NULL ,  -- Giới tính phải là 0 hoặc 1
    ngaySinh DATE NOT NULL,
    sdt VARCHAR(15) NOT NULL,  -- Số điện thoại phải có định dạng hợp lệ
    email VARCHAR(255) NOT NULL,  -- Email phải có định dạng hợp lệ
    cmnd VARCHAR(12) NOT NULL ,  -- CMND phải có 12 ký tự số
    mk VARCHAR(100) NOT NULL ,  -- Mật khẩu phải có độ dài tối thiểu là 6 ký tự
    isAdmin BIT NOT NULL,  -- Trạng thái Admin phải là 0 hoặc 1
    trangThaiLamViec BIT NOT NULL ,  -- Trạng thái làm việc phải là 0 hoặc 1
    imgPath VARCHAR(255)
);

CREATE TABLE HoaDon (
    maHD VARCHAR(10) PRIMARY KEY NOT NULL,
    maKH VARCHAR(10) NOT NULL,
    maNV VARCHAR(10) NOT NULL,
    ngayLapHD DATE NOT NULL ,  -- Ngày lập hóa đơn phải không lớn hơn ngày hiện tại
    FOREIGN KEY (maKH) REFERENCES KhachHang(maKH),
    FOREIGN KEY (maNV) REFERENCES NhanVien(maNV)
);


CREATE TABLE SanPham(
    maSP INT IDENTITY(1,1) PRIMARY KEY,
    tenSP NVARCHAR(100),
    loaiSP NVARCHAR(50),
    ttkd NVARCHAR(100),
    donGia FLOAT NOT NULL,
    chatLieu NVARCHAR(50)
);

ALTER TABLE SanPham
ADD imgPath NVARCHAR(255);  -- Định nghĩa cột imgPath kiểu NVARCHAR, độ dài 255 ký tự



CREATE TABLE ChiTietHoaDon (
    maHD VARCHAR(10) NOT NULL,
    maSP INT IDENTITY(1,1) ,
    soLuong INT NOT NULL CHECK (soLuong >= 0),  -- Số lượng không thể âm
    thanhTien BIGINT NOT NULL CHECK (thanhTien >= 0),  -- Thành tiền không thể âm
    FOREIGN KEY (maHD) REFERENCES HoaDon(maHD),
    FOREIGN KEY (maSP) REFERENCES SanPham(maSP)
);