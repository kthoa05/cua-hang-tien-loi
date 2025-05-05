package cua_hang_tien_loi.utils;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

public class StyleUtils {
	public static JMenuItem createItemMenu(String text, String iconPath) {
		JMenuItem item = new JMenuItem(text, new ImageIcon(iconPath));
		item.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		item.setIconTextGap(10);
		item.setBorderPainted(false);
		item.setBackground(Color.decode("#FAFAFA"));
		return item;
	}

	public static JLabel createHeaderTitle(String text) {
		JLabel lbl = new JLabel(text);
		lbl.setFont(new Font("Arial", Font.BOLD, 20));
		return lbl;
	}

	public static JLabel createLabel(String text) {
		JLabel lbl = new JLabel(text);
		lbl.setFont(new Font("Arial", Font.BOLD, 14));
		return lbl;
	}

	public static JLabel createLabel2(String text, int x, int y) {
		JLabel lbl = new JLabel(text);
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl.setBounds(x, y, 150, 30);
		return lbl;
	}

}
