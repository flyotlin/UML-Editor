package future.canvas.shapes;

import javax.swing.*;
import java.awt.*;

public class Class extends Shape {
    private final static Dimension size = new Dimension(120, 120);

    private JLabel text = null;

    public Class(Point origin) {
        this.setBounds(origin.x, origin.y, size.width, size.height);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setOpaque(true);
        this.setText("Class");
    }

    public void setText(String text) {
        if (this.text == null) {
            this.text = new JLabel(text);
            this.add(this.text);
        } else {
            this.text.setText(text);
        }
    }
}
