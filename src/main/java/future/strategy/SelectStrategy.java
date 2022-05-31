package future.strategy;

import future.canvas.Canvas;
import future.canvas.shapes.Shape;

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

        /**
         * 以 Select 而言：
         * - 從 shape 出發
         *      - single select
         *      - move
         * - 從 empty 出發
         *      - multiple select
         */
        if (canvas.isPointInAnyCanvasShape(origin)) {
            if (canvas.getCanvasShapeByPoint(origin) == canvas.getCanvasShapeByPoint(destination)) {
                selectSingle(destination);
            } else {
//                moveShape(destination);
            }
        } else {
            selectMultiple(origin, destination);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    private void selectSingle(Point p) {
        unselectAllShapes();
        selectOneShape(p);
    }

    private void selectMultiple(Point origin, Point destination) {
        unselectAllShapes();
        selectMultipleShapes(origin, destination);
    }

    private void selectOneShape(Point p) {
        Canvas canvas = Canvas.getInstance();
        Shape selectedShape = canvas.getCanvasShapeByPoint(p);
        if (selectedShape != null) {
            selectedShape.select();
        }
    }

    private void selectMultipleShapes(Point origin, Point destination) {
        ArrayList<Shape> shapes = Canvas.getInstance().getShapes();
        for (Shape shape : shapes) {
            if (isShapeInsideOriginAndDestination(shape, origin, destination)) {
                shape.select();
            }
        }
    }

    private void unselectAllShapes() {
        ArrayList<Shape> shapes = Canvas.getInstance().getShapes();
        for (Shape shape : shapes) {
            shape.unselect();
        }
    }

    private boolean isShapeInsideOriginAndDestination(Shape shape, Point origin, Point destination) {
        return (shape.getX() >= origin.x) && (shape.getX() + shape.getWidth() <= destination.x) && (shape.getY() >= origin.y) && (shape.getY() + shape.getHeight() <= destination.y);
    }
}
