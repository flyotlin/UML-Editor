package future.canvas.strategy;

import java.awt.event.MouseEvent;

public interface CanvasStrategy {
    void mousePressed(MouseEvent e);

    void mouseReleased(MouseEvent e);

    void mouseDragged(MouseEvent e);
}
