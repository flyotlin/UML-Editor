package future.strategy;

import future.canvas.Canvas;
import future.canvas.shapes.Class;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ClassStrategy implements CanvasStrategy {
    @Override
    public void mousePressed(MouseEvent e) {
        boolean isOriginInAnyShape = Canvas.getInstance().isOriginInAnyShape();
        if (isOriginInAnyShape) {
            return;
        }

        createClassInCanvas(e.getPoint());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    private void createClassInCanvas(Point origin) {
        Class classShape = new Class(origin);
        Canvas.getInstance().add(classShape);
    }
}
