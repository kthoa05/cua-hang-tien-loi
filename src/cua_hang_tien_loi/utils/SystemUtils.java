package cua_hang_tien_loi.utils;

import java.awt.Desktop;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class SystemUtils {

	//key f1
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
	
	

	//mo file tro giup
	public static void openFile(String filePath) {
	    try {
	        Desktop.getDesktop().browse(new java.net.URI("file:///" + filePath));
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}
}
