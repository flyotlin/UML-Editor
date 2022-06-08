import editor.Editor;
import future.UMLEditor;

public class Main {
    private static int frameWidth = 1200;
    private static int frameHeight = 800;
    public static void main(String[] args) {
//        Editor.run(frameWidth, frameHeight);
        UMLEditor.getInstance().run();
    }
}
