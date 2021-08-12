/**
 * Ciframento - programa para aplicar um algoritmo de criptografia
 * baseado no Ciframento de Cesar.
 *
 * @author Pedro Sa (742626)
 * @version 1.0
 * @since 2021-08-11
 */
public class Ciframento
{
    /**
     * Este metodo aplica o Ciframento de Cesar sobre a string de
     * entrada e retorna uma string cifrada.
     * @param msg String a cifrar.
     * @param key Chave de ciframento.
     * @return String cifrada.
     */
    public static String encrypt(String msg, int key)
    {
        // definir dados
        String encMsg = "";
        int n = msg.length();

        for (int i = 0; i < n; i++)
        {
            // aplicar a chave sobre o caractere atual
            // com o auxilio de type casting
            encMsg += (char) ((int) msg.charAt(i) + key);
        }

        return encMsg;
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
            MyIO.println(encrypt(msg, 3));

            // ler novamente
            msg = MyIO.readLine();
        }
    }
}
