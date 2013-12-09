package de.itfo2.util;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class DialogCreator {

    public static void createOKDialog(String text, Container parent){
        final JOptionPane optionPane = new JOptionPane(text);
        final JDialog dialog = new JDialog();
        dialog.setContentPane(optionPane);
        optionPane.addPropertyChangeListener(
                new PropertyChangeListener() {
                    public void propertyChange(PropertyChangeEvent e) {
                        String prop = e.getPropertyName();

                        if (dialog.isVisible()
                                && (e.getSource() == optionPane)
                                && (prop.equals(JOptionPane.VALUE_PROPERTY))) {
                            dialog.dispose();
                        }
                    }
                });
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.pack();
        dialog.setLocation(parent.getX()+parent.getWidth()/2-dialog.getWidth()/2, parent.getY()+parent.getHeight()/2-dialog.getHeight()/2);
        dialog.setVisible(true);
    }

    public static int createChoiceDialog(String header, String text, Container parent){
        return JOptionPane.showConfirmDialog(parent, text, header, JOptionPane.YES_NO_OPTION);
    }
}
