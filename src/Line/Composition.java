package Line;

import javax.swing.*;

import Shape.DrawShape;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.geom.*;

public class Composition extends DrawLine {
    public Composition(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    @Override
    public void PaintTo(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(2));
        g2.drawLine(this.x1, this.y1, this.x2, this.y2);

        int[] xPoints = { x2, x2 + 10, x2, x2 - 10 };
        int[] yPoints = { y2 - 10, y2, y2 + 10, y2 };
        g2.fillPolygon(xPoints, yPoints, 4);

        // 畫箭頭邊框
        g2.setColor(Color.GRAY);
        g2.drawPolygon(xPoints, yPoints, 4);
    }

}
