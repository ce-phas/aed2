/**
 * Ciframento Recursivo - programa para aplicar um algoritmo de criptografia
 * baseado no Ciframento de Cesar.
 *
 * @author Pedro Sa (742626)
 * @version 1.0
 * @since 2021-08-25
 */
public class CiframentoRec
{
    /**
     * Este metodo aplica o Ciframento de Cesar sobre a string de
     * entrada e retorna uma string cifrada, de forma recursiva.
     * @param msg String a cifrar.
     * @param key Chave de ciframento.
     * @param i Posicao de inicio do ciframento na string.
     * @return String cifrada.
     */
    public static String encrypt(String msg, int key, int i)
    {
        char c = (char) ((int) msg.charAt(i) + key);

        if (i == msg.length() - 1)
        {
            return "" + c;
        }
        else
        {
            return c + encrypt(msg, 3, i+1);
        }
    }

    public static void main(String args[])
    {
        // definir dados
        String msg;

        // ler a primeira string
        msg = MyIO.readLine();

        while (!(msg.length() == 3 &&
                 msg.charAt(0) == 'F' &&
                 msg.charAt(1) == 'I' &&
                 msg.charAt(2) == 'M'))
        {
            // mostrar o resultado do ciframento
            MyIO.println(encrypt(msg, 3, 0));

            // ler novamente
            msg = MyIO.readLine();
        }
    }
}
