package object.canvas;

import object.BaseMouseListener;
import object.line.NewBaseLine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Canvas extends JPanel {

    // 需要有現在使用什麽工具的資訊
    // editor.Editor
    //      - Canvas
    //      - Toolbar
    //      - Menu

    public Point pointStart = null;
    public Point pointEnd   = null;

    public Canvas() {
        this.setLayout(null);    // no layout manager, absolute positioning
    }

    public void initCanvas(int width, int height) {
        this.setSize(width, height);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.addMouseListener(new BaseMouseListener());


//        NewBaseLine line = new NewBaseLine(100, 0);
//        int len = (int) (Math.round(100) / Math.sqrt(2));
//        line.setBounds(100, 100, len, len);
//        this.add(line);
    }

    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("WWW");
        if (pointStart != null) {
            g.setColor(Color.RED);
            g.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y);
        }
    }

    public void add() {
        JLabel label = new JLabel();
        this.add(label);
    }
}
