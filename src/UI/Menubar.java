package UI;

import javax.swing.*;

import Shape.Composite;
import Shape.DrawShape;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.*;

import UI.DrawingCanvas;
import UI.DrawingCanvas.ModeType;
import base.DrawLabel;

import java.util.Map;
import java.util.HashMap;

public class Menubar extends JMenuBar {
    JMenu fileMenu = new JMenu("File");
    JMenu editMenu = new JMenu("Edit");
    JMenuItem groupItem = new JMenuItem("Group");
    JMenuItem ungroupItem = new JMenuItem("Ungroup");
    JMenuItem labelItem = new JMenuItem("Label");
    static final int Width = 900;
    static final int Height = 30;

    public void addDialog(JFrame parent, DrawingCanvas canvas) {
        JDialog dialog = new JDialog(parent, "Custom label Style", true);
        dialog.setSize(300, 200);

        dialog.setLocationRelativeTo(parent);
        dialog.setLayout(new BorderLayout());

        // ====== Center Form Panel ======
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(100, 100, 100)); // 深灰底色
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        String[] labels = { "Name", "Shape", "Color", "FontSize" };
        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            formPanel.add(new JLabel(labels[i]), gbc);
        }
        gbc.gridx = 1;
        JTextField nameField = new JTextField(10);
        gbc.gridy = 0;
        formPanel.add(nameField, gbc);

        String[] shapeOptions = { "Rect", "Oral" };
        JComboBox<String> shapeComboBox = new JComboBox<>(shapeOptions);
        gbc.gridy = 1;
        formPanel.add(shapeComboBox, gbc);

        Map<String, Color> colorMap = new HashMap<>();
        colorMap.put("Yellow", Color.YELLOW);
        colorMap.put("Red", Color.RED);
        colorMap.put("Green", Color.GREEN);
        colorMap.put("Blue", Color.BLUE);

        String[] colorOptions = { "Red", "Green", "Blue" };
        JComboBox<String> colorComboBox = new JComboBox<>(colorOptions);
        gbc.gridy = 2;
        formPanel.add(colorComboBox, gbc);

        JTextField fontSizeField = new JTextField(10);
        gbc.gridy = 3;
        formPanel.add(fontSizeField, gbc);

        dialog.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedColorName = (String) colorComboBox.getSelectedItem();
                DrawLabel.ShapeType shapeType = DrawLabel.ShapeType.None;
                if (shapeComboBox.getSelectedItem() == "Rect") {
                    shapeType = DrawLabel.ShapeType.Rect;
                } else if (shapeComboBox.getSelectedItem() == "Oral") {
                    shapeType = DrawLabel.ShapeType.Oral;
                }

                dialog.dispose();
                canvas.executeLabelAction(nameField.getText(), shapeType,
                        colorMap.getOrDefault(selectedColorName, Color.WHITE),
                        Integer.parseInt(fontSizeField.getText()));
            }
        });
        buttonPanel.add(okButton);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
                canvas.repaint();
            }
        });
        buttonPanel.add(cancelButton);
        buttonPanel.setBackground(Color.GRAY);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);

    }

    public Menubar(JFrame parent, DrawingCanvas canvas) {
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

        labelItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.setMenuAction(DrawingCanvas.MenuAction.label);
                // 這是 call Menubar class 的
                addDialog(parent, canvas);
            }
        });

    }

}
