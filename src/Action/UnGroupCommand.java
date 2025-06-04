package Action;

import UI.DrawingCanvas;

// Command implementation
public class UnGroupCommand implements MenuCommand {
    private DrawingCanvas canvas;

    public UnGroupCommand(DrawingCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void execute() {
        canvas.setMenuAction(DrawingCanvas.MenuAction.ungroup);
        canvas.executeUngroupAction();
    }
}
