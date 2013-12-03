package de.itfo2.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatPanel extends JPanel {

    /**
	 * 
	 */
	private static final long	serialVersionUID	= -4183109407028539227L;
	JTextField textfield;
    JTextArea textarea;
    JScrollPane scrollpanel;

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

        textarea = new JTextArea();
        //textarea.setEnabled(false);
        textarea.setFocusable(false);
        textarea.setForeground(Color.black);
        scrollpanel.setViewportView(textarea);
        add(scrollpanel, BorderLayout.CENTER);
    }

    public void addUsermessage(String user, String text){
        textarea.append(user + ": " + text + "\n");
        autoScroll();
        scrollpanel.revalidate();
        scrollpanel.repaint();

    }

    public void autoScroll(){
        Point point = new Point( 0, (int)(textarea.getSize().getHeight()) );
        scrollpanel.getViewport().setViewPosition( point );
    }
}
