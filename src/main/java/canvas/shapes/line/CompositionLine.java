package canvas.shapes.line;

import java.awt.*;

public class CompositionLine extends BaseLine {
    public CompositionLine(Point origin, Point destination) {
        super(origin, destination);
    }

    @Override
    protected void drawLine(Graphics g) {
        super.drawLine(g);
    }

    @Override
    protected void drawArrow(Graphics g) {
        Point delta = new Point(lineDestination.x - lineOrigin.x, lineDestination.y - lineOrigin.y);
        double len = Math.hypot(delta.x, delta.y);
        int arrowSize = 10;
        delta = new Point((int) (arrowSize * delta.x / len), (int) (arrowSize * delta.y / len));
        Point p = new Point(lineDestination.x - delta.x, lineDestination.y - delta.y);
        int x[] = {p.x, p.x - arrowSize, p.x, p.x + arrowSize};
        int y[] = {p.y + arrowSize, p.y, p.y - arrowSize, p.y};
        Polygon polygon = new Polygon(x, y, x.length);
        g.setColor(Color.BLACK);
        g.drawPolygon(polygon);
    }
}
