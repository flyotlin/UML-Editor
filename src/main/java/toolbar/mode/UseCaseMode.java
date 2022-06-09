package toolbar.mode;

import canvas.Canvas;
import canvas.factory.object.UseCaseFactory;
import canvas.strategy.CreateObjectStrategy;

public class UseCaseMode extends ToolMode {
    public UseCaseMode() {
        super("Use Case");
    }

    @Override
    public void changeCanvasStrategy() {
        super.changeCanvasStrategy();
        Canvas.getInstance().setStrategy(new CreateObjectStrategy(new UseCaseFactory()));
    }
}
