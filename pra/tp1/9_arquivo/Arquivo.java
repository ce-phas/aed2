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
    public static void main(String[] args)
    {
        try
        {
            RandomAccessFile file = new RandomAccessFile("pub.txt", "r");
            file.seek(0);
            MyIO.println(file.getFilePointer());
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
 