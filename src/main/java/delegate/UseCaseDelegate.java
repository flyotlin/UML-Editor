package delegate;

import object.UseCase;
import tools.ToolBar;
import tools.state.ButtonName;

import java.awt.event.MouseEvent;

public class UseCaseDelegate implements BaseDelegate {
    @Override
    public void click(MouseEvent e) {

    }

    @Override
    public void pressed(MouseEvent e) {
        UseCase label = (UseCase) e.getSource();

        label.setOrigX(e.getX());
        label.setOrigY(e.getY());
    }

    @Override
    public void released(MouseEvent e) {
        if (ToolBar.state.getButtonNow() == ButtonName.SELECT_BTN) {
            UseCase label = (UseCase) e.getSource();
            label.setBounds(
                    label.getX() + (e.getX() - label.getOrigX()),
                    label.getY()+ (e.getY() - label.getOrigY()),
                    100, 100);
            label.getParent().revalidate();
            label.getParent().repaint();
        }
    }
}
