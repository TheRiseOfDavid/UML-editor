package Shape;

import javax.swing.*;

import Shape.DrawShape;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

public class Rect extends DrawShape {

    public Rect(int x, int y) {
        super(x, y);
        this.shape = new Rectangle(x, y, this.shapeSize, this.shapeSize);
    }

    @Override
    public List<Point> getPorts() {
        List<Point> ports = new ArrayList<>();
        Rectangle bounds = shape.getBounds();

        ports.add(new Point(bounds.x, bounds.y)); // 左上
        ports.add(new Point(bounds.x + bounds.width / 2, bounds.y)); // 上中
        ports.add(new Point(bounds.x + bounds.width, bounds.y)); // 右上
        ports.add(new Point(bounds.x, bounds.y + bounds.height / 2)); // 左中
        ports.add(new Point(bounds.x + bounds.width, bounds.y + bounds.height / 2)); // 右中
        ports.add(new Point(bounds.x, bounds.y + bounds.height)); // 左下
        ports.add(new Point(bounds.x + bounds.width / 2, bounds.y + bounds.height)); // 下中
        ports.add(new Point(bounds.x + bounds.width, bounds.y + bounds.height)); // 右下
        return ports;
    }

    @Override
    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
        this.shape = new Rectangle(this.x, this.y, this.shapeSize, this.shapeSize);
    }
}
