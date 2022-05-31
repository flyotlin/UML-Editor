package future.canvas.shapes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ConnectionPort extends JComponent {
    public static final Dimension pointSize = new Dimension(8, 8);

    private final Shape shape;
    private ArrayList<BaseLine> lines;

    public ConnectionPort(Point origin, Shape shape) {
        this.shape = shape;
        setSize(pointSize);
        this.setBounds(origin.x, origin.y, pointSize.width, pointSize.height);
    }

    public void addLine(BaseLine line) {
        if (lines == null) {
            lines = new ArrayList<BaseLine>();
        }
        this.lines.add(line);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintConnectionPort(g);
    }

    private void paintConnectionPort(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.setPaint(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.fillRect(0, 0, pointSize.width, pointSize.height);
    }
}
