package delegate;

import object.canvas.Canvas;
import object.label.AnchorPoint;
import object.label.BaseLabel;
import object.label.Class;
import object.line.BaseLine;
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
        } else if (Toolbar.toolsNowSelected == Tools.ASSOCIATION) {
            System.out.println(e.getX() + " " + e.getY());
            System.out.println("association pressed!!");
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
        } else if (Toolbar.toolsNowSelected == Tools.ASSOCIATION) {
            System.out.println("association released!!");
            Class label = (Class) e.getSource();
            System.out.println(label.getX() + " " + label.getY() + " " + label.getOrigX() + " " + label.getOrigY() + " " + e.getX() + " " + e.getY());
            BaseLine line = new BaseLine(label.getOrigX(), label.getOrigY(), e.getX(), e.getY());
            line.setBounds(
                    label.getX()+label.getOrigX(),
                    label.getY()+label.getOrigY(),
                    Math.abs(e.getX()-label.getOrigX()),
                    Math.abs(e.getY()-label.getOrigY()));
            Canvas canvas = (Canvas) label.getParent();
            canvas.add(line);

            canvas.revalidate();
            canvas.repaint();
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
