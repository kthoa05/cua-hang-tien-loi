package cua_hang_tien_loi.ui.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cua_hang_tien_loi.controller.ChiTietHoaDonController;
import cua_hang_tien_loi.controller.HoaDonController;
import cua_hang_tien_loi.controller.KhachHangController;
import cua_hang_tien_loi.entity.ChiTietHoaDon;
import cua_hang_tien_loi.entity.HoaDon;
import cua_hang_tien_loi.entity.NhanVien;
import cua_hang_tien_loi.utils.StyleUtils;
import cua_hang_tien_loi.utils.SystemUtils;

public class TraCuuHoaDonQuanLy extends JFrame implements ActionListener {

	private JMenuItem itemTaiKhoan;
	private JMenuItem itemTroGiup;
	private JMenuItem itemDangXuat;
	private JMenuItem itemThemSP;
	private JMenuItem itemTraCuuSP;
	private JMenuItem itemCapNhatSp;
	private JMenuItem itemTraCuuKH;
	private JMenuItem itemTraCuuHD;
	private JMenuItem itemThemHD;
	private JMenuItem itemTraCuuNV;
	private JMenuItem itemThemNV;
	private JMenuItem itemCapNhatNV;
	private JMenuItem itemQuayLai;
	private JTextField txtTenKH;
	private JTextField txtSDTKH;
	private JTextField txtTenNV;
	private JTextField txtNgayLap;
	private DefaultTableModel modelTable;
	private JTable table;
	private JButton btnTim;
	private JButton btnLamMoi;
	private HoaDonController hdController;
	private ChiTietHoaDonController cthdController;
	private JComboBox<Object> cboMaHD;
	private KhachHangController khController;
	private JComboBox<Object> cboMaKH;
	private JMenuItem itemThongKeHoaDon;

	public TraCuuHoaDonQuanLy() {
		this.hdController = new HoaDonController();
		this.khController = new KhachHangController();
		this.cthdController = new ChiTietHoaDonController();
		this.UITraCuuHoaDonQuanLy();
	}

