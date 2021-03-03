package CG_Task3_Num11.Geometry;


import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;


public class Bresenham_Line extends CoordinatePlane{

    private final Point2D p1;
    private final Point2D p2;

    public Bresenham_Line(GraphicsContext gc, Point2D p1, Point2D p2) {
        super(gc);
        this.p1 = p1;
        this.p2 = p2;
    }
    public void draw() {
        buildSelection((int) p1.getX(), (int) p2.getX(), (int) p1.getY(), (int) p2.getY());
        displayPixels();
    }

    private void buildSelection(int x1, int x2, int y1, int y2) {
        int dx_abs = Math.abs(x1 - x2);
        int dy_abs = Math.abs(y1 - y2);
        if (dx_abs > dy_abs) {
            setCoordinates(new int[2][dx_abs]);
            createHorizontalCoordinates(x1, x2, y1, y2);
        } else {
            setCoordinates(new int[2][dy_abs]);
            createVerticalCoordinates(x1, x2, y1, y2);
        }
    }

    private void createHorizontalCoordinates(int x1, int x2, int y1, int y2) {
        if (x2 < x1) {
            int temp = y2;
            y2 = y1;
            y1 = temp;
            temp = x2;
            x2 = x1;
            x1 = temp;
        }
        int dir_y = y2 - y1;
        if (dir_y > 0) dir_y = 1;
        else dir_y = -1;

        int dx_abs = Math.abs(x1 - x2);
        int dy_abs = Math.abs(y1 - y2);
        int error = 0;
        int dErr = (dy_abs + 1);
        int y = y1;

        for (int x = x1; x < x2; x++) {
            addCoordinate(x, y);
            error += dErr;
            if (error >= (dx_abs + 1)) {
                y += dir_y;
                error -= (dx_abs + 1);
            }
        }
    }

    private void createVerticalCoordinates(int x1, int x2, int y1, int y2) {
        if (y2 < y1) {
            int temp = y2;
            y2 = y1;
            y1 = temp;
            temp = x2;
            x2 = x1;
            x1 = temp;
        }
        int dir_x = x2 - x1;
        if (dir_x > 0) dir_x = 1;
        else dir_x = -1;

        int dx_abs = Math.abs(x1 - x2);
        int dy_abs = Math.abs(y1 - y2);
        int error = 0;
        int dErr = (dx_abs + 1);
        int x = x1;

        for (int y = y1; y < y2; y++) {
            addCoordinate(x, y);
            error += dErr;
            if (error >= (dy_abs + 1)) {
                x += dir_x;
                error -= (dy_abs + 1);
            }
        }
    }

}
