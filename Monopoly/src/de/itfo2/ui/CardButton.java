package de.itfo2.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;

public class CardButton extends JButton {

    private Area shape;
    private double grad;

    public CardButton(Icon active, Icon inactive, double grad){
        this.grad = grad;
        setIcon(inactive);
        setRolloverIcon(active);
        setPressedIcon(active);
        setPreferredSize(new Dimension(active.getIconWidth(), active.getIconHeight()));
        initShape();
    }

    protected void initShape(){
        Dimension s = getPreferredSize();
        // in diesem Beispiel erstellen wir einen Kreis als Form
        Shape tempRec = new Rectangle2D.Float(0, 45, 180, 90);
        shape = new Area(tempRec);
        AffineTransform tr = new AffineTransform();
        tr.rotate(grad, s.getWidth()/2, s.getHeight()/2);
        shape.transform(tr);
    }

    @Override
    public boolean contains(int x, int y){
        // Wir geben nur dann true zurück, wenn sich der Cursor
        // innerhalb der vorher erzeugten Form befindet.
        if(shape == null) initShape();
        return shape.contains(x, y);
    }
}