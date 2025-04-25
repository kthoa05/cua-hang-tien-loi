package cua_hang_tien_loi.ui.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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

import cua_hang_tien_loi.controller.SanPhamController;
import cua_hang_tien_loi.entity.SanPham;
import cua_hang_tien_loi.ui.DangNhap;
import cua_hang_tien_loi.utils.StyleUtils;
import cua_hang_tien_loi.utils.SystemUtils;

public class CapNhatSanPhamQuanLy extends JFrame implements ActionListener, MouseListener {

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
	private JButton btnImg;
	private JTextField txtMaSanPham;
	private JTextField txtTenSanPham;
	private JComboBox<Object> cboLoaiSanPham;
	private JComboBox<Object> cboTrangThai;
	private JTextField txtChatLieu;
	private JButton btnLamMoi;
	private JButton btnThem;
	private JTextField txtDonGia;
	private JTextField txtMaSP;
	private JTextField txtTenSp;
	private JComboBox<String> txtTTKD;
	private JButton btnCapNhat;
	private JLabel lblImage;
	private String pathImg;
	private SanPhamController sanPhamController;
	private JButton btnTimKiem;
	private SanPhamController spController;
	private DefaultTableModel modelTable;
	private JTable table;

	public CapNhatSanPhamQuanLy() {
		// TODO Auto-generated constructor stub
		this.UICapNhatSanPham();
	}

