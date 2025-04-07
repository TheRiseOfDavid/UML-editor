package Line;

import javax.swing.*;

import Shape.DrawShape;
import UI.WorkMode;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

import base.DrawModel;

abstract public class DrawLine implements DrawModel {
    int x1, y1, x2, y2;

    public DrawLine(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    private boolean isConnect(DrawShape s1, DrawShape s2) {
        int flag = 0;
        for (Point point : s1.getPorts()) {
            if (Math.abs(point.x - x1) < 20 && Math.abs(point.y - y1) < 20) {
                x1 = point.x;
                y1 = point.y;
                flag++;
                break;
            }
        }
        for (Point point : s2.getPorts()) {
            if (Math.abs(point.x - x2) < 20 && Math.abs(point.y - y2) < 20) {
                x2 = point.x;
                y2 = point.y;
                flag++;
                break;
            }
        }
        System.out.println("connect " + flag);
        if (flag == 2)
            return true;
        else
            return false;
    }

    public boolean isValid(DrawModel s1, DrawModel s2) {
        if (s1.getDrawType() != DrawModel.DrawType.Shape || s2.getDrawType() != DrawModel.DrawType.Shape)
            return false;
        if (s1 == null || s2 == null || s1 == s2)
            return false;
        return isConnect((DrawShape) s1, (DrawShape) s2);
    }

    abstract public void PaintTo(Graphics2D g2);

    @Override
    public void draw(Graphics2D g2) {
        this.PaintTo(g2);
    };

    @Override
    public boolean isSelected(int x, int y) {
        return false;
    };

    @Override
    public DrawModel.DrawType getDrawType() {
        return DrawModel.DrawType.Line;
    }
}
