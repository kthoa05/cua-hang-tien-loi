USE QuanLy_CHTL;

--Dữ liệu bảng KhachHang:
INSERT INTO KhachHang (maKH, tenKH, phai, sdt)
VALUES 
    ('KH001', N'Nguyễn Mai Trúc', 0, '0901234567'),
    ('KH002', N'Lê Thanh Tâm', 1, '0912345678'),
    ('KH003', N'Phan Thị Mai', 0, '0923456789');

--Dữ liệu bảng NhanVien:
INSERT INTO NhanVien (maNV, hoTen, phai, ngaySinh, sdt, email, cmnd, mk, isAdmin, trangThaiLamViec, imgPath)
VALUES 
    ('NV001', N'Nguyễn Hoàng Anh', 0, '1990-05-12', '09', 'nguyenhoanganh@email.com', '123456789012', '123', 1, 1, 'path_to_img1'),
    ('NV002', N'Lê Minh Tuấn', 0, '1985-08-22', '08', 'leminhtuan@email.com', '123456789013', '456', 0, 1, 'path_to_img2'),
    ('NV003', N'Phạm Thuỳ Linh', 1, '1992-03-10', '07', 'phamthuylinh@email.com', '123456789014', '789', 0, 1, 'path_to_img3');

--Dữ liệu bảng HoaDon:
INSERT INTO HoaDon (maHD, maKH, maNV, ngayLapHD)
VALUES 
    ('HD001', 'KH001', 'NV001', '2025-04-01'),
    ('HD002', 'KH002', 'NV002', '2025-04-02'),
    ('HD003', 'KH003', 'NV003', '2025-04-03');

	SET IDENTITY_INSERT SanPham ON;
--Dữ liệu bảng SanPham:
INSERT INTO SanPham ( maSP ,imgPath, tenSP, loaiSP, TTKD, donGia, chatLieu)
VALUES 
    ( '001','path_to_img1', N'Áo sơ mi nam', N'Áo',N'Kinh Doanh'  , 200000, N'Cotton'),
    ( '002','path_to_img2', N'Váy nữ công sở', N'Váy',N'Không Kinh Doanh', 350000, N'Nylon'),
    ( '003','path_to_img3', N'Giày thể thao', N'Giày', N'Kinh Doanh', 500000, N'Kaki');
	SET IDENTITY_INSERT SanPham OFF ;

	SET IDENTITY_INSERT ChiTietHoaDon ON;
	
--Dữ liệu bảng ChiTietHoaDon:
INSERT INTO ChiTietHoaDon (soLuong, thanhTien, maHD, maSP)
VALUES 
    (2, 400000, 'HD001', '001'),
    (1, 350000, 'HD002', '002'),
    (3, 1500000, 'HD003', '003');
	SET IDENTITY_INSERT ChiTietHoaDon OFF;
