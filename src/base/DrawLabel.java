package base;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import base.DrawModel;
import Shape.DrawShape;

public class DrawLabel {
    public enum ShapeType {
        None,
        Oral,
        Rect,
    }

    protected Color labelColor;
    protected String labelName;
    protected DrawLabel.ShapeType shapeType;
    protected int fontsize;
    protected Shape shape;
    protected int shapeSize = 25;
    private int x, y;

    public DrawLabel(String labelName, DrawLabel.ShapeType shapeType, int fontsize, Color labeColor) {
        this.labelColor = labeColor;
        this.labelName = labelName;
        this.shapeType = shapeType;
        this.fontsize = fontsize;
    }

    public void paintTo(int x, int y, Graphics2D g2) {
        this.x = x + (this.shapeSize / 2);
        this.y = y + (this.shapeSize / 2);

        g2.setColor(this.labelColor);
        if (DrawLabel.ShapeType.Oral == shapeType) {
            this.shape = new Ellipse2D.Double(this.x, this.y, this.shapeSize, this.shapeSize);
        } else if (DrawLabel.ShapeType.Rect == shapeType) {
            this.shape = new Rectangle(this.x, this.y, this.shapeSize, this.shapeSize);
        }
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fill(shape);
        g2.setColor(Color.BLACK);
        g2.draw(shape);
        System.out.println(this.fontsize);
        g2.setFont(new Font("Arial", Font.PLAIN, this.fontsize));
        g2.setColor(Color.WHITE);
        FontMetrics fm = g2.getFontMetrics();
        int textX = this.x + (int) (shape.getBounds2D().getWidth() - fm.stringWidth(this.labelName)) / 2;
        int textY = this.y + (int) (shape.getBounds2D().getHeight() + fm.getAscent()) / 2;
        g2.drawString(this.labelName, textX, textY);
    }
}
