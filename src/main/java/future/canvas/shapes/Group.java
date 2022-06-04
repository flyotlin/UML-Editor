package future.canvas.shapes;

import future.canvas.Canvas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Group extends Shape {
    private ArrayList<Shape> shapesInGroup = new ArrayList<>();

    public Group(Point origin, Point destination, ArrayList<Shape> selectedShapes) {
        this.setOpaque(true);
        this.setLayout(null);

        this.setBorder(BorderFactory.createDashedBorder(Color.RED, 3, 4, 3, true));
        this.setBounds(origin, destination);

        group(selectedShapes);
    }

    @Override
    public void select() {}

    @Override
    public void unselect() {}

    @Override
    public void move() {}

    private void group(ArrayList<Shape> selectedShapes) {
        setShapesInGroup(selectedShapes);
        removeSelectedShapesFromCanvas();
        addShapesInGroupToGroup();
    }

    @Override
    public void ungroup() {
        Canvas canvas = Canvas.getInstance();
        for (Shape shape : shapesInGroup) {
            resetShapeBoundsToCanvas(shape);
            canvas.add(shape);
        }
        canvas.remove(this);
    }

    @Override
    public void hideConnectionPorts() {}

    @Override
    public void setText(String text) {}

    @Override
    public boolean isPointInShape(Point p) {
        if (p.x < this.getX() || p.x > (this.getX() + this.getWidth())) {
            return false;
        }
        return p.y >= this.getY() && p.y <= (this.getY() + this.getHeight());
    }

    @Override
    public ConnectionPort getConnectionPortByPoint(Point p) {
        return null;
    }

    private void setBounds(Point origin, Point destination) {
        int width = destination.x - origin.x;
        int height = destination.y - origin.y;
        this.setBounds(origin.x, origin.y, width, height);
    }

    private void setShapesInGroup(ArrayList<Shape> selectedShapes) {
        this.shapesInGroup.addAll(selectedShapes);
    }

    private void removeSelectedShapesFromCanvas() {
        Canvas canvas = Canvas.getInstance();
        for (Shape shape : shapesInGroup) {
            canvas.remove(shape);
        }
    }

    private void addShapesInGroupToGroup() {
        for (Shape shape : shapesInGroup) {
            resetShapeBoundsToGroup(shape);
            shape.hideConnectionPorts();
            this.add(shape);
        }
    }

    private void resetShapeBoundsToGroup(Shape shape) {
        shape.setBounds(shape.getX() - getX(), shape.getY() - getY(), shape.getWidth(), shape.getHeight());
    }

    private void resetShapeBoundsToCanvas(Shape shape) {
        shape.setBounds(shape.getX() + getX(), shape.getY() + getY(), shape.getWidth(), shape.getHeight());
    }
}
