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

import cua_hang_tien_loi.controller.NhanVienController;
import cua_hang_tien_loi.entity.NhanVien;
import cua_hang_tien_loi.ui.DangNhap;
import cua_hang_tien_loi.utils.MenuUtils;

public class ThemNhanVien extends JFrame implements ActionListener {
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
	private JLabel lblImage;
	private JButton btnImg;
	private JTextField txtMaNV;
	private JTextField txtHoTen;
	private JComboBox<String> txtGioiTinh;
	private JTextField txtNgaySinh;
	private JTextField txtSdt;
	private JTextField txtEmail;
	private JTextField txtMatKhau;
	private JButton btnLamMoi;
	private JButton btnThem;
	private String pathImg;
	private JTextField txtCmnd;
	private NhanVienController nvController;

	public ThemNhanVien() {
		// TODO Auto-generated constructor stub
		this.UIThemNhanVien();
	}

	// giao dien
	private void UIThemNhanVien() {
		setTitle("Quản lý cửa hàng tiện lợi - Trang chủ");
		setSize(1000, 750);
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
		itemTaiKhoan = MenuUtils.createItemMenu("Tài khoản", "src/cua_hang_tien_loi/icon/account.png");
		itemTroGiup = MenuUtils.createItemMenu("Trợ giúp", "src/cua_hang_tien_loi/icon/helpdesk.png");
		itemDangXuat = MenuUtils.createItemMenu("Đăng xuất", "src/cua_hang_tien_loi/icon/logout.png");

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
		itemTraCuuSP = MenuUtils.createItemMenu("Tra cứu", "src/cua_hang_tien_loi/icon/search.png");
		itemThemSP = MenuUtils.createItemMenu("Thêm", "src/cua_hang_tien_loi/icon/add.png");
		itemCapNhatSp = MenuUtils.createItemMenu("Cập nhật", "src/cua_hang_tien_loi/icon/edit.png");

		menuSanPham.add(itemTraCuuSP);
		menuSanPham.addSeparator();
		menuSanPham.add(itemThemSP);
		menuSanPham.addSeparator();
		menuSanPham.add(itemCapNhatSp);
		menuBar.add(menuSanPham);
		menuBar.add(Box.createHorizontalStrut(25));

		// khach hang
		JMenu menuKhachHang = new JMenu("Khách hàng");
		menuKhachHang.setIcon(new ImageIcon("src/cua_hang_tien_loi/icon/customer.png"));
		itemTraCuuKH = MenuUtils.createItemMenu("Tra cứu", "src/cua_hang_tien_loi/icon/search.png");
		itemThemKH = MenuUtils.createItemMenu("Thêm", "src/cua_hang_tien_loi/icon/add.png");
		itemCapNhatKH = MenuUtils.createItemMenu("Cập nhật", "src/cua_hang_tien_loi/icon/edit.png");

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
		itemTraCuuHD = MenuUtils.createItemMenu("Tra cứu", "src/cua_hang_tien_loi/icon/search.png");
		itemThemHD = MenuUtils.createItemMenu("Thêm", "src/cua_hang_tien_loi/icon/add.png");
		itemCapNhatHD = MenuUtils.createItemMenu("Cập nhật", "src/cua_hang_tien_loi/icon/edit.png");

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
		itemTraCuuNV = MenuUtils.createItemMenu("Tra cứu", "src/cua_hang_tien_loi/icon/search.png");
		itemThemNV = MenuUtils.createItemMenu("Thêm", "src/cua_hang_tien_loi/icon/add.png");
		itemCapNhatNV = MenuUtils.createItemMenu("Cập nhật", "src/cua_hang_tien_loi/icon/edit.png");

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

		itemDTTheoNgay = MenuUtils.createItemMenu("Theo ngày", "src/cua_hang_tien_loi/icon/day.png");
		itemDTTheoThang = MenuUtils.createItemMenu("Theo tháng", "src/cua_hang_tien_loi/icon/month.png");
		itemDTTheoNam = MenuUtils.createItemMenu("Theo năm", "src/cua_hang_tien_loi/icon/year.png");

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
		itemQuayLai = MenuUtils.createItemMenu("Quay lại (F1)", "src/cua_hang_tien_loi/icon/quaylai.png");
		menuBar.add(itemQuayLai);

		pnNorth.add(menuBar);
		pnNorth.add(Box.createVerticalStrut(5));

		pnMain.add(pnNorth, BorderLayout.NORTH);

		// cen
		JPanel pnCen = new JPanel();
		pnCen.setLayout(new BorderLayout());

		// left of cen
		JPanel pnLeftOfCen = new JPanel();
		pnLeftOfCen.setLayout(new BorderLayout());

		lblImage = new JLabel("Ảnh chưa chọn", JLabel.CENTER);
		lblImage.setPreferredSize(new Dimension(100, 100));
		lblImage.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnLeftOfCen.add(lblImage, BorderLayout.CENTER);

		btnImg = new JButton("Chọn ảnh");
		pnLeftOfCen.add(btnImg, BorderLayout.SOUTH);

		pnCen.add(pnLeftOfCen, BorderLayout.WEST);

		// form input
		JPanel pnFormOfCen = new JPanel();
		pnFormOfCen.setLayout(new BoxLayout(pnFormOfCen, BoxLayout.Y_AXIS));
		pnFormOfCen.setBorder(BorderFactory.createTitledBorder("Thông tin nhân viên"));

		// ma nv
		JPanel pnMaNV = new JPanel();
		pnMaNV.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel lblMaNV = MenuUtils.createLabel("Mã nhân viên:", 100, 80);
		txtMaNV = MenuUtils.createTextField(250, 80, 300, 30);

		pnMaNV.add(lblMaNV);
		pnMaNV.add(txtMaNV);

		pnFormOfCen.add(pnMaNV);

		// ho ten gioi tinh
		JPanel pnHoTenGT = new JPanel();
		pnHoTenGT.setLayout(new FlowLayout(FlowLayout.LEFT));

		// ho ten
		JLabel lblHoTen = MenuUtils.createLabel("Họ tên:", 100, 130);
		txtHoTen = MenuUtils.createTextField(250, 130, 300, 30);

		// gioi tinh
		JLabel lblGioiTinh = MenuUtils.createLabel("Giới tính:", 100, 180);
		txtGioiTinh = MenuUtils.createComboBox(new String[] { "Nam", "Nữ", "Khác" }, 250, 180, 100, 30);

		// add ho ten gt vo pn
		pnHoTenGT.add(lblHoTen);
		pnHoTenGT.add(txtHoTen);
		pnHoTenGT.add(lblGioiTinh);
		pnHoTenGT.add(txtGioiTinh);

		pnFormOfCen.add(pnHoTenGT);

		// ngay sinh
		JPanel pnNgaySinh = new JPanel();
		pnNgaySinh.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel lblNgaySinh = MenuUtils.createLabel("Ngày sinh:", 100, 230);
		txtNgaySinh = MenuUtils.createTextField(250, 230, 300, 30);

		pnNgaySinh.add(lblNgaySinh);
		pnNgaySinh.add(txtNgaySinh);

		pnFormOfCen.add(pnNgaySinh);

		// so dien thoai
		JPanel pnSDT = new JPanel();
		pnSDT.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel lblSdt = MenuUtils.createLabel("Số điện thoại:", 100, 280);
		txtSdt = MenuUtils.createTextField(250, 280, 300, 30);

		pnSDT.add(lblSdt);
		pnSDT.add(txtSdt);

		pnFormOfCen.add(pnSDT);

		// email
		JPanel pnEmail = new JPanel();
		pnEmail.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel lblEmail = MenuUtils.createLabel("Email:", 100, 330);
		txtEmail = MenuUtils.createTextField(250, 330, 300, 30);

		pnEmail.add(lblEmail);
		pnEmail.add(txtEmail);

		pnFormOfCen.add(pnEmail);

		// cmnd
		JPanel pnCmnd = new JPanel();
		pnCmnd.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel lblCmnd = MenuUtils.createLabel("CCCD:", 100, 330);
		txtCmnd = MenuUtils.createTextField(250, 330, 300, 30);

		pnCmnd.add(lblCmnd);
		pnCmnd.add(txtCmnd);

		pnFormOfCen.add(pnCmnd);

		// mat khau
		JPanel pnMatKhau = new JPanel();
		pnMatKhau.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel lblMatKhau = MenuUtils.createLabel("Mật khẩu:", 100, 330);
		txtMatKhau = MenuUtils.createTextField(250, 330, 300, 30);

		pnMatKhau.add(lblMatKhau);
		pnMatKhau.add(txtMatKhau);

		pnFormOfCen.add(pnMatKhau);

		pnCen.add(pnFormOfCen, BorderLayout.CENTER);

		// btn
		JPanel pnBtn = new JPanel();
		pnBtn.setLayout(new FlowLayout(FlowLayout.LEFT));

		btnLamMoi = new JButton("Làm mới", new ImageIcon("src/cua_hang_tien_loi/icon/lammoi.png"));
		btnThem = new JButton("Thêm", new ImageIcon("src/cua_hang_tien_loi/icon/add.png"));

		pnBtn.add(btnLamMoi);
		pnBtn.add(btnThem);

		pnCen.add(pnBtn, BorderLayout.SOUTH);
		pnMain.add(pnCen, BorderLayout.CENTER);

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
		btnThem.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnImg.addActionListener(this);

	}

