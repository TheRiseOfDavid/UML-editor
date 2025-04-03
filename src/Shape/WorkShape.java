package Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

abstract public class WorkShape {
    protected Shape shape;
    protected int shapeSize = 50;

    public Shape getShape() {
        return this.shape;
    }

    public boolean isSelected(int x, int y) {
        if (this.shape.contains(x, y)) {
            return true;
        }
        return false;
    }

    abstract public List<Point> getPorts();

    public void PaintShapeTo(Graphics2D g2) {
        // 畫圖形
        g2.setColor(Color.WHITE);
        // 讓圖形不會出現鋸齒狀
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fill(shape);

    }

    public void PaintPortTo(Graphics2D g2) {
        // 畫 port
        g2.setColor(new Color(204, 153, 51)); // 土黃色
        for (Point point : this.getPorts()) {
            g2.fillRect(point.x - 4, point.y - 4, 8, 8);
        }
    }

}
