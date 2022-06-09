package canvas.factory.line;

import canvas.shapes.line.BaseLine;
import canvas.shapes.line.CompositionLine;

import java.awt.*;

public class CompositionLineFactory implements BaseLineFactory {
    @Override
    public BaseLine createBaseLine(Point origin, Point destination) {
        return new CompositionLine(origin, destination);
    }
}
