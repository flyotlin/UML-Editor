package tools.button;

import tools.ToolBar;
import tools.state.ButtonName;
import tools.state.ToolBarState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BaseBtn extends JButton {
    protected ButtonName buttonName;

    public BaseBtn(String btnText, ButtonName btnName) {
        this.setText(btnText);
        this.buttonName = btnName;

        this.setOpaque(true);
        this.addMouseListener(new BaseBtnMouseListener());
    }

    public ButtonName getButtonName() {
        return this.buttonName;
    }

    class BaseBtnMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            JButton button = (JButton)e.getSource();
            System.out.println(button.getText() + " Clicked! ");
        }

        @Override
        public void mousePressed(MouseEvent e) {
            BaseBtn button = (BaseBtn)e.getSource();
            ToolBar.state.setButtonNow(button.buttonName);
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
