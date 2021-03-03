package CG_Task3_Num11.Geometry;


import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class Bresenham_Circle extends CoordinatePlane {
    private Point2D center;
    private int r;

    public Bresenham_Circle(GraphicsContext g, Point2D center, int r) {
        super(g);
        this.center = center;
        this.r = r;
    }

    public Point2D getCenter() {
        return center;
    }

    // выделение памяти под массив с координатами
    private void memoryAllocationCoordinates() {
        int square = (int) Math.pow((r * 2), 2);
        setCoordinates(new int[2][square]);
    }

    public void setR(int r) {
        this.r = r;
    }

    public void draw() {
        build();
        displayPixels();
    }

    public void build() {
        memoryAllocationCoordinates();
        int x = r;
        int y = 0;
        int radiusError = 1 - x;
        int x0 = (int) center.getX();
        int y0 = (int) center.getY();
        while (x >= y) {
            //addCoordinate(x + x0, y + y0);
            fillLine(x + x0, y + y0, x0);

            //addCoordinate(y + x0, x + y0);
            fillLine(y + x0, x + y0, x0);

            //addCoordinate(-x + x0, y + y0);
            fillLine(-x + x0, y + y0, x0);

           // addCoordinate(-y + x0, x + y0);
            fillLine(-y + x0, x + y0, x0);

           // addCoordinate(-x + x0, -y + y0);
            fillLine(-x + x0, -y + y0, x0);

           // addCoordinate(-y + x0, -x + y0);
            fillLine(-y + x0, -x + y0, x0);

            //addCoordinate(x + x0, -y + y0);
            fillLine(x + x0, -y + y0, x0);

            //addCoordinate(y + x0, -x + y0);
            fillLine(y + x0, -x + y0, x0);

            y++;
            if (radiusError < 0) {
                radiusError += 2 * y + 1;
            } else {
                x--;
                radiusError += 2 * (y - x + 1);
            }
        }
    }


    private void fillLine(int x, int y, int x0) {
        if (x >= x0) {
            for (int b = x; b >= x0; b--) {
                addCoordinate(b, y);
            }
        } else {
            for (int b = x; b <= x0; b++) {
                addCoordinate(b, y);
            }
        }

    }
}
