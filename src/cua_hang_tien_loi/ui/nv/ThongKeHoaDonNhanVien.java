package cua_hang_tien_loi.ui.nv;

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
import javax.swing.table.DefaultTableModel;

import cua_hang_tien_loi.controller.HoaDonController;
import cua_hang_tien_loi.entity.HoaDon;
import cua_hang_tien_loi.utils.StyleUtils;
import cua_hang_tien_loi.utils.SystemUtils;

public class ThongKeHoaDonNhanVien extends JFrame implements ActionListener {
	private JMenuItem itemTaiKhoan;
	private JMenuItem itemTroGiup;
	private JMenuItem itemDangXuat;
	private JMenuItem itemTraCuuKH;
	private JMenuItem itemTraCuuHD;
	private JMenuItem itemThemHD;
	JMenuItem itemQuayLai;
	private JButton btnLamMoi;
	private DefaultTableModel modelTable;
	private JTable table;
	private JComboBox<Object> cboNgay;
	private JComboBox<Object> cboThang;
	private JComboBox<Object> cboNam;
	private JButton btnThongKe;
	private HoaDonController hdController;
	private JMenuItem itemThongKeHoaDon;

	public ThongKeHoaDonNhanVien() {
		hdController = new HoaDonController(null);
		this.initUIThemHoaDon();
	}

	// giao dien

	private void initUIThemHoaDon() {
		setTitle("Quản lý cửa hàng tiện lợi - Trang chủ");
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// main
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BorderLayout());

		// north
		JPanel pnNorth = new JPanel();
		pnNorth.setLayout(new FlowLayout(FlowLayout.LEFT));
//			pnNorth.setBackground(Color.decode("#FAFAFA"));
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

		// thong ke
		JMenu menuThongKe = new JMenu("Thống kê");
		menuThongKe.setIcon(new ImageIcon("src/cua_hang_tien_loi/icon/thongke.png"));

		JMenu itemDoanhThu = new JMenu("Doanh thu");
		itemDoanhThu.setIcon(new ImageIcon("src/cua_hang_tien_loi/icon/doanhthu.png"));

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
		JLabel lbTitle = StyleUtils.createHeaderTitle("THỐNG KÊ HÓA ĐƠN THEO NGÀY");
		lbTitle.setAlignmentX(CENTER_ALIGNMENT);
		lbTitle.setFont(new Font("Arial", Font.BOLD, 20));
		lbTitle.setForeground(Color.BLACK);
		pn.add(lbTitle);

		JPanel pn1 = new JPanel();
		pn1.setLayout(new BoxLayout(pn1, BoxLayout.X_AXIS));
		JLabel lblNgay = new JLabel("Thời gian thống kê:");
		cboNgay = new JComboBox<>();
		for (int ngay : hdController.getNgay()) {
			cboNgay.addItem(ngay);
		}
		cboNgay.setPreferredSize(new Dimension(220, 25));
		JLabel lblThang = new JLabel("Tháng:");
		cboThang = new JComboBox<>();
		for (int thang : hdController.getThang()) {
			cboThang.addItem(thang);
		}
		cboThang.setPreferredSize(new Dimension(220, 25));
		JLabel lblNam = new JLabel("Năm:");
		cboNam = new JComboBox<>();
		for (int nam : hdController.getNam()) {
			cboNam.addItem(nam);
		}
		cboNam.setPreferredSize(new Dimension(220, 25));
		pn1.add(lblNgay);
		pn1.add(Box.createHorizontalStrut(10));
		pn1.add(cboNgay);
		pn1.add(Box.createHorizontalStrut(10));
		pn1.add(lblThang);
		pn1.add(Box.createHorizontalStrut(10));
		pn1.add(cboThang);
		pn1.add(Box.createHorizontalStrut(10));
		pn1.add(lblNam);
		pn1.add(Box.createHorizontalStrut(10));
		pn1.add(cboNam);
		pn.add(Box.createVerticalStrut(20));
		pn.add(pn1);

