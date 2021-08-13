import java.util.*;

/**
 * Is - Programa para testar uma string quanto a sua composicao e
 * mostrar os resultados em uma sequencia, de acordo com os testes:
 * 1 - composta somente por vogais;
 * 2 - composta somente por consoantes;
 * 3 - e' numero inteiro;
 * 4 - e' numero real.
 *
 * @author Pedro Sa (742626)
 * @version 1.0
 * @since 2021-08-12
 */
public class Is
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
     * @return true, se composta exclusivamente por vogais;
     * false, caso contrario.
     */
    public static boolean isAllVowel(String line)
    {
        // definir dados
        boolean result = true;
        char c;
        int i = 0;
        int n = line.length();

        // repetir enquanto o caractere atual for vogal
        // e i < n
        while (result && i < n)
        {
            c = line.charAt(i);

            // verificar se e' uma letra e se e' vogal
            result = isLetter(c) && isVowel(c);

            // incrementar iterador
            i++;
        }

        return result;
    }

    /**
     * Verifica se a string recebida e' composta somente por consoantes.
     * @param line String a testar.
     * @return true, se composta exclusivamente por consoantes;
     * false, caso contrario.
     */
    public static boolean isAllCons(String line)
    {
        // definir dados
        boolean result = true;
        char c;
        int i = 0;
        int n = line.length();

        // repetir enquanto o caractere atual nao for vogal
        // e i < n
        while (result && i < n)
        {
            c = line.charAt(i);

            // verificar se caractere e' letra e nao e' vogal
            result = isLetter(c) && !(isVowel(c));

            // incrementar iterador
            i++;
        }

        return result;
    }

    /**
     * Verifica se a string recebida e' equivalente a um numero inteiro.
     * @param line String a testar.
     * @return true, se equivalente a um inteiro; false, caso contrario.
     */
    public static boolean isInt(String line)
    {
        // definir dados
        boolean result = true;
        int i = 0;
        int n = line.length();

        while (result && i < n)
        {
            // verificar se caractere atual e' digito
            result = isDigit(line.charAt(i));

            // incrementar
            i++;
        }

        return result;
    }

    /**
     * Verifica se a string recebida e' equivalente a um numero real.
     * @param line String a testar.
     * @return true, se equivalente a um real; false, caso contrario.
     */
    public static boolean isFloat(String line)
    {
        // definir dados
        boolean result = true;
        char c;
        int i = 0;
        int n = line.length();
        int sep = 0;

        while (result && i < n)
        {
            c = line.charAt(i);

            if (isDigit(c))
                // continuar iteracao se caractere atual for digito
                result = true;
            else if (sep < 1 && (c == '.' || c == ','))
            {
                // verificar se caractere atual e' um separador decimal
                // e se e' o unico separador encontrado
                result = true;

                // incrementar quantidade encontrada de separadores
                sep++;
            }
            else
                result = false;

            i++;
        }

        return result;
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
            if (isAllVowel(line))
                // verificar se a entrada contem somente vogais
                // e gerar saida
                MyIO.println("SIM NAO NAO NAO");
            else if (isAllCons(line))
                // verificar se a entrada contem somente consoantes
                // e gerar saida
                MyIO.println("NAO SIM NAO NAO");
            else if (isInt(line))
                // verificar se a entrada corresponde a um inteiro e gerar
                // saida (sinalizando que tambem corresponde a um real)
                MyIO.println("NAO NAO SIM SIM");
            else if (isFloat(line))
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
