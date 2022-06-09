package future.factory;

import future.canvas.shapes.AssociationLine;
import future.canvas.shapes.BaseLine;
import future.canvas.shapes.GeneralizationLine;

import java.awt.*;

public class GeneralizationLineFactory implements BaseLineFactory {
    @Override
    public BaseLine createBaseLine(Point origin, Point destination) {
        return new GeneralizationLine(origin, destination);
    }
}
