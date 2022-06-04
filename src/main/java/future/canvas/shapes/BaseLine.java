package future.canvas.shapes;

import future.canvas.Canvas;

import java.awt.*;

public class BaseLine extends Shape {
    private ConnectionPort originPort;
    private ConnectionPort destinationPort;

    // Line 可以視為一個跟 Canvas 一樣大小的畫布，透過 Point origin, Point destination 在此巨大畫布上畫出 Line
    private Point lineOrigin;
    private Point lineDestination;

    public BaseLine(Point origin, Point destination) {
        initConnectionPort(origin, destination);
        initLineOriginDestination(origin, destination);

        this.setOpaque(false);
        this.setVisible(true);
        this.setBounds(0, 0, 1200, 800);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawLine(g);
        drawArrow(g);
    }

    @Override
    public void select() {}

    @Override
    public void unselect() {}

    @Override
    public void move() {}

    @Override
    public void ungroup() {}

    @Override
    public void hideConnectionPorts() {}

    @Override
    public boolean isPointInShape(Point p) {
        return false;
    }

    @Override
    public ConnectionPort getConnectionPortByPoint(Point p) {
        return null;
    }

    protected void drawLine(Graphics g) {
        Color lineColor = Color.RED;

        g.setColor(lineColor);
        g.drawLine(lineOrigin.x, lineOrigin.y, lineDestination.x, lineDestination.y);
    }

    protected void drawArrow(Graphics g) {}

    private void initConnectionPort(Point origin, Point destination) {
        Canvas canvas = Canvas.getInstance();
        Shape originShape = canvas.getCanvasShapeByPoint(origin);
        Shape destinationShape = canvas.getCanvasShapeByPoint(destination);

        this.originPort = originShape.getConnectionPortByPoint(origin);
        this.destinationPort = destinationShape.getConnectionPortByPoint(destination);
        this.originPort.addLine(this);
        this.destinationPort.addLine(this);
    }

    private void initLineOriginDestination(Point origin, Point destination) {
        Canvas canvas = Canvas.getInstance();
        Shape originShape = canvas.getCanvasShapeByPoint(origin);
        Shape destinationShape = canvas.getCanvasShapeByPoint(destination);

        this.lineOrigin = new Point(originShape.getX() + originPort.getX(), originShape.getY() + originPort.getY());
        this.lineDestination = new Point(destinationShape.getX() + destinationPort.getX(), destinationShape.getY() + destinationPort.getY());
    }
}
