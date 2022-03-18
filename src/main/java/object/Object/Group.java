package object.Object;

import object.Canvas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Group extends UMLObject {

    public static int GroupCount = 0;

    private ArrayList<BaseObject> selectedObjs;

    public Group(int x, int y, int w, int h, Canvas canvas, ArrayList<BaseObject> selectedObjs) {
        super(x, y, w, h, canvas);
        this.selectedObjs = selectedObjs;
        this.setBorder(BorderFactory.createDashedBorder(Color.RED, 3, 4, 3, true));

        for (BaseObject obj : selectedObjs) {
            obj.setBounds(obj.getX()-this.getX(), obj.getY()-this.getY(), obj.getWidth(), obj.getHeight());

//            if (obj instanceof UML
////                ((UMLObject) obj).switchListener(false);
////            }Object) {

            this.add(obj);
        }
        this.revalidate();
        this.repaint();

        /**
         * BaseObject:
         *  - Methods
         *      - setGid
         * UMLObject:
         *  - Variables
         *      - anchorPoints
         *      - connectedStartLines
         *      - connectedEndLines
         *      - umlML
         *      - umlMML
         *  - Methods
         *      - switchListener
         *      - createAnchorPoints
         *      - moveObject
         *      - decideLineCoord
         *      -
         *
         * Group:
         *
         * UMLObjectMouseListener
         *  - Variables:
         *      - canvas
         *      - obj
         *      - anchorPoints(這個可以從obj裡面取得？)
         *  - Methods
         *      -
         */
    }

    @Override
    protected void setGid() {
        GroupCount++;
        this.gid = "gr" + GroupCount;
    }
}
