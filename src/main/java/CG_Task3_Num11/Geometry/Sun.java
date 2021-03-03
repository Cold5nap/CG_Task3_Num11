package CG_Task3_Num11.Geometry;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

import java.util.Arrays;

public class Sun extends CoordinatePlane {

    private final Point2D center;
    private int radiusInside;
    private int radiusOutside;
    private double radAngle;
    private int rays;
    private int [][] originCoordinates;


    public Sun(GraphicsContext g, Point2D center, int radiusInside, int radiusOutSide, double radAngle, int rays) {
        super(g);
        this.center = center;
        this.radiusInside = radiusInside;
        this.radiusOutside = radiusOutSide;
        this.radAngle = radAngle;
        this.rays = rays;
        //setCenter();
    }

    public Point2D getCenter(){
        return center;
    }

    public void setRadiusInside(int radiusInside) {
        this.radiusInside = radiusInside;
    }

    public void setRadiusOutside(int radiusOutside) {
        this.radiusOutside = radiusOutside;
    }

    public void zoom(double scale) {
        CoordinatePlane cp = new CoordinatePlane(g);
        cp.setCoordinates(Arrays.copyOf(originCoordinates,originCoordinates.length));
        cp.setLastIndex(originCoordinates[0].length);
        //cp.setCenter(getCenterX(),getCenterY());
        cp.zoom(scale);

    }

    public void draw() {

        Bresenham_Circle circle = new Bresenham_Circle(g, center, radiusInside);
        Triangle triangle;
        circle.draw();
        addCoordinates(circle.getCoordinates());

        int x0 = (int) center.getX();
        int y0 = (int) center.getY();
        double angleBetweenVertices = 2 * Math.PI / rays;
        double angleBetweenBottom = angleBetweenVertices / 6;
        int indent = (radiusOutside - radiusInside) / 8;


        double angleBegin = radAngle;
        for (int v = 0; v < rays; v++) {
            Point2D vertex = pointOnCircle(x0, y0, radiusOutside, angleBegin);
            Point2D bottom1 = pointOnCircle(x0, y0, radiusInside + indent,
                    angleBegin + angleBetweenBottom);
            Point2D bottom2 = pointOnCircle(x0, y0, radiusInside + indent
                    , angleBegin - angleBetweenBottom);
            triangle = new Triangle(g, vertex, bottom1, bottom2);
            triangle.draw();
            addCoordinates(triangle.getCoordinates());
            angleBegin += angleBetweenVertices;
        }
        //setCenter((int)center.getX(), (int)center.getY());
        originCoordinates = Arrays.copyOf(getCoordinates(),getCoordinates().length);
    }


    private Point2D pointOnCircle(int x0, int y0, int r, double angle) {
        int x = (int) (x0 + r * Math.cos(angle));
        int y = (int) (y0 + r * Math.sin(angle));
        return new Point2D(x, y);
    }
}
