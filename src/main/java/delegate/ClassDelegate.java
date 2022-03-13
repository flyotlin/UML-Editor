package delegate;

import object.label.Class;
import toolbar.Toolbar;
import toolbar.Tools;

import java.awt.event.MouseEvent;

public class ClassDelegate implements BaseDelegate {
    @Override
    public void click(MouseEvent e) {

    }

    @Override
    public void pressed(MouseEvent e) {
        Class label = (Class) e.getSource();

        label.setOrigX(e.getX());
        label.setOrigY(e.getY());
    }

    @Override
    public void released(MouseEvent e) {
        if (Toolbar.toolsNowSelected == Tools.SELECT) {
            Class label = (Class) e.getSource();
            label.setBounds(
                    label.getX() + (e.getX() - label.getOrigX()),
                    label.getY()+ (e.getY() - label.getOrigY()),
                    100, 100);
            label.getParent().revalidate();
            label.getParent().repaint();
        }
    }

    @Override
    public void dragged(MouseEvent e) {
        if (Toolbar.toolsNowSelected == Tools.SELECT) {
            Class label = (Class) e.getSource();
            label.setBounds(
                    label.getX() + (e.getX() - label.getOrigX()),
                    label.getY()+ (e.getY() - label.getOrigY()),
                    100, 100);
            label.getParent().revalidate();
            label.getParent().repaint();
        }
    }
}
