import java.util.*;

public class Array {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int v;
        int sum = 0;
        int n = sc.nextInt();
        int[] array = new int[n];
        double avg;

        for (int i = 0; i < n; i++) {
            v = sc.nextInt();
            array[i] = v;
            sum += v;
        }

        avg = (double) sum / n;

        for (int i = 0; i < n; i++) {
            v = array[i];
            if (v > avg) {
                System.out.println(v);
            }
        }
    }
}
