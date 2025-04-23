package cua_hang_tien_loi.ui.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cua_hang_tien_loi.controller.SanPhamController;
import cua_hang_tien_loi.entity.SanPham;
import cua_hang_tien_loi.ui.DangNhap;
import cua_hang_tien_loi.utils.SystemUtils;
import cua_hang_tien_loi.utils.StyleUtils;

public class CapNhatSanPhamQuanLy extends JFrame implements ActionListener {

	private JMenuItem itemTaiKhoan;
	private JMenuItem itemTroGiup;
	private JMenuItem itemDangXuat;
	private JMenuItem itemThemSP;
	private JMenuItem itemTraCuuSP;
	private JMenuItem itemCapNhatSp;
	private JMenuItem itemTraCuuKH;
	private JMenuItem itemThemKH;
	private JMenuItem itemCapNhatKH;
	private JMenuItem itemTraCuuHD;
	private JMenuItem itemThemHD;
	private JMenuItem itemCapNhatHD;
	private JMenuItem itemTraCuuNV;
	private JMenuItem itemThemNV;
	private JMenuItem itemCapNhatNV;
	private JMenuItem itemDTTheoNgay;
	private JMenuItem itemDTTheoThang;
	private JMenuItem itemDTTheoNam;
	private JMenuItem itemQuayLai;
	private JButton btnImg;
	private JTextField txtMaSanPham;
	private JTextField txtTenSanPham;
	private JComboBox cboLoaiSanPham;
	private JComboBox cboTrangThai;
	private JTextField txtChatLieu;
	private JTextField txtKhuyenMai;
	private JTextField txtNgayBatDau;
	private JTextField txtNgayKetThuc;
	private JButton btnLamMoi;
	private JButton btnThem;
	private JTextField txtDonGia;
	private JTextField txtMaSP;
	private JTextField txtTenSp;
	private JComboBox<String> txtTTKD;
	private JButton btnCapNhat;
	private JLabel lblImage;
	private String pathImg;
	private SanPhamController sanPhamController;
	private JButton btnTimKiem;

	public CapNhatSanPhamQuanLy() {
		// TODO Auto-generated constructor stub
		this.UICapNhatSanPham();
	}

