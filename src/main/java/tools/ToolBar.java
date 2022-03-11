package tools;

import tools.button.*;
import tools.state.ButtonName;
import tools.state.ToolBarState;

import javax.swing.*;

public class ToolBar {
    public static ToolBarState state;
    private JPanel panel;

    public ToolBar() {
        // Toolbar
        this.panel = new JPanel();
        this.panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Toolbar State
        state = new ToolBarState(this.panel);

        // Buttons of Toolbar
        BaseBtn btn;
        btn = new SelectBtn();
        state.addButton(btn);

        btn = new AssociationBtn();
        state.addButton(btn);

        btn = new ClassBtn();
        state.addButton(btn);

        btn = new UseCaseBtn();
        state.addButton(btn);
    }

    public JPanel getPanel() {
        return this.panel;
    }
}

//        JButton selectBtn = new JButton("Select");
//        JButton associateBtn = new JButton("Association");
//        JButton generalizeBtn = new JButton("Generalization");
//        JButton compositeBtn = new JButton("Composition");
//        JButton classBtn = new JButton("Class");
//        JButton usecaseBtn = new JButton("Use Case");
