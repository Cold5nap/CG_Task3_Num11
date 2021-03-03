package CG_Task3_Num11;

import CG_Task3_Num11.Geometry.Sun;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;

import java.util.concurrent.atomic.AtomicInteger;

public class Picture extends Canvas {
    private final Slider zoom;

    public Picture(Slider zoom) {
        this.zoom = zoom;
    }

    public void actionZoom(Sun sun) {

        AtomicInteger a = new AtomicInteger(50);
        zoom.setOnMouseDragged(e -> {
            double scale =1;
            int value = (int) zoom.getValue();
            if (a.get() < value) {
                scale = 1 + 0.05;
            } else {
                if (a.get() > value)
                scale = 1 - 0.05;
            }

            sun.zoom(scale);
            a.set(value);

        });


    }

    public void drawSun() {
        setHeight(400);
        setWidth(700);
        Point2D center = new Point2D(getWidth() / 2, getHeight() / 2);
        int degAngle = 0;
        int radiusInside = 100;
        int radiusOutside = 200;
        double radians = Math.toRadians(degAngle);
        int numberRays = 12;

        Sun sun = new Sun(getGraphicsContext2D(), center, radiusInside, radiusOutside, radians, numberRays);
        sun.setColor(Color.ORANGE);
        sun.draw();
        actionSun(sun);
        actionZoom(sun);
    }

    public void actionSun(Sun sun) {

        sun.setCenter((int) sun.getCenter().getX(), (int) sun.getCenter().getY());
        setOnMouseDragged((e1) -> {

            int oldX = sun.getCenterX();
            int oldY = sun.getCenterY();
            int newX = (int) e1.getX();
            int newY = (int) e1.getY();
            sun.move(newX - oldX, newY - oldY);

        });
    }
}
