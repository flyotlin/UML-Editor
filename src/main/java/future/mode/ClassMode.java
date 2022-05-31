package future.mode;

import future.canvas.Canvas;
import future.factory.ClassFactory;
import future.strategy.CreateObjectStrategy;

public class ClassMode extends ToolMode {
    public ClassMode() {
        super("Class");
    }

    @Override
    public void changeCanvasStrategy() {
        Canvas.getInstance().setStrategy(new CreateObjectStrategy(new ClassFactory()));
    }
}