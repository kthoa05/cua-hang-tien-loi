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
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
import cua_hang_tien_loi.utils.StyleUtils;
import cua_hang_tien_loi.utils.SystemUtils;

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
	private JMenuItem itemTraCuuNV;
	private JMenuItem itemThemNV;
	private JMenuItem itemCapNhatNV;
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
	private JComboBox<Object> cbboGt;
	private JMenuItem itemThongKeHoaDon;

	public ThemNhanVien() {
		nvController = new NhanVienController();
		this.UIThemNhanVien();
	}

	// giao dien
	private void UIThemNhanVien() {
		setTitle("Quản lý cửa hàng tiện lợi - Thêm nhân viên");
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

		menuHoaDon.add(itemTraCuuHD);
		menuHoaDon.addSeparator();
		menuHoaDon.add(itemThemHD);
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

		itemThongKeHoaDon = StyleUtils.createItemMenu("Hoá đơn", "src/cua_hang_tien_loi/icon/invoice.png");

		menuThongKe.add(itemThongKeHoaDon);

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
		pnCen.setLayout(new BorderLayout());

		// left of cen
		JPanel pnLeftOfCen = new JPanel();
		pnLeftOfCen.setLayout(new BorderLayout());
		pnLeftOfCen.setPreferredSize(new Dimension(200, 100));

		lblImage = new JLabel("Ảnh chưa chọn", JLabel.CENTER);
		lblImage.setPreferredSize(new Dimension(100, 100));
		lblImage.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnLeftOfCen.add(lblImage, BorderLayout.CENTER);

		btnImg = new JButton("Chọn ảnh");
		pnLeftOfCen.add(btnImg, BorderLayout.SOUTH);

		pnCen.add(pnLeftOfCen, BorderLayout.WEST);

		// form input
		JPanel pnFormOfCen = new JPanel();
		pnFormOfCen.setBorder(BorderFactory.createTitledBorder("Thông tin nhân viên"));
		
		JPanel pn = new JPanel();
		pn.setLayout(new BoxLayout(pn, BoxLayout.Y_AXIS));
		pnFormOfCen.add(pn);
		// ma nv
		JPanel pnMaNV = new JPanel();
		pnMaNV.setLayout(new BoxLayout(pnMaNV, BoxLayout.X_AXIS));

		JLabel lblMaNV = StyleUtils.createLabel2("Mã nhân viên:", 100, 80);
		txtMaNV = new JTextField(25);
		JLabel lblHoTen = StyleUtils.createLabel2("Họ tên:", 100, 130);
		txtHoTen = new JTextField(25);
		
		pnMaNV.add(lblHoTen);
		pnMaNV.add(Box.createHorizontalStrut(50));
		pnMaNV.add(txtHoTen);
		pnMaNV.add(Box.createHorizontalStrut(10));
		pnMaNV.add(lblMaNV);
		pnMaNV.add(Box.createHorizontalStrut(10));
		pnMaNV.add(txtMaNV);
		pn.add(Box.createVerticalStrut(10));
		
		pn.add(pnMaNV);

		JPanel pn1 = new JPanel();
		pn1.setLayout(new BoxLayout(pn1, BoxLayout.X_AXIS));
		JLabel lblGioiTinh = StyleUtils.createLabel2("Giới tính:", 100, 180);
		cbboGt = new JComboBox<>();
		for (String gt : nvController.getPhai()) {
			cbboGt.addItem(gt);
		}
		cbboGt.setPreferredSize(new Dimension(252, 25));
		JLabel lblNgaySinh = StyleUtils.createLabel2("Ngày sinh:", 100, 230);
		txtNgaySinh = new JTextField(25);
		
		pn1.add(lblGioiTinh);
		pn1.add(Box.createHorizontalStrut(40));
		pn1.add(cbboGt);
		pn1.add(Box.createHorizontalStrut(10));
		pn1.add(lblNgaySinh);
		pn1.add(Box.createHorizontalStrut(36));
		pn1.add(txtNgaySinh);
		pn.add(Box.createVerticalStrut(10));
		pn.add(pn1);
		
		JPanel pn2 = new JPanel();
		pn2.setLayout(new BoxLayout(pn2, BoxLayout.X_AXIS));

		JLabel lblSdt = StyleUtils.createLabel2("Số điện thoại:", 100, 280);
		txtSdt = new JTextField(25);


		JLabel lblEmail = StyleUtils.createLabel2("Email:", 100, 330);
		txtEmail = new JTextField(25);

		pn2.add(lblSdt);
		pn2.add(Box.createHorizontalStrut(5));
		pn2.add(txtSdt);
		pn2.add(Box.createHorizontalStrut(10));
		pn2.add(lblEmail);
		pn2.add(Box.createHorizontalStrut(60));
		pn.add(Box.createVerticalStrut(10));
		pn2.add(txtEmail);

		pn.add(pn2);
		
		JPanel pn3 = new JPanel();
		pn3.setLayout(new BoxLayout(pn3, BoxLayout.X_AXIS));

		JLabel lblCmnd = StyleUtils.createLabel2("CCCD:", 100, 330);
		txtCmnd = new JTextField(25);
		JLabel lblMatKhau = StyleUtils.createLabel2("Mật khẩu:", 100, 330);
		txtMatKhau = new JTextField(25);
		pn3.add(lblCmnd);
		pn3.add(Box.createHorizontalStrut(60));
		pn3.add(txtCmnd);
		pn3.add(Box.createHorizontalStrut(10));
		pn3.add(lblMatKhau);
		pn3.add(Box.createHorizontalStrut(30));
		pn3.add(txtMatKhau);
		pn.add(Box.createVerticalStrut(10));
		pn.add(pn3);
		
		JPanel pn4 = new JPanel();
		pn4.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnLamMoi = new JButton("Làm mới", new ImageIcon("src/cua_hang_tien_loi/icon/lammoi.png"));
		btnThem = new JButton("Thêm", new ImageIcon("src/cua_hang_tien_loi/icon/add.png"));

		pn4.add(btnLamMoi);
		pn4.add(Box.createHorizontalStrut(10));
		pn4.add(btnThem);
		pn.add(Box.createVerticalStrut(10));
		pn.add(pn4);



		pnCen.add(pnFormOfCen, BorderLayout.CENTER);

		
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
		itemThongKeHoaDon.addActionListener(this);

		// quay lai
		itemQuayLai.addActionListener(this);

		// btn
		btnThem.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnImg.addActionListener(this);

		// key f1
		SystemUtils.setF1ToKey(pnMain, "F1", itemQuayLai);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();

		// he thong
		if (source.equals(itemTaiKhoan)) {
			new ThongTinTaiKhoanQuanLy().setVisible(true);
		} else if (source.equals(itemTroGiup)) {
			SystemUtils.openFile("/Users/lethoa/Documents/giaykhamsuckhoe.pdf");
		} else if (source.equals(itemDangXuat)) {
			SystemUtils.dangXuat(this);
		}

		// san pham
		else if (source.equals(itemThemSP)) {
			this.setVisible(false);
			new ThemSanPhamQuanLy().setVisible(true);
		} else if (source.equals(itemCapNhatSp)) {
			this.setVisible(false);
			new CapNhatSanPhamQuanLy().setVisible(true);
		} else if (source.equals(itemTraCuuSP)) {
			this.setVisible(false);
			new TraCuuSanPhamQuanLy().setVisible(true);
		}
		// khach hang
		else if (source.equals(itemTraCuuKH)) {
			this.setVisible(false);
			new TraCuuKhachHangQuanLy().setVisible(true);
		}

		// hoa don
		else if (source.equals(itemTraCuuHD)) {
			this.setVisible(false);
			new TraCuuHoaDonQuanLy().setVisible(true);
		} else if (source.equals(itemThemHD)) {
			this.setVisible(false);
			new ThemHoaDonQuanLy().setVisible(true);
		}

		// nhan vien
		else if (source.equals(itemTraCuuNV)) {
			this.setVisible(false);
			new TraCuuNhanVien().setVisible(true);
		} else if (source.equals(itemThemNV)) {
			this.setVisible(false);
			new ThemNhanVien().setVisible(true);
		} else if (source.equals(itemCapNhatNV)) {
			this.setVisible(false);
			new CapNhatNhanVien().setVisible(true);
		}

		// thong ke
		else if (source.equals(itemThongKeHoaDon)) {
			new ThongKeHoaDonQuanLy().setVisible(true);
		}

		// quay lai
		else if (source.equals(itemQuayLai)) {
			SystemUtils.quayLai(this);
		}

		// btn
		else if (source.equals(btnImg)) {
			SystemUtils.chonAnhSanPham(lblImage, pathImg);
		} else if (source.equals(btnLamMoi)) {
			this.clearTxtField();
		} else if (source.equals(btnThem)) {
			this.themNhanVien();
		}
	}

	// btn them
	
	private void themNhanVien() {
		String pathImg = SystemUtils.imagePath;
	    String ma = txtMaNV.getText();
	    String ten = txtHoTen.getText();
	    String phai = cbboGt.getSelectedItem().toString();
	    String ngaySinh = txtNgaySinh.getText();
	    String sdt = txtSdt.getText();
	    String email = txtEmail.getText();
	    String cmnd = txtCmnd.getText();
	    String mk = txtMatKhau.getText();
	    
	    // Định dạng ngày tháng
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    LocalDate ns;
	    try {
	        ns = LocalDate.parse(ngaySinh, formatter);
	    } catch (DateTimeParseException e) {
	        JOptionPane.showMessageDialog(this, "Định dạng ngày sinh không hợp lệ. Vui lòng nhập theo định dạng dd-MM-yyyy.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    
	    boolean gt = phai.equals("Nam") ? false : true;
	    NhanVien nv = new NhanVien(ma, ten, gt, ns, sdt, email, cmnd, mk, false, true, pathImg);
	    boolean statusThemNV = nvController.themNhanVien(nv);
	    if (statusThemNV) {
	        JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	    } else {
	        JOptionPane.showMessageDialog(this, "Thêm nhân viên thất bại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
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
		txtMaNV.setText("");
		txtHoTen.requestFocus();
	}
}
