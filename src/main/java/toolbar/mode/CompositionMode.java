package toolbar.mode;

import canvas.Canvas;
import canvas.factory.line.CompositionLineFactory;
import canvas.strategy.CreateLineStrategy;

public class CompositionMode extends ToolMode {
    public CompositionMode() {
        super("Composition");
    }

    @Override
    public void changeCanvasStrategy() {
        super.changeCanvasStrategy();
        Canvas.getInstance().setStrategy(new CreateLineStrategy(new CompositionLineFactory()));
    }
}
