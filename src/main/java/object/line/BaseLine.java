package object.line;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class BaseLine extends JComponent {
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public BaseLine(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        int dx = x2-x1;
        int dy = y2-y1;
        double sum = Math.hypot(dx, dy);
        this.setSize(new Dimension((int)sum, 4));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.setPaint(Color.RED);
        g2d.setStroke(new BasicStroke(4));

        int dx = x2-x1;
        int dy = y2-y1;
        double sum = Math.hypot(dx, dy);
//        AffineTransform mat = ((Graphics2D) g).getTransform();
        // rotate to correct angle
//        g2d.rotate(Math.atan(dy/(double)dx) * (180.0/(Math.PI)));
        // draw horizontal line with correct length first
        System.out.println(sum);
        Dimension d = this.getSize();
        g2d.drawLine(0,d.height/2,d.width,d.height/2);
//        g2d.drawLine(0, 0, (int) sum, 0);
//        g2d.setTransform(mat);
    }
}
