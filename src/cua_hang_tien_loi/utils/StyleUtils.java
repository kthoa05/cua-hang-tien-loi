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

	public static JTextField createTextField(int x, int y, int width, int height) {
		JTextField txt = new JTextField();
		txt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt.setBounds(x, y, width, height);
		return txt;
	}

	public static JComboBox<String> createComboBox(String[] items, int x, int y, int width, int height) {
		JComboBox<String> cb = new JComboBox<>(items);
		cb.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cb.setBounds(x, y, width, height);
		return cb;
	}

	public static JButton createButton(String text, String iconPath) {
		JButton btn = new JButton(text);
		btn.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn.setIcon(new ImageIcon(iconPath));
		btn.setBackground(new Color(255, 153, 153));
		btn.setFocusPainted(false);
		return btn;
	}

	public static JLabel createLabelTitle(String text) {
		JLabel lbl = new JLabel(text);
		lbl.setFont(new Font("Tahoma", Font.BOLD, 28));
		lbl.setForeground(Color.WHITE);
		return lbl;
	}
	
	public static JLabel createLabel2(String text, int x, int y) {
		JLabel lbl = new JLabel(text);
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl.setBounds(x, y, 150, 30);
		return lbl;
	}


}
