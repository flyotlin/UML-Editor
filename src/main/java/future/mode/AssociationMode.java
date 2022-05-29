package future.mode;

import future.canvas.Canvas;
import future.strategy.AssociationStrategy;

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
