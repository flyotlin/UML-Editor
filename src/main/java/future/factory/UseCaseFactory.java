package future.factory;

import future.canvas.shapes.BaseObject;
import future.canvas.shapes.UseCase;

import java.awt.*;

public class UseCaseFactory implements BaseObjectFactory {
    @Override
    public BaseObject createBaseObject(Point origin) {
        return new UseCase(origin);
    }
}
