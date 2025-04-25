package cua_hang_tien_loi.ui.nv;

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

import cua_hang_tien_loi.controller.SanPhamController;
import cua_hang_tien_loi.entity.SanPham;
import cua_hang_tien_loi.ui.admin.ThongTinTaiKhoanQuanLy;
import cua_hang_tien_loi.ui.admin.TraCuuKhachHangQuanLy;
import cua_hang_tien_loi.ui.admin.TraCuuSanPhamQuanLy;
import cua_hang_tien_loi.utils.StyleUtils;
import cua_hang_tien_loi.utils.SystemUtils;

public class TraCuuSanPhamNhanVien extends JFrame implements ActionListener {

	private JMenuItem itemTaiKhoan;
	private JMenuItem itemTroGiup;
	private JMenuItem itemDangXuat;
	private JMenuItem itemTraCuuKH;
	private JMenuItem itemTraCuuHD;
	private JMenuItem itemThemHD;
	private JMenuItem itemCapNhatHD;
	private JMenuItem itemDTTheoNgay;
	private JMenuItem itemDTTheoThang;
	private JMenuItem itemDTTheoNam;
	private JMenuItem itemQuayLai;
	private JTextField txtMa;
	private JTextField txtTen;
	private JComboBox<Object> cbLoai;
	private SanPhamController spController;
	private JComboBox<Object> cbTTKD;
	private JButton btnTim;
	private JButton btnLamMoi;
	private DefaultTableModel modelTable;
	private JTable table;

	public TraCuuSanPhamNhanVien() {
		// TODO Auto-generated constructor stub
		this.UITraCuuSanPhamNhanVien();
	}

	private void UITraCuuSanPhamNhanVien() {
		setTitle("Quản lý cửa hàng tiện lợi - Tra cứu sản phẩm");
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
		itemCapNhatHD = StyleUtils.createItemMenu("Cập nhật", "src/cua_hang_tien_loi/icon/edit.png");

		menuHoaDon.add(itemTraCuuHD);
		menuHoaDon.addSeparator();
		menuHoaDon.add(itemThemHD);
		menuHoaDon.addSeparator();
		menuHoaDon.add(itemCapNhatHD);
		menuBar.add(menuHoaDon);
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

		JLabel lblTieuDe = StyleUtils.createHeaderTitle("TRA CỨU SẢN PHẨM");
		pnTieuDe.add(lblTieuDe);
		pnCen.add(pnTieuDe);

		// form
		JPanel pnTimKiem = new JPanel();
		pnTimKiem.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

		JLabel lblMa = StyleUtils.createLabel("Mã sản phẩm:");
		txtMa = new JTextField(10);

		JLabel lblTen = StyleUtils.createLabel("Tên sản phẩm:");
		txtTen = new JTextField(15);

		// CAN TEST - LOAD DU LIEU TU TABLE LEN
		JLabel lblLoai = StyleUtils.createLabel("Loại:");
		cbLoai = new JComboBox<>();
		for (String loai : spController.getLoaiSP()) {
			cbLoai.addItem(loai);
		}

		// CAN TEST - LOAD DU LIEU TU TABLE LEN
		JLabel lblTTKD = StyleUtils.createLabel("TTKD:");
		cbTTKD = new JComboBox<>();
		for (String ttkd : spController.getTTKD()) {
			cbTTKD.addItem(ttkd);
		}

		// btn
		btnTim = new JButton("Tìm kiếm", new ImageIcon("src/cua_hang_tien_loi/icon/search.png"));
		btnLamMoi = new JButton("Làm mới", new ImageIcon("src/cua_hang_tien_loi/icon/lammoi.png"));

		pnTimKiem.add(lblMa);
		pnTimKiem.add(txtMa);
		pnTimKiem.add(lblTen);
		pnTimKiem.add(txtTen);
		pnTimKiem.add(lblLoai);
		pnTimKiem.add(cbLoai);
		pnTimKiem.add(lblTTKD);
		pnTimKiem.add(cbTTKD);
		pnTimKiem.add(btnTim);
		pnTimKiem.add(btnLamMoi);

		pnCen.add(pnTimKiem);

		// table
		JPanel pnKetQua = new JPanel();
		pnKetQua.setLayout(new BorderLayout());
		pnKetQua.setBorder(BorderFactory.createTitledBorder("Kết quả tìm kiếm"));

		String[] cols = { "Mã SP", "Tên SP", "Loại SP", "Giá", "Chất liệu", "Trạng thái kinh doanh" };
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

		// khach hang
		itemTraCuuKH.addActionListener(this);

		// hoa don
		itemTraCuuHD.addActionListener(this);
		itemThemHD.addActionListener(this);

		// thong ke
		itemDTTheoNgay.addActionListener(this);
		itemDTTheoThang.addActionListener(this);
		itemDTTheoNam.addActionListener(this);

		// quay lai
		itemQuayLai.addActionListener(this);

		// btn
		btnTim.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnTim.addActionListener(this);

		// key f1
		SystemUtils.setF1ToKey(pnMain, "F1", itemQuayLai);

	}

	public static void main(String[] args) {
		new TraCuuSanPhamNhanVien().setVisible(true);
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
		} else if (source.equals(itemTraCuuKH)) {
			this.setVisible(false);
			new TraCuuKhachHangQuanLy().setVisible(true);
		} else if (source.equals(itemTraCuuHD)) {
			this.setVisible(false);

		} else if (source.equals(itemThemHD)) {
			this.setVisible(false);

		} else if (source.equals(itemCapNhatHD)) {
			this.setVisible(false);

		} else if (source.equals(itemQuayLai)) {
			SystemUtils.quayLai(this);
		}

		// btn
		if (source.equals(btnLamMoi)) {
			this.clear();
		} else if (source.equals(btnTim)) {
			this.timKiem();
		}
	}

	// tim
	private void timKiem() {
		String maSP = txtMa.getText();
		String tenSP = txtTen.getText();
		String loaiSP = cbLoai.getSelectedItem().toString();
		String ttkd = cbTTKD.getSelectedItem().toString();
		boolean ttkdStatus = ttkd.equals("Kinh doanh") ? true : false;

		List<SanPham> dssp = spController.timKiemSanPham(maSP, tenSP, loaiSP, ttkdStatus);

		modelTable.setRowCount(0);

		// load du lieu len table
		for (SanPham sp : dssp) {
			Object[] row = { sp.getMaSP(), sp.getTenSP(), sp.getLoaiSP(), sp.getDonGia(), sp.getChatLieu(),
					sp.isTTKD() ? "Kinh doanh" : "Ngừng kinh doanh" };
			modelTable.addRow(row);
		}
	}

	// lam moi
	private void clear() {
		txtMa.setText("");
		txtTen.setText("");
	}

}
