package tools.state;

import tools.button.BaseBtn;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ToolBarState {
    private ButtonName buttonNow = ButtonName.NULL_BTN;
    private ArrayList<BaseBtn> buttonsList;
    private JPanel toolbarPanel;

    public ToolBarState(JPanel toolbarPanel) {
        this.buttonNow = ButtonName.NULL_BTN;
        this.buttonsList = new ArrayList<BaseBtn>();
        this.toolbarPanel = toolbarPanel;
    }

    public ButtonName getButtonNow() {
        return this.buttonNow;
    }

    public void addButton(BaseBtn btn) {
        this.buttonsList.add(btn);
        this.toolbarPanel.add(btn);
    }

    public void setButtonNow(ButtonName name) {
        Color defaultColor = new Color(238, 238, 238);
        for (BaseBtn btn : this.buttonsList) {
            if (btn.getButtonName() == name) {
                if (btn.getBackground() == Color.GRAY) {
                    btn.setBackground(defaultColor);
                } else {
                    btn.setBackground(Color.GRAY);
                }
            } else {
                btn.setBackground(defaultColor);
            }
        }
        this.buttonNow = name;
    }

    public void setButtonNow(ButtonName name, ToolBarState state) {
        this.buttonNow = name;
    }
}
