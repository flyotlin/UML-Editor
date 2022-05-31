package future.mode;

import future.canvas.Canvas;
import future.factory.AssociationLineFactory;
import future.strategy.CreateLineStrategy;

public class AssociationMode extends ToolMode {
    public AssociationMode() {
        super("Association");
    }

    @Override
    public void changeCanvasStrategy() {
        super.changeCanvasStrategy();
        Canvas.getInstance().setStrategy(new CreateLineStrategy(new AssociationLineFactory()));
    }
}
