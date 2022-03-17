package object.new_obj;

import java.awt.*;

public class BaseLine {
    public static int LineCount = 0;
    public String gid;

    // Line的start及end point(以canvas座標)
    public Point start;
    public Point end;

    // Line start及end連接的UMLObject
    public UMLObject startObj;
    public UMLObject endObj;

    // layer
    public int layer;

    public BaseLine(int x1, int y1, int x2, int y2) {
        start = new Point(x1, y1);
        end = new Point(x2, y2);
        this.setGid();
    }

    public void drawLine(Graphics2D g2d) {
        g2d.drawLine(start.x, start.y, end.x, end.y);

        double x = end.x - start.x;
        double y = end.y - start.y;
        double theta_1 = Math.atan(y/x) * (180.0 / Math.PI);
        double alpha_1 = Math.toRadians(225+theta_1);
        double alpha_2 = Math.toRadians(135+theta_1);
        if (x >= 0) {
            g2d.drawLine(end.x, end.y, (int) (end.x+10*Math.cos(alpha_1)), (int) (end.y+10*Math.sin(alpha_1)));
            g2d.drawLine(end.x, end.y, (int) (end.x+10*Math.cos(alpha_2)), (int) (end.y+10*Math.sin(alpha_2)));
        } else {
            g2d.drawLine(end.x, end.y, (int) (end.x-10*Math.cos(alpha_1)), (int) (end.y-10*Math.sin(alpha_1)));
            g2d.drawLine(end.x, end.y, (int) (end.x-10*Math.cos(alpha_2)), (int) (end.y-10*Math.sin(alpha_2)));
        }
    }

    private void setGid() {
        LineCount++;
        this.gid = "l" + LineCount;
    }
}
