package Shape;

import java.util.ArrayList;
import java.util.List;

import base.DrawModel;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.*;

public class Composite extends DrawShape {
    protected List<DrawModel> models = new ArrayList<>();

    public Composite(List<DrawModel> ModelsInCanvas, List<DrawModel> selectedModels) {
        int x1 = Integer.MAX_VALUE, y1 = Integer.MAX_VALUE, x2 = Integer.MIN_VALUE, y2 = Integer.MIN_VALUE;
        for (DrawModel selectedModel : selectedModels) {
            this.models.add(selectedModel);
            if (selectedModel.getDrawType() == DrawModel.DrawType.Line) {
                continue;
            }
            DrawShape shape = (DrawShape) selectedModel;
            Rectangle bounds = shape.getShape().getBounds();
            x1 = Math.min(x1, bounds.x);
            y1 = Math.min(y1, bounds.y);
            x2 = Math.max(x2, bounds.x + bounds.width);
            y2 = Math.max(y2, bounds.y + bounds.height);
        }
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);
        int width = Math.abs(x1 - x2);
        int height = Math.abs(y1 - y2);
        this.shape = new Rectangle(x, y, width, height);
    }

    public List<Point> getPorts() {
        List<Point> ports = new ArrayList<>();
        return ports;
    }

    public void addShape(DrawModel otherModel) {
        models.add(otherModel);
    }

    // 回傳原本在 composite 的 object
    public List<DrawModel> destory() {
        return this.models;
    }

    @Override
    public Shape getShape() {
        return this.shape;
    }

    @Override
    public boolean isSelected(int x, int y) {
        for (DrawModel model : models) {
            if (model.isSelected(x, y)) {
                this.drawPort = true;
                return true;
            }
        }
        this.drawPort = false;
        return false;
    }

    @Override
    public void draw(Graphics2D g2) {
        for (DrawModel model : models) {
            if (this.drawPort == true) {
                if (model.getDrawType() == DrawModel.DrawType.Shape) {
                    DrawShape shape = (DrawShape) model;
                    shape.isSelected(this.drawPort);
                    shape.draw(g2);
                }
            } else {
                model.draw(g2);
            }

        }
    }
}
