package future.toolbar;

import future.mode.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Toolbar extends JPanel {
    private static Toolbar toolbar;
    private final ArrayList<ToolButton> buttons = new ArrayList<ToolButton>();

    private Toolbar() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(new ToolButton(new SelectMode()));
        this.add(new ToolButton(new AssociationMode()));
        this.add(new ToolButton(new GeneralizationMode()));
        this.add(new ToolButton(new CompositionMode()));
        this.add(new ToolButton(new ClassMode()));
        this.add(new ToolButton(new UseCaseMode()));

        ToolButton selectButton = this.buttons.get(0);
        selectButton.setBackground(true);
        selectButton.changeCanvasStrategy();
    }

    public static Toolbar getInstance() {
        if (toolbar == null) {
            toolbar = new Toolbar();
        }
        return toolbar;
    }

    @Override
    public Component add(Component comp) {
        this.buttons.add((ToolButton) comp);
        return super.add(comp);
    }

    public void setButtonBackgroundColor(String selectedButtonName) {
        for (ToolButton button : buttons) {
            button.setBackground(button.getName() == selectedButtonName);
        }
    }
}
