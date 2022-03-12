package toolbar;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Toolbar extends JPanel {
    public static Tools toolsNowSelected = Tools.SELECT;
    private ArrayList<JButton> toolList;

    public Toolbar() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.toolList = new ArrayList<JButton>();

        ToolButton btn;
        for (Tools tool : Tools.values()) {
            btn = new ToolButton(tool);
            this.toolList.add(btn);
            this.add(btn);
        }
        this.toolList.get(Tools.SELECT.ordinal()).setBackground(Color.GRAY);
    }

    public void notifyButtonsChange(Tools toolName) {
        Color white = new Color(238, 238, 238);
        Color gray = Color.GRAY;
        for (JButton b : this.toolList) {
            if (toolName == ((ToolButton) b).toolsName) {
                b.setBackground(gray);
            } else {
                b.setBackground(white);
            }
        }
        toolsNowSelected = toolName;
    }
}
