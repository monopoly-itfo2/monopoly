package de.itfo2.ui;

import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

import javax.swing.Icon;
import javax.swing.JButton;

public class CardButton extends JButton {

    /**
	 * 
	 */
	private static final long	serialVersionUID	= 6427533110847581234L;
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