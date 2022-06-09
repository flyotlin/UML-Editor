package toolbar.mode;

import canvas.Canvas;
import canvas.factory.line.AssociationLineFactory;
import canvas.strategy.CreateLineStrategy;

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
