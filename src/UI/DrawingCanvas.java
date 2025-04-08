package UI;

import javax.swing.*;

import Line.*;
import Shape.*;
import Shape.Composite;
import base.DrawModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class DrawingCanvas extends JPanel {
    static final int UIWidth = 800;
    static final int UIHeight = 700;
    // 存在於這個 canvas 的 model
    private List<DrawModel> models = new ArrayList<>();
    // 被選中的圖形
    private List<DrawModel> selectedModels = new ArrayList<>();
    // x1, y1 一開始點擊的位置, x2,y2 拖曳結束的位置
    int x1, y1, x2, y2;
    Select select;
    private boolean isDragged = false;

    // 用於知道我現在要畫哪個圖形
    public enum ModeType {
        selectMode,
        associationMode,
        generalizationMode,
        compositionMode,
        rectMode,
        ovalMode,
    }

    private ModeType currentType = ModeType.selectMode;

    // 用於知道我現在要畫哪個圖形
    public enum MenuAction {
        none,
        group,
        ungroup,
        label,
    }

    private MenuAction currMenuAction = MenuAction.none;

    public DrawingCanvas getPanel() {
        return this;
    }

    public void setModeType(ModeType type) {
        this.currentType = type;
    }

    public void setMenuAction(MenuAction action) {
        this.currMenuAction = action;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (currentType == ModeType.selectMode && select != null) {
            select.draw(g2);
        } else {
            select = null;
        }

        // 畫所有的圖形
        for (DrawModel model : models) {
            model.draw(g2);
        }
        // 畫被選中的圖形
        for (DrawModel model : selectedModels) {
            model.draw(g2);
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
                selectedModels.clear();

                switch (currentType) {
                    case rectMode:
                        models.add(new Rect(x1, y1));
                        break;
                    case ovalMode:
                        models.add(new Oval(x1, y1));
                        break;
                    case selectMode:
                    case associationMode:
                    case generalizationMode:
                    case compositionMode:
                        for (DrawModel model : models) {
                            if (model.isSelected(x1, y1)) {
                                selectedModels.add(model);
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
                switch (currentType) {
                    case selectMode:
                        x1 = e.getX();
                        y1 = e.getY();
                        x2 = x1;
                        y2 = y1;
                        break;
                    default:
                        break;
                }
                repaint();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                x2 = e.getX();
                y2 = e.getY();

                // 用於 line mode 去連接第二個物件
                if (currentType != DrawingCanvas.ModeType.selectMode) {
                    for (DrawModel model : models) {
                        if (model.isSelected(x2, y2)) {
                            selectedModels.add(model);
                        }
                    }
                }

                switch (currentType) {
                    case selectMode:
                        if (isDragged == false) {
                            return;
                        }
                        isDragged = false;
                        select = new Select(x1, y1, x2, y2);
                        for (DrawModel model : models) {
                            if (select.isContains(model)) {
                                selectedModels.add(model);
                            }
                        }
                        select = null;
                        break;
                    case associationMode:
                        Association associate = new Association(x1, y1, x2, y2);
                        if (selectedModels.size() == 2
                                && associate.isValid(selectedModels.get(0), selectedModels.get(1))) {
                            models.add(associate);
                            System.out.println("line connect!");
                        }
                        break;
                    case generalizationMode:
                        System.out.println(selectedModels);
                        Generalization general = new Generalization(x1, y1, x2, y2);
                        if (selectedModels.size() == 2
                                && general.isValid(selectedModels.get(0), selectedModels.get(1))) {
                            models.add(general);
                            System.out.println("line connect!");
                        }
                        break;
                    case compositionMode:
                        Composition composite = new Composition(x1, y1, x2, y2);
                        if (selectedModels.size() == 2
                                && composite.isValid(selectedModels.get(0), selectedModels.get(1))) {
                            models.add(composite);
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
                isDragged = true;
                switch (currentType) {
                    case selectMode:
                        if (selectedModels.size() > 0) {
                            for (DrawModel model : selectedModels) {
                                model.move(x2 - x1, y2 - y1);
                            }
                            // 必須重新更新，否則之後拖移的位置會跟基礎點相同
                            x1 = x2;
                            y1 = y2;
                        }
                        select = new Select(x1, y1, x2, y2);
                        break;
                    default:
                        break;
                }
                repaint();
            }
        });
    }

    public void executeGroupAction() {
        // 新增 composite
        if (currentType != ModeType.selectMode)
            return;
        if (currMenuAction != MenuAction.group)
            return;
        Composite composite = new Composite(models, selectedModels);
        System.out.println("Create compositive object ");
        System.out.println(models);
        System.out.println(selectedModels);
        System.out.println(selectedModels.get(0) == models.get(0));

        models.removeAll(selectedModels);
        selectedModels.clear();

        models.add(composite);
        selectedModels.add(composite);
        repaint();
    }

    // 刪除 composite
    public void executeUngroupAction() {
        if (currentType != ModeType.selectMode)
            return;
        if (currMenuAction != MenuAction.ungroup)
            return;
        if (selectedModels.size() != 1)
            return;
        if (!(selectedModels.get(0) instanceof Composite))
            return;

        Composite composite = (Composite) selectedModels.get(0);
        composite.isSelected(false);
        models.addAll(composite.destory());
        models.remove(composite);
        selectedModels.clear();
        repaint();
    }

}
