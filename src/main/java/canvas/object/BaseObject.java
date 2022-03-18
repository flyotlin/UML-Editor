package canvas.object;

import canvas.Canvas;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class BaseObject extends JLabel {
    protected canvas.Canvas canvas;

    // gid
    protected static int baseObjCount = 0;

    // layer
    public int layer;

    // original x and y, used to move the object
    public Point origPos = new Point();

    // mouse listeners
    MouseListener objMouseListener;
    MouseMotionListener objMouseMotionListener;

    public BaseObject(
            Canvas canvas, Point p, int w, int h,
            Color bgColor, Border border) {
        this.canvas = canvas;
        this.setBounds(p.x, p.y, w, h);
        this.setBackground(bgColor);
        this.setBorder(border);
        this.setOpaque(true);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.turnOnMouseListener(true);
        this.setBaseObjCount();
    }

    /**
     * Turn On/Off the mouse listener and mouse motion
     * listeners on BaseObject.
     *
     * @param turnOn    turn on or off the mouse listeners
     */
    public void turnOnMouseListener(boolean turnOn) {
        for (MouseListener l : this.getMouseListeners()) {
            this.removeMouseListener(l);
        }
        for (MouseMotionListener l : this.getMouseMotionListeners()) {
            this.removeMouseMotionListener(l);
        }
        if (turnOn) {
            this.addMouseListener(objMouseListener);
            this.addMouseMotionListener(objMouseMotionListener);
        }
    }

    /**
     * Set the original position when mouse pressed on BaseObject.
     * <br>
     * @param p     the original mouse position(<b>BaseObject Coordinate</b>)
     */
    public void setObjectOrigPos(Point p) {
        origPos.x = p.x;
        origPos.y = p.y;
    }

    /**
     * Move the object to new position newPos.
     * <br>
     * Subclasses can override this class to specify additional object moving mechanism.
     * <br>
     * @param newPos     the new mouse position(<b>BaseObject Coordinate</b>)
     */
    public void moveObject(Point newPos) {
        int dx = newPos.x-origPos.x;
        int dy = newPos.y-origPos.y;
        this.setBounds(
                this.getX()+dx,
                this.getY()+dy,
                this.getWidth(),
                this.getHeight()
        );
        this.revalidate();
        this.repaint();
    }

    private void setBaseObjCount() {
        baseObjCount++;
    }

}
