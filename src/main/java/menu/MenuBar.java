package menu;

import canvas.Canvas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends javax.swing.JMenuBar {
    private Canvas canvas;

    public MenuBar(Canvas canvas) {
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
                canvas.removeGroupObject();
            }
        });

        JMenuItem renameItem = new JMenuItem("Change Object Name");
        renameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(canvas, "New UMLObject Name:", null);
                canvas.renameUMLObject(name);
            }
        });

        JMenu editMenu = new JMenu("Edit");
        editMenu.add(groupItem);
        editMenu.add(ungroupItem);
        editMenu.add(renameItem);

        this.add(fileMenu);
        this.add(editMenu);
    }
}
