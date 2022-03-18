package object.canvas;

import object.new_obj.*;
import toolbar.Toolbar;
import toolbar.Tools;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class NewCanvas extends JPanel {
    public ArrayList<BaseObject> objects;
    public ArrayList<BaseLine> lines;

    // draw temp line(animation)
    private Point tempStart = null;
    private Point tempEnd = null;

    // selected object now
    private UMLObject selectedObj;

    public NewCanvas() {
        this.objects = new ArrayList<BaseObject>();
        this.lines = new ArrayList<BaseLine>();
        this.addMouseListener(new NewCanvasMouseListener(this, objects));
        this.addMouseMotionListener(new NewCanvasMouseMotionListener());

        this.setLayout(null);
        this.setSize(100, 100);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void clearAllAnchorPoints() {
        for (BaseObject obj : objects) {
            if (obj.isGroup) {
                continue;
            }
            UMLObject umlObj = (UMLObject) obj;
            for (AnchorPoint p : umlObj.anchorPoints) {
                umlObj.remove(p);
            }
            umlObj.anchorPoints.clear();
            umlObj.revalidate();
            umlObj.repaint();
        }
    }

    // 只給Canvas呼叫，因為在空白處建立新Class，所以設為private
    private void createUMLObject(int x, int y) {
        Border border;
        if (Toolbar.toolsNowSelected == Tools.CLASS) {
            border = BorderFactory.createLineBorder(Color.BLACK);
        } else {
            border = new CircleBorder();
        }
        BaseObject obj = new UMLObject(x, y, this, border);
        objects.add(obj);
        this.add(obj);
        this.revalidate();
        this.repaint();
    }

    private UMLObject checkIsDestInUMLObject(UMLObject self, int x2, int y2) {
        for (BaseObject obj : objects) {
            // bad, but how to?
            if (!(obj instanceof  UMLObject))
                continue;

            if (obj == self)
                continue;
            if (x2 < obj.getX() || x2 > obj.getX()+UMLObject.objectWidth)
                continue;
            if (y2 < obj.getY() || y2 > obj.getY()+UMLObject.objectHeight)
                continue;
            return (UMLObject) obj;
        }
        return null;
    }

    private int[] fixCoord(int x, int y, int x1, int y1, int x2, int y2) {
        int[] ret = new int[4];
        ret[0] = x+x1;
        ret[1] = y+y1;
        ret[2] = x+x2;
        ret[3] = y+y2;
        return ret;
    }

    // 給UMLObject呼叫，因為mouse event會在UMLObject上觸發，所以設為public
    // check的時候，start可以避免自己連自己
    // x: lineEndX, y: lineEndY
    public void createLine(UMLObject start, int x, int y) {
        int[] coord = fixCoord(start.getX(), start.getY(), start.lineOrigX, start.lineOrigY, x, y);
        // 先檢查終點是否在object中
        UMLObject end = checkIsDestInUMLObject(start, coord[2], coord[3]);
        if (end == null) {
            return;
        }
        // FIXME: 好像有點bug，end抓的位置怪怪的。另外，line太多會擋到label
        int[] s = start.decideLineCoord(start.lineOrigX, start.lineOrigY);
        int[] e = end.decideLineCoord(x-end.getX(), y-end.getY());

        BaseLine line = BaseLine.LineFactory(start.getX()+s[0], start.getY()+s[1], end.getX()+e[0],end.getY()+e[1]);

        // keep record, this helps move objects
        line.startObj = start;
        line.endObj = end;
        start.connectedStartLines.add(line);
        end.connectedEndLines.add(line);

        this.lines.add(line);
        this.revalidate();
        this.repaint();
    }

    // 暫時先不用，因為畫線還需要對準anchorPoints
    public void drawTempLine(int x1, int y1, int x2, int y2) {
        tempStart = new Point(x1, y1);
        tempEnd = new Point(x2, y2);
        this.revalidate();
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(4));

        // draw temp line for animation
        if (tempStart != null) {
            g2d.drawLine(tempStart.x, tempStart.y, tempEnd.x, tempEnd.y);
        }

        for (BaseLine l : this.lines) {
            l.drawLine(g2d);
        }
    }

    class NewCanvasMouseListener implements MouseListener {

        private NewCanvas canvas;
        private ArrayList<BaseObject> objects;

        public NewCanvasMouseListener(NewCanvas canvas, ArrayList<BaseObject> objects) {
            this.canvas = canvas;
            this.objects = objects;
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            switch (Toolbar.toolsNowSelected) {
                case SELECT:
                    canvas.clearAllAnchorPoints();
                    break;
                case CLASS:
                case USECASE:
                    canvas.createUMLObject(e.getX(), e.getY());
                    break;
                default:
                    break;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    class NewCanvasMouseMotionListener implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }
}
