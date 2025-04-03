package Line;

import javax.swing.*;

import Shape.WorkShape;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.geom.*;

public class Generalization extends WorkLine {
    public Generalization(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    @Override
    public void PaintTo(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(2));
        g2.drawLine(this.x1, this.y1, this.x2, this.y2);

        // 數學的力量，可以看
        // https://stackoverflow.com/questions/2027613/how-to-draw-a-directed-arrow-line-in-java
        // d 與 h 是寬度
        int d = 10, h = 10;
        int dx = x2 - x1, dy = y2 - y1;
        double D = Math.sqrt(dx * dx + dy * dy);
        double xm = D - d, xn = xm, ym = h, yn = -h, x;
        double sin = dy / D, cos = dx / D;

        x = xm * cos - ym * sin + x1;
        ym = xm * sin + ym * cos + y1;
        xm = x;

        x = xn * cos - yn * sin + x1;
        yn = xn * sin + yn * cos + y1;
        xn = x;

        int[] xPoints = { x2, (int) xm, (int) xn };
        int[] yPoints = { y2, (int) ym, (int) yn };

        g2.fillPolygon(xPoints, yPoints, 3);

        // 畫箭頭邊框
        g2.setColor(Color.GRAY);
        g2.drawPolygon(xPoints, yPoints, 3);
    }

}
