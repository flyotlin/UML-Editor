package canvas.strategy;

import canvas.Canvas;
import canvas.shapes.Shape;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SelectStrategy implements BaseStrategy {
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Canvas canvas = Canvas.getInstance();
        Point origin = canvas.getOrigin();
        Point destination = canvas.getDestination();

        unselectAllShapes();
        if (canvas.isPointInAnyCanvasShape(origin)) {
            selectSingle(destination);
            moveShape(origin, destination);
        } else {
            selectMultiple(origin, destination);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {}

    private void selectSingle(Point p) {
        Canvas canvas = Canvas.getInstance();
        Shape selectedShape = canvas.getCanvasShapeByPoint(p);
        if (selectedShape != null) {
            canvas.select(selectedShape);
        }
    }

    private void selectMultiple(Point origin, Point destination) {
        ArrayList<Shape> shapes = Canvas.getInstance().getShapes();
        for (Shape shape : shapes) {
            if (isShapeInsideOriginAndDestination(shape, origin, destination)) {
                Canvas.getInstance().select(shape);
            }
        }
    }

    private void unselectAllShapes() {
        ArrayList<Shape> shapes = Canvas.getInstance().getShapes();
        for (Shape shape : shapes) {
            Canvas.getInstance().unselect(shape);
        }
    }

    private boolean isShapeInsideOriginAndDestination(Shape shape, Point origin, Point destination) {
        return (shape.getX() >= origin.x) && (shape.getX() + shape.getWidth() <= destination.x) && (shape.getY() >= origin.y) && (shape.getY() + shape.getHeight() <= destination.y);
    }

    private void moveShape(Point origin, Point destination) {
        Canvas canvas = Canvas.getInstance();
        Shape shape = canvas.getCanvasShapeByPoint(origin);
        Point delta = new Point(destination.x - origin.x, destination.y - origin.y);
        shape.move(new Point(shape.getX() + delta.x, shape.getY() + delta.y));
    }
}
