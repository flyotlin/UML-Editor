package delegate;

import object.Class;
import tools.ToolBar;
import tools.state.ButtonName;

import javax.swing.*;
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
        if (ToolBar.state.getButtonNow() == ButtonName.SELECT_BTN) {
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
