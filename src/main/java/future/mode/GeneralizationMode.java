package future.mode;

import future.canvas.Canvas;
import future.factory.GeneralizationLineFactory;
import future.strategy.CreateLineStrategy;

public class GeneralizationMode extends ToolMode {
    public GeneralizationMode() {
        super("Generalization");
    }

    @Override
    public void changeCanvasStrategy() {
        super.changeCanvasStrategy();
        Canvas.getInstance().setStrategy(new CreateLineStrategy(new GeneralizationLineFactory()));
    }
}
