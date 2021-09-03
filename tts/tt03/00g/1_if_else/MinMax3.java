import java.util.*;

public class MinMax3 {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int a, b, c;

        System.out.print("a: ");
        a = sc.nextInt();
        System.out.print("b: ");
        b = sc.nextInt();
        System.out.print("c: ");
        c = sc.nextInt();

        int min = a;
        int max = a;

        if (b < min) {
            min = b;
        }
        if (c < min) {
            min = c;
        }

        if (b > max) {
            max = b;
        }
        if (c > max) {
            max = c;
        }

        System.out.println("Min = " + min);
        System.out.println("Max = " + max);
    }
}
