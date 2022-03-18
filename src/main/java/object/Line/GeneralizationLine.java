package object.Line;

import java.awt.*;

public class GeneralizationLine extends BaseLine {
    public GeneralizationLine(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
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
            g2d.drawLine((int) (end.x+arrowLen*Math.cos(alpha_1)), (int) (end.y+arrowLen*Math.sin(alpha_1)), (int) (end.x+arrowLen*Math.cos(alpha_2)), (int) (end.y+arrowLen*Math.sin(alpha_2)));
        } else {
            g2d.drawLine(end.x, end.y, (int) (end.x-arrowLen*Math.cos(alpha_1)), (int) (end.y-arrowLen*Math.sin(alpha_1)));
            g2d.drawLine(end.x, end.y, (int) (end.x-arrowLen*Math.cos(alpha_2)), (int) (end.y-arrowLen*Math.sin(alpha_2)));
            g2d.drawLine((int) (end.x-arrowLen*Math.cos(alpha_1)), (int) (end.y-arrowLen*Math.sin(alpha_1)), (int) (end.x-arrowLen*Math.cos(alpha_2)), (int) (end.y-arrowLen*Math.sin(alpha_2)));
        }
    }
}
