package canvas.factory.object;

import canvas.shapes.object.BaseObject;

import java.awt.*;

public interface BaseObjectFactory {
    BaseObject createBaseObject(Point origin);
}
