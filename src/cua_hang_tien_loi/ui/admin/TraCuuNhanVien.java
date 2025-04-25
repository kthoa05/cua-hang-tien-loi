package cua_hang_tien_loi.ui.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

import cua_hang_tien_loi.controller.NhanVienController;
import cua_hang_tien_loi.entity.NhanVien;
import cua_hang_tien_loi.utils.StyleUtils;
import cua_hang_tien_loi.utils.SystemUtils;

public class TraCuuNhanVien extends JFrame implements ActionListener {
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
	private JTextField txtMa;
	private JTextField txtTen;
	private JTextField txtSdt;
	private JComboBox<Object> cbGT;
	private JTextField txtCccd;
	private JButton btnTim;
	private JButton btnLamMoi;
	private JTable table;
	private DefaultTableModel modelTable;
	private NhanVienController nvController;

	public TraCuuNhanVien() {
		// TODO Auto-generated constructor stub
		this.UITraCuuNhanVien();
	}

	// giao diện
	private void UITraCuuNhanVien() {
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

		// tieu de
		JPanel pnTieuDe = new JPanel(new FlowLayout(FlowLayout.CENTER));

		JLabel lblTieuDe = StyleUtils.createHeaderTitle("TRA CỨU NHÂN VIÊN");
		pnTieuDe.add(lblTieuDe);
		pnCen.add(pnTieuDe);

		// form
		JPanel pnTimKiem = new JPanel();
		pnTimKiem.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

		// ma nv
		JLabel lblMa = StyleUtils.createLabel("Mã nhân viên:");
		txtMa = new JTextField(10);

		// tennv
		JLabel lblTen = StyleUtils.createLabel("Tên nhân viên:");
		txtTen = new JTextField(15);

		// sdt
		JLabel lblSdt = StyleUtils.createLabel("SĐT:");
		txtSdt = new JTextField(15);

		// CAN TEST - LOAD DU LIEU TU TABLE LEN
		JLabel lblLoai = StyleUtils.createLabel("Giới tính:");
		cbGT = new JComboBox<>();
		for (String gt : nvController.getPhai()) {
			cbGT.addItem(gt);
		}

		// cmnd
		JLabel lblCccd = StyleUtils.createLabel("CCCD:");
		txtCccd = new JTextField(15);

		// btn
		btnTim = new JButton("Tìm kiếm", new ImageIcon("src/cua_hang_tien_loi/icon/search.png"));
		btnLamMoi = new JButton("Làm mới", new ImageIcon("src/cua_hang_tien_loi/icon/lammoi.png"));

		pnTimKiem.add(lblMa);
		pnTimKiem.add(txtMa);
		pnTimKiem.add(lblTen);
		pnTimKiem.add(txtTen);
		pnTimKiem.add(lblSdt);
		pnTimKiem.add(txtSdt);
		pnTimKiem.add(lblLoai);
		pnTimKiem.add(cbGT);
		pnTimKiem.add(lblCccd);
		pnTimKiem.add(txtCccd);
		pnTimKiem.add(btnTim);
		pnTimKiem.add(btnLamMoi);

		pnCen.add(pnTimKiem);

		// table
		JPanel pnKetQua = new JPanel();
		pnKetQua.setLayout(new BorderLayout());
		pnKetQua.setBorder(BorderFactory.createTitledBorder("Kết quả tìm kiếm"));

		String[] cols = { "Mã NV", "Tên NV", "Giới tính", "CCCD", "Mật khẩu", "SĐT", "Email", "TTLV" };
		modelTable = new DefaultTableModel(cols, 0);
		table = new JTable(modelTable);
		table.setPreferredScrollableViewportSize(new Dimension(550, 150));
		JScrollPane scroll = new JScrollPane(table);

		pnKetQua.add(scroll, BorderLayout.CENTER);

		pnCen.add(pnKetQua);

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

		// key f1
		SystemUtils.setF1ToKey(pnMain, "F1", itemQuayLai);
	}

	public static void main(String[] args) {
		new TraCuuNhanVien().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();

		if (source.equals(itemTaiKhoan)) {
			new ThongTinTaiKhoanQuanLy().setVisible(true);
		} else if (source.equals(itemTroGiup)) {
			this.setVisible(false);
			new TraCuuSanPhamQuanLy().setVisible(true);
		} else if (source.equals(itemDangXuat)) {
			SystemUtils.dangXuat(this);
		} else if (source.equals(itemThemSP)) {
			this.setVisible(false);
			new ThemSanPhamQuanLy().setVisible(true);
		} else if (source.equals(itemCapNhatSp)) {
			this.setVisible(false);
			new CapNhatSanPhamQuanLy().setVisible(true);
		} else if (source.equals(itemTraCuuKH)) {
			this.setVisible(false);
			new TraCuuKhachHangQuanLy().setVisible(true);
		} else if (source.equals(itemCapNhatKH)) {
			this.setVisible(false);
			new CapNhatThongTinKhachHangQuanLy().setVisible(true);
		} else if (source.equals(itemTraCuuHD)) {
			this.setVisible(false);

		} else if (source.equals(itemThemHD)) {
			this.setVisible(false);

		} else if (source.equals(itemCapNhatHD)) {
			this.setVisible(false);

		} else if (source.equals(itemTraCuuNV)) {
			this.setVisible(false);
			new TraCuuNhanVien().setVisible(true);
		} else if (source.equals(itemThemNV)) {
			this.setVisible(false);
			new ThemNhanVien().setVisible(true);
		} else if (source.equals(itemCapNhatNV)) {
			this.setVisible(false);

		} else if (source.equals(itemQuayLai)) {
			SystemUtils.quayLai(this);
		}
		// btn
		if (source.equals(btnLamMoi)) {
			this.clear();
		} else if (source.equals(btnTim)) {
//			this.timKiem();
		}
	}

	private void timKiem() {
		String ma = txtMa.getText();
		String ten = txtTen.getText();
		String phai = cbGT.getSelectedItem().toString();
		String sdt = txtSdt.getText();
		String cccd = txtCccd.getText();

		boolean gtBoolean = phai.equals("Nữ") ? true : false;

		List<NhanVien> dsnv = nvController.getNV(ma, ten, gtBoolean, sdt, cccd);

		modelTable.setRowCount(0);

		// load du lieu len table
		for (NhanVien nv : dsnv) {
			Object[] row = { nv.getMaNV(), nv.getHoTen(), nv.isPhai() ? "Nữ" : "Nam", nv.getCmnd(), nv.getMk(),
					nv.getSdt(), nv.getEmail(), nv.isTrangThaiLamViec() ? "Đang làm việc" : "Ngưng làm việc" };
			modelTable.addRow(row);
		}
	}

	private void clear() {
		txtMa.setText("");
		txtTen.setText("");
		txtSdt.setText("");
		txtCccd.setText("");
	}
}