	private <E> void UICapNhatSanPham() {
		setTitle("Quản lý cửa hàng tiện lợi - Cập nhật sản phẩm");
		setSize(1000, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// main
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BorderLayout());

		// north
		JPanel pnNorth = new JPanel();
		pnNorth.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnNorth.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		pnNorth.setPreferredSize(new Dimension(750, 40));

		// Menu
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(null);

		// he thong
		JMenu menuHeThong = new JMenu("Hệ thống");
		menuHeThong.setIcon(new ImageIcon("src/cua_hang_tien_loi/icon/hethong.png"));
		menuHeThong.setBorderPainted(false);
		itemTaiKhoan = StyleUtils.createItemMenu("Tài khoản", "src/cua_hang_tien_loi/icon/account.png");
		itemTroGiup = StyleUtils.createItemMenu("Trợ giúp", "src/cua_hang_tien_loi/icon/helpdesk.png");
		itemDangXuat = StyleUtils.createItemMenu("Đăng xuất", "src/cua_hang_tien_loi/icon/logout.png");

		menuHeThong.add(itemTaiKhoan);
		menuHeThong.addSeparator();
		menuHeThong.add(itemTroGiup);
		menuHeThong.addSeparator();
		menuHeThong.add(itemDangXuat);
		menuBar.add(menuHeThong);
		menuBar.add(Box.createHorizontalStrut(25));

		// san pham
		JMenu menuSanPham = new JMenu("Sản phẩm");
		menuSanPham.setIcon(new ImageIcon("src/cua_hang_tien_loi/icon/product.png"));
		itemTraCuuSP = StyleUtils.createItemMenu("Tra cứu", "src/cua_hang_tien_loi/icon/search.png");
		itemThemSP = StyleUtils.createItemMenu("Thêm", "src/cua_hang_tien_loi/icon/add.png");
		itemCapNhatSp = StyleUtils.createItemMenu("Cập nhật", "src/cua_hang_tien_loi/icon/edit.png");

		menuHeThong.add(itemTaiKhoan);
		menuHeThong.addSeparator();
		menuSanPham.add(itemThemSP);
		menuSanPham.addSeparator();
		menuSanPham.add(itemCapNhatSp);
		menuBar.add(menuSanPham);
		menuBar.add(Box.createHorizontalStrut(25));

		// khach hang
		JMenu menuKhachHang = new JMenu("Khách hàng");
		menuKhachHang.setIcon(new ImageIcon("src/cua_hang_tien_loi/icon/customer.png"));
		itemTraCuuKH = StyleUtils.createItemMenu("Tra cứu", "src/cua_hang_tien_loi/icon/search.png");
		itemThemKH = StyleUtils.createItemMenu("Thêm", "src/cua_hang_tien_loi/icon/add.png");
		itemCapNhatKH = StyleUtils.createItemMenu("Cập nhật", "src/cua_hang_tien_loi/icon/edit.png");

		menuKhachHang.add(itemTraCuuKH);
		menuKhachHang.addSeparator();
		menuKhachHang.add(itemThemKH);
		menuKhachHang.addSeparator();
		menuKhachHang.add(itemCapNhatKH);
		menuBar.add(menuKhachHang);
		menuBar.add(Box.createHorizontalStrut(25));

		// hoa don
		JMenu menuHoaDon = new JMenu("Hoá đơn");
		menuHoaDon.setIcon(new ImageIcon("src/cua_hang_tien_loi/icon/invoice.png"));
		itemTraCuuHD = StyleUtils.createItemMenu("Tra cứu", "src/cua_hang_tien_loi/icon/search.png");
		itemThemHD = StyleUtils.createItemMenu("Thêm", "src/cua_hang_tien_loi/icon/add.png");
		itemCapNhatHD = StyleUtils.createItemMenu("Cập nhật", "src/cua_hang_tien_loi/icon/edit.png");

		menuHoaDon.add(itemTraCuuHD);
		menuHoaDon.addSeparator();
		menuHoaDon.add(itemThemHD);
		menuHoaDon.addSeparator();
		menuHoaDon.add(itemCapNhatHD);
		menuBar.add(menuHoaDon);
		menuBar.add(Box.createHorizontalStrut(25));

		// nhan vien
		JMenu menuNhanVien = new JMenu("Nhân viên");
		menuNhanVien.setIcon(new ImageIcon("src/cua_hang_tien_loi/icon/employee.png"));
		itemTraCuuNV = StyleUtils.createItemMenu("Tra cứu", "src/cua_hang_tien_loi/icon/search.png");
		itemThemNV = StyleUtils.createItemMenu("Thêm", "src/cua_hang_tien_loi/icon/add.png");
		itemCapNhatNV = StyleUtils.createItemMenu("Cập nhật", "src/cua_hang_tien_loi/icon/edit.png");

		menuNhanVien.add(itemTraCuuNV);
		menuNhanVien.addSeparator();
		menuNhanVien.add(itemThemNV);
		menuNhanVien.addSeparator();
		menuNhanVien.add(itemCapNhatNV);
		menuBar.add(menuNhanVien);
		menuBar.add(Box.createHorizontalStrut(25));

		// thong ke
		JMenu menuThongKe = new JMenu("Thống kê");
		menuThongKe.setIcon(new ImageIcon("src/cua_hang_tien_loi/icon/thongke.png"));

		JMenu itemDoanhThu = new JMenu("Doanh thu");
		itemDoanhThu.setIcon(new ImageIcon("src/cua_hang_tien_loi/icon/doanhthu.png"));

		itemDTTheoNgay = StyleUtils.createItemMenu("Theo ngày", "src/cua_hang_tien_loi/icon/day.png");
		itemDTTheoThang = StyleUtils.createItemMenu("Theo tháng", "src/cua_hang_tien_loi/icon/month.png");
		itemDTTheoNam = StyleUtils.createItemMenu("Theo năm", "src/cua_hang_tien_loi/icon/year.png");

		itemDoanhThu.add(itemDTTheoNgay);
		itemDoanhThu.addSeparator();
		itemDoanhThu.add(itemDTTheoThang);
		itemDoanhThu.addSeparator();
		itemDoanhThu.add(itemDTTheoNam);
		menuThongKe.add(itemDoanhThu);

		menuBar.add(menuThongKe);
		menuBar.add(Box.createHorizontalStrut(25));

		// quay lai
		menuBar.add(Box.createVerticalStrut(10));
		itemQuayLai = StyleUtils.createItemMenu("Quay lại (F1)", "src/cua_hang_tien_loi/icon/quaylai.png");
		menuBar.add(itemQuayLai);

		pnNorth.add(menuBar);
		pnNorth.add(Box.createVerticalStrut(5));

		pnMain.add(pnNorth, BorderLayout.NORTH);

		// cen
		JPanel pnCen = new JPanel();

		pnCen.setLayout(new BoxLayout(pnCen, BoxLayout.X_AXIS));

		// left of cen
		JPanel pnLeftOfCen = new JPanel();
		pnLeftOfCen.setLayout(new BorderLayout());
		pnLeftOfCen.setPreferredSize(new Dimension(200, 200));

		lblImage = new JLabel("Ảnh chưa chọn", JLabel.CENTER);
		lblImage.setPreferredSize(new Dimension(100, 100));
		lblImage.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnLeftOfCen.add(lblImage, BorderLayout.CENTER);

		btnImg = new JButton("Chọn ảnh");
		pnLeftOfCen.add(btnImg, BorderLayout.SOUTH);

		pnCen.add(pnLeftOfCen);

		// center of cen

		JPanel pnCenterOfCen = new JPanel();
		pnCenterOfCen.setLayout(new BoxLayout(pnCenterOfCen, BoxLayout.Y_AXIS));
		pnCenterOfCen.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		// ma sp
		JPanel pnMaSanPham = new JPanel();
		pnMaSanPham.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblMaSanPham = new JLabel("Mã sản phẩm:");
		txtMaSanPham = new JTextField(20);
		pnMaSanPham.add(lblMaSanPham);
		pnMaSanPham.add(txtMaSanPham);

		// ten sp
		JPanel pnTenSanPham = new JPanel();
		pnTenSanPham.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTenSanPham = new JLabel("Tên sản phẩm:");
		txtTenSanPham = new JTextField(19);
		pnTenSanPham.add(lblTenSanPham);
		pnTenSanPham.add(txtTenSanPham);

		// loai sp
		JPanel pnLoaiSanPham = new JPanel();
		pnLoaiSanPham.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblLoaiSanPham = new JLabel("Loại sản phẩm:");
		String[] loaiSanPham = { "Điện thoại", "Máy tính", "Tivi", "Máy ảnh" };
		cboLoaiSanPham = new JComboBox<>(loaiSanPham);
		pnLoaiSanPham.add(lblLoaiSanPham);
		pnLoaiSanPham.add(cboLoaiSanPham);

		// trang thai kinh doanh
		JPanel pnTrangThai = new JPanel();
		pnTrangThai.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTrangThai = new JLabel("TTKD:");
		String[] trangThai = { "Còn hàng", "Hết hàng", "Sắp ra mắt" };
		cboTrangThai = new JComboBox<>(trangThai);
		pnTrangThai.add(lblTrangThai);
		pnTrangThai.add(cboTrangThai);

		// don gia
		JPanel pnDonGia = new JPanel();
		pnDonGia.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblDonGia = new JLabel("Đơn giá:");
		txtDonGia = new JTextField(20);
		pnDonGia.add(lblDonGia);
		pnDonGia.add(txtDonGia);

		// add vo pn
		pnCenterOfCen.add(pnMaSanPham);
		pnCenterOfCen.add(pnTenSanPham);
		pnCenterOfCen.add(pnLoaiSanPham);
		pnCenterOfCen.add(pnTrangThai);
		pnCenterOfCen.add(pnDonGia);

		pnCen.add(pnCenterOfCen);

		// right of cen

		JPanel pnRightOfCen = new JPanel();
		pnRightOfCen.setLayout(new BoxLayout(pnRightOfCen, BoxLayout.Y_AXIS));
		pnRightOfCen.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		// chat lieu
		JPanel pnChatLieu = new JPanel();
		pnChatLieu.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblChatLieu = new JLabel("Chất liệu:");
		txtChatLieu = new JTextField(20);
		pnChatLieu.add(lblChatLieu);
		pnChatLieu.add(txtChatLieu);

		// % km
		JPanel pnKhuyenMai = new JPanel();
		pnKhuyenMai.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblKhuyenMai = new JLabel("% Khuyến mãi:");
		txtKhuyenMai = new JTextField(20);
		pnKhuyenMai.add(lblKhuyenMai);
		pnKhuyenMai.add(txtKhuyenMai);

		// ngay bd km
		JPanel pnNgayBatDau = new JPanel();
		pnNgayBatDau.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblNgayBatDau = new JLabel("Ngày bắt đầu KM:");
		txtNgayBatDau = new JTextField(20);
		pnNgayBatDau.add(lblNgayBatDau);
		pnNgayBatDau.add(txtNgayBatDau);

		// ngay ket thuc km
		JPanel pnNgayKetThuc = new JPanel();
		pnNgayKetThuc.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblNgayKetThuc = new JLabel("Ngày kết thúc KM:");
		txtNgayKetThuc = new JTextField(20);
		pnNgayKetThuc.add(lblNgayKetThuc);
		pnNgayKetThuc.add(txtNgayKetThuc);

		// button
		JPanel pnBtn = new JPanel();
		pnBtn.setLayout(new FlowLayout(FlowLayout.LEFT));

		btnLamMoi = new JButton("Làm mới", new ImageIcon("src/cua_hang_tien_loi/icon/lammoi.png"));
		btnCapNhat = new JButton("Cập nhật", new ImageIcon("src/cua_hang_tien_loi/icon/edit.png"));

		pnBtn.add(btnLamMoi);
		pnBtn.add(btnCapNhat);

		// add vo pnRight
		pnRightOfCen.add(pnChatLieu);
		pnRightOfCen.add(pnKhuyenMai);
		pnRightOfCen.add(pnNgayBatDau);
		pnRightOfCen.add(pnNgayKetThuc);
		pnRightOfCen.add(pnBtn);

		pnCen.add(pnRightOfCen);

		pnMain.add(pnCen, BorderLayout.CENTER);

		// south
		JPanel pnSouth = new JPanel();
		pnSouth.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));

		JPanel pnForm = new JPanel();
		pnForm.setLayout(new BoxLayout(pnForm, BoxLayout.X_AXIS));

		JLabel lblMaSp = new JLabel("Mã SP:");
		txtMaSP = new JTextField(10);

		JLabel lblTenSp = new JLabel("Tên SP:");
		txtTenSp = new JTextField(10);

		JLabel lblTTKD = new JLabel("TTKD:");
		String[] item = { "Kinh doanh", "Ngừng kinh doanh" };
		txtTTKD = new JComboBox<String>(item);

		btnTimKiem = new JButton("Tìm kiếm", new ImageIcon("src/cua_hang_tien_loi/icon/search.png"));

		// add vo panel
		pnSouth.add(lblMaSp);
		pnSouth.add(txtMaSP);
		pnSouth.add(lblTenSp);
		pnSouth.add(txtTenSp);
		pnSouth.add(lblTTKD);
		pnSouth.add(txtTTKD);
		pnSouth.add(Box.createHorizontalStrut(40));
		pnSouth.add(btnTimKiem);

		pnMain.add(pnSouth, BorderLayout.SOUTH);

		add(pnMain);

		// event

		// he thong
		itemTaiKhoan.addActionListener(this);
		itemTroGiup.addActionListener(this);
		itemDangXuat.addActionListener(this);

		// san pham
		itemTraCuuSP.addActionListener(this);
		itemCapNhatSp.addActionListener(this);
		itemThemSP.addActionListener(this);

		// khach hang
		itemTraCuuKH.addActionListener(this);
		itemCapNhatKH.addActionListener(this);
		itemThemKH.addActionListener(this);

		// hoa don
		itemTraCuuHD.addActionListener(this);
		itemThemHD.addActionListener(this);

		// nhan vien
		itemTraCuuNV.addActionListener(this);
		itemCapNhatNV.addActionListener(this);
		itemThemNV.addActionListener(this);

		// thong ke
		itemDTTheoNgay.addActionListener(this);
		itemDTTheoThang.addActionListener(this);
		itemDTTheoNam.addActionListener(this);

		// quay lai
		itemQuayLai.addActionListener(this);

		// btn
		btnCapNhat.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnImg.addActionListener(this);
		btnTimKiem.addActionListener(this);

		// key f1
		SystemUtils.setF1ToKey(pnMain, "F1", itemQuayLai);
	}

	public static void main(String[] args) {
		new CapNhatSanPhamQuanLy().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source.equals(itemTaiKhoan)) {
			this.thongTinTaiKhoan();
		} else if (source.equals(itemTroGiup)) {
			SystemUtils.openFile("/Users/lethoa/Documents/giaykhamsuckhoe.pdf");
		} else if (source.equals(itemDangXuat)) {
			this.dangXuat();
		} else if (source.equals(itemThemSP)) {
			this.setVisible(false);
			new FormThemSanPhamQuanLy().setVisible(true);
		} else if (source.equals(itemCapNhatSp)) {
			this.setVisible(true);
			new CapNhatSanPhamQuanLy().setVisible(true);
		} else if (source.equals(btnImg)) {
			this.chonAnhSanPham();
		} else if (source.equals(btnLamMoi)) {
			this.clearTxtField();
		} else if (source.equals(btnCapNhat)) {
			this.capNhatSanPham();
		} else if (source.equals(btnTimKiem)) {
			this.timKiemSanPham();
		}
	}

	private void timKiemSanPham() {
		String ma = txtMaSanPham.getText();
		String ten = txtTenSanPham.getText();
		String ttkd = cboTrangThai.getSelectedItem().toString();
		boolean ttkdBoolean = ttkd.equals("Kinh Doanh") ? true : false;

		List<SanPham> sp = sanPhamController.timKiemSanPham(ma, ten, ttkdBoolean);

		// do du lieu len form
	}

	// btn cap nhat
	private void capNhatSanPham() {
		String ma = txtMaSanPham.getText();
		String ten = txtTenSanPham.getText();
		String ttkd = cboTrangThai.getSelectedItem().toString();
		double donGia = Double.parseDouble(txtDonGia.getText());
		String chatLieu = txtChatLieu.getText();
		double km = Double.parseDouble(txtKhuyenMai.getText());
		LocalDate bd = LocalDate.parse(txtNgayBatDau.getText());
		LocalDate kt = LocalDate.parse(txtNgayKetThuc.getText());

		boolean ttkdBoolean = ttkd.equals("Kinh Doanh") ? true : false;

		SanPham sp = new SanPham(pathImg, ma, ten, chatLieu, ttkdBoolean, donGia, chatLieu, km, bd, kt);

		boolean statusCapNhatSP = sanPhamController.capNhatSanPham(sp);
		if (!statusCapNhatSP) {
			JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm sản phẩm thành công", "Thông báo",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	// btn them anh
	private void chonAnhSanPham() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Chọn ảnh sản phẩm");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int result = fileChooser.showOpenDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			String imagePath = selectedFile.getAbsolutePath();

			lblImage.setText("");
			lblImage.setIcon(
					new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));

			pathImg = imagePath;
		}
	}

	private void thongTinTaiKhoan() {

	}

	private void dangXuat() {
		int choice = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn đăng xuất?", "Thông báo",
				JOptionPane.YES_NO_OPTION);
		if (choice == JOptionPane.YES_OPTION) {
			this.setVisible(false);
			new DangNhap().setVisible(true);
		}
	}

	// btn lam moi
	private void clearTxtField() {
		lblImage.setText("Ảnh chưa chọn");
		lblImage.setIcon(null);
		pathImg = "";
		txtMaSanPham.setText("");
		txtTenSanPham.setText("");
		txtDonGia.setText("");
		txtChatLieu.setText("");
		txtKhuyenMai.setText("");
		txtNgayBatDau.setText("");
		txtNgayKetThuc.setText("");
	}
}
