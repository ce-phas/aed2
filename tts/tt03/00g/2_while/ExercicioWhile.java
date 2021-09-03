import java.util.*;

public class ExercicioWhile1 {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = 5;
        int i = 0;
        int j = 0;
        double sum = 0;
        double[] array = new array[n];

        while (i <= n) {
            System.out.println("Nota " + i + ": ");
            array[i] = sc.nextDouble();
            i++;
        }

        while (j < n) {
            sum += array[j];
            j++;
        }

        System.out.println(sum / n);
    }
}
