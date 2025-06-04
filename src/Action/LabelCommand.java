package Action;
import UI.DrawingCanvas;
import UI.Dialog;

// Command implementation
public class LabelCommand implements MenuCommand {
    private DrawingCanvas canvas;
    private Dialog dialog;

    public LabelCommand(DrawingCanvas canvas, Dialog dialog) {
        this.canvas = canvas;
        this.dialog = dialog; 
    }

    @Override
    public void execute() {
        canvas.setMenuAction(DrawingCanvas.MenuAction.label);
        dialog.display();
    }
}
