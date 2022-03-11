import editor.Editor;

public class Main {
    public static void main(String[] args) {
        System.out.println(Main.class.getPackage());
        System.out.println(Main.class.getName());
        Editor.run(1200, 800);
    }
}
