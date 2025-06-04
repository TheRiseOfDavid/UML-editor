package Action;

import UI.DrawingCanvas;

// Command implementation
public class GroupCommand implements MenuCommand {
    private DrawingCanvas canvas;

    public GroupCommand(DrawingCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void execute() {
        canvas.setMenuAction(DrawingCanvas.MenuAction.group);
        canvas.executeGroupAction();
    }
}
