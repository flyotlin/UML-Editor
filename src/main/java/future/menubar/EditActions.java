package future.menubar;

import java.awt.event.ActionListener;

public class EditActions {
    public ActionListener getEditActionListener(String action) {
        if (action == "group") {
            return e -> {
                this.group();
            };
        }
        if (action == "ungroup") {
            return e -> {
                this.ungroup();
            };
        }
        if (action == "rename") {
            return e -> {
                this.rename();
            };
        }
        return null;
    }

    public void group() {
        System.out.println("group");
    }

    public void ungroup() {
        System.out.println("ungroup");
    }

    public void rename() {
        System.out.println("rename");
    }
}
