package UI;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;

public class Menubar extends JMenuBar {
    JMenu fileMenu = new JMenu("File");
    JMenu editMenu = new JMenu("Edit");
    JMenuItem groupItem = new JMenuItem("Group");
    JMenuItem ungroupItem = new JMenuItem("Ungroup");
    JMenuItem labelItem = new JMenuItem("Label");
    Dialog dialog;
    static final int Width = 900;
    static final int Height = 30;

    public Menubar(JFrame parent, DrawingCanvas canvas) {
        this.add(fileMenu);
        this.add(editMenu);
        this.setPreferredSize(new Dimension(Width, Height)); // 設定高 50, 寬 900
        this.setBackground(new Color(200, 200, 200)); // 設定背景色 (淺灰色)

        editMenu.add(groupItem);
        editMenu.add(ungroupItem);
        editMenu.add(labelItem);
        dialog = new Dialog(parent, canvas);

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

        labelItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.setMenuAction(DrawingCanvas.MenuAction.label);
                // 這是 call Menubar class 的
                dialog.display();
            }
        });

    }

}
