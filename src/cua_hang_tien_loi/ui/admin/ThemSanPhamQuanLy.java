package cua_hang_tien_loi.ui.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import cua_hang_tien_loi.utils.StyleUtils;
import cua_hang_tien_loi.utils.SystemUtils;

public class ThemSanPhamQuanLy extends JFrame implements ActionListener {

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
	private JMenuItem itemQuayLai;
	private JButton btnImg;
	private JTextField txtMaSanPham;
	private JTextField txtTenSanPham;
	private JComboBox<Object> cboLoaiSanPham;
	private JComboBox<Object> cboTrangThai;
	private JTextField txtChatLieu;
	private JButton btnLamMoi;
	private JButton btnThem;
	private JTextField txtDonGia;
	private SanPhamController sanPhamController;
	private JLabel lblImage;
	private String pathImg;
	private JMenuItem itemThongKeHoaDon;

	public ThemSanPhamQuanLy() {
		sanPhamController = new SanPhamController();
		this.UIFormThemSanPham();
	}

	private void UIFormThemSanPham() {
		setTitle("Quản lý cửa hàng tiện lợi - Thêm sản phẩm");
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
		cboLoaiSanPham = new JComboBox<>();
		for (String loai : sanPhamController.getLoaiSP()) {
			cboLoaiSanPham.addItem(loai);
		}

		pnLoaiSanPham.add(lblLoaiSanPham);
		pnLoaiSanPham.add(cboLoaiSanPham);

		// trang thai kinh doanh
		JPanel pnTrangThai = new JPanel();
		pnTrangThai.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTrangThai = new JLabel("TTKD:");
		cboTrangThai = new JComboBox<>();
		for (String status : sanPhamController.getTTKD()) {
			cboTrangThai.addItem(status);
		}
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

		// button
		JPanel pnBtn = new JPanel();
		pnBtn.setLayout(new FlowLayout(FlowLayout.LEFT));

		btnLamMoi = new JButton("Làm mới", new ImageIcon("src/cua_hang_tien_loi/icon/lammoi.png"));
		btnThem = new JButton("Thêm", new ImageIcon("src/cua_hang_tien_loi/icon/add.png"));

		pnBtn.add(btnLamMoi);
		pnBtn.add(btnThem);

		Dimension txtSize = new Dimension(120, 25);

		txtMaSanPham.setPreferredSize(txtSize);
		txtTenSanPham.setPreferredSize(txtSize);
		txtChatLieu.setPreferredSize(txtSize);
		txtDonGia.setPreferredSize(txtSize);
		cboLoaiSanPham.setPreferredSize(new Dimension(185, 25));
		cboTrangThai.setPreferredSize(new Dimension(185, 25));
		Dimension lblSize = new Dimension(120, 25);

		lblMaSanPham.setPreferredSize(lblSize);
		lblTenSanPham.setPreferredSize(lblSize);
		lblChatLieu.setPreferredSize(lblSize);
		lblDonGia.setPreferredSize(lblSize);
		lblTrangThai.setPreferredSize(lblSize);
		lblLoaiSanPham.setPreferredSize(lblSize);

		// add vo pnRight
		pnRightOfCen.add(pnChatLieu);
		pnRightOfCen.add(pnBtn);

		pnCen.add(pnRightOfCen);

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
			this.themSanPham();
			this.clearTxtField();
		}

	}

	// btn them
	private void themSanPham() {

		String pathImg = SystemUtils.imagePath;

		String ma = txtMaSanPham.getText();
		String ten = txtTenSanPham.getText();
		String loai = cboLoaiSanPham.getSelectedItem().toString();
		String ttkd = cboTrangThai.getSelectedItem().toString();
		double donGia = Double.parseDouble(txtDonGia.getText());
		String chatLieu = txtChatLieu.getText();

		boolean ttkdBoolean = ttkd.equals("Kinh Doanh") ? true : false;

		SanPham maSP = new SanPham();
		SanPham sp = new SanPham(pathImg, ma, ten, loai, ttkdBoolean, donGia, chatLieu);

		boolean statusThemSP = sanPhamController.themSanPham(sp);
		
		System.out.println("str img: " + pathImg);
		if (statusThemSP) {
			JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công", "Thông báo",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "Thêm sản phẩm thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
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
	}
}
