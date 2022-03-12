package delegate;

import object.UseCase;
import toolbar.Toolbar;
import toolbar.Tools;

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
        if (Toolbar.toolsNowSelected == Tools.SELECT) {
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
