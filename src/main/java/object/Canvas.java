package object;

import delegate.BaseMouseListener;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {

    // 需要有現在使用什麽工具的資訊
    // editor.Editor
    //      - Canvas
    //      - Toolbar
    //      - Menu
    public Canvas() {
        this.setLayout(null);    // no layout manager, absolute positioning
    }

    public void initCanvas(int width, int height) {
        this.setSize(width, height);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.addMouseListener(new BaseMouseListener());
    }

    public void add() {
        JLabel label = new JLabel();
        this.add(label);
    }
}
