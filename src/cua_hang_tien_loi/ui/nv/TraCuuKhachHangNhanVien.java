package cua_hang_tien_loi.ui.nv;

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
import cua_hang_tien_loi.utils.SystemUtils;

public class TraCuuKhachHangNhanVien extends JFrame implements ActionListener {

	private JMenuItem itemTaiKhoan;
	private JMenuItem itemTroGiup;
	private JMenuItem itemDangXuat;
	private JMenuItem itemTraCuuKH;
	private JMenuItem itemTraCuuHD;
	private JMenuItem itemThemHD;
	private JMenuItem itemCapNhatHD;
	private JMenuItem itemQuayLai;
	private JMenuItem itemThongKeHoaDon;
	private JTextField txtMaKH, txtTenKH, txtSdt;
	private JButton btnTim;
	private JButton btnLamMoi;
	private DefaultTableModel model;
	private JTable table;
	private KhachHangController khController;

	public TraCuuKhachHangNhanVien() {
		this.khController = new KhachHangController();
		UIThemKhachHangQuanLy();
	}

	private void UIThemKhachHangQuanLy() {
		setTitle("Quản lý cửa hàng tiện lợi - Tra cứu khách hàng");
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

		// add pnmain
		pnMain.add(pnCenterWrapper, BorderLayout.WEST);

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
	    if (khController != null) {
	        String ma = txtMaKH.getText().trim();
	        String ten = txtTenKH.getText();
	        String sdt = txtSdt.getText();
	        List<KhachHang> dskh = khController.getKhachHangForTraCuu(ma, ten, sdt);
	        model.setRowCount(0);
	        // load dữ liệu lên table
	        for (KhachHang khItem : dskh) {
	            Object[] row = { khItem.getMaKH(), khItem.getTenKH(), khItem.getSdt(), khItem.isPhai() ? "Nữ" : "Nam" };
	            model.addRow(row);
	        }
	    } else {
	        // Xử lý trường hợp khController là null
	        System.err.println("KhachHangController chưa được khởi tạo.");
	    }
	}


	private void lamMoiForm() {
		txtMaKH.setText("");
		txtTenKH.setText("");
		txtSdt.setText("");
		model.setRowCount(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
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
		else if (source == btnTim) {
			this.timKhachHang();
		} else if (source == btnLamMoi) {
			this.lamMoiForm();
		}
	}
}
