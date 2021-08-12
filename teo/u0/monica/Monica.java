import java.util.*;

public class Monica
{
    public static int getMax(int a, int b, int c)
    {
        int max = a;

        if (b > max)
            max = b;
        if (c > max)
            max = c;

        return max;
    }

    public static void main(String args[])
    {
        String[] ages = new String[]{"", "", ""};
        String in = MyIO.readLine();
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
                    pos++;
                else
                {
                    ages[pos] += in.charAt(i);
                }
            }

            monica = Integer.parseInt(ages[0]);
            f1 = Integer.parseInt(ages[1]);
            f2 = Integer.parseInt(ages[2]);
            f3 = monica - f1 - f2;
            max = getMax(f1, f2, f3);

            MyIO.println(max);
            ages = new String[]{"", "", ""};
            pos = 0;
            in = MyIO.readLine();
            len = in.length();
        }
    }
}
