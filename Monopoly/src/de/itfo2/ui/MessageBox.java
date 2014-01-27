package de.itfo2.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MessageBox {
	private JButton[] options = new JButton[3];
	private JPanel buttonPnl = new JPanel(new BorderLayout());
	private int optionIdx = 0;
	private JTextArea message = new JTextArea();
	private JFrame frm;

	public MessageBox(String message) {
		frm = new JFrame();
		JPanel pnl = new JPanel(new BorderLayout());
		setMessage(message);
		this.message.setWrapStyleWord(true);
		this.message.setLineWrap(true);
		this.message.setBackground(pnl.getBackground ());
		Font f = new Font(Font.SANS_SERIF, Font.BOLD, 12);
		
		this.message.setFont(f);
		pnl.add(this.message, BorderLayout.CENTER);
		buttonPnl.setSize(300, 60);
		pnl.add(buttonPnl, BorderLayout.SOUTH);
		frm.setSize(300, 150);
		frm.add(pnl);
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int x = (screenSize.width - frm.getWidth()) / 2;
		int y = (screenSize.height - frm.getHeight()) / 2;
		frm.setLocation(x, y);
	}

	public void setMessage(String message) {
		this.message.setText(message);
	}

	public void setTitle(String title) {
		frm.setTitle(title);
	}

	public JButton addOption(String option) {
		options[optionIdx] = new JButton(option);
		options[optionIdx].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		switch (optionIdx) {
		case 0:
			buttonPnl.add(options[optionIdx],BorderLayout.CENTER);
			break;
		case 1:
			buttonPnl.remove(options[0]);
			buttonPnl.add(options[0],BorderLayout.EAST);
			buttonPnl.add(options[1],BorderLayout.WEST);
			break;
		case 2:
			buttonPnl.remove(options[1]);
			buttonPnl.add(options[0],BorderLayout.EAST);
			buttonPnl.add(options[1],BorderLayout.CENTER);
			buttonPnl.add(options[2],BorderLayout.WEST);
			break;
		default:
			System.err.println("This Widget can only handly three Options!");
			break;
		}
		optionIdx++;
		return options[optionIdx-1];
	}

	public void show() {
		frm.setVisible(true);
	}
	
	public void hide() {
		frm.setVisible(false);
	}
	
	public void dispose() {
		frm.dispose();
	}

}
