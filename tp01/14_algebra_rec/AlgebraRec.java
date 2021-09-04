public class AlgebraRec
{
    /**
     * Verifica se o caractere e' representacao de um binario.
     * @param c Caractere a testar.
     * @return true, se for uma representacao de binario; false, caso contrario.
     */
    public static boolean isBinDigit(char c) {
        return (c == '0' || c == '1');
    }

    /**
     * Simplifica a string original de modo a eliminar
     * caracteres desnecessarios.
     * @param s String a simplificar.
     * @return String simplificada.
     */
    public static String simplify(String s) {
        int i = 0;
        int len = s.length();
        String simp = "";

        while (i < len) {
            switch (s.charAt(i)) {
                case '(':
                    simp += '(';
                    i++;
                    break;
                case ')':
                    simp += ')';
                    i++;
                    break;
                case 'a':
                    simp += 'a';
                    i += 3;
                    break;
                case 'n':
                    simp += 'n';
                    i += 3;
                    break;
                case 'o':
                    simp += 'o';
                    i += 2;
                    break;
                case 'A':
                    simp += s.charAt(2);
                    i++;
                    break;
                case 'B':
                    simp += s.charAt(4);
                    i++;
                    break;
                case 'C':
                    simp += s.charAt(6);
                    i++;
                    break;
                default:
                    i++;
                    break;
            }
        }

        return simp;
    }

    /**
     * Testa se a subexpressao atual e' completa e retorna o
     * numero de operandos.
     * @param i Indice de inicio da subexpressao.
     * @param exp Expressao completa.
     * @return Numero de operandos; 0, se nao for uma subexpressao completa.
    */
    public static int testSub(int i, String exp) {
        char c;
        int n = 0;
        int p = 0;
        boolean ctrl = true;

        i++;

        while (ctrl && p < 1) {
            c = exp.charAt(++i);

            if (isBinDigit(c)) {
                n++;
            } else if (c == ')') {
                p++;
            } else {
                ctrl = false;
            }
        }

        return (ctrl ? n : 0);
    }

    /**
     * Faz o parse de uma subexpressao AND completa.
     * @param start Indice de inicio da subexpressao.
     * @param stop Indice de termino da subexpressao.
     * @param exp Expressao completa.
     * @return Avaliacao da subexpressao booleana.
    */
    public static char parseAnd(int start, int stop, String exp) {
        int i = start;
        boolean result = true;

        while (result && i < stop) {
            result = exp.charAt(i) == '1';
            i++;
        }

        return (result ? '1' : '0');
    }

    /**
     * Faz o parse de uma subexpressao OR completa.
     * @param start Indice de inicio da subexpressao.
     * @param stop Indice de termino da subexpressao.
     * @param exp Expressao completa.
     * @return Avaliacao da subexpressao booleana.
    */
    public static char parseOr(int start, int stop, String exp) {
        int i = start;
        boolean result = false;

        while (!result && i < stop) {
            result = exp.charAt(i) == '1';
            i++;
        }

        return (result ? '1' : '0');
    }

    /**
     * Faz o parse da expressao inteira por meio da resolucao
     * recursiva de subexpressoes completas.
     * @param terms Total de termos distintos na expressao.
     * @return Resultado da expressao booleana (0 ou 1).
    */
    public static String parseExpression(int terms, String exp) {
        int len = exp.length();
        int i = 0;
        int n;
        char c;
        String aux = "";

        if (len == 1) {
            return exp;
        } else {
            while (i < len) {
                c = exp.charAt(i);

                if (c == 'n') {
                    if (exp.charAt(i+2) == '0') {
                        aux += '1';
                        i += 4;
                    } else if (exp.charAt(i+2) == '1') {
                        aux += '0';
                        i += 4;
                    } else {
                        aux += c;
                        i++;
                    }
                } else if (c == 'a') {
                    n = testSub(i, exp);
                    if (n == 0) {
                        aux += c;
                        i++;
                    } else {
                        aux += parseAnd(i+2, i+2+n, exp);
                        i += n + 3;
                    }
                } else if (c == 'o') {
                    n = testSub(i, exp);
                    if (n == 0) {
                        aux += c;
                        i++;
                    } else {
                        aux += parseOr(i+2, i+2+n, exp);
                        i += n + 3;
                    }
                } else {
                    aux += c;
                    i++;
                }
                n = 0;
            }
            return parseExpression(terms, aux);
        }
    }

    public static void main(String[] args)
    {
        String exp = MyIO.readLine();
        String result;

        while (exp.charAt(0) != '0') {
            int terms = (int) exp.charAt(0) % 48;
            exp = simplify(exp);
            result = parseExpression(terms, exp);
            MyIO.println(result);
            exp = MyIO.readLine();
        }
    }
}
