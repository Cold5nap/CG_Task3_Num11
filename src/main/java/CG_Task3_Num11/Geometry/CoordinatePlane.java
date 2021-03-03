package CG_Task3_Num11.Geometry;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.security.PrivateKey;
import java.util.Arrays;

public class CoordinatePlane {
    GraphicsContext g;
    private int[][] coordinates;
    private int lastIndex = 0;
    private int centerX;
    private int centerY;

    public CoordinatePlane(GraphicsContext g) {
        this.g = g;
    }

    void drawMarker(){
        Polygon marker = new Polygon();

    }

    public void setCenter(int centerX,int centerY) {
        this.centerX = centerX;
        this.centerY = centerY;
    }
    public int getCenterX(){
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setColor(Color color) {
        g.setFill(color);
    }

    public int[][] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(int[][] coordinates) {
        this.coordinates = coordinates;
    }

    public void setLastIndex(int lastIndex) {
        this.lastIndex = lastIndex;
    }

    private void drawPixel(int x, int y) {
        g.fillRect(x, y, 1, 1);
    }

    void displayPixels() {
        for (int i = 0; i < lastIndex; i++) {
            drawPixel(coordinates[0][i], coordinates[1][i]);
        }
    }

    void addCoordinate(int x, int y) {
        coordinates[0][lastIndex] = x;
        coordinates[1][lastIndex] = y;
        lastIndex++;
    }

    void addCoordinates(int[][] array) {
        int lastIndex = 0;
        int oldSize = 0;
        if (this.lastIndex != 0) {
            oldSize = this.lastIndex;
        }
        int size = oldSize + array[0].length;
        int[][] a = new int[2][size];
        for (int i = 0; i < oldSize; i++) {
            a[0][lastIndex] = coordinates[0][i];
            a[1][lastIndex] = coordinates[1][i];
            lastIndex++;
        }
        for (int i = 0; i < array[0].length; i++) {
            a[0][lastIndex] = array[0][i];
            a[1][lastIndex] = array[1][i];
            lastIndex++;
        }
        this.lastIndex = lastIndex;
        coordinates = a;
    }

    public void move(int deltaX, int deltaY) {
        centerX+=deltaX;
        centerY+=deltaY;

        clearPixels();
        for (int i = 0; i < lastIndex; i++) {
            coordinates[0][i] += deltaX;
            coordinates[1][i] += deltaY;
        }
        displayPixels();
    }

    public void zoom(double scale) {
        clearPixels();
        for (int i = 0; i < lastIndex; i++) {
            coordinates[0][i] = (int) (scale*(coordinates[0][i]-centerX)+centerX);
            coordinates[1][i] = (int) (scale*(coordinates[1][i]-centerY)+centerY);
        }
        displayPixels();
    }

    public void zoom(double scale , int [][]coordinates) {
        clearPixels();
        for (int i = 0; i < lastIndex; i++) {
            coordinates[0][i] = (int) (scale*(coordinates[0][i]-centerX)+centerX);
            coordinates[1][i] = (int) (scale*(coordinates[1][i]-centerY)+centerY);
        }
        displayPixels();
    }

    private void clearPixel(int x, int y) {
        g.clearRect(x, y, 1, 1);
    }

    public void clearPixels() {
        for (int i = 0; i < lastIndex; i++) {
            clearPixel(coordinates[0][i], coordinates[1][i]);
        }
    }

}
