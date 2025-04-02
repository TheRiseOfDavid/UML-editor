package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Sidebar {
    private JPanel panel = new JPanel();
    private List<WorkMode> buttons;
    int width = 0;
    int height = 0;

    public Sidebar(int width, int height) {
        buttons = new ArrayList<>();

        this.panel.setPreferredSize(new Dimension(width, height));
        this.panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
    }

    public JPanel getPanel() {
        return panel;
    }

    public void addButton(WorkMode button) {
        button.addActionListener(new ButtonClickListener());
        buttons.add(button);
        panel.add(button.getButton());
    }

    // 當有 button 被點擊的時候則其他 button 要恢復到原來的狀態
    private class ButtonClickListener implements ActionListener {
        private void resetButtonState() {
            // 遍歷所有按鈕並將它們的狀態設為未選中
            for (WorkMode button : buttons) {
                button.setBackground(Color.GRAY);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            resetButtonState();
            // 這裡可以根據需要更新按鈕的狀態
            // JButton clickedButton = (JButton) e.getSource();
            // clickedButton.setBackground(Color.BLACK);
        }
    }
}
