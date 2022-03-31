package canvas.object;

import canvas.Canvas;
import canvas.line.BaseLine;
import canvas.handler.UMLObjectMouseListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class UMLObject extends BaseObject {
    // universal UMLObject configs
    private final static int UMLObjectWidth = 120;
    private final static int UMLObjectHeight = 120;
    private final static Color bgColor = Color.YELLOW;

    // anchor points
    private ArrayList<AnchorPoint> anchorPoints;

    // connected lines
    private ArrayList<BaseLine> headLines;
    private ArrayList<BaseLine> tailLines;

    /**
     * Constructor for UMLObject
     *
     * @param canvas    the canvas that this UMLObject belongs to
     * @param p         the position of the UMLObject(In Canvas Coordinate)
     * @param border    the border of this UMLObject(Line for Class, Circle for UseCase)
     */
    public UMLObject(Canvas canvas, Point p, Border border) {
        super(canvas, p, UMLObjectWidth, UMLObjectHeight, bgColor, border);

        // 根據subclass需求自行設定mouse listener
        this.objMouseListener = new UMLObjectMouseListener(canvas, this);
        this.objMouseMotionListener = new UMLObjectMouseListener(canvas, this);
        this.turnOnMouseListener(true);

        // Set default text for UMLObject
        if (border instanceof LineBorder) {
            this.setText("<html><p style='padding-top: 10px;'>" + "UMLObject " + baseObjCount + "</p></html>");
        } else {
            this.setText("UMLObject " + baseObjCount);
        }

        // Initialize ArrayLists
        this.anchorPoints = new ArrayList<AnchorPoint>();
        this.headLines = new ArrayList<BaseLine>();
        this.tailLines = new ArrayList<BaseLine>();

        if (border instanceof LineBorder) {
            this.setVerticalAlignment(SwingConstants.TOP);

            JSeparator sep = new JSeparator();
            sep.setBounds(0, 40, 120, 120);
            sep.setForeground(Color.BLACK);
            this.add(sep);

            sep = new JSeparator();
            sep.setBounds(0, 80, 120, 120);
            sep.setForeground(Color.BLACK);
            this.add(sep);
        }
    }

    @Override
    public void moveObject(Point newPos) {
        super.moveObject(newPos);
        this.moveConnectedLines(newPos);
        this.revalidate();
        this.repaint();
    }

    /**
     * Move the lines connected to the UMLObject.
     * <br>
     * @param newPos     UMLObject's new position(<b>BaseObject Coordinate</b>)
     */
    private void moveConnectedLines(Point newPos) {
        int dx = newPos.x-origPos.x;
        int dy = newPos.y-origPos.y;
        for (BaseLine line : this.headLines) {
            line.start.x += dx;
            line.start.y += dy;
        }
        for (BaseLine line : this.tailLines) {
            line.end.x += dx;
            line.end.y += dy;
        }
        canvas.revalidate();
        canvas.repaint();
    }

    /**
     * Create 4 anchor points on UMLObject,
     * and add 4 points to ArrayList anchorPoints.
     */
    public void createAnchorPoints() {
        AnchorPoint p;
        int size = AnchorPoint.pointSize;
        int w = this.getWidth();
        int h = this.getHeight();
        Point[] apPos = new Point[]{
                new Point(w/2-size/2, 0),       // top
                new Point(w/2-size/2, h-size),  // bottom
                new Point(0, h/2-size/2),       // left
                new Point(w-size, h/2-size/2)   // right
        };
        for (Point pos : apPos) {
            p = new AnchorPoint(pos);
            this.add(p);
            this.anchorPoints.add(p);
        }
        this.revalidate();
        this.repaint();
    }

    public void removeAnchorPoints() {
        for (AnchorPoint p : anchorPoints) {
            this.remove(p);
        }
        this.anchorPoints.clear();
        this.revalidate();
        this.repaint();
    }

    /**
     * Decide which anchor point the line connected to.
     * @param p     the position that line connected to the UMLObject(Canvas Coordinate)
     * @return      the position of the to-be-connected anchor point(in UMLCoordinate)
     */
    public Point decideLineConnectPoint(Point p) {
        // from Canvas Coordinate to UMLObject Coordinate
        p.x = p.x - this.getX();
        p.y = p.y - this.getY();

        int size = AnchorPoint.pointSize;
        int w = getWidth();
        int h = getHeight();
        double slope = h/((double) w);
        boolean cond_1 = p.y - (slope * p.x) >= 0;
        boolean cond_2 = p.y + (slope * p.x - w) >= 0;
        Point[] apPos = new Point[]{
                new Point(w/2-size/2, 0),       // top
                new Point(w/2-size/2, h-size),  // bottom
                new Point(0, h/2-size/2),       // left
                new Point(w-size, h/2-size/2)   // right
        };
        if (cond_1 && cond_2) {
            return apPos[1];    // bottom
        }
        if (!cond_1 && !cond_2) {
            return apPos[0];    // top
        }
        if (!cond_1) {
            return apPos[3];    // right
        }
        return apPos[2];        // left
    }

    public void addHeadLines(BaseLine line) {
        this.headLines.add(line);
    }

    public void addTailLines(BaseLine line) {
        this.tailLines.add(line);
    }
}
