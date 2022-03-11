package canvas;

import object.UMLObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//import ;

public class Canvas {
    private JPanel canvas;
    private ArrayList<UMLObject> umlObjectList;    // Holds all UMLObjects inside canvas

    // 需要有現在使用什麽工具的資訊
    // editor.Editor
    //      - Canvas
    //      - Toolbar
    //      - Menu
    public Canvas() {
        this.canvas = new JPanel();
        this.canvas.setLayout(null);    // no layout manager, absolute positioning
    }

    public JPanel getCanvas() {
        return this.canvas;
    }

    public void initCanvas(int width, int height) {
        this.canvas.setSize(width, height);
        this.canvas.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.canvas.addMouseListener(new CanvasMouseListener());
    }

    public void add() {
        JLabel label = new JLabel();
        this.canvas.add(label);
    }
}
