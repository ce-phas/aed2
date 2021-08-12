public class Matriz
{
    public static void main(String args[])
    {
        // definir dados
        int matrix[][] = new int[10][10];
        int sum[] = new int[10];
        int n;

        // ler dimensao da matriz quadrada
        n = MyIO.readInt();

        // ler dados e inserir na matriz
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                matrix[i][j] = MyIO.readInt();
            }
        }

        // fazer soma da matriz
        for (int j = 0; j < n; j++)
        {
            sum[j] = 0;
            for (int i = 0; i < n; i++)
            {
                sum[j] += matrix[i][j];
            }
        }

        // mostrar resultado
        for (int k = 0; k < n; k++)
        {
            MyIO.print(sum[k] + " ");
        }

        // inserir linha no EOF de saida
        MyIO.println("");
    }
}
