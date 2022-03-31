package canvas;

import canvas.line.BaseLine;
import canvas.handler.CanvasMouseListener;
import canvas.object.BaseObject;
import canvas.object.CircleBorder;
import canvas.object.GroupObject;
import canvas.object.UMLObject;
import toolbar.Toolbar;
import toolbar.Tools;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Canvas extends JPanel {
    // basic Canvas configs
    private final static int canvasWidth = 100;
    private final static int canvasHeight = 100;

    // holds both UMLObject and GroupObject
    private ArrayList<BaseObject> objects;
    // holds all lines
    private ArrayList<BaseLine> lines;
    // selected BaseObjects now
    private ArrayList<BaseObject> selectedObjects;

    // pressed and released point
    public Point pressedPos = null;
    public Point releasedPos = null;

    // mouse listener
    private MouseListener cMouseListener;
    private MouseMotionListener cMouseMotionListener;

    /**
     * Constructor for Canvas
     */
    public Canvas() {
        this.objects = new ArrayList<BaseObject>();
        this.lines = new ArrayList<BaseLine>();
        this.selectedObjects = new ArrayList<BaseObject>();

        this.cMouseListener = new CanvasMouseListener(this);
        this.cMouseMotionListener = null;

        // add mouse listeners
        this.addMouseListener(cMouseListener);
        this.addMouseMotionListener(cMouseMotionListener);

        // set Canvas basic configs
        this.setLayout(null);
        this.setSize(canvasWidth, canvasHeight);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(4));

        for (BaseLine l : this.lines) {
            l.drawLine(g2d);
        }
    }

    /**
     * Create UMLObject on the canvas.
     * @param p     the position of the new UMLObject(in Canvas Coordinate)
     */
    public void createUMLObject(Point p) {
        Border border;
        if (Toolbar.toolsNowSelected == Tools.CLASS)
            border = BorderFactory.createLineBorder(Color.BLACK);
        else
            border = new CircleBorder();
        UMLObject obj = new UMLObject(this, p, border);
        this.objects.add(obj);
        this.add(obj);
        this.revalidate();
        this.repaint();
    }

    /**
     * Create new Line connecting 2 UMLObjects head and tail.
     *
     * @param head  the head UMLObject being connected
     * @param p     the mouse released position on tail UMLObject(In head UMLObject Coordinates)
     */
    public void createLine(UMLObject head, Point p) {
        Point headPosInCanvas = toCanvasCoordinate(head, head.origPos);
        Point tailPosInCanvas = toCanvasCoordinate(head, p);

        UMLObject tail = isLineDestInUMLObject(head, tailPosInCanvas);
        if (tail == null)
            return;

        Point headConnectPos = head.decideLineConnectPoint(headPosInCanvas);
        Point tailConnectPos = tail.decideLineConnectPoint(tailPosInCanvas);

        BaseLine line = BaseLine.LineFactory(
                toCanvasCoordinate(head, headConnectPos),
                toCanvasCoordinate(tail, tailConnectPos));

        line.startObj = head;
        line.endObj = tail;
        head.addHeadLines(line);
        tail.addTailLines(line);
        this.lines.add(line);

        this.revalidate();
        this.repaint();
    }

    public void createGroupObject() {
        if (selectedObjects.size() <= 1) {
            return;
        }

        Point[] points = getTopLeft2BottomRightPoints();
        Point topLeft = points[0];
        Point bottomRight = points[1];

        GroupObject g = new GroupObject(this, selectedObjects, topLeft, bottomRight.x-topLeft.x, bottomRight.y-topLeft.y);
        this.objects.add(g);
        this.add(g);
        this.revalidate();
        this.repaint();
    }

    public void removeGroupObject() {
        if (selectedObjects.size() != 1)
            return;
        if (selectedObjects.get(0) instanceof UMLObject)
            return;
        ((GroupObject) selectedObjects.get(0)).removeSelectedObjectsFromGroup();
        this.remove(selectedObjects.get(0));
        this.selectedObjects.clear();
        this.revalidate();
        this.repaint();
    }

    public void selectSingleObjects(BaseObject obj) {
        this.selectedObjects.add(obj);
    }

    public void selectMultipleObjects() {
        // TODO: 做一些處理把tempStart, tempEnd改成左上到右下
        Point[] points = getTopLeft2BottomRightPoints();
        Point topLeft = points[0];
        Point bottomRight = points[1];

        for (BaseObject obj : this.objects) {
            Point p1 = new Point(obj.getX(), obj.getY());
            Point p2 = new Point(p1.x+obj.getWidth(), p1.y+obj.getHeight());
            if (p1.x < topLeft.x || p2.x > bottomRight.x)
                continue;
            if (p1.y < topLeft.y || p2.y > bottomRight.y)
                continue;
            this.selectedObjects.add(obj);
            if (obj instanceof UMLObject)
                ((UMLObject) obj).createAnchorPoints();
        }
    }

    public void renameUMLObject(String newName) {
        if ( newName == null || newName.equals(""))
            return;
        if (selectedObjects.size() != 1)
            return;
        if (selectedObjects.get(0) instanceof GroupObject)
            return;
        UMLObject obj = (UMLObject) selectedObjects.get(0);
        obj.setText(newName);
    }

    /**
     * Clear AnchorPoints of all UMLObjects in canvas.
     */
    public void clearAnchorPoints() {
        this.selectedObjects.clear();
        for (BaseObject obj : objects) {
            if (obj instanceof GroupObject) {
                continue;
            }
            ((UMLObject) obj).removeAnchorPoints();
        }
    }

    /**
     * Get the ArrayList of lines inside the canvas.
     * <br>
     * Notice: This destroys the encapsulation of the Class Canvas, but can be useful for GroupObject.
     * @return  ArrayList of BaseLine
     */
    public ArrayList<BaseLine> getCanvasLines() {
        return this.lines;
    }

    /**
     * Convert @param Point p in UMLObject Coordinate to Canvas Coordinate.
     *
     * @param base  UMLObject in the canvas, @param p's position is relative to this Object.
     * @param p     the position in UMLObject Coordinate that needs to be converted.
     * @return      the converted position in Canvas Coordinate.
     */
    private Point toCanvasCoordinate(UMLObject base, Point p) {
        Point ret;
        ret = new Point(base.getX()+p.x, base.getY()+p.y);
        return ret;
    }

    /**
     * Check if the destination p is in any of the UMLObject.
     * If p is in head itself, this check also failed.
     *
     * @param head  the head UMLObject of the line
     * @param p     the tail position(in Canvas Coordinate)
     * @return      null if dest not in any of the UMLObjects, else return the tail UMLObject.
     */
    private UMLObject isLineDestInUMLObject(UMLObject head, Point p) {
        for (BaseObject obj : objects) {
            if (obj instanceof GroupObject)
                continue;
            if (obj == head)
                continue;
            if (p.x < obj.getX() || p.x > obj.getX()+obj.getWidth())
                continue;
            if (p.y < obj.getY() || p.y > obj.getY()+obj.getHeight())
                continue;
            return (UMLObject) obj;
        }
        return null;
    }

    /**
     * Convert releasedPos and pressedPos to Point topLeft and Point bottomRight.
     * <br><br>
     * Used in selectMultipleObject and createGroupObject.
     * <br><br>
     * When a bunch of objects selected, pressed point and released point might not be from top-left to bottom-right, so this method helps the conversion.
     * <br>
     * @return Point[]  an Point array consisting of Point topLeft and Point bottomRight.
     */
    private Point[] getTopLeft2BottomRightPoints() {
        Point topLeft = new Point();
        Point bottomRight = new Point();
        int orig_dx, orig_dy, dx, dy;

        orig_dx = releasedPos.x - pressedPos.x;
        orig_dy = releasedPos.y - pressedPos.y;
        dx = Math.abs(orig_dx);
        dy = Math.abs(orig_dy);
        topLeft.setLocation(
                pressedPos.x + ((orig_dx < 0) ? orig_dx : 0),
                pressedPos.y + ((orig_dy < 0) ? orig_dy : 0)
        );
        bottomRight.setLocation(
                topLeft.x + dx,
                topLeft.y + dy
        );

        return new Point[] {topLeft, bottomRight};
    }
}
