package canvas.object;

import javax.swing.*;
import java.awt.*;

public class AnchorPoint extends JComponent {
    public static final int pointSize = 8;

    public AnchorPoint(Point p) {
        setSize(new Dimension(pointSize, pointSize));
        this.setBounds(p.x, p.y, pointSize, pointSize);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.setPaint(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.fillRect(0, 0, pointSize, pointSize);
    }
}
