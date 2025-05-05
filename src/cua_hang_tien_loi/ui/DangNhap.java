package cua_hang_tien_loi.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.Flow;

import javax.swing.*;

import cua_hang_tien_loi.controller.NhanVienController;
import cua_hang_tien_loi.entity.NhanVien;
import cua_hang_tien_loi.ui.admin.TrangChuQuanLy;
import cua_hang_tien_loi.ui.nv.TrangChuDangNhapNhanVien;

public class DangNhap extends JFrame implements ActionListener {
	private static JTextField txtTaiKhoan;
	private static JPasswordField txtMatKhau;
	private JButton btnDangNhap, btnThoat, btnQuenMK;
	private JToggleButton btnHienMatKhau;
	private static NhanVienController nvController;
	public static NhanVien thongTinNV;

	public DangNhap() {
		nvController = new NhanVienController();
		setTitle("Quản lý cửa hàng tiện lợi - Đăng nhập");
		setSize(580, 370);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel pnMain = new JPanel(new BorderLayout());
		add(pnMain);

		// north
		JLabel lblDangNhap = new JLabel("ĐĂNG NHẬP", SwingConstants.CENTER);
		lblDangNhap.setFont(new Font("Arial", Font.BOLD, 25));
		lblDangNhap.setOpaque(true);
		lblDangNhap.setBackground(new Color(0, 153, 204));
		lblDangNhap.setForeground(Color.WHITE);
		lblDangNhap.setPreferredSize(new Dimension(600, 60));
		pnMain.add(lblDangNhap, BorderLayout.NORTH);

		// cen
		JPanel pnCenter = new JPanel(new BorderLayout(20, 0));
		pnMain.add(pnCenter, BorderLayout.CENTER);

		// Icon
		JLabel lblIcon = new JLabel();
		ImageIcon icon = new ImageIcon("src/cua_hang_tien_loi/icon/login.png");
		Image scaled = icon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
		lblIcon.setIcon(new ImageIcon(scaled));
		pnCenter.add(lblIcon, BorderLayout.WEST);

		// form
		JPanel pnForm = new JPanel();
		pnForm.setLayout(new BoxLayout(pnForm, BoxLayout.Y_AXIS));
		pnForm.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		pnCenter.add(pnForm, BorderLayout.CENTER);

		// Kích thước chung
		Dimension inputSize = new Dimension(250, 30);
		Dimension inputSizeMatKhau = new Dimension(220, 30);

		// tai khoan
		JPanel pnTaiKhoan = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTaiKhoan = new JLabel("Tài khoản:");

		JPanel pnTaiKhoanInput = new JPanel();
		pnTaiKhoanInput.setLayout(new BoxLayout(pnTaiKhoanInput, BoxLayout.X_AXIS));

		txtTaiKhoan = new JTextField();
		txtTaiKhoan.setPreferredSize(inputSize);
		txtTaiKhoan.setMaximumSize(inputSize);
		txtTaiKhoan.setMinimumSize(inputSize);

		pnTaiKhoanInput.add(txtTaiKhoan);
		pnTaiKhoan.add(lblTaiKhoan);
		pnTaiKhoan.add(pnTaiKhoanInput);

		pnForm.add(pnTaiKhoan);

		// mat khau
		JPanel pnMatKhau = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblMatKhau = new JLabel("Mật khẩu:");

		JPanel pnMatKhauInput = new JPanel();
		pnMatKhauInput.setLayout(new BoxLayout(pnMatKhauInput, BoxLayout.X_AXIS));

		txtMatKhau = new JPasswordField();
		txtMatKhau.setPreferredSize(inputSizeMatKhau);
		txtMatKhau.setMaximumSize(inputSizeMatKhau);
		txtMatKhau.setMinimumSize(inputSizeMatKhau);

		btnHienMatKhau = new JToggleButton(new ImageIcon("src/cua_hang_tien_loi/icon/xem-pass.png"));
		btnHienMatKhau.setPreferredSize(new Dimension(30, 30));

		pnMatKhauInput.add(txtMatKhau);
		pnMatKhauInput.add(Box.createRigidArea(new Dimension(5, 0))); // kc giua field vs nut
		pnMatKhauInput.add(btnHienMatKhau);

		pnMatKhau.add(lblMatKhau);
		pnMatKhau.add(pnMatKhauInput);

		pnForm.add(pnMatKhau);

		// quen mat khau
		btnQuenMK = new JButton("Quên Mật Khẩu?");
		btnQuenMK.setBorderPainted(false);
		btnQuenMK.setContentAreaFilled(false);
		btnQuenMK.setForeground(Color.BLUE);
		btnQuenMK.setFont(new Font("Arial", Font.ITALIC, 12));
		btnQuenMK.setAlignmentX(Component.LEFT_ALIGNMENT);
		pnForm.add(btnQuenMK);
		pnForm.add(Box.createVerticalStrut(15));

		// thoat & dang nhap
		JPanel pnButtons = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
		btnDangNhap = new JButton("Đăng nhập");
		btnDangNhap.setBackground(new Color(0, 153, 76));
		btnDangNhap.setForeground(Color.WHITE);

		btnThoat = new JButton("Thoát");
		btnThoat.setBackground(Color.RED);
		btnThoat.setForeground(Color.WHITE);

		pnButtons.add(btnThoat);
		pnButtons.add(btnDangNhap);
		pnForm.add(pnButtons);

		// event
		btnDangNhap.addActionListener(this);
		btnThoat.addActionListener(this);
		btnQuenMK.addActionListener(this);
		btnHienMatKhau.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == btnDangNhap) {
			this.dangNhap();
		} else if (source == btnThoat) {
			System.exit(0);
		} else if (source == btnHienMatKhau) {
			this.anHienMatKhau();
		} else if (source == btnQuenMK) {
			JOptionPane.showMessageDialog(this, "Vui lòng liên hệ quản trị viên!");
		}
	}

	private void dangNhap() {
		String taiKhoan = txtTaiKhoan.getText().trim();
		String matKhau = new String(txtMatKhau.getPassword()).trim();

		thongTinNV = nvController.getThongTinTaiKhoan(taiKhoan, matKhau);

		if (thongTinNV == null) {
			JOptionPane.showMessageDialog(this, "Sai tài khoản hoặc mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (thongTinNV.isAdmin()) {
			this.setVisible(false);
			new TrangChuQuanLy().setVisible(true);
		} else {
			this.setVisible(false);
			new TrangChuDangNhapNhanVien().setVisible(true);
		}
	}

	private void anHienMatKhau() {
		if (btnHienMatKhau.isSelected()) {
			txtMatKhau.setEchoChar((char) 0);
			btnHienMatKhau.setIcon(new ImageIcon("src/cua_hang_tien_loi/icon/an-pass.png"));
		} else {
			txtMatKhau.setEchoChar('*');
			btnHienMatKhau.setIcon(new ImageIcon("src/cua_hang_tien_loi/icon/xem-pass.png"));
		}
	}
}
