package delegate;

import canvas.Canvas;
import object.Class;
import object.UseCase;
import tools.ToolBar;
import tools.state.ButtonName;

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
        if (ToolBar.state.getButtonNow() == ButtonName.CLASS_BTN) {
            String timeStamp = new SimpleDateFormat("HH.mm.ss").format(new java.util.Date());
            Class c = new Class("Class: " + timeStamp, e.getX(), e.getY(), 100, 100);
            canvas.add(c);

            canvas.revalidate();
            canvas.repaint();
        } else if (ToolBar.state.getButtonNow() == ButtonName.USECASE_BTN) {
            String timeStamp = new SimpleDateFormat("HH.mm.ss").format(new java.util.Date());
            UseCase c = new UseCase("Use Case: " + timeStamp, e.getX(), e.getY(), 100, 100);
            canvas.add(c);

            canvas.revalidate();
            canvas.repaint();
        }
    }

    @Override
    public void released(MouseEvent e) {

    }
}
