import java.util.*;

public class Cometa
{
    static Scanner sc = new Scanner(System.in);

    public static int getYear(int year)
    {
        int rem = (year - 1986) % 76;

        if (rem == 0)
        {
            return (year + 76);
        }
        else
        {
            return (year + 76 - rem);
        }
    }

    public static void main(String[] args)
    {
        int year = sc.nextInt();
        int next;

        while (year != 0)
        {
            next = getYear(year);
            System.out.println(next);
            year = sc.nextInt();
        }
    }
}
