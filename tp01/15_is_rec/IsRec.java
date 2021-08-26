import java.util.*;

/**
 * IsRec - Programa para testar uma string quanto a sua composicao e
 * mostrar os resultados em uma sequencia, de acordo com os testes:
 * 1 - composta somente por vogais;
 * 2 - composta somente por consoantes;
 * 3 - e' numero inteiro;
 * 4 - e' numero real.
 *
 * @author Pedro Sa (742626)
 * @version 1.0
 * @since 2021-08-25
 */
public class IsRec
{
    // metodos auxiliares

    /**
     * Converte o caractere recebido em maiusculo, caso seja minusculo.
     * @param c Caractere a converter.
     * @return Caractere convertido, se aplicavel. Caso contrario, retorna
     * o proprio caractere.
     */
    public static char toUpper(char c)
    {
        return ('a' <= c && c <= 'z') ? ((char) (c - 32)) : c;
    }

    /**
     * Verifica se o caractere recebido e' uma letra.
     * @param c Caractere a testar.
     * @return true, se for letra; false, caso contrario.
     */
    public static boolean isLetter(char c)
    {
        c = toUpper(c);
        return ('A' <= c && c <= 'Z');
    }

    /**
     * Verifica se o caractere recebido e' uma vogal.
     * @param c Caractere a testar.
     * @return true, se for vogal; false, caso contrario.
     */
    public static boolean isVowel(char c)
    {
        c  = toUpper(c);
        return (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U');
    }

    /**
     * Verifica se o caractere recebido e' uma consoante.
     * @param c Caractere a testar.
     * @return true, se for consoante; false, caso contrario.
     */
    public static boolean isCons(char c)
    {
        return (isLetter(c) && !isVowel(c));
    }

    /**
     * Verifica se o caractere recebido e' um digito (entre 0 e 9).
     * @param c Caractere a testar.
     * @return true, se for digito; false, caso contrario.
     */
    public static boolean isDigit(char c)
    {
        return ('0' <= c && c <= '9');
    }

    // metodos principais

    /**
     * Verifica se a string recebida e' composta somente por vogais.
     * @param line String a testar.
     * @param i Posicao inicial para teste.
     * @return true, se composta exclusivamente por vogais;
     * false, caso contrario.
     */
    public static boolean isAllVowel(String line, int i)
    {
        char c = line.charAt(i);
        boolean result = isLetter(c) && isVowel(c);

        if (i == line.length() - 1)
        {
            return result;
        }
        else
        {
            return (isAllVowel(line, i+1) && result);
        }
    }

    /**
     * Verifica se a string recebida e' composta somente por consoantes.
     * @param line String a testar.
     * @param i Posicao inicial para teste.
     * @return true, se composta exclusivamente por consoantes;
     * false, caso contrario.
     */
    public static boolean isAllCons(String line, int i)
    {
        char c = line.charAt(i);
        boolean result = isLetter(c) && !(isVowel(c));

        if (i == line.length() - 1)
        {
            return result;
        }
        else
        {
            return (isAllCons(line, i+1) && result);
        }
    }

    /**
     * Verifica se a string recebida e' equivalente a um numero inteiro.
     * @param line String a testar.
     * @param i Posicao inicial para teste.
     * @return true, se equivalente a um inteiro; false, caso contrario.
     */
    public static boolean isInt(String line, int i)
    {
        boolean result = isDigit(line.charAt(i));

        if (i == line.length() - 1)
        {
            return result;
        }
        else
        {
            return (result && isInt(line, i+1));
        }
    }

    /**
     * Verifica se a string recebida e' equivalente a um numero real.
     * @param line String a testar.
     * @param i Posicao inicial para teste.
     * @param sep Quantidade inicial de separadores.
     * @return true, se equivalente a um real; false, caso contrario.
     */
    public static boolean isFloat(String line, int i, int sep)
    {
        char c = line.charAt(i);

        if (i == line.length() - 1)
        {
            return (isDigit(c) || sep == 0 && (c == '.' || c == ','));
        }
        else if (sep == 0 && (c == '.' || c == ','))
        {
            return (true && isFloat(line, i+1, sep+1));
        }
        else
        {
            return (isDigit(c) && isFloat(line, i+1, sep));
        }
    }

    public static void main(String args[])
    {
        // definir dados
        String line;

        // ler a primeira string
        line = MyIO.readLine();

        while (!(line.length() == 3 &&
                 line.charAt(0) == 'F' &&
                 line.charAt(1) == 'I' &&
                 line.charAt(2) == 'M'))
        {
            if (isAllVowel(line, 0))
                // verificar se a entrada contem somente vogais
                // e gerar saida
                MyIO.println("SIM NAO NAO NAO");
            else if (isAllCons(line, 0))
                // verificar se a entrada contem somente consoantes
                // e gerar saida
                MyIO.println("NAO SIM NAO NAO");
            else if (isInt(line, 0))
                // verificar se a entrada corresponde a um inteiro e gerar
                // saida (sinalizando que tambem corresponde a um real)
                MyIO.println("NAO NAO SIM SIM");
            else if (isFloat(line, 0, 0))
                // verificar se a entrada corresponde a um numero real
                // e gerar saida
                MyIO.println("NAO NAO NAO SIM");
            else
                MyIO.println("NAO NAO NAO NAO");

            // ler nova string
            line = MyIO.readLine();
        }
    }
}
