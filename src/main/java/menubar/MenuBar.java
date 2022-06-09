package menubar;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    private static MenuBar menuBar;
    private final EditActions actions;

    private MenuBar() {
        actions = new EditActions();
        JMenu fileMenu = getFileMenu();
        JMenu editMenu = getEditMenu();

        this.add(fileMenu);
        this.add(editMenu);
    }

    public static MenuBar getInstance() {
        if (menuBar == null) {
            menuBar = new MenuBar();
        }
        return menuBar;
    }

    private JMenu getFileMenu() {
        JMenu fileMenu = new JMenu("File");

        JMenuItem openFileItem = new JMenuItem("Open File");
        JMenuItem closeItem = new JMenuItem("Close");

        fileMenu.add(openFileItem);
        fileMenu.add(closeItem);

        return fileMenu;
    }

    private JMenu getEditMenu() {
        JMenu editMenu = new JMenu("Edit");

        JMenuItem groupItem = new JMenuItem("Group");
        groupItem.addActionListener(actions.getEditActionListener("group"));

        JMenuItem ungroupItem = new JMenuItem("Ungroup");
        ungroupItem.addActionListener(actions.getEditActionListener("ungroup"));

        JMenuItem renameItem = new JMenuItem("Rename Object");
        renameItem.addActionListener(actions.getEditActionListener("rename"));

        editMenu.add(groupItem);
        editMenu.add(ungroupItem);
        editMenu.add(renameItem);

        return editMenu;
    }
}
