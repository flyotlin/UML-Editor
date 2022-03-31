package toolbar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ToolButton extends JButton {
    public Tools toolsName;
    public ToolButton(Tools tool) {
        this.toolsName = tool;
        this.setText(tool.name());
        this.setOpaque(true);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.addMouseListener(new BaseButtonMouseListener());
    }

    class BaseButtonMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            ToolButton btn = (ToolButton) e.getSource();
            btn.setBackground(Color.GRAY);
            Toolbar toolBar = (Toolbar) btn.getParent();
            toolBar.notifyButtonsChange(btn.toolsName);
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
