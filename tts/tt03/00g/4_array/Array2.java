import java.util.*;

public class Array2 {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int v;
        int sum = 0;
        int n = sc.nextInt();
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();;
        }

        avg = (double) sum / n;

        for (int i = 0; i < n; i = 2*i+1) {
            sum += array[i];
        }

        System.out.println(sum);
    }
}
