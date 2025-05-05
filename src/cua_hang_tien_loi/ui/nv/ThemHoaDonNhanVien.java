package cua_hang_tien_loi.ui.nv;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cua_hang_tien_loi.controller.ChiTietHoaDonController;
import cua_hang_tien_loi.controller.HoaDonController;
import cua_hang_tien_loi.entity.ChiTietHoaDon;
import cua_hang_tien_loi.entity.HoaDon;
import cua_hang_tien_loi.entity.KhachHang;
import cua_hang_tien_loi.entity.NhanVien;
import cua_hang_tien_loi.entity.SanPham;
import cua_hang_tien_loi.ui.admin.CapNhatNhanVien;
import cua_hang_tien_loi.ui.admin.CapNhatSanPhamQuanLy;
import cua_hang_tien_loi.ui.admin.ThemHoaDonQuanLy;
import cua_hang_tien_loi.ui.admin.ThemNhanVien;
import cua_hang_tien_loi.ui.admin.ThemSanPhamQuanLy;
import cua_hang_tien_loi.ui.admin.ThongKeHoaDonQuanLy;
import cua_hang_tien_loi.ui.admin.ThongTinTaiKhoanQuanLy;
import cua_hang_tien_loi.ui.admin.TraCuuHoaDonQuanLy;
import cua_hang_tien_loi.ui.admin.TraCuuKhachHangQuanLy;
import cua_hang_tien_loi.ui.admin.TraCuuNhanVien;
import cua_hang_tien_loi.ui.admin.TraCuuSanPhamQuanLy;
import cua_hang_tien_loi.utils.StyleUtils;
import cua_hang_tien_loi.utils.SystemUtils;

public class ThemHoaDonNhanVien extends JFrame implements ActionListener {
	private JMenuItem itemTaiKhoan;
	private JMenuItem itemTroGiup;
	private JMenuItem itemDangXuat;
	private JMenuItem itemTraCuuKH;
	private JMenuItem itemTraCuuHD;
	private JMenuItem itemThemHD;
	private JMenuItem itemQuayLai;
	private JTextField txtMaHD;
	private JTextField txtNgayLapHD;
	private JTextField txtSoLuong;
	private JTextField txtMaNV;
	private JTextField txtSP;
	private JTextField txtDonGia;
	private JButton btnThem;
	private JButton btnLamMoi;
	private JButton btnXuatHoaDon;
	private DefaultTableModel modelTable;
	private JTable table;
	private ChiTietHoaDonController cthdController;
	private HoaDonController hdController;
	private JMenuItem itemThongKeHoaDon;
	private JTextField txtMaKH;

	public ThemHoaDonNhanVien() {
		cthdController = new ChiTietHoaDonController();
		hdController = new HoaDonController();
		this.initUIThemHoaDon();
	}

	// giao dien

	private void initUIThemHoaDon() {
		setTitle("Quản lý cửa hàng tiện lợi - Thêm hóa đơn");
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
		JLabel lbTitle = StyleUtils.createHeaderTitle("THÊM HÓA ĐƠN");
		lbTitle.setAlignmentX(CENTER_ALIGNMENT);
		lbTitle.setFont(new Font("Arial", Font.BOLD, 20));
		lbTitle.setForeground(Color.BLACK);
		pn.add(lbTitle);

		JPanel pn1 = new JPanel();
		pn1.setLayout(new BoxLayout(pn1, BoxLayout.X_AXIS));
		JLabel lblMaHD = new JLabel("Mã HD:");
		txtMaHD = new JTextField(25);
		JLabel lblNgayLapHD = new JLabel("Ngày lập HD:");
		txtNgayLapHD = new JTextField(25);
		JLabel lblSoLuong = new JLabel("Số lượng:");
		txtSoLuong = new JTextField(25);
		pn1.add(lblMaHD);
		pn1.add(Box.createHorizontalStrut(10));
		pn1.add(txtMaHD);
		pn1.add(lblNgayLapHD);
		pn1.add(Box.createHorizontalStrut(10));
		pn1.add(txtNgayLapHD);
		pn1.add(lblSoLuong);
		pn1.add(Box.createHorizontalStrut(10));
		pn1.add(txtSoLuong);
		pn.add(Box.createVerticalStrut(20));
		pn.add(pn1);

		JPanel pn2 = new JPanel();
		pn2.setLayout(new BoxLayout(pn2, BoxLayout.X_AXIS));
		JLabel lblMaNV = new JLabel("Mã NV:");
		txtMaNV = new JTextField(25);
		JLabel lblMaSP = new JLabel("Mã SP:");
		txtSP = new JTextField(25);
		JLabel lblDonGia = new JLabel("Đơn giá:");
		txtDonGia = new JTextField(25);
		pn2.add(lblMaNV);
		pn2.add(Box.createHorizontalStrut(10));
		pn2.add(txtMaNV);
		pn2.add(lblMaSP);
		pn2.add(Box.createHorizontalStrut(43));
		pn2.add(txtSP);
		pn2.add(lblDonGia);
		pn2.add(Box.createHorizontalStrut(20));
		pn2.add(txtDonGia);
		pn.add(Box.createVerticalStrut(20));
		pn.add(pn2);

		// button
		JPanel pn3 = new JPanel();
		pn3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel lblMaKH = new JLabel("Mã KH:");
		txtMaKH = new JTextField(25);
		btnThem = new JButton("Thêm", new ImageIcon("src/cua_hang_tien_loi/icon/add.png"));
		btnLamMoi = new JButton("Làm mới", new ImageIcon("src/cua_hang_tien_loi/icon/reload.png"));
		btnXuatHoaDon = new JButton("Xuất hóa đơn", new ImageIcon("src/cua_hang_tien_loi/icon/xuathd.png"));
		pn3.add(lblMaKH);
		pn3.add(txtMaKH);
		pn3.add(btnThem);
		pn3.add(Box.createHorizontalStrut(5));
		pn3.add(btnLamMoi);
		pn3.add(Box.createHorizontalStrut(5));
		pn3.add(btnXuatHoaDon);
		pn.add(Box.createVerticalStrut(10));
		pn.add(pn3);

		pnCen.add(pn);

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

		btnThem.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnXuatHoaDon.addActionListener(this);

		// key f1
		SystemUtils.setF1ToKey(pnMain, "F1", itemQuayLai);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		// he thong
		if (source.equals(itemTaiKhoan)) {
			new ThongTinTaiKhoanNhanVien().setVisible(true);
		} else if (source.equals(itemTroGiup)) {
			SystemUtils.openFile("C:/hd/BaoCaoHSK_Nhom18.docx");
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
			this.setVisible(false);
			new ThongKeHoaDonNhanVien().setVisible(true);
		}

		// quay lai
		else if (source.equals(itemQuayLai)) {
			SystemUtils.quayLai(this);
		}

		// btn
		else if (source.equals(btnLamMoi)) {
			this.clear();
		} else if (source.equals(btnThem)) {
			this.themHoaDon();
		} else if (source.equals(btnXuatHoaDon)) {
			this.xuatHoaDon();
		}
	}

