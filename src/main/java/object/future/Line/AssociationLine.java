package object.future.Line;

import java.awt.*;

public class AssociationLine extends BaseLine {
    public AssociationLine(Point head, Point tail) {
        super(head, tail);
    }

    @Override
    public void drawLine(Graphics2D g2d) {
        super.drawLine(g2d);

        double x = end.x - start.x;
        double y = end.y - start.y;
        double theta_1 = Math.atan(y/x) * (180.0 / Math.PI);
        double alpha_1 = Math.toRadians(225+theta_1);
        double alpha_2 = Math.toRadians(135+theta_1);
        if (x >= 0) {
            g2d.drawLine(end.x, end.y, (int) (end.x+arrowLen*Math.cos(alpha_1)), (int) (end.y+arrowLen*Math.sin(alpha_1)));
            g2d.drawLine(end.x, end.y, (int) (end.x+arrowLen*Math.cos(alpha_2)), (int) (end.y+arrowLen*Math.sin(alpha_2)));
        } else {
            g2d.drawLine(end.x, end.y, (int) (end.x-arrowLen*Math.cos(alpha_1)), (int) (end.y-arrowLen*Math.sin(alpha_1)));
            g2d.drawLine(end.x, end.y, (int) (end.x-arrowLen*Math.cos(alpha_2)), (int) (end.y-arrowLen*Math.sin(alpha_2)));
        }
    }
}
