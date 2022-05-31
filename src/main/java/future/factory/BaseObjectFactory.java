package future.factory;

import future.canvas.shapes.BaseObject;

import java.awt.*;

public interface BaseObjectFactory {
    BaseObject createBaseObject(Point origin);
}
