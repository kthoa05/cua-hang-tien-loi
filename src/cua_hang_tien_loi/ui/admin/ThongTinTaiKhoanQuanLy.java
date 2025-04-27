package cua_hang_tien_loi.ui.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cua_hang_tien_loi.ui.DangNhap;
import cua_hang_tien_loi.utils.StyleUtils;

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

		// cen CAN LAM
		JPanel pnCen = new JPanel();
		pnCen.setLayout(new BorderLayout());

		// anh
		JPanel pnAnh = new JPanel();
		pnAnh.setPreferredSize(new Dimension(150, 150));
		pnAnh.setBorder(BorderFactory.createTitledBorder("Ảnh nhân viên"));

		// lay duong dan anh
		String pathToImage = DangNhap.thongTinNV.getImgPath();
		ImageIcon imageIcon = new ImageIcon(pathToImage);

		// Resize ảnh cho vừa khung
		Image image = imageIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
		JLabel lblAnh = new JLabel(new ImageIcon(image));

		pnAnh.add(lblAnh);

		// Thêm panel ảnh vào center (hoặc west nếu muốn)
		pnCen.add(pnAnh, BorderLayout.WEST);

		// form
		JPanel pnForm = new JPanel();
		pnForm.setLayout(new BoxLayout(pnForm, BoxLayout.Y_AXIS));

		// ma nv
		JPanel pnMa = new JPanel();
		JLabel lblMa = StyleUtils.createLabel("Mã NV:");
		String ma = DangNhap.thongTinNV.getMaNV();
		JLabel lblInputMa = new JLabel(ma);

		pnMa.add(lblMa);
		pnMa.add(lblInputMa);

		pnForm.add(pnMa);

		// ten nv
		JPanel pnTen = new JPanel();
		JLabel lblTen = StyleUtils.createLabel("Họ tên NV:");
		String ten = DangNhap.thongTinNV.getHoTen();
		JLabel lblInputTen = new JLabel(ten);

		pnTen.add(lblTen);
		pnTen.add(lblInputTen);

		pnForm.add(pnTen);

		// gioi tinh
		JPanel pnGT = new JPanel();
		JLabel lblGT = StyleUtils.createLabel("Giới tính:");
		boolean gt = DangNhap.thongTinNV.isPhai();
		String gtText = gt ? "Nữ" : "Nam";
		JLabel lblInputGT = new JLabel(gtText);

		pnGT.add(lblGT);
		pnGT.add(lblInputGT);

		pnForm.add(pnGT);

		// cmnd
		JPanel pnCmnd = new JPanel();
		JLabel lblCmnd = StyleUtils.createLabel("CCCD:");
		String cmnd = DangNhap.thongTinNV.getCmnd();
		JLabel lblInputCmnd = new JLabel(cmnd);

		pnCmnd.add(lblCmnd);
		pnCmnd.add(lblInputCmnd);

		pnForm.add(pnCmnd);

		// sdt
		JPanel pnSdt = new JPanel();
		JLabel lblSdt = StyleUtils.createLabel("SĐT:");
		String sdt = DangNhap.thongTinNV.getSdt();
		JLabel lblInputSdt = new JLabel(sdt);

		pnSdt.add(lblSdt);
		pnSdt.add(lblInputSdt);

		pnForm.add(pnSdt);

		// email
		JPanel pnEmail = new JPanel();
		JLabel lblEmail = StyleUtils.createLabel("Email:");
		String email = DangNhap.thongTinNV.getEmail();
		JLabel lblInputEmail = new JLabel(email);

		pnEmail.add(lblEmail);
		pnEmail.add(lblInputEmail);

		pnForm.add(pnEmail);

		pnCen.add(pnForm, BorderLayout.CENTER);

		pnMain.add(pnCen, BorderLayout.CENTER);

		add(pnMain);
	}

}
