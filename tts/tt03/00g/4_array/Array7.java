import java.util.*;

public class Array5 {
    public static Scanner sc = new Scanner(System.in);

    public static int[] genArray(int n) {
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }

        return array;
    }

    public static void main(String[] args) {
        int[] array = genArray(5);
        int sum = 0;
        int min = array[0];

        for (int i = 0; i < 5; i++) {
            sum += array[i];

            if (array[i] < min) {
                min = array[i];
            }
        }

        System.out.println("Soma: " + sum);
        System.out.println("Média: " + (double) sum / 5);
        System.out.println("Menor: " + min);
    }
}