	private void UITraCuuHoaDonQuanLy() {
		setTitle("Quản lý cửa hàng tiện lợi - Tra cứu hoá đơn");
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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

		menuKhachHang.add(itemTraCuuKH);
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

		// CAN LAM
		JPanel pn = new JPanel();
		pn.setLayout(new BoxLayout(pn, BoxLayout.Y_AXIS));
		JLabel lbTitle = StyleUtils.createHeaderTitle("TRA CỨU HÓA ĐƠN");
		lbTitle.setAlignmentX(CENTER_ALIGNMENT);
		lbTitle.setFont(new Font("Arial", Font.BOLD, 20));
		lbTitle.setForeground(Color.BLACK);
		pn.add(lbTitle);


		JPanel pn1 = new JPanel();
		pn1.setLayout(new BoxLayout(pn1, BoxLayout.X_AXIS));

		JLabel lbTenKH = new JLabel("Tên KH:");
		txtTenKH = new JTextField(25);
		JLabel lbMaHD = new JLabel("Mã HD");
		cboMaHD = new JComboBox<>();
		cboMaHD.addItem("None");
		for (String ma : hdController.getMaHoaDon()) {
			cboMaHD.addItem(ma);
		}

		JLabel lbSDTKH = new JLabel("SĐT KH:");
		txtSDTKH = new JTextField(25);
		pn1.add(lbTenKH);
		pn1.add(Box.createHorizontalStrut(10));
		pn1.add(txtTenKH);
		pn1.add(Box.createHorizontalStrut(10));
		pn1.add(lbMaHD);
		pn1.add(Box.createHorizontalStrut(10));
		pn1.add(cboMaHD);
		pn1.add(Box.createHorizontalStrut(10));
		pn1.add(lbSDTKH);
		pn1.add(Box.createHorizontalStrut(10));
		pn1.add(txtSDTKH);
		pn.add(Box.createVerticalStrut(10));
		pn.add(pn1);

		JPanel pn2 = new JPanel();
		pn2.setLayout(new BoxLayout(pn2, BoxLayout.X_AXIS));

		JLabel lbTenNV = new JLabel("Tên NV:");
		txtTenNV = new JTextField(25);
		JLabel lbMaKH = new JLabel("Mã KH");
		cboMaKH = new JComboBox<>();
		cboMaKH.addItem("None");
		for (String maKH : khController.getMaKH()) {
			cboMaKH.addItem(maKH);
		}
		JLabel lbNgayLap = new JLabel("Ngày lập:");
		txtNgayLap = new JTextField(25);
		pn2.add(lbTenNV);
		pn2.add(Box.createHorizontalStrut(10));
		pn2.add(txtTenNV);
		pn2.add(Box.createHorizontalStrut(10));
		pn2.add(lbMaKH);
		pn2.add(Box.createHorizontalStrut(10));
		pn2.add(cboMaKH);
		pn2.add(Box.createHorizontalStrut(10));
		pn2.add(lbNgayLap);
		pn2.add(Box.createHorizontalStrut(10));
		pn2.add(txtNgayLap);
		pn.add(Box.createVerticalStrut(10));
		pn.add(pn2);

		JPanel pn3 = new JPanel();

		pn3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnTim = new JButton("Tìm kiếm", new ImageIcon("src/cua_hang_tien_loi/icon/search.png"));
		btnLamMoi = new JButton("Làm mới", new ImageIcon("src/cua_hang_tien_loi/icon/reload.png"));
		pn3.add(btnTim);
		pn3.add(btnLamMoi);
		pn.add(Box.createVerticalStrut(10));
		pn.add(pn3);
		pnCen.add(pn);
		pnMain.add(pnCen, BorderLayout.CENTER);

		JPanel pnSouth = new JPanel();

		String[] title = { "Mã HD", "Mã KH", "Ngày lập", "Tên KH", "Nhân viên", "SĐT", "Tổng tiền" };
		modelTable = new DefaultTableModel(title, 0);
		table = new JTable(modelTable);
		JScrollPane scroll = new JScrollPane(table);

		scroll.setPreferredSize(new Dimension(1000, 350));
		pnSouth.add(scroll);

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
		btnTim.addActionListener(this);
		btnLamMoi.addActionListener(this);

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
		else if (source.equals(btnLamMoi)) {
			this.clear();
		} else if (source.equals(btnTim)) {
			this.traCuuHoaDon();
		}
	}

	private void traCuuHoaDon() {

		String ten = txtTenKH.getText();
		String maHD = cboMaHD.getSelectedItem().toString();
		String sdt = txtSDTKH.getText();
		String tenNV = txtTenNV.getText();
		String maKH = cboMaKH.getSelectedItem().toString();
		String ngayLap = txtNgayLap.getText();

		List<HoaDon> ds = hdController.traCuuHoaDon(ten, maHD, maKH, ngayLap, tenNV, sdt);
		ChiTietHoaDon cthd = cthdController.timChiTietTheoMa(maHD);
		
		modelTable.setRowCount(0);

		// load du lieu len table
		for (HoaDon hd : ds) {
			Object[] row = { hd.getMaHD(), hd.getKh().getMaKH(), hd.getNgayLapHD(), hd.getKh().getTenKH(),
					hd.getNv().getHoTen(), hd.getKh().getSdt(), cthd.getThanhTien() * cthd.getSoLuong() };
			modelTable.addRow(row);
		}
	}

	private void clear() {
		txtTenKH.setText("");
		cboMaKH.setSelectedIndex(0);
		txtSDTKH.setText("");
		txtTenNV.setText("");
		cboMaHD.setSelectedIndex(0);
		txtNgayLap.setText("");
		txtTenKH.requestFocus();
	}
}
