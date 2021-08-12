/**
 * AquecimentoRecursivo - programa para retornar o numero de caracteres
 * maiusculos de uma string, por meio de um metodo recursivo.
 *
 * @author Pedro Sa (742626)
 * @version 1.0
 * @since 2021-08-03
 */
public class AquecimentoRecursivo
{
    /**
     * Metodo recursivo para contar o numero de maiusculas em uma string.
     * @param string Cadeia de caracteres a ser testada.
     * @param length Comprimento da string
     * @return Total de maiusculas encontradas na string.
     */
    public static int countUpper(String string, int length)
    {
        int count = 0;
        char c;

        if (length < 0)
        {
            return count;
        }
        else
        {
            c = string.charAt(length);
            if ('A' <= c && c <= 'Z')
                count++;
            return count + countUpper(string, length - 1);
        }
    }

    public static void main(String[] args)
    {
        String string = MyIO.readLine();
        int count;
        int length;

        while (!string.equals("FIM"))
        {
            length = string.length() - 1;
            count = countUpper(string, length);
            MyIO.println(count);
            string = MyIO.readLine();
        }
    }
}
