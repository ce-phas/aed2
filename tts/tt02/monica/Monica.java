import java.util.*;
import java.lang.*;

public class Monica
{
    static Scanner sc = new Scanner(System.in);

    public static int str2int(String sint)
    {
        int c;
        int x = 0;
        int len = sint.length();
        double n = len - 1.0;

        for (int i = 0; i < len; i++)
        {
            c = (int) sint.charAt(i) % 48;
            x += c * (int) Math.pow(10.0, n);
            n--;
        }

        return x;
    }

    public static int getMax(int a, int b, int c)
    {
        int max = a;

        if (b > max)
        {
            max = b;
        }
        if (c > max)
        {
            max = c;
        }

        return max;
    }

    public static void main(String args[])
    {
        String[] ages = new String[]{"", "", ""};
        String in = sc.nextLine();
        int monica;
        int f1;
        int f2;
        int f3;
        int len = in.length();
        int pos = 0;
        int max;

        while (in.charAt(0) != '0')
        {
            for (int i = 0; i < len; i++)
            {
                if (in.charAt(i) == ' ')
                {
                    pos += 1;
                }
                else
                {
                    ages[pos] += in.charAt(i);
                }
            }

            monica = str2int(ages[0]);
            f1 = str2int(ages[1]);
            f2 = str2int(ages[2]);
            f3 = monica - f1 - f2;
            max = getMax(f1, f2, f3);

            System.out.println(max);
            ages = new String[]{"", "", ""};
            pos = 0;
            in = sc.nextLine();
            len = in.length();
        }
    }
}
