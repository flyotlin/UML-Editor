package object.line;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

public class NewBaseLine extends JComponent {
    public static int numOfBaseLine = 0;

    public String gid;
    public String startObject;
    public String endObject;
    public double angle;
    public double length;

    public NewBaseLine(double length, double angle) {
        this.length = length;
        this.angle = angle;
        this.gid = "l" + numOfBaseLine;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(4));
        int len = (int) (Math.round(this.length) / Math.sqrt(2));

        Line2D line = new Line2D.Double(0, 0, len, len);
//        Line2D l1 = new Line2D.Double(len, len, len, len-5);
//        Line2D l2 = new Line2D.Double(len, len, len-5, len);

        AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(angle), line.getX1(), line.getY1());
        g2d.draw(at.createTransformedShape(line));

//        g2d.draw(line);
//        g2d.draw(l1);
//        g2d.draw(l2);

//        g2d.drawLine(0, 0, len, len);
//        g2d.drawLine(len, len, len, len-5);
//        g2d.drawLine(len, len, len-5, len);
    }
}
