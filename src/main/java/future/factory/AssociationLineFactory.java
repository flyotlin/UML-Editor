package future.factory;

import future.canvas.shapes.AssociationLine;
import future.canvas.shapes.BaseLine;

import java.awt.*;

public class AssociationLineFactory implements BaseLineFactory {
    @Override
    public BaseLine createBaseLine(Point origin, Point destination) {
        return new AssociationLine(origin, destination);
    }
}
