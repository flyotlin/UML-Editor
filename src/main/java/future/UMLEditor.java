package future;

import future.canvas.Canvas;
import future.toolbar.Toolbar;

import javax.swing.*;
import java.awt.*;

public class UMLEditor {
    private static UMLEditor umlEditor;

    private final Dimension frameDimension = new Dimension(1200, 800);
    private final String editorName = "UML Editor";

    private final JFrame frame;

    private UMLEditor() {
        frame = new JFrame(editorName);
        frame.setSize(frameDimension);
        frame.setResizable(false);

        /**
         * Add all parts into the frame (main window).
         *
         * Containing toolbar, canvas, menu.
         */
        frame.getContentPane().add(BorderLayout.WEST, Toolbar.getInstance());
        frame.getContentPane().add(BorderLayout.CENTER, Canvas.getInstance());
    }

    public static UMLEditor getInstance() {
        if (umlEditor == null) {
            umlEditor = new UMLEditor();
        }
        return umlEditor;
    }

    public void run() {
        frame.setVisible(true);
    }
}
