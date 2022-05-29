package future.strategy;

import future.canvas.Canvas;
import future.canvas.shapes.Class;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ClassStrategy implements CanvasStrategy {
    private boolean isOriginInAnyShape = false;
    private boolean isDestinationInAnyShape = false;

    @Override
    public void mousePressed(MouseEvent e) {
        isOriginInAnyShape = Canvas.getInstance().isOriginInAnyShape();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Canvas canvas = Canvas.getInstance();
        isDestinationInAnyShape = canvas.isDestinationInAnyShape();

        if (isOriginInAnyShape || isDestinationInAnyShape) {
            return;
        }

        if ((canvas.getOrigin().x != canvas.getDestination().x) || (canvas.getOrigin().y != canvas.getDestination().y)) {
            return;
        }

        createClassInCanvas(e.getPoint());
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    private void createClassInCanvas(Point origin) {
        Class classShape = new Class(origin);
        Canvas.getInstance().add(classShape);
    }
}
