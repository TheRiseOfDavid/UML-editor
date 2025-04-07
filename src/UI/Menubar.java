package UI;

import javax.swing.*;

import Shape.Composite;
import Shape.DrawShape;

import java.awt.Dimension;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import UI.DrawingCanvas;

public class Menubar extends JMenuBar {
    JMenu fileMenu = new JMenu("File");
    JMenu editMenu = new JMenu("Edit");
    JMenuItem groupItem = new JMenuItem("Group");
    JMenuItem ungroupItem = new JMenuItem("Ungroup");
    JMenuItem labelItem = new JMenuItem("Label");
    static final int Width = 900;
    static final int Height = 30;

    public Menubar(DrawingCanvas canvas) {
        this.add(fileMenu);
        this.add(editMenu);
        this.setPreferredSize(new Dimension(Width, Height)); // 設定高 50, 寬 900
        this.setBackground(new Color(200, 200, 200)); // 設定背景色 (淺灰色)

        editMenu.add(groupItem);
        editMenu.add(ungroupItem);
        editMenu.add(labelItem);

        groupItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.setMenuAction(DrawingCanvas.MenuAction.group);
                canvas.executeGroupAction();
            }
        });

        ungroupItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.setMenuAction(DrawingCanvas.MenuAction.ungroup);
                canvas.executeUngroupAction();
            }
        });
    }

}
