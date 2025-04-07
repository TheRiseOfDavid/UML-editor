package Line;

import javax.swing.*;

import Shape.DrawShape;
import base.DrawModel;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

public class Select extends DrawLine {
    private Shape shape;

    public Select(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);
        int width = Math.abs(x1 - x2);
        int height = Math.abs(y1 - y2);
        this.shape = new Rectangle(x, y, width, height);
    }

    public boolean isContains(DrawModel otherModel) {
        if (otherModel.getDrawType() != DrawModel.DrawType.Shape) {
            return false;
        }
        DrawShape otherShape = (DrawShape) otherModel;
        if (!shape.contains(otherShape.getShape().getBounds2D())) {
            return false;
        }
        otherShape.isSelected(true);
        return true;
    }

    @Override
    public void PaintTo(Graphics2D g2) {
        // 畫圖形
        g2.setColor(Color.WHITE);
        // 讓圖形不會出現鋸齒狀
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.draw(shape);
        return;
    }
}
