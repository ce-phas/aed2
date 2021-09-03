import java.util.*;

public class ExercicioWhile3 {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int i = 0;
        int n = sc.nextInt();
        while (i < n) {
            System.out.println(i * 2 + 1);
            i++;
        }
    }
}
