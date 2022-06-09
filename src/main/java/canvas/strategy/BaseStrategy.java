package canvas.strategy;

import java.awt.event.MouseEvent;

public interface BaseStrategy {
    void mousePressed(MouseEvent e);

    void mouseReleased(MouseEvent e);

    void mouseDragged(MouseEvent e);
}
