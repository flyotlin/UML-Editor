package object;

import java.awt.*;

public class UseCase extends BaseLabel {
    public UseCase(String text, int x, int y, int w, int h) {
        super(text, x, y, w, h);
        this.setBorder(new RoundedBorder(Color.BLACK, 10));
    }
}
