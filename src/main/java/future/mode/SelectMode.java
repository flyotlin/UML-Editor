package future.mode;

import future.canvas.Canvas;
import future.strategy.SelectStrategy;

public class SelectMode extends ToolMode {
    public SelectMode() {
        super("Select");
    }

    @Override
    public void changeCanvasStrategy() {
        Canvas.getInstance().setStrategy(new SelectStrategy());
    }
}
