package canvas.factory.line;

import canvas.shapes.line.BaseLine;
import canvas.shapes.line.GeneralizationLine;

import java.awt.*;

public class GeneralizationLineFactory implements BaseLineFactory {
    @Override
    public BaseLine createBaseLine(Point origin, Point destination) {
        return new GeneralizationLine(origin, destination);
    }
}
