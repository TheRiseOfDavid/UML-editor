package UI;

import javax.swing.*;

import Shape.Composite;
import Shape.DrawShape;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.*;

import UI.DrawingCanvas;
import UI.DrawingCanvas.ModeType;
import base.DrawLabel;

public class Dialog extends JDialog {
    DrawingCanvas canvas;
    JFrame parent;

    JTextField nameField;
    String[] labels = { "Name", "Shape", "Color", "FontSize" };

    String[] colorOptions = { "Yellow", "Red", "Green", "Blue" };
    JComboBox<String> colorComboBox;
    Map<String, Color> colorMap = new HashMap<>();
    String[] shapeOptions = { "Rect", "Oral" };
    JComboBox<String> shapeComboBox;
    JTextField fontSizeField;

    JPanel buttonPanel;
    JButton okButton;
    JButton cancelButton;

    public Dialog(JFrame parent, DrawingCanvas canvas) {
        super(parent, "Custom label Style", true);
        this.parent = parent;
        this.canvas = canvas;

        this.setSize(300, 200);
        this.setLayout(new BorderLayout());

        // 建立底板
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(100, 100, 100)); // 深灰底色
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 定義可被改變的 color
        colorMap.put("Yellow", Color.YELLOW);
        colorMap.put("Red", Color.RED);
        colorMap.put("Green", Color.GREEN);
        colorMap.put("Blue", Color.BLUE);

        // 建立 UI
        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            formPanel.add(new JLabel(labels[i]), gbc);
        }
        gbc.gridx = 1;
        nameField = new JTextField(10);
        gbc.gridy = 0;
        formPanel.add(nameField, gbc);

        this.shapeComboBox = new JComboBox<>(shapeOptions);
        gbc.gridy = 1;
        formPanel.add(shapeComboBox, gbc);

        colorComboBox = new JComboBox<>(colorOptions);
        gbc.gridy = 2;
        formPanel.add(colorComboBox, gbc);

        fontSizeField = new JTextField(10);
        gbc.gridy = 3;
        formPanel.add(fontSizeField, gbc);

        this.add(formPanel, BorderLayout.CENTER);

        buttonPanel = new JPanel();
        okButton = new JButton("OK");

        buttonPanel.add(okButton);
        cancelButton = new JButton("Cancel");

        buttonPanel.add(cancelButton);
        buttonPanel.setBackground(Color.GRAY);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setVisible(false);
    }

    private class OkButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedColorName = (String) colorComboBox.getSelectedItem();
            DrawLabel.ShapeType shapeType = DrawLabel.ShapeType.None;
            if (shapeComboBox.getSelectedItem() == "Rect") {
                shapeType = DrawLabel.ShapeType.Rect;
            } else if (shapeComboBox.getSelectedItem() == "Oral") {
                shapeType = DrawLabel.ShapeType.Oral;
            }

            dispose();
            canvas.executeLabelAction(nameField.getText(), shapeType,
                    colorMap.getOrDefault(selectedColorName, Color.WHITE),
                    Integer.parseInt(fontSizeField.getText()));
        }
    }

    private class CancelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

    public void display() {
        this.setLocationRelativeTo(parent); // 當被按下按鈕後，在決定在哪個地方出現
        okButton.addActionListener(new OkButtonListener());
        cancelButton.addActionListener(new CancelButtonListener());
        this.setVisible(true); // 顯示對話框
    }

}
