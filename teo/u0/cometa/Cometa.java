public class Cometa
{
    public static int getYear(int year)
    {
        int rem = (year - 1986) % 76;

        if (rem == 0)
            return year + 76;
        else
            return year + 76 - rem;
    }
    public static void main(String args[])
    {
        int year = MyIO.readInt();
        int next;

        while (year != 0)
        {
            next = getYear(year);
            MyIO.println(next);
            year = MyIO.readInt();
        }
    }
}
