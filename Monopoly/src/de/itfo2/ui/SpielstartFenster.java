package de.itfo2.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.itfo2.objects.Spieler;
import de.itfo2.objects.Verwalter;

public class SpielstartFenster extends JDialog implements ActionListener{
	
	SpielerPanel[] panels = new SpielerPanel[4];
	JButton button_okay;
	
	public SpielstartFenster(){
		this.setSize(800,  400);
		this.setModal(true);
		this.setBackground(Color.BLACK);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.setLayout(new FlowLayout());
		build();
		pack();
		setVisible(true);
	}	
	
	private void build(){
		panels[0] = new SpielerPanel("Spieler 1");
		add(panels[0]);
		panels[1] = new SpielerPanel("Spieler 2");
		add(panels[1]);
		panels[2] = new SpielerPanel("Spieler 3");
		add(panels[2]);
		panels[3] = new SpielerPanel("Spieler 4");
		add(panels[3]);
		
		button_okay = new JButton("Spiel starten");
		button_okay.addActionListener(this);
		add(button_okay);
	}
	
	public ArrayList<Spieler> getSpielerDaten(){
		
		ArrayList<Spieler> spielerdaten = new ArrayList<Spieler>();
		for(SpielerPanel panel : panels){
			if(panel.isChecked())
				spielerdaten.add(panel.getSpielerDaten());
			}
		return spielerdaten;
	}
	
	private class SpielerPanel extends JPanel implements ListSelectionListener, ActionListener{
		private JCheckBox checkbox = new JCheckBox("Aktivieren");
		private JTextField field_name = new JTextField();
		//private JComboBox cb_figuren = new JComboBox();
		private JLabel label_figur = new JLabel();
		JList list;

		public SpielerPanel(String name){
			this.setLayout(new BorderLayout());
			this.setSize(200,  400);
			this.setBackground(Color.BLACK);
			
			DefaultListModel model = new DefaultListModel();
						
			
			field_name.setPreferredSize(new Dimension(150, 30));
			field_name.setText(name);
			
			for(Spielfigur spielfigur : SpielfigurList.getInstance().spielfiguren){
				//cb_figuren.addItem(spielfigur.getName());
				model.addElement(spielfigur.getName());
			}
			
			list = new JList(model); //data has type Object[]
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
			list.setVisibleRowCount(-1);
			list.setSelectedIndex(0);
			list.addListSelectionListener(this);
			JScrollPane listScroller = new JScrollPane(list);
			listScroller.setPreferredSize(new Dimension(122, 100));
			
//			cb_figuren.addActionListener(this);
//			cb_figuren.setPreferredSize(new Dimension(122, 30));
			ImageIcon icon = new ImageIcon(getClass().getResource(SpielfigurList.getInstance().getSpielfigurByName((String)list.getSelectedValue()).getPathBig()));
			label_figur.setIcon(icon);
			label_figur.setPreferredSize(new Dimension(78, 120));
			
			checkbox.addActionListener(this);
			
			this.add(checkbox, BorderLayout.SOUTH);
			this.add(field_name, BorderLayout.NORTH);
			//this.add(cb_figuren, BorderLayout.CENTER);
			this.add(listScroller, BorderLayout.CENTER);
			this.add(label_figur, BorderLayout.EAST);
			
			field_name.setEnabled(false);
			list.setEnabled(false);
			label_figur.setEnabled(false);
		
		}
		
		public JLabel getLabelFigur(){
			return label_figur;
		}
		
		public Spieler getSpielerDaten(){
			return new Spieler(field_name.getText(), 10000, null, SpielfigurList.getInstance().getSpielfigurByName((String)list.getSelectedValue()));
		}
		
		public boolean isChecked(){
			return checkbox.isSelected();
		}

		@Override
		public void valueChanged(ListSelectionEvent e) {
			ImageIcon icon = new ImageIcon(getClass().getResource(SpielfigurList.getInstance().getSpielfigurByName((String)list.getSelectedValue()).getPathBig()));
			label_figur.setIcon(icon);
			repaint();
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==checkbox){
					field_name.setEnabled(isChecked());
					list.setEnabled(isChecked());
					label_figur.setEnabled(isChecked());
			}
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Verwalter.getInstance().addSpieler(getSpielerDaten());
		this.setVisible(false);
	}

}
