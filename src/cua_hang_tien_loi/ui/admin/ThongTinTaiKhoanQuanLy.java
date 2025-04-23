package cua_hang_tien_loi.ui.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ThongTinTaiKhoanQuanLy extends JFrame {

	public ThongTinTaiKhoanQuanLy() {
		// TODO Auto-generated constructor stub
		this.UIThongTinTaiKhoan();
	}

	private void UIThongTinTaiKhoan() {
		setTitle("Quản lý cửa hàng tiện lợi - Thông tin tài khoản");
		setSize(600, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// main
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BorderLayout());

		// north
		JPanel pnNorth = new JPanel();
		JLabel lblTieuDe = new JLabel("THÔNG TIN TÀI KHOẢN");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTieuDe.setAlignmentX(CENTER_ALIGNMENT);
		lblTieuDe.setForeground(Color.white);
		pnNorth.setBackground(Color.cyan);
		pnNorth.add(lblTieuDe);

		pnMain.add(pnNorth, BorderLayout.NORTH);

		// west
		JPanel pnWest = new JPanel();
		pnWest.setLayout(new BorderLayout());
		pnWest.setPreferredSize(new Dimension(200, 200));

		JLabel lblImage = new JLabel();
		lblImage.setPreferredSize(new Dimension(100, 100));
		lblImage.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		pnMain.add(pnWest, BorderLayout.WEST);

		// cen
		JPanel pnCen = new JPanel();

		pnMain.add(pnCen, BorderLayout.CENTER);

		add(pnMain);
	}

	public static void main(String[] args) {
		new ThongTinTaiKhoanQuanLy().setVisible(true);
	}
}
