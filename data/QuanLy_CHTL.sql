CREATE DATABASE QuanLy_CHTL
ON PRIMARY
    (NAME = 'QuanLy_CHTL_DATA',
    FILENAME = 'C:\DL\QuanLy_CHTL_DATA.MDF',
    SIZE = 10,
    MAXSIZE = 40,
    FILEGROWTH = 1)
LOG ON 
    (NAME = 'QuanLy_CHTL_LOG',
    FILENAME = 'C:\DL\QuanLy_CHTL.LDF',
    SIZE = 6,
    MAXSIZE = 8,
    FILEGROWTH = 1);

USE QuanLy_CHTL;

CREATE TABLE KhachHang(
    maKH VARCHAR(10) PRIMARY KEY NOT NULL,
    tenKH NVARCHAR(100) NOT NULL,
    phai BIT NOT NULL CHECK (phai IN (0, 1)),  -- Giới tính phải là 0 hoặc 1
    sdt VARCHAR(15) NOT NULL CHECK (sdt LIKE '0[0-9][0-9][0-9][0-9][0-9][0-9][0-9]')  -- Số điện thoại phải có định dạng hợp lệ
);

CREATE TABLE NhanVien (
    maNV VARCHAR(10) PRIMARY KEY NOT NULL,
    hoTen NVARCHAR(100) NOT NULL,
    phai BIT NOT NULL CHECK (phai IN (0, 1)),  -- Giới tính phải là 0 hoặc 1
    ngaySinh DATE NOT NULL,
    sdt VARCHAR(15) NOT NULL CHECK (sdt LIKE '0[0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),  -- Số điện thoại phải có định dạng hợp lệ
    email VARCHAR(255) NOT NULL CHECK (email LIKE '%_@__%.__%'),  -- Email phải có định dạng hợp lệ
    cmnd VARCHAR(12) NOT NULL CHECK (cmnd LIKE '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),  -- CMND phải có 12 ký tự số
    mk VARCHAR(100) NOT NULL CHECK (LEN(mk) >= 6),  -- Mật khẩu phải có độ dài tối thiểu là 6 ký tự
    isAdmin BIT NOT NULL CHECK (isAdmin IN (0, 1)),  -- Trạng thái Admin phải là 0 hoặc 1
    trangThaiLamViec BIT NOT NULL CHECK (trangThaiLamViec IN (0, 1)),  -- Trạng thái làm việc phải là 0 hoặc 1
    imgPath VARCHAR(255)
);

CREATE TABLE HoaDon (
    maHD VARCHAR(10) PRIMARY KEY NOT NULL,
    maKH VARCHAR(10) NOT NULL,
    maNV VARCHAR(10) NOT NULL,
    ngayLapHD DATE NOT NULL CHECK (ngayLapHD <= GETDATE()),  -- Ngày lập hóa đơn phải không lớn hơn ngày hiện tại
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


