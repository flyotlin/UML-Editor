package object.new_obj;

import object.canvas.NewCanvas;
import toolbar.Toolbar;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/**
 * for Class and Usecase
 * extends from BaseObject
 */

public class UMLObject extends BaseObject {
    public static int UMLObjCount = 0;
    // 之後應該把w, h放到base，讓group能夠自行設定大小，moveObject也就不用傳w, h
    public final static int objectWidth = 120;
    public final static int objectHeight = 120;
    private static Color bgColor = Color.YELLOW;

    // line creation
    public int lineOrigX;
    public int lineOrigY;

    // border variable
    // anchor points
    public ArrayList<AnchorPoint> anchorPoints;

    // connected lines
    public ArrayList<BaseLine> connectedStartLines;
    public ArrayList<BaseLine> connectedEndLines;

    public UMLObject(int x, int y, NewCanvas canvas) {
        super(x, y, objectWidth, objectHeight, bgColor, canvas);
        this.init(this.gid);
    }

    public UMLObject(int x, int y, String text, NewCanvas canvas) {
        super(x, y, objectWidth, objectHeight, bgColor, canvas);
        this.init(text);
    }

    private void init(String text) {
        this.anchorPoints = new ArrayList<AnchorPoint>();
        this.connectedStartLines = new ArrayList<BaseLine>();
        this.connectedEndLines = new ArrayList<BaseLine>();
        this.setText(text);
        this.addMouseListener(new UMLObjectMouseListener(canvas,this, anchorPoints));
        this.addMouseMotionListener(new UMLObjectMouseMotionListener(canvas, this));
    }

    public void createAnchorPoints() {
        AnchorPoint p;
        int ps = AnchorPoint.pointSize;
        int uw = UMLObject.objectWidth;
        int uh = UMLObject.objectHeight;
        int[][] coords = new int[][]{
                {uw/2-ps/2, 0}, // 上
                {uw/2-ps/2, uh-ps}, // 下
                {0, uh/2-ps/2}, // 左
                {uw-ps, uh/2-ps/2}  // 右
        };
        for (int[] coord : coords) {
            p = new AnchorPoint();
            p.setBounds(coord[0], coord[1], ps, ps);
            this.add(p);
            anchorPoints.add(p);
        }
        this.revalidate();
        this.repaint();
    }

    @Override
    public void moveObject(int newX, int newY, int w, int h) {
        super.moveObject(newX, newY, w, h);
        // handle connected line's position
        for (BaseLine line : this.connectedStartLines) {
            line.start.x += (newX-origX);
            line.start.y += (newY-origY);
        }
        for (BaseLine line : this.connectedEndLines) {
            line.end.x += (newX-origX);
            line.end.y += (newY-origY);
        }
        canvas.revalidate();
        canvas.repaint();
        this.revalidate();
        this.repaint();
    }

    /**
     * Pass x, y in coordinates by this UMLObject
     * */
    public int[] decideLineCoord(int x, int y) {
        double slope = UMLObject.objectHeight/(double) UMLObject.objectWidth;
        boolean res1 = (y - (slope * x) >= 0) ? true : false;
        boolean res2 = (y + (slope * (x-UMLObject.objectWidth)) >= 0) ? true : false;

        int ps = AnchorPoint.pointSize;
        int uw = UMLObject.objectWidth;
        int uh = UMLObject.objectHeight;
        int[][] coords = new int[][]{
                {uw/2-ps/2, 0}, // 上
                {uw/2-ps/2, uh-ps}, // 下
                {0, uh/2-ps/2}, // 左
                {uw-ps, uh/2-ps/2}  // 右
        };
        if (res1 && res2) {     // 下
            return coords[1];
        }
        if (!res1 && !res2) {   // 上
            return coords[0];
        }
        if (!res1) {    // 右
            return coords[3];
        }
        return coords[2];    // 左
    }

    @Override
    protected void setGid() {
        UMLObjCount++;
        this.gid = "uml" + UMLObjCount;
    }

    class UMLObjectMouseListener implements MouseListener {

        private NewCanvas canvas;
        private UMLObject obj;
        private ArrayList<AnchorPoint> anchorPoints;

        public UMLObjectMouseListener(NewCanvas canvas, UMLObject obj, ArrayList<AnchorPoint> anchorPoints) {
            this.canvas = canvas;
            this.obj = obj;
            this.anchorPoints = anchorPoints;
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            switch (Toolbar.toolsNowSelected) {
                case SELECT:
                    canvas.clearAllAnchorPoints();
                    obj.createAnchorPoints();
                    obj.setObjectOrigPosition(e.getX(), e.getY());
                    break;
                case ASSOCIATION:
                    obj.lineOrigX = e.getX();
                    obj.lineOrigY = e.getY();
                    break;
                default:
                    break;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            switch (Toolbar.toolsNowSelected) {
                case SELECT:
                    obj.moveObject(e.getX(), e.getY(), UMLObject.objectWidth, UMLObject.objectHeight);
                    break;
                case ASSOCIATION:
                case GENERALIZATION:
                case COMPOSITION:
                    canvas.createLine(obj, e.getX(), e.getY());
                    break;
                default:
                    break;
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    class UMLObjectMouseMotionListener implements MouseMotionListener {

        private NewCanvas canvas;
        private UMLObject obj;

        public UMLObjectMouseMotionListener(NewCanvas canvas, UMLObject obj) {
            this.canvas = canvas;
            this.obj = obj;
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            switch (Toolbar.toolsNowSelected) {
                case SELECT:
                    obj.moveObject(e.getX(), e.getY(), UMLObject.objectWidth, UMLObject.objectHeight);
                    break;
                case ASSOCIATION:
//                    int x1 = obj.getX() + obj.lineOrigX;
//                    int y1 = obj.getY() + obj.lineOrigY;
//                    int x2 = obj.getX() + e.getX();
//                    int y2 = obj.getY() + e.getY();
//                    canvas.drawTempLine(x1, y1, x2, y2);
                    break;
                default:
                    break;
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }
}
