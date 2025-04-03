package Shape;

import javax.swing.*;

import Shape.WorkShape;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

public class Oval extends WorkShape {

    public Oval(int x, int y) {
        this.shape = new Ellipse2D.Double(x, y, this.shapeSize, this.shapeSize);
    }

    @Override
    public List<Point> getPorts() {
        List<Point> ports = new ArrayList<>();
        Ellipse2D ellipse = (Ellipse2D) shape;

        // 計算圓心
        double centerX = ellipse.getCenterX();
        double centerY = ellipse.getCenterY();
        double radiusX = ellipse.getWidth() / 2;
        double radiusY = ellipse.getHeight() / 2;

        // 這四個點是圓形的左、右、上、下端點
        ports.add(new Point((int) (centerX - radiusX), (int) centerY)); // 左
        ports.add(new Point((int) (centerX + radiusX), (int) centerY)); // 右
        ports.add(new Point((int) centerX, (int) (centerY - radiusY))); // 上
        ports.add(new Point((int) centerX, (int) (centerY + radiusY))); // 下
        return ports;
    }
}
