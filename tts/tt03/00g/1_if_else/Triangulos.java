import java.util.*;

public class Triangulos {
    public static Scanner sc = new Scanner(System.in);

    public static void whichTri(double a, double b, double c) {
        if (a == b && b == c) {
            System.out.println("Equilátero");
        } else if (a != b && a != c && b != c) {
            System.out.println("Escaleno");
        } else {
            System.out.println("Isósceles");
        }
    }

    public static void main(String[] args) {
        double a, b, c;

        System.out.print("Lado 1: ");
        a = sc.nextDouble();
        System.out.print("Lado 2: ");
        b = sc.nextDouble();
        System.out.print("Lado 3: ");
        c = sc.nextDouble();
    }
}
