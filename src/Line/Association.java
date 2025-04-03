package Line;

import javax.swing.*;

import Shape.WorkShape;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

public class Association extends WorkLine {
    public Association(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    @Override
    public void PaintTo(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(2));
        g2.drawLine(this.x1, this.y1, this.x2, this.y2);
    }

}
