package canvas.line;

import java.awt.*;

public class CompositionLine extends BaseLine {
    public CompositionLine(Point head, Point tail) {
        super(head, tail);
    }

    @Override
    public void drawLine(Graphics2D g2d) {
        super.drawLine(g2d);

        double x = end.x - start.x;
        double y = end.y - start.y;
        double theta_1 = Math.atan(y/x) * (180.0 / Math.PI);
        double alpha_1 = Math.toRadians(-45+theta_1);
        double alpha_2 = Math.toRadians(45+theta_1);

        int t1, t2;
        if (x >= 0) {
            t1 = (int) (end.x+arrowLen*Math.cos(alpha_1));
            t2 = (int) (end.y+arrowLen*Math.sin(alpha_1));
            g2d.drawLine(end.x, end.y, (int) (end.x+arrowLen*Math.cos(alpha_1)), (int) (end.y+arrowLen*Math.sin(alpha_1)));
            g2d.drawLine(t1, t2, (int) (t1+arrowLen*Math.cos(alpha_2)), (int) (t2+arrowLen*Math.sin(alpha_2)));

            g2d.drawLine(end.x, end.y, (int) (end.x+arrowLen*Math.cos(alpha_2)), (int) (end.y+arrowLen*Math.sin(alpha_2)));
            t1 = (int) (end.x+arrowLen*Math.cos(alpha_2));
            t2 = (int) (end.y+arrowLen*Math.sin(alpha_2));
            g2d.drawLine(t1, t2, (int) (t1+arrowLen*Math.cos(alpha_1)), (int) (t2+arrowLen*Math.sin(alpha_1)));
        } else {
            t1 = (int) (end.x-arrowLen*Math.cos(alpha_1));
            t2 = (int) (end.y-arrowLen*Math.sin(alpha_1));
            g2d.drawLine(end.x, end.y, (int) (end.x-arrowLen*Math.cos(alpha_1)), (int) (end.y-arrowLen*Math.sin(alpha_1)));
            g2d.drawLine(t1, t2, (int) (t1-arrowLen*Math.cos(alpha_2)), (int) (t2-arrowLen*Math.sin(alpha_2)));

            g2d.drawLine(end.x, end.y, (int) (end.x-arrowLen*Math.cos(alpha_2)), (int) (end.y-arrowLen*Math.sin(alpha_2)));
            t1 = (int) (end.x-arrowLen*Math.cos(alpha_2));
            t2 = (int) (end.y-arrowLen*Math.sin(alpha_2));
            g2d.drawLine(t1, t2, (int) (t1-arrowLen*Math.cos(alpha_1)), (int) (t2-arrowLen*Math.sin(alpha_1)));
        }
    }
}
