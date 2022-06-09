package future.mode;

import future.canvas.Canvas;
import future.factory.UseCaseFactory;
import future.strategy.CreateObjectStrategy;

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