	public static void main(String[] args) {
		new ThemNhanVien().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source.equals(itemTaiKhoan)) {
			this.thongTinTaiKhoan();
		} else if (source.equals(itemTroGiup)) {

		} else if (source.equals(itemDangXuat)) {
			this.dangXuat();
		} else if (source.equals(itemThemSP)) {
			this.setVisible(false);
			new FormThemSanPhamQuanLy().setVisible(true);
		} else if (source.equals(itemCapNhatSp)) {
			this.setVisible(true);
			new CapNhatSanPhamQuanLy().setVisible(true);
		} else if (source.equals(btnImg)) {
			this.chonAnhNhanVien();
		} else if (source.equals(btnLamMoi)) {
			this.clearTxtField();
		} else if (source.equals(btnThem)) {
			this.themNhanVien();
		}
	}

	private void thongTinTaiKhoan() {

	}

	// btn them
	private void themNhanVien() {
		String ma = txtMaNV.getText();
		String ten = txtHoTen.getText();
		String phai = txtGioiTinh.getSelectedItem().toString();
		String ngaySinh = txtNgaySinh.getText();
		String sdt = txtSdt.getText();
		String email = txtEmail.getText();
		String cmnd = txtCmnd.getText();
		String mk = txtMatKhau.getText();

		LocalDate ns = LocalDate.parse(ngaySinh);
		boolean gt = phai.equals("Nam") ? false : true;

		NhanVien nv = new NhanVien(ma, ten, gt, ns, sdt, email, cmnd, mk, false, true);

		boolean statusThemNV = nvController.themNhanVien(nv);
		if (!statusThemNV) {
			JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công", "Thông báo",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "Thêm nhân viên thất bại", "Thông báo",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	// btn them anh
	private void chonAnhNhanVien() {
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
		lblImage.setText("");
		lblImage.setIcon(null);
		pathImg = "";
		txtHoTen.setText("");
		txtNgaySinh.setText("");
		txtSdt.setText("");
		txtEmail.setText("");
		txtCmnd.setText("");
		txtMatKhau.setText("");
	}
}
