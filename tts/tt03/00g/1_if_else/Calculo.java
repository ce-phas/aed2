import java.util.*;
import java.lang.*;

public class Banco {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        double a, b;

        System.out.print("a: ");
        a = sc.nextDouble();
        System.out.print("b: ");
        b = sc.nextDouble();

        if (a < b) {
            System.out.println(Math.cbrt(a));
            System.out.println(Math.log(a) / Math.log(b));
        } else {
            System.out.println(Math.cbrt(b));
            System.out.println(Math.log(b) / Math.log(a));
        }
    }
}
