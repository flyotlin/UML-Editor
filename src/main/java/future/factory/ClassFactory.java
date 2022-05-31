package future.factory;

import future.canvas.shapes.BaseObject;
import future.canvas.shapes.Class;

import java.awt.*;

public class ClassFactory implements BaseObjectFactory {
    @Override
    public BaseObject createBaseObject(Point origin) {
        return new Class(origin);
    }
}
