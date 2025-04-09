package Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import base.DrawLabel;
import base.DrawModel;

abstract public class DrawShape implements DrawModel {
    protected Shape shape;
    protected DrawLabel label;
    protected int x, y;
    protected int shapeSize = 50;
    protected boolean drawPort = false;
    protected boolean drawLabel = false;

    public DrawShape(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Shape getShape() {
        return this.shape;
    }

    @Override
    public boolean isSelected(int x, int y) {
        if (this.shape.contains(x, y)) {
            return true;
        }
        return false;
    }

    public void isSelected(boolean b) {
        this.drawPort = b;
    }

    abstract public List<Point> getPorts();

    public void paintShapeTo(Graphics2D g2) {
        // 畫圖形
        g2.setColor(Color.WHITE);
        // 讓圖形不會出現鋸齒狀
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fill(shape);
    }

    public void paintPortTo(Graphics2D g2) {
        // 畫 port
        g2.setColor(new Color(204, 153, 51)); // 土黃色
        for (Point point : this.getPorts()) {
            g2.fillRect(point.x - 4, point.y - 4, 8, 8);
        }
    }

    public void setLabel(DrawLabel drawLabel) {
        this.label = drawLabel;
    }

    public void paintLabelTo(Graphics2D g2) {
        label.paintTo(this.x, this.y, g2);
    }

    @Override
    public void draw(Graphics2D g2) {
        this.paintShapeTo(g2);
        if (this.drawPort) {
            this.paintPortTo(g2);
            this.drawPort = false;
        }
        if (this.label != null) {
            System.out.println(label);
            this.paintLabelTo(g2);
        }

    };

    @Override
    public DrawModel.DrawType getDrawType() {
        return DrawModel.DrawType.Shape;
    }

    @Override
    abstract public void move(int dx, int dy);

}
