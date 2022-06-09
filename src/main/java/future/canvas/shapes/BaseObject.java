package future.canvas.shapes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BaseObject extends Shape {
    private final static Dimension size = new Dimension(120, 120);

    private JLabel text = null;
    private ArrayList<ConnectionPort> connectionPorts = new ArrayList<ConnectionPort>();

    public BaseObject(Point origin) {
        this.setBounds(origin.x, origin.y, size.width, size.height);
        this.setOpaque(true);
        this.setLayout(null);
    }

    @Override
    public void select() {
        showConnectionPorts();
    }

    @Override
    public void unselect() {
        hideConnectionPorts();
    }

    @Override
    public void move(Point newPos) {
        this.setBounds(newPos.x, newPos.y, getWidth(), getHeight());
        for (ConnectionPort port : connectionPorts) {
            port.move();
        }
    }

    @Override
    public void ungroup() {}

    @Override
    public boolean isPointInShape(Point p) {
        if (p.x < this.getX() || p.x > (this.getX() + this.getWidth())) {
            return false;
        }
        return p.y >= this.getY() && p.y <= (this.getY() + this.getHeight());
    }

    public void setText(String text) {
        if (this.text == null) {
            this.text = new JLabel(text);
            this.text.setBounds(40, 18, 80, 80);
            this.add(this.text);
        } else {
            this.text.setText(text);
        }
    }

    public void showConnectionPorts() {
        initializeConnectionPorts(true);
        setConnectionPortsVisibility(true);
        this.revalidate();
        this.repaint();
    }

    public void hideConnectionPorts() {
        setConnectionPortsVisibility(false);
    }

    private void setConnectionPortsVisibility(boolean isVisible) {
        for (ConnectionPort port : connectionPorts) {
            port.setVisible(isVisible);
        }
    }

    private void initializeConnectionPorts(boolean isVisible) {
        if (connectionPorts.size() == 0) {
            connectionPorts = createConnectionPorts();
        }
        if (!isVisible) {
            hideConnectionPorts();
        }
    }

    private ArrayList<ConnectionPort> createConnectionPorts() {
        int size = ConnectionPort.pointSize.width;
        int w = this.getWidth();
        int h = this.getHeight();

        ConnectionPort top = new ConnectionPort(new Point(w / 2 - size / 2, 0));
        ConnectionPort bottom = new ConnectionPort(new Point(w / 2 - size / 2, h - size));
        ConnectionPort left = new ConnectionPort(new Point(0, h / 2 - size / 2));
        ConnectionPort right = new ConnectionPort(new Point(w - size, h / 2 - size / 2));

        ArrayList<ConnectionPort> ports = new ArrayList<>();
        addConnectionPort(top, ports);
        addConnectionPort(bottom, ports);
        addConnectionPort(left, ports);
        addConnectionPort(right, ports);

        return ports;
    }

    private void addConnectionPort(ConnectionPort port, ArrayList<ConnectionPort> ports) {
        ports.add(port);
        this.add(port);
    }

    public ConnectionPort getConnectionPortByPoint(Point p) {
        initializeConnectionPorts(false);

        // TODO: some complex logic, must be refactored later
        Point pInCanvas = new Point(p.x - getX(), p.y - getY());
        int w = getWidth();
        int h = getHeight();
        double slope = h / ((double) w);
        boolean cond_1 = pInCanvas.y - (slope * pInCanvas.x) >= 0;
        boolean cond_2 = pInCanvas.y + (slope * pInCanvas.x - w) >= 0;

        if (cond_1 && cond_2) {
            return connectionPorts.get(1);    // bottom
        }
        if (!cond_1 && !cond_2) {
            return connectionPorts.get(0);    // top
        }
        if (!cond_1) {
            return connectionPorts.get(3);    // right
        }
        return connectionPorts.get(2);        // left
    }
}
