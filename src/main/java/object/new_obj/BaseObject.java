package object.new_obj;

import object.canvas.NewCanvas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class BaseObject extends JLabel  {
    String gid = "";
    public Boolean isGroup = false;
    NewCanvas canvas;

    // layer
    public int layer;

    // // original x and y, used to move the object
    public int origX;
    public int origY;

    public BaseObject(int x, int y, int w, int h, Color bgColor, NewCanvas canvas) {
        this.setOpaque(true);
        this.setBounds(x, y, w, h);
        this.setBackground(bgColor);
        this.canvas = canvas;
        this.setGid();
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void moveObject(int newX, int newY, int w, int h) {
        // handle object's position
        this.setBounds(
                this.getX()+(newX-origX),
                this.getY()+(newY-origY),
                w, h
        );

        this.revalidate();
        this.repaint();
    }

    public void setObjectOrigPosition(int x, int y) {
        origX = x;
        origY = y;
    }

    protected abstract void setGid();
}
