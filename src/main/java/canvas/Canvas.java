package canvas;

import canvas.shapes.Group;
import canvas.shapes.Shape;
import canvas.strategy.BaseStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Canvas extends JPanel implements MouseListener, MouseMotionListener {
    private final static Dimension canvasSize = new Dimension(100, 100);
    private static Canvas canvas;
    private ArrayList<Shape> shapes;
    private ArrayList<Shape> selectedShapes = new ArrayList<>();
    private BaseStrategy strategy;
    private Point origin;
    private Point destination;

    private Canvas() {
        this.shapes = new ArrayList<Shape>();
        this.origin = null;
        this.destination = null;

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.setLayout(null);
        this.setSize(canvasSize);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public static Canvas getInstance() {
        if (canvas == null) {
            canvas = new Canvas();
        }
        return canvas;
    }

    public void setStrategy(BaseStrategy strategy) {
        this.strategy = strategy;
    }

    public Point getOrigin() {
        return origin;
    }

    public Point getDestination() {
        return destination;
    }

    private void saveOriginPoint(Point p) {
        origin = p;
    }

    private void saveDestinationPoint(Point p) {
        destination = p;
    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public ArrayList<Shape> getSelectedShapes() {
        return selectedShapes;
    }

    public Shape getCanvasShapeByPoint(Point p) {
        for (Shape shape : shapes) {
            if (shape.isPointInShape(p)) {
                return shape;
            }
        }
        return null;
    }

    public boolean isPointInAnyCanvasShape(Point p) {
        Shape shape = getCanvasShapeByPoint(p);
        return shape != null;
    }

    public boolean areTwoPointsInSameShape(Point p1, Point p2) {
        Shape s1 = getCanvasShapeByPoint(p1);
        Shape s2 = getCanvasShapeByPoint(p2);
        return s1 == s2;
    }

    public boolean isPointInGroup(Point p) {
        Shape shape = getCanvasShapeByPoint(p);
        return shape instanceof Group;
    }

    public void select(Shape shape) {
        selectedShapes.add(shape);
        shape.select();
    }

    public void unselect(Shape shape) {
        selectedShapes.remove(shape);
        shape.unselect();
    }

    @Override
    public Component add(Component comp) {
        Component returnComp = super.add(comp);
        this.shapes.add((Shape) comp);

        super.revalidate();
        super.repaint();

        return returnComp;
    }

    @Override
    public void remove(Component comp) {
        this.shapes.remove(comp);
        this.selectedShapes.remove(comp);
        super.remove(comp);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        saveOriginPoint(e.getPoint());
        strategy.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        saveDestinationPoint(e.getPoint());
        strategy.mouseReleased(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        saveDestinationPoint(e.getPoint());
        strategy.mouseDragged(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
