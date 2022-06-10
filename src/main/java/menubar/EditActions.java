package menubar;

import canvas.Canvas;
import canvas.shapes.Group;
import canvas.shapes.Shape;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditActions {
    public ActionListener getEditActionListener(String action) {
        if (action == "group") {
            return e -> {
                this.group();
            };
        }
        if (action == "ungroup") {
            return e -> {
                this.ungroup();
            };
        }
        if (action == "rename") {
            return e -> {
                this.rename();
            };
        }
        return null;
    }

    public void group() {
        Canvas canvas = Canvas.getInstance();
        ArrayList<Shape> selectedShapes = canvas.getSelectedShapes();

        if (selectedShapes.size() <= 1) {
            return;
        }
        Group group = new Group(canvas.getOrigin(), canvas.getDestination(), selectedShapes);
        canvas.add(group);
    }

    public void ungroup() {
        Canvas canvas = Canvas.getInstance();
        ArrayList<Shape> selectedShapes = canvas.getSelectedShapes();

        if (selectedShapes.size() != 1) {
            return;
        }
        Shape selectedShape = selectedShapes.get(0);
        selectedShape.ungroup();
    }

    public void rename() {
        Canvas canvas = Canvas.getInstance();
        ArrayList<Shape> selectedShapes = canvas.getSelectedShapes();
        if (selectedShapes.size() != 1) {
            return;
        }

        Shape selectedShape = selectedShapes.get(0);
        String name = JOptionPane.showInputDialog(canvas, "New UMLObject Name:", null);
        if (name != null) {
            selectedShape.setText(name);
        }
    }
}
