package object.new_obj;

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

    public BaseLine(int x1, int y1, int x2, int y2) {
        start = new Point(x1, y1);
        end = new Point(x2, y2);
        this.setGid();
    }

    public static BaseLine LineFactory(int x1, int y1, int x2, int y2) {
        if (Toolbar.toolsNowSelected == Tools.ASSOCIATION) {
            return new AssociationLine(x1, y1, x2, y2);
        } else if (Toolbar.toolsNowSelected == Tools.GENERALIZATION) {
            return new GeneralizationLine(x1, y1, x2, y2);
        } else if (Toolbar.toolsNowSelected == Tools.COMPOSITION) {
            return new CompositionLine(x1, y1, x2, y2);
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
