import java.io.*;

/**
 * Arquivo - programa
 *
 * @author Pedro Sa (742626)
 * @version 1.0
 * @since 2021-08-23
 */
public class Arquivo
{
    public static void fwriteBytes(String fileName, int n) throws IOException
    {
        RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
        double x;

        for (int i = 0; i < n; i++)
        {
            x = MyIO.readDouble();
            raf.writeDouble(x);
        }
        raf.close();
    }

    public static void freadBytes(String fileName, int n) throws IOException
    {
        RandomAccessFile raf = new RandomAccessFile(fileName, "r");
        double x;
        long pos = 8 * (n - 1);

        for (long i = pos; i > -1; i -= 8)
        {
            raf.seek(i);
            x = raf.readDouble();
            if (x == (int) x)
                MyIO.println((int) x);
            else
                MyIO.println(x);
        }
        raf.close();
    }

    public static void main(String[] args)
    {
        String fileName = "out.txt";
        int n = MyIO.readInt();
        try
        {
            fwriteBytes(fileName, n);
            freadBytes(fileName, n);
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
}
