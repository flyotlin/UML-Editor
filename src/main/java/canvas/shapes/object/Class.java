package canvas.shapes.object;


import javax.swing.*;
import java.awt.*;

public class Class extends BaseObject {
    public Class(Point origin) {
        super(origin);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setText("Class");
    }
}
