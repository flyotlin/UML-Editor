package canvas.handler;

import canvas.Canvas;
import toolbar.Toolbar;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CanvasMouseListener implements MouseListener {

    private Canvas canvas;

    public CanvasMouseListener(Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        canvas.pressedPos = e.getPoint();
        switch (Toolbar.toolsNowSelected) {
            case SELECT:
                canvas.clearAnchorPoints();
                break;
            case CLASS:
            case USECASE:
                canvas.createUMLObject(e.getPoint());
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        canvas.releasedPos = e.getPoint();
        switch (Toolbar.toolsNowSelected) {
            case SELECT:
                canvas.selectMultipleObjects();
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
