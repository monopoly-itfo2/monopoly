package de.itfo2.ui;

import de.itfo2.util.ButtonCreator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class MiddlePanel extends JPanel {

    private JButton rollDiceButton, startButton, nextButton;
    private CardButton gemeinschaftskartenButton, ereigniskartenButton;
    private GridBagConstraints c;

    private StatusPanel[] statuspanel = new StatusPanel[4];
    private ChatPanel chatpanel;

    public MiddlePanel() throws IOException {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        c = new GridBagConstraints();
        setPreferredSize(new Dimension(720, 720));

        build();
    }

    public void build() throws IOException {

        final MiddlePanel mp = this;

        c.gridx = 3;
        c.gridy = 0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        BufferedImage img = ImageIO.read(getClass().getResource("/de/itfo2/ui/resources/karte_pink_schraeg.png"));
        ImageIcon icon = new ImageIcon(img);
        //gemeinschaftskartenButton = ButtonCreator.getIconButton(icon, null, null);
        gemeinschaftskartenButton = ButtonCreator.getCardIconButton(icon, icon, Math.toRadians(45));
        gemeinschaftskartenButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(gemeinschaftskartenButton, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        img = ImageIO.read(getClass().getResource("/de/itfo2/ui/resources/karte_orange_schraeg.png"));
        icon = new ImageIcon(img);
        //ereigniskartenButton = (CardButton)ButtonCreator.getIconButton(icon, null, null);
        ereigniskartenButton = ButtonCreator.getCardIconButton(icon, icon, Math.toRadians(45));
        ereigniskartenButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(ereigniskartenButton, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        statuspanel[0] = new StatusPanel();
        add(statuspanel[0], c);

        c.gridx = 1;
        statuspanel[1] = new StatusPanel();
        add(statuspanel[1], c);

        c.gridx = 2;
        img = ImageIO.read(getClass().getResource("/de/itfo2/ui/resources/wuerfel.gif"));
        icon = new ImageIcon(getClass().getResource("/de/itfo2/ui/resources/wuerfel.gif"));
        rollDiceButton = ButtonCreator.getIconButton(new ImageIcon(img), icon, icon);

        rollDiceButton.setHorizontalAlignment(JButton.CENTER);
        this.add(rollDiceButton, c);

        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new BorderLayout());
        actionPanel.setBackground(Color.WHITE);
        
        c.gridx = 0;
        c.gridy = 0;
        img = ImageIO.read(getClass().getResource("/de/itfo2/ui/resources/startbutton.png"));
        icon = new ImageIcon(img);
        startButton = ButtonCreator.getIconButton(icon, icon, icon);
        startButton.setHorizontalAlignment(JButton.CENTER);
        actionPanel.add(startButton, BorderLayout.WEST);

        c.gridx = 1;
        c.gridy = 0;
        img = ImageIO.read(getClass().getResource("/de/itfo2/ui/resources/sanduhr.png"));
        icon = new ImageIcon(img);
        nextButton = ButtonCreator.getIconButton(icon, icon, icon);
        nextButton.setHorizontalAlignment(JButton.CENTER);
        img = ImageIO.read(getClass().getResource("/de/itfo2/ui/resources/sanduhr_inactive.png"));
        nextButton.setDisabledIcon(new ImageIcon(img));
        actionPanel.add(nextButton, BorderLayout.EAST);
        
        c.fill = c.HORIZONTAL;
        this.add(actionPanel, c);

        c.gridy = 1;
        c.gridx = 3;
        statuspanel[2] = new StatusPanel();
        add(statuspanel[2], c);

        c.gridx = 4;
        statuspanel[3] = new StatusPanel();
        add(statuspanel[3], c);

        c.gridwidth = 2;
        c.gridx = 3;
        c.gridy = 2;
        c.fill = GridBagConstraints.CENTER;
        //c.fill = GridBagConstraints.HORIZONTAL;
        chatpanel = new ChatPanel(300, 200);
        add(chatpanel, c);
    }

    public void updateFeld(){

        for(int i=0;i<3;i++){
            if(getStatuspanel(i)!=null)
                statuspanel[i].repaint();
        }
    }

    // Würfeln
    public int getRandom() {
        return (int) (Math.random() * 5 + 1);
    }

    public int getSpielerGeld()
    {
        return 0;
    }

    public StatusPanel getStatuspanel(int pos) {
        return statuspanel[pos];
    }

    public JButton getRollDiceButton() {
        return rollDiceButton;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getNextButton() {
        return nextButton;
    }

    public CardButton getGemeinschaftskartenButton() {
        return gemeinschaftskartenButton;
    }

    public CardButton getEreigniskartenButton() {
        return ereigniskartenButton;
    }

    public ChatPanel getChatpanel() {
        return chatpanel;
    }
}
