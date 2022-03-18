package canvas.line;

import canvas.object.UMLObject;
import toolbar.Toolbar;
import toolbar.Tools;

import java.awt.*;

public abstract class BaseLine {
    public static int LineCount = 0;
    public String gid;
    protected int arrowLen = 20;

    // Line的start及end point(以canvas座標)
    public Point start;
    public Point end;

    // Line start及end連接的UMLObject
    public UMLObject startObj;
    public UMLObject endObj;

    // layer
    public int layer;

    public BaseLine(Point head, Point tail) {
        start = head;
        end = tail;
        this.setGid();
    }

    public static BaseLine LineFactory(Point head, Point tail) {
        if (Toolbar.toolsNowSelected == Tools.ASSOCIATION) {
            return new AssociationLine(head, tail);
        } else if (Toolbar.toolsNowSelected == Tools.GENERALIZATION) {
            return new GeneralizationLine(head, tail);
        } else if (Toolbar.toolsNowSelected == Tools.COMPOSITION) {
            return new CompositionLine(head, tail);
        }
        return null;
    }

    public void drawLine(Graphics2D g2d) {
        g2d.drawLine(start.x, start.y, end.x, end.y);
    }

    private void setGid() {
        LineCount++;
        this.gid = "l" + LineCount;
    }
}
