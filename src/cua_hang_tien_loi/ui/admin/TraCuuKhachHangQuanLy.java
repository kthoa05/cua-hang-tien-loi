package cua_hang_tien_loi.ui.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

import cua_hang_tien_loi.controller.KhachHangController;
import cua_hang_tien_loi.entity.KhachHang;
import cua_hang_tien_loi.entity.NhanVien;
import cua_hang_tien_loi.utils.StyleUtils;

public class TraCuuKhachHangQuanLy extends JFrame implements ActionListener {

	private JMenuItem itemTaiKhoan, itemTroGiup, itemDangXuat, itemThemSP, itemCapNhatSp, itemTraCuuKH, itemThemKH,
			itemCapNhatKH, itemTraCuuHD, itemThemHD, itemCapNhatHD, itemTraCuuNV, itemThemNV, itemCapNhatNV,
			itemDTTheoNgay, itemDTTheoThang, itemDTTheoNam, itemQuayLai, itemTraCuuSP;

	private JTextField txtMaKH, txtTenKH, txtSdt;
	private JButton btnTim;
	private JButton btnLamMoi;
	private DefaultTableModel model;
	private JTable table;
	private KhachHangController khController;

	public TraCuuKhachHangQuanLy() {
		UIThemKhachHangQuanLy();
	}

	private void UIThemKhachHangQuanLy() {
		setTitle("Quản lý cửa hàng tiện lợi - Tra cứu khách hàng");
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel pnMain = new JPanel(new BorderLayout());

		// ===== MENU =====
		JPanel pnNorth = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnNorth.setPreferredSize(new Dimension(750, 40));
		pnNorth.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(null);

		// Hệ thống
		JMenu menuHeThong = new JMenu("Hệ thống");
		menuHeThong.setIcon(new ImageIcon("src/cua_hang_tien_loi/icon/hethong.png"));
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

		// Sản phẩm
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

		// Khách hàng
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

		// Hoá đơn
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

		// Nhân viên
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

		// Thống kê
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

		// Quay lại
		itemQuayLai = StyleUtils.createItemMenu("Quay lại (F1)", "src/cua_hang_tien_loi/icon/quaylai.png");
		menuBar.add(itemQuayLai);

		pnNorth.add(menuBar);
		pnMain.add(pnNorth, BorderLayout.NORTH);

		// ===== FORM =====
		// ===== FORM =====
		JPanel pnCenter = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		Font labelFont = new Font("Arial", Font.PLAIN, 16);

		// Label + Field
		JLabel lblMaKH = new JLabel("Mã khách hàng:");
		lblMaKH.setFont(labelFont);
		txtMaKH = new JTextField();
		txtMaKH.setPreferredSize(new Dimension(300, 30));

		JLabel lblTenKH = new JLabel("Tên khách hàng:");
		lblTenKH.setFont(labelFont);
		txtTenKH = new JTextField();
		txtTenKH.setPreferredSize(new Dimension(300, 30));

		JLabel lblSDT = new JLabel("Số điện thoại:");
		lblSDT.setFont(labelFont);
		txtSdt = new JTextField();
		txtSdt.setPreferredSize(new Dimension(300, 30));

		// Row 1
		gbc.gridx = 0;
		gbc.gridy = 0;
		pnCenter.add(lblMaKH, gbc);
		gbc.gridx = 1;
		pnCenter.add(txtMaKH, gbc);

		// Row 2
		gbc.gridx = 0;
		gbc.gridy = 1;
		pnCenter.add(lblTenKH, gbc);
		gbc.gridx = 1;
		pnCenter.add(txtTenKH, gbc);

		// Row 3
		gbc.gridx = 0;
		gbc.gridy = 2;
		pnCenter.add(lblSDT, gbc);
		gbc.gridx = 1;
		pnCenter.add(txtSdt, gbc);

		gbc.gridy = 4;
		JPanel pnButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));

		btnTim = new JButton("Tìm kiếm");
		btnLamMoi = new JButton("Làm mới");

		pnButtons.add(btnTim);
		pnButtons.add(btnLamMoi);
		gbc.gridwidth = 2;
		pnCenter.add(pnButtons, gbc);

		gbc.gridy = 5;
		pnCenter.add(new JPanel(), gbc);

		JPanel pnTable = new JPanel(new BorderLayout());
		model = new DefaultTableModel(new Object[] { "Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Phái" }, 0);
		table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(1000, 200));
		pnTable.add(scrollPane, BorderLayout.CENTER);

		JPanel pnCenterWrapper = new JPanel(new BorderLayout());
		pnCenterWrapper.add(pnCenter, BorderLayout.NORTH);
		pnCenterWrapper.add(pnTable, BorderLayout.CENTER);

		// Thêm vào pnMain
		pnMain.add(pnCenterWrapper, BorderLayout.WEST);

		add(pnMain);

		addActionEvents();
	}

	private void addActionEvents() {

		btnTim.addActionListener(this);
		btnLamMoi.addActionListener(this);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				hienThiThongTin(row);
			}
		});
	}

	private void hienThiThongTin(int row) {
		txtMaKH.setText(model.getValueAt(row, 0).toString());
		txtTenKH.setText(model.getValueAt(row, 1).toString());
		txtSdt.setText(model.getValueAt(row, 2).toString());
	}

	private void timKhachHang() {
		String ma = txtMaKH.getText().trim();
		String ten = txtTenKH.getText();
		String sdt = txtSdt.getText();

		List<KhachHang> dskh = khController.getKhachHangForTraCuu(ma, ten, sdt);

		model.setRowCount(0);

		// load du lieu len table
		for (KhachHang khItem : dskh) {
			Object[] row = { khItem.getMaKH(), khItem.getTenKH(), khItem.getSdt(), khItem.isPhai() ? "Nữ" : "Nam" };
			model.addRow(row);
		}

	}

	private void lamMoiForm() {
		txtMaKH.setText("");
		txtTenKH.setText("");
		txtSdt.setText("");
		table.clearSelection();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == btnTim) {
			this.timKhachHang();
		} else if (source == btnLamMoi) {
			this.lamMoiForm();
		}
	}

	public static void main(String[] args) {
		new TraCuuKhachHangQuanLy().setVisible(true);
	}

}
