package canvas.factory.line;

import canvas.shapes.line.AssociationLine;
import canvas.shapes.line.BaseLine;

import java.awt.*;

public class AssociationLineFactory implements BaseLineFactory {
    @Override
    public BaseLine createBaseLine(Point origin, Point destination) {
        return new AssociationLine(origin, destination);
    }
}
