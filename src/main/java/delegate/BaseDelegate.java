package delegate;

import java.awt.event.MouseEvent;

public interface BaseDelegate {
    public void click(MouseEvent e);
    public void pressed(MouseEvent e);
    public void released(MouseEvent e);
}
