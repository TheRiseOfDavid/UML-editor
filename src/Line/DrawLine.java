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
    DrawShape s1, s2;
    int p1, p2; // 連接的點

    // TODO: 這邊改用 shape port 去實做抓取節點，不然這樣會抓不到 x1,y1 x2,y2;
    public DrawLine(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    private boolean isConnect(DrawShape s1, DrawShape s2) {
        int flag = 0;
        List<Point> pointsS1 = s1.getPorts();
        for (int i = 0; i < pointsS1.size(); i++) {
            Point point = pointsS1.get(i);
            if (Math.abs(point.x - x1) < 20 && Math.abs(point.y - y1) < 20) {
                x1 = point.x;
                y1 = point.y;
                p1 = i;
                flag++;
                this.s1 = s1;
                break;
            }
        }
        List<Point> pointsS2 = s2.getPorts();
        for (int i = 0; i < pointsS2.size(); i++) {
            Point point = pointsS2.get(i);
            if (Math.abs(point.x - x2) < 20 && Math.abs(point.y - y2) < 20) {
                x2 = point.x;
                y2 = point.y;
                p2 = i;
                this.s2 = s2;
                flag++;
                break;
            }
        }
        System.out.println("connect " + flag);
        if (flag < 2) {
            return false;
        }
        return true;
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
        if (s1 == null || s2 == null) {
            return;
        }
        List<Point> pointsS1 = s1.getPorts();
        List<Point> pointsS2 = s2.getPorts();
        this.x1 = pointsS1.get(p1).x;
        this.y1 = pointsS1.get(p1).y;
        this.x2 = pointsS2.get(p2).x;
        this.y2 = pointsS2.get(p2).y;
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

    @Override
    public void move(int dx, int dy) {
        return;
    };
}
