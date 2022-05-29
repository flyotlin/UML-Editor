package future.toolbar.mode;

import future.canvas.Canvas;
import future.canvas.strategy.SelectStrategy;

public class SelectMode extends ToolMode {
    public SelectMode() {
        super("Select");
    }

    @Override
    public void changeCanvasStrategy() {
        Canvas.getInstance().setStrategy(new SelectStrategy());
    }
}
