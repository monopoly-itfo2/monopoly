package de.itfo2.util;


import javax.imageio.ImageIO;
import javax.swing.*;

import de.itfo2.ui.CardButton;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ButtonCreator {
    public static JButton getIconButton(ImageIcon icon, ImageIcon rolloverIcon, ImageIcon clickIcon) throws IOException {
        JButton b = new JButton(icon);
        b.setSize(icon.getIconWidth(), icon.getIconHeight());
        b.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b.setOpaque(false);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setContentAreaFilled(false);
        if(rolloverIcon != null)
            b.setRolloverIcon(rolloverIcon);
        if(clickIcon != null)
            b.setPressedIcon(clickIcon);

        return b;
    }

    public static CardButton getCardIconButton(ImageIcon active, ImageIcon inactive, double grad) throws IOException {
        CardButton b = new CardButton(active, inactive, grad);
        b.setSize(active.getIconWidth(), active.getIconHeight());
        b.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b.setOpaque(false);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setContentAreaFilled(false);

        return b;
    }
}
