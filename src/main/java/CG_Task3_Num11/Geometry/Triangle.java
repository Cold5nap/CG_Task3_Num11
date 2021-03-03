package CG_Task3_Num11.Geometry;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

import java.util.Arrays;

public class Triangle extends CoordinatePlane{
    private final Point2D vertex;
    private final Point2D bottom1;
    private final Point2D bottom2;

    public Triangle(GraphicsContext g, Point2D vertex, Point2D bottom1, Point2D bottom2) {
        super(g);
        this.vertex = vertex;
        this.bottom1 = bottom1;
        this.bottom2 = bottom2;
    }

    private Bresenham_Line line1;
    private Bresenham_Line line2;
    private Bresenham_Line line3;


    public void draw() {

        line1 = new Bresenham_Line(g, vertex, bottom1);
        line1.draw();
        line2 = new Bresenham_Line(g, vertex, bottom2);
        line2.draw();
        line3 = new Bresenham_Line(g, bottom2, bottom1);
        line3.draw();

        addCoordinates(line1.getCoordinates());
        addCoordinates(line2.getCoordinates());
        addCoordinates(line3.getCoordinates());
    }

    public void zoom(int scale) {
        line1.zoom(scale);
        line2.zoom(scale);
        line3.zoom(scale);
    }

    public void move(int deltaX,int deltaY) {
        line1.move(deltaX,deltaY);
        line2.move(deltaX,deltaY);
        line3.move(deltaX,deltaY);
    }


    private void drawPx(int x, int y) {
        g.fillRect(x, y, 1, 1);
    }
}
