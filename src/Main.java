import javax.swing.*;

import UI.DrawingCanvas;
import UI.Sidebar;
import UI.WorkMode;
import UI.Menubar;
import java.awt.*;
import java.io.File;

public class Main {
    static final int UIWidth = 900;
    static final int UIHeight = 600;

    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("UML Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(UIWidth, UIHeight);

        // 生成 canvas
        DrawingCanvas canvas = new DrawingCanvas();

        // 生成 menubar
        Menubar menubar = new Menubar(frame, canvas);
        frame.setJMenuBar(menubar);

        // 生成 sidebar
        WorkMode selectMode = new WorkMode("./src/img/select.png", canvas,
                DrawingCanvas.ModeType.selectMode);
        selectMode.setBackground(Color.BLACK);
        canvas.setModeType(DrawingCanvas.ModeType.selectMode);

        WorkMode associationMode = new WorkMode("./src/img/associate.png", canvas,
                DrawingCanvas.ModeType.associationMode);
        WorkMode generalizationMode = new WorkMode("./src/img/general.png", canvas,
                DrawingCanvas.ModeType.generalizationMode);
        WorkMode compositionMode = new WorkMode("./src/img/composite.png", canvas,
                DrawingCanvas.ModeType.compositionMode);
        WorkMode rectMode = new WorkMode("./src/img/rect.png", canvas,
                DrawingCanvas.ModeType.rectMode);
        WorkMode ovalMode = new WorkMode("./src/img/oval.png", canvas,
                DrawingCanvas.ModeType.ovalMode);
        Sidebar sidebar = new Sidebar(100, UIHeight);

        // 在更新 gui 的時候，怕 multi-thread 同時控制 UI 導致出錯，因此用這 function 包住，保證 thread safely
        SwingUtilities.invokeLater(() -> {

            sidebar.addButton(selectMode);
            sidebar.addButton(associationMode);
            sidebar.addButton(generalizationMode);
            sidebar.addButton(compositionMode);
            sidebar.addButton(rectMode);
            sidebar.addButton(ovalMode);

            frame.setLayout(new BorderLayout());

            frame.add(sidebar.getPanel(), BorderLayout.WEST);
            frame.add(canvas.getPanel(), BorderLayout.EAST);
            frame.setVisible(true);
        });
    }
}
