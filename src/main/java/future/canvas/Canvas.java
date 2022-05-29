package future.canvas;

import future.canvas.shapes.Shape;
import future.canvas.strategy.CanvasStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Canvas extends JPanel implements MouseListener, MouseMotionListener {
    private static Canvas canvas;
    private final static Dimension canvasSize = new Dimension(100, 100);

    private CanvasStrategy strategy;
    private ArrayList<Shape> shapes;
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

    public void setStrategy(CanvasStrategy strategy) {
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

    private boolean isPointInAnyShape(Point p) {
        for (Shape shape : shapes) {
            if (p.x < shape.getX() || p.x > (shape.getX() + shape.getWidth())) {
                continue;
            }
            if (p.y < shape.getY() || p.y > (shape.getY() + shape.getHeight())) {
                continue;
            }
            return true;
        }
        return false;
    }

    public boolean isOriginInAnyShape() {
        return isPointInAnyShape(origin);
    }

    public boolean isDestinationInAnyShape() {
        return isPointInAnyShape(destination);
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
    public void mouseClicked(MouseEvent e) {}

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
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        saveDestinationPoint(e.getPoint());
        strategy.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
}
