package editor;

import canvas.Canvas;
import menu.MenuBar;
import tools.ToolBar;

import javax.swing.*;
import java.awt.*;

// Apply Singleton Design Pattern
// 為了能有唯一一個Editor實例管理所有Editor底下的工具及物件

public class Editor {
    private static String EDITOR_NAME = "UML Editor";

    private static JFrame editor;   // editor.Editor Main JFrame
    private static Canvas canvas;

    public static void run(int frameWidth, int frameHeight) {
        if (editor == null) {
            editor = new JFrame(EDITOR_NAME);
            editor.setSize(frameWidth, frameHeight);
            editor.setResizable(false);

            // Buttons
            ToolBar toolbar = new ToolBar();

            // MenuBar
            JMenuBar menuBar = MenuBar.getMenuBar();

            // Canvas
            canvas = new Canvas();
            canvas.initCanvas(100, 100);

            // Attach to editor's main frame
            editor.getContentPane().add(BorderLayout.WEST, toolbar.getPanel());
            editor.getContentPane().add(BorderLayout.NORTH, menuBar);
            editor.getContentPane().add(BorderLayout.CENTER, canvas.getCanvas());

            // Run editor(show)
            editor.setVisible(true);
        }
    }

    public static void repaint() {
        editor.repaint();

    }
}
