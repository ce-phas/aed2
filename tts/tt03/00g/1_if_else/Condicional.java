import java.util.*;

public class Condicional {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int a, b;

        System.out.print("a: ");
        a = sc.nextInt();
        System.out.print("b: ");
        b = sc.nextInt();

        if (a > 45 || b > 45) {
            System.out.println(a + b);
        } else if (a > 20 && b > 20) {
            if (a > b) {
                System.out.println(a - b);
            } else {
                System.out.println(b - a);
            }
        } else if (a < 10 && b != 0) {
            System.out.println(a / b);
        } else if (b < 10 && a != 0) {
            System.out.println(b / a);
        } else {
            System.out.println("Pedro");
        }
    }
}
