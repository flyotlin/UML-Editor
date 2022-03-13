package delegate;

import object.canvas.Canvas;
import object.label.AnchorPoint;
import object.label.BaseLabel;
import object.label.Class;
import toolbar.Toolbar;
import toolbar.Tools;

import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ClassDelegate implements BaseDelegate {
    @Override
    public void click(MouseEvent e) {

    }

    @Override
    public void pressed(MouseEvent e) {
        Class label = (Class) e.getSource();

        if (Toolbar.toolsNowSelected == Tools.SELECT) {
            // Remove all AnchorPoint objects
            Canvas canvas = (Canvas) label.getParent();
            for (Component comp : canvas.getComponents()) {
                BaseLabel bl = (BaseLabel) comp;
                // TODO: Need to check if comp is a BaseLabel
                for (Component c : bl.getComponents()) {
                    bl.remove(c);
                }
            }

            // Create new AnchorPoint objects on the selected Class
            AnchorPoint p;
            p = new AnchorPoint();
            p.setBounds(50-(AnchorPoint.pointSize/2), 0, AnchorPoint.pointSize, AnchorPoint.pointSize);
            label.add(p);

            p = new AnchorPoint();
            p.setBounds(50-(AnchorPoint.pointSize/2), 100-AnchorPoint.pointSize, AnchorPoint.pointSize, AnchorPoint.pointSize);
            label.add(p);

            p = new AnchorPoint();
            p.setBounds(0, 50-(AnchorPoint.pointSize/2), AnchorPoint.pointSize, AnchorPoint.pointSize);
            label.add(p);

            p = new AnchorPoint();
            p.setBounds(100-AnchorPoint.pointSize, 50-(AnchorPoint.pointSize/2), AnchorPoint.pointSize, AnchorPoint.pointSize);
            label.add(p);

            // Why no revalidate and repaint?
        }
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
