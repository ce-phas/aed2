import java.util.*;

public class ExercicioWhile6 {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        int i = 0;
        int n1 = 0;
        int n2 = 1;

        while (i < n)
        {
            int sum = n1 + n2;
            n1 = n2;
            n2 = sum;

            i++;
        }

        System.out.println("n(" + n + "): " + n2);
    }
}
