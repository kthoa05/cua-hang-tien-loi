package cua_hang_tien_loi.ui.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class QuanLyNhanVien extends JFrame {
	JTable table;

	public QuanLyNhanVien() {
		setTitle("Quản lý nhân viên");
		setSize(1200, 750);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// panel main
		JPanel pnMain = new JPanel(new BorderLayout(10, 10));
		pnMain.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// panel left
		JPanel pnLeft = new JPanel();
		pnLeft.setLayout(new BoxLayout(pnLeft, BoxLayout.Y_AXIS));
		pnLeft.setBackground(new Color(230, 230, 250)); // màu nhẹ đẹp
		pnLeft.setPreferredSize(new Dimension(200, 0));

		JLabel iconLabel = new JLabel(new ImageIcon("src/cua_hang_tien_loi/user-icon.png"));
		iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel titleLabel = new JLabel("Quản lý nhân viên");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
		titleLabel.setForeground(Color.RED);
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		pnLeft.add(iconLabel);
		pnLeft.add(Box.createVerticalStrut(10));
		pnLeft.add(titleLabel);

		// add vao ben trai
		pnMain.add(pnLeft, BorderLayout.WEST);

		// panel right
		JPanel pnRight = new JPanel(new BorderLayout(10, 10));

		// icon + btn
		JPanel pnBtn = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnBtn.setBackground(Color.WHITE);

		JButton btnThem = createButtonWithIcon("Thêm", "src/cua_hang_tien_loi/icon/add.png");
		JButton btnXoa = createButtonWithIcon("Xóa", "src/cua_hang_tien_loi/icon/delete.png");
		JButton btnSua = createButtonWithIcon("Sửa", "src/cua_hang_tien_loi/icon/edit.png");
		JButton btnTim = createButtonWithIcon("Tìm", "src/cua_hang_tien_loi/icon/search.svg");

		pnBtn.add(btnThem);
		pnBtn.add(btnXoa);
		pnBtn.add(btnSua);
		pnBtn.add(btnTim);

		pnRight.add(pnBtn, BorderLayout.NORTH);

		// table
		String[] columns = { "Mã NV", "Họ tên", "Chức vụ", "Lương" };
		JTable table = new JTable(new DefaultTableModel(columns, 0));
		JScrollPane scrollPane = new JScrollPane(table);

		pnRight.add(scrollPane, BorderLayout.CENTER);

		// add vao ben phai
		pnMain.add(pnRight, BorderLayout.CENTER);

		add(pnMain);
		setVisible(true);
	}

	private JButton createButtonWithIcon(String text, String iconPath) {
		JButton button = new JButton(text);
		button.setIcon(new ImageIcon(iconPath));
		button.setFocusPainted(false);
		button.setForeground(Color.BLACK);
		button.setFont(new Font("Arial", Font.BOLD, 20));
		button.setHorizontalTextPosition(SwingConstants.RIGHT);
		button.setIconTextGap(10);
		button.setPreferredSize(new Dimension(150, 60));
		return button;
	}

	public static void main(String[] args) {
		new QuanLyNhanVien().setVisible(true);
	}
}
