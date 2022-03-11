package tools.button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Btn extends JButton {
    public Btn() {
        this.addMouseListener(new BaseBtnMouseListener());
    }
    class BaseBtnMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            JButton button = (JButton)e.getSource();
            System.out.println(button.getText() + " Clicked! ");
        }

        @Override
        public void mousePressed(MouseEvent e) {
            JButton button = (JButton)e.getSource();
            if (Color.GRAY == button.getBackground()) {
                button.setBackground(new Color(238, 238, 238));
            } else {
                button.setBackground(Color.GRAY);
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
}
