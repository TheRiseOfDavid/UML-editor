package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.*;
import Action.*; 

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
    Map<String, MenuCommand> commandMap = new HashMap<>();


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

        groupItem.addActionListener(new MenuActionListener());
        groupItem.setActionCommand("group");
        ungroupItem.addActionListener(new MenuActionListener());
        ungroupItem.setActionCommand("ungroup");
        labelItem.addActionListener(new MenuActionListener());
        labelItem.setActionCommand("label");

        commandMap.put("group", new GroupCommand(canvas));
        commandMap.put("ungroup", new UnGroupCommand(canvas));
        commandMap.put("label", new LabelCommand(canvas, dialog));
    }

    private class MenuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cmdName = e.getActionCommand(); 
            MenuCommand cmd = commandMap.get(cmdName);
            if (cmd != null) {
                cmd.execute();
            }
        }
    }
}

