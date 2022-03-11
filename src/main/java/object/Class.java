package object;

import tools.ToolBar;
import tools.state.ButtonName;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Class extends UMLObject {
    private JLabel label;
    private static Color backgroundColor = Color.YELLOW;
    private static Color borderColor = Color.RED;

    private static int orig_x;
    private static int orig_y;

    public Class(String text, int x, int y, int w, int h) {
        this.label = new JLabel(text);
        label.setBounds(x, y, w, h);
        label.setOpaque(true);
        label.setBackground(backgroundColor);
        label.setBorder(BorderFactory.createLineBorder(borderColor));
        label.addMouseListener(new ClassMouseListener());
    }

    public JLabel getLabel() {
        return this.label;
    }

    public class ClassMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            JLabel c = (JLabel) e.getSource();

            System.out.println("I'm clicked!! " + c.getText());
            Class.orig_x = e.getX();
            Class.orig_y = e.getY();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (ToolBar.state.getButtonNow() == ButtonName.SELECT_BTN) {
                JLabel c = (JLabel) e.getSource();
                c.setBounds(c.getX() + (e.getX() - Class.orig_x), c.getY()+ (e.getY() - Class.orig_y), 100, 100);
                c.getParent().revalidate();
                c.getParent().repaint();
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
