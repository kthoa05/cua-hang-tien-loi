package cua_hang_tien_loi.ui.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cua_hang_tien_loi.controller.NhanVienController;
import cua_hang_tien_loi.entity.NhanVien;
import cua_hang_tien_loi.utils.StyleUtils;
import cua_hang_tien_loi.utils.SystemUtils;

public class CapNhatNhanVien extends JFrame implements ActionListener, MouseListener {

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
	private JTextField txtMa;
	private JTextField txtTen;
	private JComboBox<Object> cboGt;
	private JTextField txtSdt;
	private JButton btnLamMoi;
	private JButton btnCapNhat;
	private JTextField txtCccd;
	private JComboBox<Object> cboTTLV;
	private JTextField txtMaSP;
	private JTextField txtTenSp;
	private JComboBox<String> txtTTKD;
	private JButton btnTimKiem;
	private JTextField txtMaNV;
	private JTextField txtTenNV;
	private DefaultTableModel modelTable;
	private JTable table;
	private String imgPath;
	private NhanVienController nvController;

	public CapNhatNhanVien() {
		// TODO Auto-generated constructor stub
		this.UICapNhatNhanVien();
	}

	// giao dien
	private void UICapNhatNhanVien() {
		setTitle("Quản lý cửa hàng tiện lợi - Cập nhật nhân viên");
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
		pnCen.setLayout(new BorderLayout());

		// north
		JPanel pnHeader = new JPanel();
		JLabel lblCapNhat = StyleUtils.createHeaderTitle("CẬP NHẬT NHÂN VIÊN");
		lblCapNhat.setAlignmentX(CENTER_ALIGNMENT);
		pnHeader.add(lblCapNhat);

		pnCen.add(pnHeader, BorderLayout.NORTH);

		// left
		JPanel pnLeftOfCen = new JPanel();
		pnLeftOfCen.setLayout(new BorderLayout());
		pnLeftOfCen.setPreferredSize(new Dimension(200, 200));

		lblImage = new JLabel("Ảnh chưa chọn", JLabel.CENTER);
		lblImage.setPreferredSize(new Dimension(100, 100));
		lblImage.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnLeftOfCen.add(lblImage, BorderLayout.CENTER);

		btnImg = new JButton("Chọn ảnh");
		pnLeftOfCen.add(btnImg, BorderLayout.SOUTH);

		pnCen.add(pnLeftOfCen, BorderLayout.WEST);

		// center

		JPanel pnCenter = new JPanel();

		JPanel pnCenterOfCen = new JPanel();
		pnCenterOfCen.setLayout(new BoxLayout(pnCenterOfCen, BoxLayout.Y_AXIS));
		pnCenterOfCen.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		// ten nv
		JPanel pnTen = new JPanel();
		pnTen.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTen = StyleUtils.createLabel("Tên NV:");
		txtTen = new JTextField(19);
		pnTen.add(lblTen);
		pnTen.add(txtTen);

		// sdt
		JPanel pnSdt = new JPanel();
		pnSdt.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblSdt = StyleUtils.createLabel("SĐT:");
		txtSdt = new JTextField(20);

		pnSdt.add(lblSdt);
		pnSdt.add(txtSdt);

		// gt
		JPanel pnGt = new JPanel();
		JLabel lblGt = StyleUtils.createLabel("Giới tính:");
		cboGt = new JComboBox<>();
		for (String gt : nvController.getPhai()) {
			cboGt.addItem(gt);
		}

		pnGt.add(lblGt);
		pnGt.add(cboGt);

		// add vo pn
		pnCenterOfCen.add(pnTen);
		pnCenterOfCen.add(pnGt);
		pnCenterOfCen.add(pnSdt);

		pnCenter.add(pnCenterOfCen);

		// right of cen

		JPanel pnRightOfCen = new JPanel();
		pnRightOfCen.setLayout(new BoxLayout(pnRightOfCen, BoxLayout.Y_AXIS));
		pnRightOfCen.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		// cccd
		JPanel pnCccd = new JPanel();
		pnCccd.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblCccd = new JLabel("CCCD:");
		txtCccd = new JTextField(20);
		pnCccd.add(lblCccd);
		pnCccd.add(txtCccd);

		// ttlv
		JPanel pnTTLV = new JPanel();
		pnTTLV.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTTLV = new JLabel("TTLV:");
		cboTTLV = new JComboBox<>();
		for (String ttlv : nvController.getTrangThaiLamViec()) {
			cboTTLV.addItem(ttlv);
		}

		pnTTLV.add(lblTTLV);
		pnTTLV.add(cboTTLV);

		// add vo pnRight
		pnRightOfCen.add(pnCccd);
		pnRightOfCen.add(pnTTLV);

		pnCenter.add(pnRightOfCen);

		// tim kiem
		JPanel pnSearch = new JPanel();
		pnSearch.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));

		JPanel pnForm = new JPanel();
		pnForm.setLayout(new BoxLayout(pnForm, BoxLayout.X_AXIS));

		JLabel lblMaNV = new JLabel("Mã NV:");
		txtMaNV = new JTextField(10);

		JLabel lblTenNV = new JLabel("Tên NV:");
		txtTenNV = new JTextField(10);

		btnTimKiem = new JButton("Tìm kiếm", new ImageIcon("src/cua_hang_tien_loi/icon/search.png"));

		// add vo panel
		pnSearch.add(lblMaNV);
		pnSearch.add(txtMaNV);
		pnSearch.add(lblTenNV);
		pnSearch.add(txtTenNV);
		pnSearch.add(Box.createHorizontalStrut(40));
		pnSearch.add(btnTimKiem);

		pnCenter.add(pnSearch);

		// btn cap nhat
		JPanel pnBtn = new JPanel();
		pnBtn.setLayout(new FlowLayout(FlowLayout.LEFT));

		btnLamMoi = new JButton("Làm mới", new ImageIcon("src/cua_hang_tien_loi/icon/lammoi.png"));
		btnCapNhat = new JButton("Cập nhật", new ImageIcon("src/cua_hang_tien_loi/icon/edit.png"));

		pnBtn.add(btnLamMoi);
		pnBtn.add(btnCapNhat);

		pnCenter.add(pnBtn);

		pnCen.add(pnCenter, BorderLayout.CENTER);

		pnMain.add(pnCen, BorderLayout.CENTER);

		// south table
		JPanel pnKetQua = new JPanel();
		pnKetQua.setLayout(new BorderLayout());
		pnKetQua.setBorder(BorderFactory.createTitledBorder("Kết quả tìm kiếm"));

		String[] cols = { "Mã NV", "Tên NV", "Giới tính", "CCCD", "Mật khẩu", "SĐT", "Email", "TTLV" };
		modelTable = new DefaultTableModel(cols, 0);
		table = new JTable(modelTable);
		table.setPreferredScrollableViewportSize(new Dimension(550, 150));
		JScrollPane scroll = new JScrollPane(table);

		pnKetQua.add(scroll);

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

		// btn
		btnImg.addActionListener(this);
		btnCapNhat.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnTimKiem.addActionListener(this);

	}

	public static void main(String[] args) {
		new CapNhatNhanVien().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Object source = e.getSource();

		if (source.equals(itemTaiKhoan)) {
			new ThongTinTaiKhoanQuanLy().setVisible(true);
		} else if (source.equals(itemTroGiup)) {
			SystemUtils.openFile("/Users/lethoa/Documents/giaykhamsuckhoe.pdf");
		} else if (source.equals(itemDangXuat)) {
			SystemUtils.dangXuat(this);
		} else if (source.equals(itemThemSP)) {
			this.setVisible(false);
			new FormThemSanPhamQuanLy().setVisible(true);
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
			new CapNhatNhanVien().setVisible(true);
		} else if (source.equals(itemQuayLai)) {
			SystemUtils.quayLai(this);
		}

		// btn
		else if (source.equals(btnImg)) {
			SystemUtils.chonAnhSanPham(lblImage, imgPath);
		} else if (source.equals(btnCapNhat)) {
			this.capNhat();
		} else if (source.equals(btnLamMoi)) {
			this.clear();
		} else if (source.equals(btnTimKiem)) {
			this.timKiem();
		}
	}

	private void capNhat() {
		String ten = txtTen.getText();
		String sdt = txtSdt.getText();
		String gioiTinh = cboGt.getSelectedItem().toString();
		String cccd = txtCccd.getText();
		String ttlv = cboTTLV.getSelectedItem().toString();

		boolean gt = gioiTinh.equals("Nữ") ? true : false;
		boolean status = ttlv.equals("Đang làm việc") ? true : false;

		NhanVien nv = new NhanVien(ten, status, sdt, sdt, gt, ten, gioiTinh, cccd, ttlv);

		boolean statusCapNhatNV = nvController.capNhatNhanVien(nv);
		if (!statusCapNhatNV) {
			JOptionPane.showMessageDialog(this, "Cập nhật nhân viên sản phẩm thành công", "Thông báo",
					JOptionPane.INFORMATION_MESSAGE);
			modelTable.setRowCount(0);
		} else {
			JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void timKiem() {
		String ma = txtMaNV.getText();
		String ten = txtTenNV.getText();

		List<NhanVien> dsnv = nvController.getNV(ma, ten, null, null, null);

		modelTable.setRowCount(0);

		// load du lieu len table
		for (NhanVien nv : dsnv) {
			Object[] row = { nv.getMaNV(), nv.getHoTen(), nv.isPhai() ? "Nữ" : "Nam", nv.getCmnd(), nv.getMk(),
					nv.getSdt(), nv.getEmail(), nv.isTrangThaiLamViec() ? "Đang làm việc" : "Ngưng làm việc" };
			modelTable.addRow(row);
		}
	}

	private void clear() {
		txtTen.setText("");
		txtSdt.setText("");
		txtCccd.setText("");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtTen.setText(modelTable.getValueAt(row, 2).toString());
		txtSdt.setText(modelTable.getValueAt(row, 6).toString());
		String gt = modelTable.getValueAt(row, 3).toString();
		cboGt.setSelectedItem(gt);
		txtCccd.setText(modelTable.getValueAt(row, 4).toString());
		String ttlv = modelTable.getValueAt(row, 8).toString();
		cboTTLV.setSelectedItem(ttlv);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
