package future.factory;

import future.canvas.shapes.BaseLine;

import java.awt.*;

public interface BaseLineFactory {
    BaseLine createBaseLine(Point origin, Point destination);
}
