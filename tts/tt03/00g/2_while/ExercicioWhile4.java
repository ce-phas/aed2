import java.util.*;

public class ExercicioWhile4 {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int i = 1;
        int x = 1;
        int n = sc.nextInt();
        System.out.println(i);
        while (i < n) {
            if (i % 2 != 0) {
                x += 4;
            } else {
                x += 7;
            }
            System.out.println(x);
            i++;
        }
    }
}
