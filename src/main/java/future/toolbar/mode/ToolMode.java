package future.toolbar.mode;

public abstract class ToolMode {
    private final String name;

    public ToolMode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Left empty on purpose, must be implemented by subclasses.
     */
    public void changeCanvasStrategy() {
    }
}
