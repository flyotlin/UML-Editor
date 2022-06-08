package future.canvas.shapes;

import javax.swing.*;
import java.awt.*;

public abstract class Shape extends JPanel {
    public abstract void select();

    public abstract void unselect();

    public abstract void move(Point newPos);

    public abstract void ungroup();

    public abstract void hideConnectionPorts();

    public abstract void setText(String text);

    public abstract boolean isPointInShape(Point p);

    public abstract ConnectionPort getConnectionPortByPoint(Point p);
}
