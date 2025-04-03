package UI;

import javax.swing.*;

import Line.*;
import Shape.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

public class DrawingCanvas extends JPanel {
    static final int UIWidth = 800;
    static final int UIHeight = 700;
    private List<WorkShape> shapes = new ArrayList<>();
    private List<WorkLine> lines = new ArrayList<>();
    // 被選中的 shape
    private List<WorkShape> selectedShape = new ArrayList<>();
    // x1, y1 一開始點擊的位置, x2,y2 拖曳結束的位置
    int x1, y1, x2, y2;
    public Select select = new Select(0, 0, 0, 0);

    // 用於知道我現在要畫哪個圖形
    public enum ShapeType {
        selectMode,
        associationMode,
        generalizationMode,
        compositionMode,
        rectMode,
        ovalMode,
    }

    private ShapeType currentType = ShapeType.selectMode;

    public DrawingCanvas getPanel() {
        return this;
    }

    public void setShapeType(ShapeType type) {
        this.currentType = type;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (WorkShape shape : shapes) {
            // 把 shape 的圖形畫到 g2 上
            shape.PaintShapeTo(g2);
        }
        // 畫被選中的 shape
        for (WorkShape shape : selectedShape) {
            shape.PaintPortTo(g2);
        }

        // 把線給畫出來
        for (WorkLine line : lines) {
            line.PaintTo(g2);
        }
        // 畫 select
        if (select != null) {
            select.PaintTo(g2);
        }

    }

    public DrawingCanvas() {

        setPreferredSize(new Dimension(UIWidth, UIHeight));
        setBackground(Color.BLACK);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                x1 = e.getX();
                y1 = e.getY();
                selectedShape.clear();

                switch (currentType) {
                    case rectMode:
                        shapes.add(new Rect(x1, y1));
                        break;
                    case ovalMode:
                        shapes.add(new Oval(x1, y1));
                        break;
                    case selectMode:
                    case associationMode:
                    case generalizationMode:
                    case compositionMode:
                        for (WorkShape shape : shapes) {
                            if (shape.isSelected(x1, y1)) {
                                selectedShape.add(shape);
                            }
                        }
                        break;
                    default:
                        break;
                }
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                x1 = e.getX();
                y1 = e.getY();
                x2 = x1;
                y2 = y1;
                repaint();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                x2 = e.getX();
                y2 = e.getY();
                for (WorkShape shape : shapes) {
                    if (shape.isSelected(x2, y2)) {
                        selectedShape.add(shape);
                    }
                }

                switch (currentType) {
                    case selectMode:
                        if (select != null) {
                            for (WorkShape shape : shapes) {
                                if (select.isContains(shape)) {
                                    selectedShape.add(shape);
                                }
                            }
                        }

                        select = null;
                        break;
                    case associationMode:
                        Association associate = new Association(x1, y1, x2, y2);
                        if (selectedShape.size() == 2
                                && associate.isValid(selectedShape.get(0), selectedShape.get(1))) {
                            lines.add(associate);
                            System.out.println("line connect!");
                        }
                        break;
                    case generalizationMode:
                        Generalization general = new Generalization(x1, y1, x2, y2);
                        if (selectedShape.size() == 2
                                && general.isValid(selectedShape.get(0), selectedShape.get(1))) {
                            lines.add(general);
                            System.out.println("line connect!");
                        }
                        break;
                    case compositionMode:
                        Composition composite = new Composition(x1, y1, x2, y2);
                        if (selectedShape.size() == 2
                                && composite.isValid(selectedShape.get(0), selectedShape.get(1))) {
                            lines.add(composite);
                            System.out.println("line connect!");
                        }
                        break;
                    default:
                        break;
                }
                repaint();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                x2 = e.getX();
                y2 = e.getY();
                switch (currentType) {
                    case selectMode:
                        select = new Select(x1, y1, x2, y2);
                        break;
                    default:
                        break;
                }
                repaint();
            }
        });

    }

}
