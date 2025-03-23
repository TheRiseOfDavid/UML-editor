package UI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Sidebar {
    private JPanel panel = new JPanel();
    int width = 0;
    int height = 0;

    public Sidebar(int width, int height) {
        this.panel.setPreferredSize(new Dimension(width, height));
        this.panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
    }

    public JPanel getPanel() {
        return panel;
    }

    public void addButton(UMLMode button) {
        panel.add(button.getButton());
    }
}
