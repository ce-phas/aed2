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
        int[] array1 = genArray(5);
        int[] array2 = genArray(5);

        
    }
}