		// button
		JPanel pn2 = new JPanel();
		pn2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnThongKe = new JButton("Thống kê", new ImageIcon("src/cua_hang_tien_loi/icon/thongke.png"));
		btnLamMoi = new JButton("Làm mới", new ImageIcon("src/cua_hang_tien_loi/icon/reload.png"));
		pn2.add(btnThongKe);
		pn2.add(btnLamMoi);
		pn.add(Box.createVerticalStrut(10));
		pn.add(pn2);

		pnCen.add(pn);

		JPanel pnSouth = new JPanel();

		String[] title = { "Mã HD", "Ngày lập HD", "Số lượng", "Mã NV", "Mã SP", "Đơn giá" };
		modelTable = new DefaultTableModel(title, 0);
		table = new JTable(modelTable);
		JScrollPane scroll = new JScrollPane(table);

		scroll.setPreferredSize(new Dimension(1000, 340));
		pnSouth.add(new JScrollPane(scroll));

		pnMain.add(pnSouth, BorderLayout.SOUTH);
		pnMain.add(pnCen, BorderLayout.CENTER);

		add(pnMain);

		// event

		// he thong
		itemTaiKhoan.addActionListener(this);
		itemTroGiup.addActionListener(this);
		itemDangXuat.addActionListener(this);

		// khach hang
		itemTraCuuKH.addActionListener(this);

		// hoa don
		itemTraCuuHD.addActionListener(this);
		itemThemHD.addActionListener(this);

		// thong ke
		itemThongKeHoaDon.addActionListener(this);

		// quay lai
		itemQuayLai.addActionListener(this);

		// key f1
		SystemUtils.setF1ToKey(pnMain, "F1", itemQuayLai);

		// btn
		btnLamMoi.addActionListener(this);
		btnThongKe.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();

		// he thong
		if (source.equals(itemTaiKhoan)) {
			new ThongTinTaiKhoanNhanVien().setVisible(true);
		} else if (source.equals(itemTroGiup)) {
			SystemUtils.openFile("/Users/lethoa/Documents/giaykhamsuckhoe.pdf");
		} else if (source.equals(itemDangXuat)) {
			SystemUtils.dangXuat(this);
		}

		// khach hang
		else if (source.equals(itemTraCuuKH)) {
			this.setVisible(false);
			new TraCuuKhachHangNhanVien().setVisible(true);
		}

		// hoa don
		else if (source.equals(itemTraCuuHD)) {
			this.setVisible(false);
			new TraCuuHoaDonNhanVien().setVisible(true);
		} else if (source.equals(itemThemHD)) {
			this.setVisible(false);
			new ThemHoaDonNhanVien().setVisible(true);
		}

		// thong ke
		else if (source.equals(itemThongKeHoaDon)) {
			new ThongKeHoaDonNhanVien().setVisible(true);
		}

		// quay lai
		else if (source.equals(itemQuayLai)) {
			SystemUtils.quayLai(this);
		}

		// btn
		else if (source.equals(btnLamMoi)) {
			this.clear();
		} else if (source.equals(btnThongKe)) {
			this.thongKe();
		}
	}

	private void thongKe() {
		String ngay = cboNgay.getSelectedItem().toString();
		String thang = cboThang.getSelectedItem().toString();
		String nam = cboNam.getSelectedItem().toString();

		int ngayInt = Integer.parseInt(ngay);
		int thangInt = Integer.parseInt(thang);
		int namInt = Integer.parseInt(nam);

//		"Mã HD", "Ngày lập HD", "Số lượng", "Mã NV", "Mã SP", "Đơn giá";
		List<Object[]> ketQuaThongKe = hdController.thongKeHoaDon(ngayInt, thangInt, namInt);

		modelTable.setRowCount(0);

		// load du lieu len table
		for (Object[] row : ketQuaThongKe) {
			modelTable.addRow(row);
		}

	}

	private void clear() {
		cboNgay.setSelectedIndex(0);
		cboThang.setSelectedIndex(0);
		cboNam.setSelectedIndex(0);
		modelTable.setRowCount(0);
	}
	
	public static void main(String[] args) {
		new ThongKeHoaDonNhanVien().setVisible(true);
	}
}
