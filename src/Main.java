import javax.swing.*;

import UI.Sidebar;
import UI.UMLMode;

import java.awt.*;
import java.io.File;

public class Main {
    static final int UIWidth = 900;
    static final int UIHeight = 600;

    public static void main(String[] args) throws Exception {

        JFrame frame = new JFrame("UML Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(UIWidth, UIHeight);

        UMLMode selectMode = new UMLMode("./src/img/cursor.png");
        UMLMode associationMode = new UMLMode("./src/img/cursor.png");
        UMLMode generalizationMode = new UMLMode("./src/img/cursor.png");
        UMLMode compositionMode = new UMLMode("./src/img/cursor.png");
        UMLMode rectMode = new UMLMode("./src/img/cursor.png");
        UMLMode ovalMode = new UMLMode("./src/img/cursor.png");
        Sidebar sidebar = new Sidebar(100, UIHeight);
        sidebar.addButton(selectMode);
        sidebar.addButton(associationMode);
        sidebar.addButton(generalizationMode);
        sidebar.addButton(compositionMode);
        sidebar.addButton(rectMode);
        sidebar.addButton(ovalMode);

        frame.setLayout(new BorderLayout());
        frame.add(sidebar.getPanel(), BorderLayout.WEST);
        frame.setVisible(true);
    }
}
