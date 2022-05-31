package future.strategy;

import future.canvas.Canvas;
import future.canvas.shapes.AssociationLine;

import java.awt.*;
import java.awt.event.MouseEvent;

public class CreateLineStrategy implements BaseStrategy {
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Canvas canvas = Canvas.getInstance();
        Point origin = canvas.getOrigin();
        Point destination = canvas.getDestination();

        if (!canvas.isPointInAnyCanvasShape(origin)) {
            return;
        }
        if (!canvas.isPointInAnyCanvasShape(destination)) {
            return;
        }
        if (canvas.areTwoPointsInSameShape(origin, destination)) {
            return;
        }
        createAssociationLine(origin, destination);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    private void createAssociationLine(Point origin, Point destination) {
        Canvas canvas = Canvas.getInstance();
        AssociationLine line = new AssociationLine(origin, destination);
        canvas.add(line);
    }
}
