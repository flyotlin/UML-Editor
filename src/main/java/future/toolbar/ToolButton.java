package future.toolbar;

import future.mode.ToolMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ToolButton extends JButton implements MouseListener {
    private final ToolMode mode;
    private final String name;

    public ToolButton(ToolMode mode) {
        this.mode = mode;
        this.name = mode.getName();

        this.setText(this.name);
        this.setOpaque(true);
        this.setBorderPainted(false);
        this.setFocusPainted(false);

        this.addMouseListener(this);
    }

    public String getName() {
        return name;
    }

    public void setBackground(boolean selected) {
        Color notSelectedColor = new Color(238, 238, 238);
        Color selectedColor = Color.GRAY;

        if (selected) {
            super.setBackground(selectedColor);
        } else {
            super.setBackground(notSelectedColor);
        }
    }

    public void changeCanvasStrategy() {
        this.mode.changeCanvasStrategy();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Toolbar toolbar = Toolbar.getInstance();
        toolbar.setButtonBackgroundColor(name);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        changeCanvasStrategy();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
