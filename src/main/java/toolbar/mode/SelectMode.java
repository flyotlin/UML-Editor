package toolbar.mode;

import canvas.Canvas;
import canvas.strategy.SelectStrategy;

public class SelectMode extends ToolMode {
    public SelectMode() {
        super("Select");
    }

    @Override
    public void changeCanvasStrategy() {
        Canvas.getInstance().setStrategy(new SelectStrategy());
    }
}
