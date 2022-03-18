package object.future.Handler;

import object.future.Canvas;
import object.future.Object.GroupObject;
import toolbar.Toolbar;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GroupObjectMouseListener implements MouseListener, MouseMotionListener {
    private Canvas canvas;
    private GroupObject obj;

    public GroupObjectMouseListener(Canvas canvas, GroupObject obj) {
        this.canvas = canvas;
        this.obj = obj;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        obj.setObjectOrigPos(e.getPoint());
        switch (Toolbar.toolsNowSelected) {
            case SELECT:
                canvas.clearAnchorPoints();
                canvas.selectSingleObjects(obj);
                break;
            case ASSOCIATION:
            case GENERALIZATION:
            case COMPOSITION:
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (Toolbar.toolsNowSelected) {
            case SELECT:
                obj.moveObject(e.getPoint());
                break;
            case ASSOCIATION:
            case GENERALIZATION:
            case COMPOSITION:
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

    @Override
    public void mouseDragged(MouseEvent e) {
        switch (Toolbar.toolsNowSelected) {
            case SELECT:
                obj.moveObject(e.getPoint());
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