	private <E> void UICapNhatSanPham() {
		setTitle("Quản lý cửa hàng tiện lợi - Cập nhật sản phẩm");
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

		menuHeThong.add(itemTaiKhoan);
		menuHeThong.addSeparator();
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
		JLabel lblChatLieu = new JLabel("Chất liệu:");
		txtChatLieu = new JTextField(20);
		pnMaSanPham.add(lblMaSanPham);
		pnMaSanPham.add(Box.createHorizontalStrut(5));
		pnMaSanPham.add(txtMaSanPham);
		pnMaSanPham.add(lblChatLieu);
		pnMaSanPham.add(txtChatLieu);

		// ten sp
		JPanel pnTenSanPham = new JPanel();
		pnTenSanPham.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTenSanPham = new JLabel("Tên sản phẩm:");
		txtTenSanPham = new JTextField(20);
		JLabel lblTrangThai = new JLabel("TTKD:");
		cboTrangThai = new JComboBox<>();
		cboTrangThai.setPreferredSize(new Dimension(203, 22));
		pnTenSanPham.add(lblTenSanPham);
		pnTenSanPham.add(Box.createHorizontalStrut(2));
		pnTenSanPham.add(txtTenSanPham);
		pnTenSanPham.add(lblTrangThai);

		pnTenSanPham.add(Box.createHorizontalStrut(9));
		pnTenSanPham.add(cboTrangThai);

		// loai sp
		JPanel pnLoaiSanPham = new JPanel();
		pnLoaiSanPham.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblLoaiSanPham = new JLabel("Loại sản phẩm:");
		cboLoaiSanPham = new JComboBox<>();
		cboLoaiSanPham.setPreferredSize(new Dimension(200, 22));
		for (String loai : spController.getLoaiSP()) {
			cboLoaiSanPham.addItem(loai);
		}
		JLabel lblDonGia = new JLabel("Đơn giá:");
		txtDonGia = new JTextField(20);
		pnLoaiSanPham.add(lblLoaiSanPham);
		pnLoaiSanPham.add(Box.createHorizontalStrut(2));
		pnLoaiSanPham.add(cboLoaiSanPham);
		pnLoaiSanPham.add(lblDonGia);
		pnLoaiSanPham.add(Box.createHorizontalStrut(2));
		pnLoaiSanPham.add(txtDonGia);

		// button
		JPanel pnBtn = new JPanel();
		pnBtn.setLayout(new FlowLayout(FlowLayout.RIGHT));

		btnLamMoi = new JButton("Làm mới", new ImageIcon("src/cua_hang_tien_loi/icon/lammoi.png"));
		btnCapNhat = new JButton("Cập nhật", new ImageIcon("src/cua_hang_tien_loi/icon/edit.png"));

		pnBtn.add(btnLamMoi);

		// add vo pn
		pnCenterOfCen.add(pnMaSanPham);
		pnCenterOfCen.add(pnTenSanPham);
		pnCenterOfCen.add(pnLoaiSanPham);

		pnCenterOfCen.add(pnBtn);

		pnCen.add(pnCenterOfCen);

		pnMain.add(pnCen, BorderLayout.CENTER);

		// south
		JPanel pnSouth = new JPanel();
		pnSouth.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));

		JPanel pnForm = new JPanel();
		pnForm.setLayout(new BoxLayout(pnForm, BoxLayout.X_AXIS));

		JLabel lblMaSp = new JLabel("Mã SP:");
		txtMaSP = new JTextField(10);

		JLabel lblTenSp = new JLabel("Tên SP:");
		txtTenSp = new JTextField(10);

		JLabel lblTTKD = new JLabel("TTKD:");
		String[] item = { "Kinh doanh", "Ngừng kinh doanh" };
		txtTTKD = new JComboBox<String>(item);

		btnTimKiem = new JButton("Tìm kiếm", new ImageIcon("src/cua_hang_tien_loi/icon/search.png"));

		String[] title = { "Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Chất liệu", "Đơn giá", "TTKD" };
		modelTable = new DefaultTableModel(title, 0);
		table = new JTable(modelTable);

		// add vo panel
		pnSouth.add(lblMaSp);
		pnSouth.add(txtMaSP);
		pnSouth.add(lblTenSp);
		pnSouth.add(txtTenSp);
		pnSouth.add(lblTTKD);
		pnSouth.add(txtTTKD);
		pnSouth.add(Box.createHorizontalStrut(40));
		pnSouth.add(btnTimKiem);
		pnSouth.add(Box.createVerticalStrut(40));
		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(1000, 150));
		pnSouth.add(new JScrollPane(scroll));

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
		itemDTTheoNgay.addActionListener(this);
		itemDTTheoThang.addActionListener(this);
		itemDTTheoNam.addActionListener(this);

		// quay lai
		itemQuayLai.addActionListener(this);

		// btn
		btnCapNhat.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnImg.addActionListener(this);
		btnTimKiem.addActionListener(this);

		// key f1
		SystemUtils.setF1ToKey(pnMain, "F1", itemQuayLai);
	}

	public static void main(String[] args) {
		new CapNhatSanPhamQuanLy().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source.equals(itemTaiKhoan)) {
			new ThongTinTaiKhoanQuanLy().setVisible(true);
		} else if (source.equals(itemTroGiup)) {
			SystemUtils.openFile("/Users/lethoa/Documents/giaykhamsuckhoe.pdf");
		} else if (source.equals(itemDangXuat)) {
			this.dangXuat();
		} else if (source.equals(itemThemSP)) {
			this.setVisible(false);
			new ThemSanPhamQuanLy().setVisible(true);
		} else if (source.equals(itemCapNhatSp)) {
			this.setVisible(true);
			new CapNhatSanPhamQuanLy().setVisible(true);
		}

		// btn
		else if (source.equals(btnImg)) {
			SystemUtils.chonAnhSanPham(lblImage, pathImg);
		} else if (source.equals(btnLamMoi)) {
			this.clearTxtField();
		} else if (source.equals(btnCapNhat)) {
			this.capNhatSanPham();
		} else if (source.equals(btnTimKiem)) {
			this.timKiemSanPham();
		}
	}

	private void timKiemSanPham() {
		String ma = txtMaSanPham.getText();
		String ten = txtTenSanPham.getText();
		String ttkd = cboTrangThai.getSelectedItem().toString();
		boolean ttkdBoolean = ttkd.equals("Kinh Doanh") ? true : false;

		List<SanPham> dssp = sanPhamController.timKiemSanPham(ma, ten, null, ttkdBoolean);

		// do du lieu len form
		for (SanPham sp : dssp) {
			Object[] row = { sp.getMaSP(), sp.getTenSP(), sp.getLoaiSP(), sp.getChatLieu(), sp.getDonGia(),
					sp.isTTKD() ? "Kinh doanh" : "Không kinh doanh" };
			modelTable.addRow(row);
		}

	}

	// btn cap nhat
	private void capNhatSanPham() {
		String ma = txtMaSanPham.getText();
		String ten = txtTenSanPham.getText();
		String ttkd = cboTrangThai.getSelectedItem().toString();
		double donGia = Double.parseDouble(txtDonGia.getText());
		String chatLieu = txtChatLieu.getText();

		boolean ttkdBoolean = ttkd.equals("Kinh Doanh") ? true : false;

		SanPham sp = new SanPham(pathImg, ma, ten, chatLieu, ttkdBoolean, donGia, chatLieu);

		boolean statusCapNhatSP = sanPhamController.capNhatSanPham(sp);
		if (!statusCapNhatSP) {
			JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm sản phẩm thành công", "Thông báo",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void dangXuat() {
		int choice = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn đăng xuất?", "Thông báo",
				JOptionPane.YES_NO_OPTION);
		if (choice == JOptionPane.YES_OPTION) {
			this.setVisible(false);
			new DangNhap().setVisible(true);
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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
//		"Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Chất liệu", "Đơn giá", "TTKD" 
		txtMaSanPham.setText(modelTable.getValueAt(row, 1).toString());
		txtTenSanPham.setText(modelTable.getValueAt(row, 2).toString());
		String loai = modelTable.getValueAt(row, 3).toString();
		cboLoaiSanPham.setSelectedItem(loai);
		txtChatLieu.setText(modelTable.getValueAt(row, 4).toString());
		txtDonGia.setText(modelTable.getValueAt(row, 5).toString());
		String ttkd = modelTable.getValueAt(row, 6).toString();
		cboTrangThai.setSelectedItem(ttkd);
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
