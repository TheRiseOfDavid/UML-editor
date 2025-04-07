package base;

import java.awt.*;

public interface DrawModel {
    enum DrawType {
        Shape,
        Line,
    }

    void draw(Graphics2D g2);

    boolean isSelected(int x, int y);

    DrawType getDrawType();

};
