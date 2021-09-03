/**
 * Palindromo - programa que verifica se cadeias de caracteres
 * de entrada sao palindromos e retorna os resultados
 *
 * @author Pedro Sa (742626)
 * @version 1.1
 * @since 2021-08-03
 */
public class Palindromo
{
    /**
     * Este metodo determina se uma cadeia de caracteres configura
     * ou nao um palindromo.
     * @param line Cadeia de caracteres a ser testada.
     * @return true, se for um palindromo; false, caso contrario.
     */
    public static boolean isPalindrome(String line)
    {
        // definir dados
        boolean pal = true;
        int length = line.length();
        int i = 0;
        int j = length - 1;

        while (pal && j > i)
        {
            // testar se caractere na posicao atual e' igual ao
            // caractere na posicao espelhada
            pal = pal && (line.charAt(j) == line.charAt(i));
            i++;
            j--;
        }

        return pal;
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
            // chamar metodo para verificacao e saida
            if (isPalindrome(line))
                MyIO.println("SIM");
            else
                MyIO.println("NAO");

            // ler novamente
            line = MyIO.readLine();
        }
    }
}
