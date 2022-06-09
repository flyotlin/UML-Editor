package canvas.shapes.line;

import java.awt.*;

public class GeneralizationLine extends BaseLine {
    public GeneralizationLine(Point origin, Point destination) {
        super(origin, destination);
    }

    @Override
    protected void drawLine(Graphics g) {
        super.drawLine(g);
    }

    @Override
    protected void drawArrow(Graphics g) {
        Point start = lineOrigin;
        Point end = lineDestination;
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        int arrowLen = 20;
        double x = end.x - start.x;
        double y = end.y - start.y;
        double theta_1 = Math.atan(y/x) * (180.0 / Math.PI);
        double alpha_1 = Math.toRadians(225+theta_1);
        double alpha_2 = Math.toRadians(135+theta_1);
        if (x >= 0) {
            g2d.drawLine(end.x, end.y, (int) (end.x+arrowLen*Math.cos(alpha_1)), (int) (end.y+arrowLen*Math.sin(alpha_1)));
            g2d.drawLine(end.x, end.y, (int) (end.x+arrowLen*Math.cos(alpha_2)), (int) (end.y+arrowLen*Math.sin(alpha_2)));
            g2d.drawLine((int) (end.x+arrowLen*Math.cos(alpha_1)), (int) (end.y+arrowLen*Math.sin(alpha_1)), (int) (end.x+arrowLen*Math.cos(alpha_2)), (int) (end.y+arrowLen*Math.sin(alpha_2)));
        } else {
            g2d.drawLine(end.x, end.y, (int) (end.x-arrowLen*Math.cos(alpha_1)), (int) (end.y-arrowLen*Math.sin(alpha_1)));
            g2d.drawLine(end.x, end.y, (int) (end.x-arrowLen*Math.cos(alpha_2)), (int) (end.y-arrowLen*Math.sin(alpha_2)));
            g2d.drawLine((int) (end.x-arrowLen*Math.cos(alpha_1)), (int) (end.y-arrowLen*Math.sin(alpha_1)), (int) (end.x-arrowLen*Math.cos(alpha_2)), (int) (end.y-arrowLen*Math.sin(alpha_2)));
        }
    }
}