	private void xuatHoaDon() {
		String maHD = txtMaHD.getText();

		HoaDon hoaDon = hdController.timHoaDonTheoMa(maHD);
		ChiTietHoaDon chiTietHD = cthdController.timChiTietTheoMa(maHD);

		int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xuất hóa đơn?", "Xác nhận",
				JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			if (hoaDon == null || chiTietHD == null) {
				JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn để xuất.", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			File file = new File("HoaDon_" + maHD + ".txt");
			try (PrintWriter writer = new PrintWriter(file)) {
				writer.println("=========== HÓA ĐƠN ===========");
				writer.println("Mã hóa đơn:     " + hoaDon.getMaHD());
				writer.println("Ngày lập:       " + hoaDon.getNgayLapHD());
				writer.println("Nhân viên:      " + hoaDon.getNv().getHoTen());
				writer.println("--------------------------------");
				writer.println("Sản phẩm:       " + chiTietHD.getSp().getMaSP());
				writer.println("Số lượng:       " + chiTietHD.getSoLuong());
				writer.println("Thành tiền:     " + chiTietHD.getThanhTien());
				writer.println("--------------------------------");
				writer.println("TỔNG TIỀN:      " + chiTietHD.getSoLuong() * chiTietHD.getThanhTien());
				writer.println("================================");

				JOptionPane.showMessageDialog(this, "Xuất hóa đơn thành công" + file.getAbsolutePath(), "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
				Desktop.getDesktop().open(file);

			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, "Lỗi khi xuất hóa đơn" + e.getMessage(), "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this, "Hủy xuất hóa đơn.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void themHoaDon() {
		String maHD = txtMaHD.getText();
		String ngayLap = txtNgayLapHD.getText();
		Date ngayLapHD = Date.valueOf(ngayLap);
		String maKH = txtMaKH.getText();

		int soLuong = Integer.parseInt(txtSoLuong.getText());
		String maNV = txtMaNV.getText();
		String maSP = txtSP.getText();
		double donGia = Double.parseDouble(txtDonGia.getText());

		HoaDon hoaDon = new HoaDon(maHD, new KhachHang(maKH), new NhanVien(maNV, null), ngayLapHD);
		ChiTietHoaDon chiTietHD = new ChiTietHoaDon(hoaDon, new SanPham(maSP), soLuong, (long) (soLuong * donGia));

		hoaDon.setTongTien(hoaDon.tongTien());

		boolean statusInsertHD = hdController.themHoaDon(hoaDon);
		boolean statusInsertCTHD = cthdController.themCTHD(chiTietHD);

		if (statusInsertHD && statusInsertCTHD) {
			JOptionPane.showMessageDialog(this, "Hóa đơn đã được thêm thành công!", "Thông báo",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "Thêm hóa đơn không thành công.", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void clear() {
		txtMaHD.setText("");
		txtNgayLapHD.setText("");
		txtSoLuong.setText("");
		txtMaNV.setText("");
		txtSP.setText("");
		txtDonGia.setText("");
		txtMaHD.requestFocus();
	}

	public static void main(String[] args) {
		new ThemHoaDonNhanVien().setVisible(true);
	}
}
