package future.toolbar.mode;

import future.canvas.Canvas;
import future.canvas.strategy.AssociationStrategy;

public class AssociationMode extends ToolMode {
    public AssociationMode() {
        super("Association");
    }

    @Override
    public void changeCanvasStrategy() {
        super.changeCanvasStrategy();
        Canvas.getInstance().setStrategy(new AssociationStrategy());
    }
}
