import java.util.*;

public class Array4 {
    public static Scanner sc = new Scanner(System.in);

    public static int[] genArray(int n) {
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }

        return array;
    }

    public static void main(String[] args) {
        int n = sc.nextInt();
        int[] array = genArray(n);

        for (int i = 1; i < n; i++) {
            int k = array[i];
            int j = i - 1;

            while (j >= 0 && key < array[i]) {
                array[j+1] = array[j];
                j--;
            }

            array[j+1] = key;
        }
    }
}
