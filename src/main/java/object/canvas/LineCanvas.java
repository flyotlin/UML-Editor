package object.canvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class LineCanvas extends JPanel implements MouseListener, MouseMotionListener {
    public Point pointStart = null;
    public Point pointEnd = null;
    public ArrayList<Point[]> lines;

    public LineCanvas() {
        super();
        this.lines = new ArrayList<Point[]>();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (pointStart != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.RED);
            g2d.setStroke(new BasicStroke(4));
            g2d.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y);
            for (Point[] p : this.lines) {
                g2d.drawLine(p[0].x, p[0].y, p[1].x, p[1].y);

                double x = p[1].x - p[0].x;
                double y = p[1].y - p[0].y;
                double theta_1 = Math.atan(y/x) * (180.0 / Math.PI);
                double alpha_1 = Math.toRadians(225+theta_1);
                double alpha_2 = Math.toRadians(135+theta_1);
                if (x >= 0) {
                    g2d.drawLine(p[1].x, p[1].y, (int) (p[1].x+10*Math.cos(alpha_1)), (int) (p[1].y+10*Math.sin(alpha_1)));
                    g2d.drawLine(p[1].x, p[1].y, (int) (p[1].x+10*Math.cos(alpha_2)), (int) (p[1].y+10*Math.sin(alpha_2)));
                } else {
                    g2d.drawLine(p[1].x, p[1].y, (int) (p[1].x-10*Math.cos(alpha_1)), (int) (p[1].y-10*Math.sin(alpha_1)));
                    g2d.drawLine(p[1].x, p[1].y, (int) (p[1].x-10*Math.cos(alpha_2)), (int) (p[1].y-10*Math.sin(alpha_2)));
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.pointStart = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.pointEnd = e.getPoint();
        this.lines.add(new Point[]{this.pointStart, this.pointEnd});
        this.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.pointEnd = e.getPoint();
        this.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
