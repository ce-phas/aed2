public class Nivelamento
{
    public static boolean inArray(int x, int[] array)
    {
        boolean contains = false;
        int n = array.length;
        int index = 0;

        while (!contains && index < n)
        {
            contains = (x == array[index]);
            index++;
        }

        return contains;
    }

    public static boolean inAscArray(int x, int[] array)
    {
        boolean contains = false;
        int n = array.length;
        int index = n / 2;
        n--;

        if (x == array[index])
        {
            contains = true;
        }
        else if (x < array[index])
        {
            while (!contains && index > 0)
            {
                index--;
                contains = (x == array[index]);
            }
        }
        else
        {
            while (!contains && index < n)
            {
                index++;
                contains = (x == array[index]);
            }
        }

        return contains;            
    }

    public static void getMaxMin(int[] array)
    {
        int max = array[0];
        int min = array[0];
        int n = array.length;

        for (int i = 0; i < n; i++)
        {
            if (array[i] > max)
                max = array[i];
            if (array[i] < min)
                min = array[i];
        }

        System.out.println("max = " + max);
        System.out.println("min = " + min);
    }

    public static void getMaxMin2(int[] array)
    {
        int max = array[0];
        int min = array[0];
        int n = array.length;

        for (int i = 0; i < n; i++)
        {
            if (array[i] > max)
                max = array[i];
            else if (array[i] < min)
                min = array[i];
        }

        System.out.println("max = " + max);
        System.out.println("min = " + min);
    }

    public static void main(String args[])
    {
    }
}
