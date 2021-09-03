import java.util.*;

public class ExercicioFor2 {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int num = 0;
        int den = 0;

        for (int i = 0; i < 5; i++) {
            int n = sc.nextInt();

            if (n >= 80) {
                num += n;
                den++;
            }
        }

        System.out.println(num / den);
    }
}
