package canvas.factory.object;

import canvas.shapes.object.BaseObject;
import canvas.shapes.object.Class;

import java.awt.*;

public class ClassFactory implements BaseObjectFactory {
    @Override
    public BaseObject createBaseObject(Point origin) {
        return new Class(origin);
    }
}
