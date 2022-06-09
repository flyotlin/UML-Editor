package canvas.factory.object;

import canvas.shapes.object.BaseObject;
import canvas.shapes.object.UseCase;

import java.awt.*;

public class UseCaseFactory implements BaseObjectFactory {
    @Override
    public BaseObject createBaseObject(Point origin) {
        return new UseCase(origin);
    }
}
