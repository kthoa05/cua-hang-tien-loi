create database quanlychtl;
use quanlychtl;

CREATE TABLE KhachHang(
    maKH VARCHAR(10) PRIMARY KEY NOT NULL,
    tenKH NVARCHAR(100) NOT NULL,
    phai BIT NOT NULL CHECK (phai IN (0, 1)),  -- Giới tính phải là 0 hoặc 1
    sdt VARCHAR(15) NOT NULL
);

CREATE TABLE NhanVien (
    maNV VARCHAR(10) PRIMARY KEY NOT NULL,
    hoTen NVARCHAR(100) NOT NULL,
    phai BIT NOT NULL CHECK (phai IN (0, 1)),  -- Giới tính phải là 0 hoặc 1
    ngaySinh DATE NOT NULL,
    sdt VARCHAR(15) NOT NULL,
    email VARCHAR(255) NOT NULL,
    cmnd VARCHAR(12) NOT NULL,
    mk VARCHAR(100) NOT NULL,
    isAdmin BIT NOT NULL,
    trangThaiLamViec BIT NOT NULL,
    imgPath VARCHAR(255)
);

CREATE TABLE HoaDon (
    maHD VARCHAR(10) PRIMARY KEY NOT NULL,
    maKH VARCHAR(10) NOT NULL,
    maNV VARCHAR(10) NOT NULL,
    ngayLapHD DATE NOT NULL,
    FOREIGN KEY (maKH) REFERENCES KhachHang(maKH),
    FOREIGN KEY (maNV) REFERENCES NhanVien(maNV)
);

CREATE TABLE SanPham (
    maSP VARCHAR(10) PRIMARY KEY NOT NULL,
    imgPath VARCHAR(255),
    tenSP NVARCHAR(255) NOT NULL,
    loaiSP NVARCHAR(255),
    TTKD BIT NOT NULL CHECK (TTKD IN (0, 1)),  -- Trạng thái kích hoạt phải là 0 hoặc 1
    donGia FLOAT NOT NULL CHECK (donGia >= 0),  -- Đơn giá không thể âm
    chatLieu NVARCHAR(255)
);

CREATE TABLE ChiTietHoaDon (
    maHD VARCHAR(10) NOT NULL,
    maSP VARCHAR(10) NOT NULL,
    soLuong INT NOT NULL CHECK (soLuong >= 0),  -- Số lượng không thể âm
    thanhTien BIGINT NOT NULL CHECK (thanhTien >= 0),  -- Thành tiền không thể âm
    FOREIGN KEY (maHD) REFERENCES HoaDon(maHD),
    FOREIGN KEY (maSP) REFERENCES SanPham(maSP)
);


