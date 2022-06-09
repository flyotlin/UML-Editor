package toolbar.mode;

import canvas.Canvas;
import canvas.factory.line.GeneralizationLineFactory;
import canvas.strategy.CreateLineStrategy;

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
