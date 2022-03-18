package object.future.Object;

import object.future.Canvas;
import object.future.Handler.GroupObjectMouseListener;
import object.future.Line.BaseLine;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class GroupObject extends BaseObject {

    private final static Border border = BorderFactory.createDashedBorder(Color.RED, 3, 4, 3, true);

    private ArrayList<BaseObject> selectedObjects;
    private ArrayList<BaseLine> lines;

    /**
     * Constructor for GroupObject
     * @param canvas
     * @param p         the position of the GroupObject
     * @param w         the width of the GroupObject
     * @param h         the height of the GroupObject
     */
    public GroupObject(Canvas canvas, ArrayList<BaseObject> selectedObjects, Point p, int w, int h) {
        super(canvas, p, w, h, null, border);

        this.lines = new ArrayList<BaseLine>();

        // 根據subclass需求自行設定mouse listener
        this.objMouseListener = new GroupObjectMouseListener(canvas, this);
        this.objMouseMotionListener = new GroupObjectMouseListener(canvas, this);

        this.addMouseListener(objMouseListener);
        this.addMouseMotionListener(objMouseMotionListener);

        this.selectedObjects = selectedObjects;
        this.addSelectedObjectsToGroup();
        this.addContainedLinesToGroup();
    }

    @Override
    public void moveObject(Point newPos) {
        super.moveObject(newPos);
        this.moveContainedLines(newPos);
        this.revalidate();
        this.repaint();
    }

    /**
     * Move lines contained in the group when the GroupObject is moved.
     * @param newPos    the new position of the GroupObject
     */
    private void moveContainedLines(Point newPos) {
        int dx = newPos.x-origPos.x;
        int dy = newPos.y-origPos.y;
        for (BaseLine line : this.lines) {
            line.start.x += dx;
            line.start.y += dy;
            line.end.x += dx;
            line.end.y += dy;
        }
        canvas.revalidate();
        canvas.repaint();
    }

    /**
     * Add multiple selected BaseObjects into GroupObject.
     */
    private void addSelectedObjectsToGroup() {
        // add to GroupObject
        // disable mouse listeners for the selected object
        for (BaseObject obj : selectedObjects) {
            obj.turnOnMouseListener(false);
            obj.setBounds(obj.getX()-this.getX(), obj.getY()-this.getY(), obj.getWidth(), obj.getHeight());
            canvas.remove(obj);
            this.add(obj);
        }
        this.revalidate();
        this.repaint();
    }

    /**
     * Remove selected BaseObjects from the GroupObject
     */
    public void removeSelectedObjectsFromGroup() {
        for (Component c : this.getComponents()) {
            BaseObject obj = (BaseObject) c;
            // enable listeners
            obj.turnOnMouseListener(true);

            // reset positions for objects
            obj.setBounds(this.getX()+obj.getX(), this.getY()+obj.getY(), obj.getWidth(), obj.getHeight());
            this.remove(obj);
            canvas.add(obj);
        }
    }

    /**
     * Add BaseLines contained in the GroupObject inside.
     * <br>
     * So that GroupObject can also move those lines together.
     */
    private void addContainedLinesToGroup() {
        for (BaseLine line : canvas.getCanvasLines()) {
            if (line.start.x < getX() || line.end.x > getX()+getWidth())
                continue;
            if (line.start.y < getY() || line.end.y > getY()+getHeight())
                continue;
            this.lines.add(line);
        }
    }
}
