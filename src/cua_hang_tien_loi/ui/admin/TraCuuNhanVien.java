package cua_hang_tien_loi.ui.admin;

import java.awt.BorderLayout;
import java.awt.Color;
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
	private JMenuItem itemTraCuuHD;
	private JMenuItem itemThemHD;
	private JMenuItem itemTraCuuNV;
	private JMenuItem itemThemNV;
	private JMenuItem itemCapNhatNV;
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
	private JMenuItem itemThongKeHoaDon;

	public TraCuuNhanVien() {
		nvController = new NhanVienController();
		// TODO Auto-generated constructor stub
		this.UITraCuuNhanVien();
	}

	// giao diện
	private void UITraCuuNhanVien() {
		setTitle("Quản lý cửa hàng tiện lợi - Tra cứu nhân viên");
		setSize(1000, 600);
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

		JPanel pn = new JPanel();
		pn.setLayout(new BoxLayout(pn, BoxLayout.Y_AXIS));

		// tieu de

		JLabel lbTitle = StyleUtils.createHeaderTitle("TRA CỨU NHÂN VIÊN");

		lbTitle.setFont(new Font("Arial", Font.BOLD, 20));
		lbTitle.setForeground(Color.BLACK);
		pn.add(lbTitle);

		// form
		JPanel pnTimKiem = new JPanel();
		pnTimKiem.setLayout(new BoxLayout(pnTimKiem, BoxLayout.X_AXIS));

		JLabel lblMa = new JLabel("Mã nhân viên:");
		txtMa = new JTextField(25);
		JLabel lblTen = new JLabel("Tên nhân viên:");
		txtTen = new JTextField(25);

		pnTimKiem.add(lblMa);
		pnTimKiem.add(Box.createHorizontalStrut(10));
		pnTimKiem.add(txtMa);
		pnTimKiem.add(Box.createHorizontalStrut(10));
		pnTimKiem.add(lblTen);
		pnTimKiem.add(Box.createHorizontalStrut(10));
		pnTimKiem.add(txtTen);
		pn.add(Box.createVerticalStrut(10));
		pn.add(pnTimKiem);

		JPanel pn2 = new JPanel();
		pn2.setLayout(new BoxLayout(pn2, BoxLayout.X_AXIS));
		JLabel lblSdt = new JLabel("SĐT:");
		txtSdt = new JTextField(25);

		// cmnd
		JLabel lblCccd = new JLabel("CCCD:");
		txtCccd = new JTextField(25);

		pn2.add(lblSdt);
		pn2.add(Box.createHorizontalStrut(60));
		pn2.add(txtSdt);
		pn2.add(Box.createHorizontalStrut(10));
		pn2.add(lblCccd);
		pn2.add(Box.createHorizontalStrut(55));
		pn2.add(txtCccd);
		pn.add(Box.createVerticalStrut(10));
		pn.add(pn2);

		JPanel pn3 = new JPanel();
		pn3.setLayout(new BoxLayout(pn3, BoxLayout.X_AXIS));

		JLabel lblGT = new JLabel("Giới tính:");
		cbGT = new JComboBox<>();
		for (String gt : nvController.getPhai()) {
			cbGT.addItem(gt);
		}
		pn3.add(lblGT);
		pn3.add(Box.createHorizontalStrut(35));
		pn3.add(cbGT);
		pn.add(Box.createVerticalStrut(10));
		pn.add(pn3);

		JPanel pn4 = new JPanel();

		pn4.setLayout(new FlowLayout(FlowLayout.RIGHT));

		// btn
		btnTim = new JButton("Tìm kiếm", new ImageIcon("src/cua_hang_tien_loi/icon/search.png"));
		btnLamMoi = new JButton("Làm mới", new ImageIcon("src/cua_hang_tien_loi/icon/lammoi.png"));

		pn4.add(btnTim);
		pn4.add(Box.createVerticalStrut(10));
		pn4.add(btnLamMoi);
		pn.add(Box.createVerticalStrut(10));
		pn.add(pn4);

		pnCen.add(pn);

		// table
		JPanel pnKetQua = new JPanel();
		pnKetQua.setLayout(new BorderLayout());
		pnKetQua.setBorder(BorderFactory.createTitledBorder("Kết quả tìm kiếm"));

		String[] cols = { "Mã NV", "Tên NV", "Giới tính", "CCCD", "Mật khẩu", "SĐT", "Email", "TTLV" };
		modelTable = new DefaultTableModel(cols, 0);
		table = new JTable(modelTable);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(1000, 300));

		pnKetQua.add(scroll);

		pnMain.add(pnCen, BorderLayout.CENTER);

		pnMain.add(pnKetQua, BorderLayout.SOUTH);

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
		if (source.equals(btnLamMoi)) {
			this.clear();
		} else if (source.equals(btnTim)) {
			this.timKiem();
		}
	}

	private void timKiem() {
		String ma = txtMa.getText();
		String ten = txtTen.getText();
		String phai = cbGT.getSelectedItem().toString();
		String sdt = txtSdt.getText();
		String cccd = txtCccd.getText();
		boolean gtBoolean = phai.equals("Nữ");

		// Log các giá trị đầu vào
		System.out.println("Ma: " + ma);
		System.out.println("Ten: " + ten);
		System.out.println("Phai: " + phai);
		System.out.println("Sdt: " + sdt);
		System.out.println("Cccd: " + cccd);

		List<NhanVien> dsnv = nvController.getNV(ma, ten, sdt, gtBoolean, cccd);

		// Log danh sách nhân viên trả về
		System.out.println("Danh sách nhân viên: " + dsnv);

		modelTable.setRowCount(0);
		for (NhanVien nv : dsnv) {
			Object[] row = { nv.getMaNV(), nv.getHoTen(), nv.isPhai() ? "Nữ" : "Nam", nv.getCmnd(), nv.getMk(),
					nv.getSdt(), nv.getEmail(), nv.isTrangThaiLamViec() ? "Đang làm việc" : "Ngưng làm việc" };
			modelTable.addRow(row);
		}

		// Log sau khi cập nhật bảng
		System.out.println("Bảng đã được cập nhật.");
	}

	private void clear() {
		txtMa.setText("");
		txtTen.setText("");
		txtSdt.setText("");
		txtCccd.setText("");
		modelTable.setRowCount(0);
	}
}
