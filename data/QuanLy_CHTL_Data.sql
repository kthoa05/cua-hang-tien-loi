use quanlychtl;


INSERT INTO KhachHang (maKH, tenKH, phai, sdt)
VALUES 
    ('KH001', N'Nguyễn Mai Trúc', 0, '0901234567'),
    ('KH002', N'Lê Thanh Tâm', 1, '0912345678'),
    ('KH003', N'Phan Thị Mai', 0, '0923456789');

INSERT INTO NhanVien (maNV, hoTen, phai, ngaySinh, sdt, email, cmnd, mk, isAdmin, trangThaiLamViec, imgPath)
VALUES 
    ('NV001', N'Nguyễn Hoàng Anh', 0, '1990-05-12', '0987654321', 'nguyenhoanganh@email.com', '123456789012', 'password123', 1, 1, 'path_to_img1'),
    ('NV002', N'Lê Minh Tuấn', 0, '1985-08-22', '0976543210', 'leminhtuan@email.com', '123456789013', 'password456', 0, 1, 'path_to_img2'),
    ('NV003', N'Phạm Thuỳ Linh', 1, '1992-03-10', '0965432109', 'phamthuylinh@email.com', '123456789014', 'password789', 0, 1, 'path_to_img3');

INSERT INTO HoaDon (maHD, maKH, maNV, ngayLapHD)
VALUES 
    ('HD001', 'KH001', 'NV001', '2025-04-01'),
    ('HD002', 'KH002', 'NV002', '2025-04-02'),
    ('HD003', 'KH003', 'NV003', '2025-04-03');


INSERT INTO SanPham (maSP, imgPath, tenSP, loaiSP, TTKD, donGia, chatLieu)
VALUES 
    ('SP001', 'path_to_img1', N'Áo sơ mi nam', N'Áo', 1, 200000, N'Cotton'),
    ('SP002', 'path_to_img2', N'Váy nữ công sở', N'Váy', 1, 350000, N'Nylon'),
    ('SP003', 'path_to_img3', N'Giày thể thao', N'Giày', 1, 500000, N'Kaki');

INSERT INTO ChiTietHoaDon (maHD, maSP, soLuong, thanhTien)
VALUES 
    ('HD001', 'SP001', 2, 400000),
    ('HD002', 'SP002', 1, 350000),
    ('HD003', 'SP003', 3, 1500000);

INSERT INTO ChiTietHoaDon (maHD, maSP, soLuong, thanhTien)
VALUES 
    ('HD001', 'SP001', 2, 500000)
