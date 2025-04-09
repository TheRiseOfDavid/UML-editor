package UI;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;

public class Menubar extends JMenuBar {
    private JMenu fileMenu = new JMenu("File");
    private JMenu editMenu = new JMenu("Edit");
    private JMenuItem groupItem = new JMenuItem("Group");
    private JMenuItem ungroupItem = new JMenuItem("Ungroup");
    private JMenuItem labelItem = new JMenuItem("Label");
    private Dialog dialog;
    private static final int Width = 900;
    private static final int Height = 30;
    private JFrame parent;
    private DrawingCanvas canvas;

    public Menubar(JFrame parent, DrawingCanvas canvas) {
        this.add(fileMenu);
        this.add(editMenu);
        this.setPreferredSize(new Dimension(Width, Height)); // 設定高 50, 寬 900
        this.setBackground(new Color(200, 200, 200)); // 設定背景色 (淺灰色)
        this.parent = parent;
        this.canvas = canvas;

        editMenu.add(groupItem);
        editMenu.add(ungroupItem);
        editMenu.add(labelItem);
        dialog = new Dialog(parent, canvas);

        groupItem.addActionListener(new GroupItemListener());
        ungroupItem.addActionListener(new UngroupItemListener());
        labelItem.addActionListener(new LabelItemListener());
    }

    private class GroupItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            canvas.setMenuAction(DrawingCanvas.MenuAction.group);
            canvas.executeGroupAction();
        }
    }

    private class UngroupItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            canvas.setMenuAction(DrawingCanvas.MenuAction.ungroup);
            canvas.executeUngroupAction();
        }
    }

    private class LabelItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            canvas.setMenuAction(DrawingCanvas.MenuAction.label);
            // 這是 call Menubar class 的
            dialog.display();
        }
    }

}
