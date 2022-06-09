package future.factory;

import future.canvas.shapes.BaseLine;
import future.canvas.shapes.CompositionLine;
import future.canvas.shapes.GeneralizationLine;

import java.awt.*;

public class CompositionLineFactory implements BaseLineFactory {
    @Override
    public BaseLine createBaseLine(Point origin, Point destination) {
        return new CompositionLine(origin, destination);
    }
}
