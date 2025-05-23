package cua_hang_tien_loi.utils;

import java.awt.Desktop;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import cua_hang_tien_loi.ui.DangNhap;
import cua_hang_tien_loi.ui.admin.TrangChuQuanLy;
import cua_hang_tien_loi.ui.nv.TrangChuDangNhapNhanVien;

public class SystemUtils {

	private static String pathImg;
	public static String imagePath;

	// key f1
	public static void setF1ToKey(JPanel pnMain, String keyStroke, JMenuItem menuItem) {
		// Tạo KeyStroke cho phím
		KeyStroke key = KeyStroke.getKeyStroke(keyStroke);

		// Tạo ActionListener cho phím tắt
		Action action = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuItem.doClick(); // Giả lập click cho JMenuItem
			}
		};

		// Gán phím tắt cho JPanel
		InputMap inputMap = pnMain.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = pnMain.getActionMap();
		inputMap.put(key, keyStroke); // Đặt phím tắt vào InputMap
		actionMap.put(keyStroke, action); // Đặt hành động vào ActionMap
	}

	// mo file tro giup
	public static void openFile(String filePath) {
	    try {
	        File file = new File(filePath);
	        if (!file.exists()) {
	            JOptionPane.showMessageDialog(null, "File không tồn tại!");
	            return;
	        }
	        Desktop.getDesktop().open(file);
	    } catch (IOException e) {
	        JOptionPane.showMessageDialog(null, "Không thể mở file: " + e.getMessage());
	        e.printStackTrace();
	    }
	}


	// he thong quaylai
	public static void quayLai(JFrame frame) {
		int choice = JOptionPane.showConfirmDialog(frame, "Bạn có muốn quay lại trang chủ không?", "Xác nhận",
				JOptionPane.YES_NO_OPTION);
		if (choice == JOptionPane.YES_OPTION) {
			frame.setVisible(false);
			if (DangNhap.thongTinNV.isAdmin()) {
				new TrangChuQuanLy().setVisible(true);
			} else {
				new TrangChuDangNhapNhanVien().setVisible(true);

			}
		}
	}

	// he thong: dang xuat
	public static void dangXuat(JFrame frame) {
		int choice = JOptionPane.showConfirmDialog(frame, "Bạn có chắc chắn muốn đăng xuất?", "Thông báo",
				JOptionPane.YES_NO_OPTION);
		if (choice == JOptionPane.YES_OPTION) {
			frame.setVisible(false);
			new DangNhap().setVisible(true);
		}
	}

	// btn chon anh
	public static String chonAnhSanPham(JLabel lblImage, String currentPath) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Chọn ảnh sản phẩm");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			imagePath = file.getAbsolutePath();

			// Cập nhật hình ảnh trong JLabel
			lblImage.setIcon(new ImageIcon(imagePath));
			lblImage.setIcon(
					new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			lblImage.setText(""); 

			return imagePath; 
		}
		return currentPath; 
	}

}