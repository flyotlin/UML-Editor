package future.mode;

import future.canvas.Canvas;
import future.strategy.ClassStrategy;

public class ClassMode extends ToolMode {
    public ClassMode() {
        super("Class");
    }

    @Override
    public void changeCanvasStrategy() {
        Canvas.getInstance().setStrategy(new ClassStrategy());
    }
}
