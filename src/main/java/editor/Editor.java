package editor;

import menu.MenuBar;
import toolbar.Toolbar;

import javax.swing.*;
import java.awt.*;

// Apply Singleton Design Pattern
// 為了能有唯一一個Editor實例管理所有Editor底下的工具及物件

public class Editor {
    private static String EDITOR_NAME = "UML Editor";

    private static JFrame editor;   // editor.Editor Main JFrame
    private static java.awt.Canvas canvas;

    public static void run(int frameWidth, int frameHeight) {
        if (editor == null) {
            editor = new JFrame(EDITOR_NAME);
            editor.setSize(frameWidth, frameHeight);
            editor.setResizable(false);

            // Toolbar
            Toolbar toolbar = new Toolbar();

            // Canvas
            canvas.Canvas canvas = new canvas.Canvas();

            // MenuBar
            JMenuBar menuBar = new MenuBar(canvas);

            // Attach to editor's main frame
            editor.getContentPane().add(BorderLayout.WEST, toolbar);
            editor.getContentPane().add(BorderLayout.NORTH, menuBar);
            editor.getContentPane().add(BorderLayout.CENTER, canvas);

            // Run editor(show)
            editor.setVisible(true);
        }
    }
}
