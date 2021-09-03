import java.lang.*;

class Ponto {
    private double x;
    private double y;
    private int id;
    private static int nextID = 0;

    Ponto() {
        x = 0;
        y = 0;
        id = nextID++;
    }

    Ponto(double x, double y) {
        this.x = x;
        this.y = y;
        id = nextID++;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public int getID() {
        return this.id;
    }

    public static int getNextID() {
        return nextID;
    }

    public double dist(double x, double y) {
        double dx = this.getX() - x;
        double dy = this.getY() - y;

        return Math.sqrt(Math.pow(dx, 2.0) + Math.pow(dy, 2.0));
    }

    public static double dist(double x1, double x2, double y1, double y2) {
        double dx = x1 - x2;
        double dy = y1 - y2;

        return Math.sqrt(Math.pow(dx, 2.0) + Math.pow(dy, 2.0));
    }

    public double dist(Ponto p) {
        double dx = this.getX() - p.getX();
        double dy = this.getY() - p.getY();

        return Math.sqrt(Math.pow(dx, 2.0) + Math.pow(dy, 2.0));
    }

    public static boolean isTriangulo(Ponto p1, Ponto p2, Ponto p3) {
        double d1 = p1.dist(p2);
        double d2 = p1.dist(p3);
        double d3 = p2.dist(p3);

        return (d1 + d2 > d3 || d1 + d3 > d2 || d2 + d3 > d1);
    }

    public double getAreaRetangulo(Ponto p) {
        double a = Math.abs(this.getX() - p.getX());
        double b = Math.abs(this.getY() - p.getY());

        return a * b;
    }
}