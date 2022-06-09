package canvas.strategy;

import canvas.Canvas;
import canvas.shapes.line.BaseLine;
import canvas.factory.line.BaseLineFactory;

import java.awt.*;
import java.awt.event.MouseEvent;

public class CreateLineStrategy implements BaseStrategy {
    private final BaseLineFactory factory;

    public CreateLineStrategy(BaseLineFactory factory) {
        this.factory = factory;
    }

    @Override
    public void mousePressed(MouseEvent e) {}

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
        if (canvas.isPointInGroup(origin) || canvas.isPointInGroup(destination)) {
            return;
        }
        if (canvas.areTwoPointsInSameShape(origin, destination)) {
            return;
        }
        createBaseLineInCanvas(origin, destination);
    }

    @Override
    public void mouseDragged(MouseEvent e) {}

    private void createBaseLineInCanvas(Point origin, Point destination) {
        Canvas canvas = Canvas.getInstance();
        BaseLine line = factory.createBaseLine(origin, destination);
        canvas.add(line);
    }
}
