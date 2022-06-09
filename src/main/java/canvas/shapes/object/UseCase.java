package canvas.shapes.object;

import canvas.shapes.CircleBorder;

import java.awt.*;

public class UseCase extends BaseObject {
    public UseCase(Point origin) {
        super(origin);
        setText("UseCase");
        setBorder(new CircleBorder());
    }
}
