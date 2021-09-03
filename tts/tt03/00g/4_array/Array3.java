import java.util.*;

public class Array3 {
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
        int min = array[0];
        int pos = 0;

        for (int i = 1; i < n; i++) {
            if (array[i] < min) {
                min = array[i];
                pos = i;
            }
        }

        System.out.println(pos);
    }
}
