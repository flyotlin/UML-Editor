package object;

import delegate.BaseDelegate;
import delegate.CanvasDelegate;
import delegate.ClassDelegate;
import delegate.UseCaseDelegate;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BaseMouseListener implements MouseListener {
    BaseDelegate delegate;

    private void setDelegate(MouseEvent e) {
        Component c = (Component) e.getSource();
        String name = c.getClass().getName();

        if (name == "object.label.Class") {
            delegate = new ClassDelegate();
        } else if (name == "object.label.UseCase") {
            delegate = new UseCaseDelegate();
        } else if (name == "object.canvas.Canvas") {
            delegate = new CanvasDelegate();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.setDelegate(e);
        delegate.pressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.setDelegate(e);
        delegate.released(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
