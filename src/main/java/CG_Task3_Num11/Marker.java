package CG_Task3_Num11;

import CG_Task3_Num11.Geometry.Bresenham_Line;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class Marker extends Canvas {

    public Marker(int width, int height) {
        setWidth(width);
        setHeight(height);
        Rectangle r = new Rectangle(100, 100);

        setOnMouseClicked(e -> {
            int oldWidth = (int) getWidth();
            int oldHeight = (int) getHeight();
            int oldX = (int) e.getX();
            int oldY = (int) e.getY();
            setOnMouseDragged(e2 -> {
                int newX = (int) e2.getX();
                int newY = (int) e2.getY();
                int dX = newX - oldX;
                int dY = newY - oldY;
                resize(oldWidth + dX, oldHeight + dY);
            });
        });


    }
    void drawMarker(){
        GraphicsContext gc = getGraphicsContext2D();
        for (int i = 0; i < 20; i++) {
            Bresenham_Line l0 = new Bresenham_Line(gc, new Point2D(i,0),
                    new Point2D(i,getHeight() / 5));
            Bresenham_Line l1 = new Bresenham_Line(gc, new Point2D(0,i),
                    new Point2D(getWidth()/5,i));
            l0.draw();
            l1.draw();
        }

    }
}
