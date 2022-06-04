package future.canvas.shapes;

import future.canvas.Canvas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// TODO: Group 繼承 BaseObject 或許不是太好?
// 原本是希望可以直接用 BaseObject 的 constructor，但變得有點怪怪的
public class Group extends BaseObject {
    private ArrayList<Shape> shapesInGroup = new ArrayList<>();

    public Group(Point origin, Point destination, ArrayList<Shape> selectedShapes) {
        super(origin);
        this.setBorder(BorderFactory.createDashedBorder(Color.RED, 3, 4, 3, true));
        this.setBounds(origin, destination);

        group(selectedShapes);
    }

    @Override
    public void select() {
        // Left empty on purpose
    }

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
            // TODO: Is there a better solution here?
            resetShapeBoundsToGroup(shape);
            ((BaseObject) shape).hideConnectionPorts();
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
