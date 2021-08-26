import java.util.*;

/**
 * Alteracao - programa para sortear dois caracteres aleatórios, substituindo
 * o primeiro pelo segundo (quando aplicavel) na string recebida.
 *
 * @author Pedro Sa (742626)
 * @version 1.0
 * @since 2021-08-12
 */
public class Alteracao
{
    // declarar objeto gerador aleatorio
    public static Random generator;

    /**
     * Este metodo sorteia dois caracteres minusculos aleatorios e
     * substitui todas as ocorrencias do primeiro caractere pelo segundo
     * na string recebida.
     * @param line String a alterar.
     * @return String com as substituicoes efetuadas, quando aplicavel.
     */
    public static String randomize(String line)
    {
        // definir dados
        String randString = "";
        int n = line.length();

        // sortear os caracteres
        char oldc = (char) ('a' + (Math.abs(generator.nextInt()) % 26));
        char newc = (char) ('a' + (Math.abs(generator.nextInt()) % 26));

        for (int i = 0; i < n; i++)
        {
            char now = line.charAt(i);

            // verificar se o caractere atual corresponde
            // ao caractere que deve ser substituido
            if (now == oldc)
                randString += newc;
            else
                randString += now;
        }

        return randString;
    }

    public static void main(String args[])
    {
        // definir dados
        String line;
        String randString;

        // instanciar o gerador aleatorio
        generator = new Random(4);

        // ler a primeira string
        line = MyIO.readLine();

        while (!(line.length() == 3 &&
                 line.charAt(0) == 'F' &&
                 line.charAt(1) == 'I' &&
                 line.charAt(2) == 'M'))
        {
            // obter a string randomizada a mostrar
            randString = randomize(line);
            MyIO.println(randString);

            // ler nova string
            line = MyIO.readLine();
        }
    }
}
