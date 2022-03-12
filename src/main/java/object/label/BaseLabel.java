package object.label;

import object.BaseMouseListener;

import javax.swing.*;
import java.awt.*;

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
        this.setBorder(BorderFactory.createLineBorder(borderColor));
        this.addMouseListener(new BaseMouseListener());
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
