package base;

import java.awt.*;

public interface DrawModel {
    enum DrawType {
        Shape,
        Line,
    }

    void draw(Graphics2D g2);

    void move(int dx, int dy);

    boolean isSelected(int x, int y);

    DrawType getDrawType();

};
