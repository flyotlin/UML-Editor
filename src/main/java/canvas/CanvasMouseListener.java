package canvas;

import editor.Editor;
import object.Class;
import tools.ToolBar;
import tools.state.ButtonName;
import tools.state.ToolBarState;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;

public class CanvasMouseListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        JPanel canvas = (JPanel)e.getSource();

        // Class
        if (ToolBar.state.getButtonNow() == ButtonName.CLASS_BTN) {
            String timeStamp = new SimpleDateFormat("HH.mm.ss").format(new java.util.Date());
            Class c = new Class(timeStamp, e.getX(), e.getY(), 100, 100);
            canvas.add(c.getLabel());

            canvas.revalidate();
            canvas.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
