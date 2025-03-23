package UI;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class UMLMode {
    String imgPath;
    int width = 90;
    int height = 90;
    private JButton button;

    public UMLMode(String imgPath) throws Exception {
        if (!this.CheckImage(imgPath)) {
            throw new Exception("the img path doesn't exists.");
        }
        button = new JButton();
        button.setLayout(new BorderLayout());
        button.setPreferredSize(new Dimension(width, height));
        button.setMargin(new Insets(0, 0, 0, 0));

        Image img = new ImageIcon(imgPath).getImage().getScaledInstance(100, 100,
                Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(img));
    }

    public JButton getButton() {
        return this.button;
    }

    private boolean CheckImage(String imgPath) {
        File cursorImageFile = new File(imgPath);
        if (cursorImageFile.exists()) {
            return true;
        }
        return false;
    }

}
