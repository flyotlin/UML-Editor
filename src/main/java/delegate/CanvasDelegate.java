package delegate;

import object.canvas.Canvas;
import object.label.BaseLabel;
import object.label.Class;
import object.label.UseCase;
import toolbar.Toolbar;
import toolbar.Tools;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

public class CanvasDelegate implements BaseDelegate {
    @Override
    public void click(MouseEvent e) {

    }

    @Override
    public void pressed(MouseEvent e) {
        Canvas canvas = (Canvas) e.getSource();

        // Class
        if (Toolbar.toolsNowSelected == Tools.SELECT) {
            // Remove all AnchorPoint objects
            for (Component comp : canvas.getComponents()) {
                BaseLabel bl = (BaseLabel) comp;
                // TODO: Need to check if comp is a BaseLabel
                for (Component c : bl.getComponents()) {
                    bl.remove(c);
                }
            }

            canvas.revalidate();
            canvas.repaint();
        } else if (Toolbar.toolsNowSelected == Tools.CLASS) {
            String timeStamp = new SimpleDateFormat("HH.mm.ss").format(new java.util.Date());
            Class c = new Class("Class: " + timeStamp, e.getX(), e.getY(), 100, 100);
            canvas.add(c);

            canvas.revalidate();
            canvas.repaint();
        } else if (Toolbar.toolsNowSelected == Tools.USECASE) {
            String timeStamp = new SimpleDateFormat("HH.mm.ss").format(new java.util.Date());
            UseCase c = new UseCase("Use Case: " + timeStamp, e.getX(), e.getY(), 100, 100);
            canvas.add(c);

            canvas.revalidate();
            canvas.repaint();
        }
    }

    @Override
    public void released(MouseEvent e) {
        if (Toolbar.toolsNowSelected == Tools.ASSOCIATION) {
            System.out.println("Canvas released!");
        }
    }

    @Override
    public void dragged(MouseEvent e) {

    }
}
