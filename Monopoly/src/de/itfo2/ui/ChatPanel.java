package de.itfo2.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ChatPanel extends JPanel {

    JTextField textfield;
    JScrollPane scrollpanel;
    Container messagepanel;
    public ChatPanel(int x, int y){
        setPreferredSize(new Dimension(x, y));
        setLayout(new BorderLayout());

        textfield = new JTextField();
        textfield.setBounds(10, (int)this.getPreferredSize().getHeight()-40, (int)this.getPreferredSize().getWidth()-20, 30);
        textfield.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    addUsermessage("System", textfield.getText());
                    textfield.setText("");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        add(textfield, BorderLayout.SOUTH);

        scrollpanel = new JScrollPane();
        scrollpanel.setBounds(10, 10, (int)this.getPreferredSize().getWidth()-20, (int)this.getPreferredSize().getHeight()-50);

        messagepanel = new JPanel();
        messagepanel.setPreferredSize(new Dimension(scrollpanel.getWidth(), scrollpanel.getHeight()));
        messagepanel.setSize((int)scrollpanel.getPreferredSize().getWidth(), (int)scrollpanel.getPreferredSize().getHeight());
        messagepanel.setLayout(new FlowLayout());
        scrollpanel.setViewportView(messagepanel);
        add(scrollpanel, BorderLayout.CENTER);

        addUsermessage("Micha", "Hallo erste Nachricht!");
        addUsermessage("Micha", "Hallo zweite! Nachricht!");
    }

    public void addUsermessage(String user, String text){
        JLabel label = new JLabel(user + ": " + text);
        label.setSize((int)this.getPreferredSize().getWidth()-20, 30);
        messagepanel.add(label);
        messagepanel.revalidate();
        messagepanel.repaint();
        scrollpanel.revalidate();
        scrollpanel.repaint();
    }
}
