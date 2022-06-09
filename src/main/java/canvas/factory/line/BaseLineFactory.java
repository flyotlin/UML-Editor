package canvas.factory.line;

import canvas.shapes.line.BaseLine;

import java.awt.*;

public interface BaseLineFactory {
    BaseLine createBaseLine(Point origin, Point destination);
}
