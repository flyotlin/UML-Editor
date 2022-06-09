package toolbar.mode;

import canvas.Canvas;
import canvas.factory.object.ClassFactory;
import canvas.strategy.CreateObjectStrategy;

public class ClassMode extends ToolMode {
    public ClassMode() {
        super("Class");
    }

    @Override
    public void changeCanvasStrategy() {
        Canvas.getInstance().setStrategy(new CreateObjectStrategy(new ClassFactory()));
    }
}
