package object.label;

import object.BaseMouseListener;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class BaseLabel extends JLabel {
    private static Color backgroundColor = Color.YELLOW;
    private static Color borderColor = Color.RED;

    private int origX;
    private int origY;

    public BaseLabel(String text, int x, int y, int w, int h) {
        this.setOpaque(true);
        this.setText(text);
        this.setBounds(x, y, w, h);
        this.setBackground(backgroundColor);

        // MouseListener and MouseMotionListener
        // TODO: leave only one listener with all the functions
        this.addMouseListener(new BaseMouseListener());
        this.addMouseMotionListener(new BaseMouseListener());
    }

    public int getOrigX() {
        return this.origX;
    }

    public int getOrigY() {
        return this.origY;
    }

    public void setOrigX(int x) {
        this.origX = x;
    }

    public void setOrigY(int y) {
        this.origY = y;
    }
}
