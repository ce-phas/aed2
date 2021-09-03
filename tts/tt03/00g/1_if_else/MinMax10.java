import java.util.*;

public class MinMax10 {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int[] array = new int[10];

        for (int m = 1; m < 11; m++) {
            System.out.print("x" + m + ": ");
            array[m] = sc.nextInt();
        }

        int min = array[0];
        int max = array[0];

        for (int n = 1; n < 10; n++) {
            if (array[n] < min) {
                min = array[n];
            }
            if (array[n] > max) {
                max = array[n];
            }
        }

        System.out.println("Min = " + min);
        System.out.println("Max = " + max);
    }
}
