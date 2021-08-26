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
        // definir dados
        RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
        double x;

        for (int i = 0; i < n; i++)
        {
            // ler valor real
            x = MyIO.readDouble();

            // escrever valor em bytes no arquivo
            raf.writeDouble(x);
        }

        // fechar
        raf.close();
    }

    public static void freadBytes(String fileName, int n) throws IOException
    {
        // definir dados
        RandomAccessFile raf = new RandomAccessFile(fileName, "r");
        double x;
        long pos = 8 * (n - 1);

        for (long i = pos; i > -1; i -= 8)
        {
            // pular para a posicao i em bytes
            raf.seek(i);

            // ler valor real
            x = raf.readDouble();

            // remover separador se equivalente a um inteiro
            if (x == (int) x)
            {
                MyIO.println((int) x);
            }
            else
            {
                MyIO.println(x);
            }
        }

        // fechar
        raf.close();
    }

    public static void main(String[] args)
    {
        // definir dados
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
