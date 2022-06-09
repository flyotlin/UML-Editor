package future.mode;

import future.canvas.Canvas;
import future.factory.CompositionLineFactory;
import future.factory.GeneralizationLineFactory;
import future.strategy.CreateLineStrategy;

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
