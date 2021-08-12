/**
 * Aquecimento - programa para retornar o numero de caracteres
 * maiusculos de uma string.
 *
 * @author Pedro Sa (742626)
 * @version 1.0
 * @since 2021-08-03
 */
public class Aquecimento
{
    /**
     * Metodo para contar o numero de maiusculas em uma string.
     * @param string Cadeia de caracteres a ser testada.
     * @return Total de maiusculas encontradas na string.
     */
    public static int countUpper(String string)
    {
        int count = 0;
        int length = string.length();
        char c;

        for (int i = 0; i < length; i++)
        {
            c = string.charAt(i);
            if ('A' <= c && c <= 'Z')
                count++;
        }

        return count;
    }

    public static void main(String[] args)
    {
        String string = MyIO.readLine();
        int count;

        while (!string.equals("FIM"))
        {
            count = countUpper(string);
            MyIO.println(count);
            string = MyIO.readLine();
        }
    }
}
