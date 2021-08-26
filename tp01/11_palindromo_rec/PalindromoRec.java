/**
 * Palindromo Recursivo - programa que verifica se cadeias de caracteres
 * de entrada sao palindromos e retorna os resultados
 *
 * @author Pedro Sa (742626)
 * @version 1.1
 * @since 2021-08-03
 */
public class PalindromoRec
{
    /**
     * Este metodo determina se uma cadeia de caracteres configura
     * ou nao um palindromo.
     * @param line Cadeia de caracteres a ser testada.
     * @return true, se for um palindromo; false, caso contrario.
     */
    public static boolean isPalindrome(String line, int i, int j)
    {
        if (i == j)
        {
            return true;
        }
        else if (line.charAt(i) != line.charAt(j))
        {
            return false;
        }
        else if (i < j)
        {
            return isPalindrome(line, i+1, j-1);
        }
        return true;
    }

    public static void main(String args[])
    {
        // definir dados
        String line;

        // ler a primeira linha
        line = MyIO.readLine();

        while (!(line.charAt(0) == 'F' &&
                 line.charAt(1) == 'I' &&
                 line.charAt(2) == 'M' &&
                 line.length() == 3))
        {
            // encontrar ultimo indice da string
            int j = line.length() - 1;

            // chamar metodo para verificacao e saida
            if (isPalindrome(line, 0, j))
                MyIO.println("SIM");
            else
                MyIO.println("NAO");

            // ler novamente
            line = MyIO.readLine();
        }
    }
}
