package canvas.object;

import canvas.Canvas;
import canvas.line.BaseLine;
import canvas.handler.GroupObjectMouseListener;

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
            if (line.contain == 0 || line.contain == 1) {
                line.start.x += dx;
                line.start.y += dy;
            }
            if (line.contain == 0 || line.contain == 2) {
                line.end.x += dx;
                line.end.y += dy;
            }
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
            canvas.objects.add(obj);
        }
        for (BaseLine line : this.lines) {
            line.contain = line.previousContain;
        }
    }

    /**
     * Add BaseLines contained in the GroupObject inside.
     * <br>
     * So that GroupObject can also move those lines together.
     */
    private void addContainedLinesToGroup() {
        for (BaseLine line : canvas.getCanvasLines()) {
            boolean headContained = false, tailContained = false;
            if (line.start.x >= getX() && line.start.y >= getY()
                && line.start.x <= getX()+getWidth() && line.start.y <= getY()+getHeight())
                headContained = true;
            if (line.end.x >= getX() && line.end.y >= getY()
                    && line.end.x <= getX()+getWidth() && line.end.y <= getY()+getHeight())
                tailContained = true;
            if (!headContained && !tailContained) {
                continue;
            }
            line.previousContain = line.contain;
            if (headContained && tailContained) {
                line.contain = 0;
            } else if (headContained) {
                line.contain = 1;
            } else {
                line.contain = 2;
            }
            this.lines.add(line);
        }
    }
}
