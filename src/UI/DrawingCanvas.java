package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawingCanvas {
    static final int UIWidth = 800;
    static final int UIHeight = 700;
    private JPanel panel;

    // 用於知道我現在要畫哪個圖形
    public enum ShapeType {
        selectMode,
        associationMode,
        generalizationMode,
        compositionMode,
        rectMode,
        ovalMode,
    }

    private ShapeType type;

    public DrawingCanvas() {
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(UIWidth, UIHeight));
        panel.setBackground(Color.BLACK);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setShapeType(ShapeType type) {
        this.type = type;
    }
}
