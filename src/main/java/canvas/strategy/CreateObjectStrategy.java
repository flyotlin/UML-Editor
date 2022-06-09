package canvas.strategy;

import canvas.Canvas;
import canvas.shapes.object.BaseObject;
import canvas.factory.object.BaseObjectFactory;

import java.awt.*;
import java.awt.event.MouseEvent;

public class CreateObjectStrategy implements BaseStrategy {
    private final BaseObjectFactory factory;

    public CreateObjectStrategy(BaseObjectFactory factory) {
        this.factory = factory;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        createBaseObjectInCanvas(e.getPoint());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    private void createBaseObjectInCanvas(Point origin) {
        BaseObject object = factory.createBaseObject(origin);
        Canvas.getInstance().add(object);
    }
}
