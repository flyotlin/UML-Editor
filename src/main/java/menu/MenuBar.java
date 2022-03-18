package menu;

import object.Canvas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends javax.swing.JMenuBar {
    private object.future.Canvas canvas;

    public MenuBar(object.future.Canvas canvas) {
        super();
        this.canvas = canvas;

        JMenuItem openFileItem = new JMenuItem("Open File");
        JMenuItem closeItem = new JMenuItem("Close");
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(openFileItem);
        fileMenu.add(closeItem);

        JMenuItem groupItem = new JMenuItem("Group");
        groupItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.createGroupObject();
            }
        });

        JMenuItem ungroupItem = new JMenuItem("UnGroup");
        ungroupItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("UNGROUP!");
                canvas.removeGroupObject();
            }
        });

        JMenuItem renameItem = new JMenuItem("Change Object Name");
        JMenu editMenu = new JMenu("Edit");
        editMenu.add(groupItem);
        editMenu.add(ungroupItem);
        editMenu.add(renameItem);

        this.add(fileMenu);
        this.add(editMenu);
    }
//    private MenuBar() {
//        super();
//    }
//
//    private static NewCanvas canvas;
//
//    public static MenuBar getMenuBar() {
//
//        JMenuItem openFileItem = new JMenuItem("Open File");
//        JMenuItem closeItem = new JMenuItem("Close");
//        JMenu fileMenu = new JMenu("File");
//        fileMenu.add(openFileItem);
//        fileMenu.add(closeItem);
//
//        JMenuItem groupItem = new JMenuItem("Group");
//        groupItem.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("Group!!!");
//            }
//        });
//
//        JMenuItem ungroupItem = new JMenuItem("UnGroup");
//        JMenuItem renameItem = new JMenuItem("Change Object Name");
//        JMenu editMenu = new JMenu("Edit");
//        editMenu.add(groupItem);
//        editMenu.add(ungroupItem);
//        editMenu.add(renameItem);
//
//        MenuBar menuBar = new MenuBar();
//        menuBar.add(fileMenu);
//        menuBar.add(editMenu);
//
//        return menuBar;
//    }
}
