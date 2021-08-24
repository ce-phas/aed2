/**
 * Arquivo - programa
 *
 * @author Pedro Sa (742626)
 * @version 1.0
 * @since 2021-08-23
 */
public class Arquivo
{
    public static void main(String args[])
    {
        // definir dados
        String title;

        // ler dados
        title = MyIO.readLine();

        while (!(title.length() == 3 &&
                 title.charAt(0) == 'F' &&
                 title.charAt(1) == 'I' &&
                 title.charAt(2) == 'M'))
        {
            title = MyIO.readLine();
        }
    }
}
 