package UI;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.awt.event.*;

public class WorkMode extends JButton {
    private String imgPath;
    private int width = 90;
    private int height = 90;
    private DrawingCanvas canvas;
    private DrawingCanvas.ModeType type;

    public WorkMode(String imgPath, DrawingCanvas canvas, DrawingCanvas.ModeType type) throws Exception {
        // 設定 button UI
        if (!this.CheckImage(imgPath)) {
            throw new Exception("the img path doesn't exists.");
        }

        this.canvas = canvas;
        this.type = type;
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(width, height));
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setBackground(Color.GRAY);
        Image img = new ImageIcon(imgPath).getImage().getScaledInstance(100, 100,
                Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(img));

        // canvas 要畫的圖形
        this.addActionListener(new WorkModeListener());
    }

    public JButton getButton() {
        return this;
    }

    private boolean CheckImage(String imgPath) {
        File cursorImageFile = new File(imgPath);
        if (cursorImageFile.exists()) {
            return true;
        }
        return false;
    }

    private class WorkModeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            canvas.setModeType(type);
            System.out.println("type is " + type);
            setBackground(Color.BLACK);
        }
    }

}
