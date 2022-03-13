package menu;

import javax.swing.*;

public class MenuBar extends javax.swing.JMenuBar {
    private MenuBar() {
        super();
    }

    public static MenuBar getMenuBar() {
        JMenuItem openFileItem = new JMenuItem("Open File");
        JMenuItem closeItem = new JMenuItem("Close");
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(openFileItem);
        fileMenu.add(closeItem);

        JMenuItem copyItem = new JMenuItem("Copy");
        JMenuItem pasteItem = new JMenuItem("Paste");
        JMenuItem cutItem = new JMenuItem("Cut");
        JMenu editMenu = new JMenu("Edit");
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        editMenu.add(cutItem);

        MenuBar menuBar = new MenuBar();
        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        return menuBar;
    }
}
