#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

/**
 * Palindromo Recursivo - programa que verifica se cadeias de caracteres
 * de entrada sao palindromos e retorna os resultados
 *
 * @author Pedro Sa (742626)
 * @version 1.1
 * @since 2021-08-25
 */

/**
    * Este metodo determina se uma cadeia de caracteres configura
    * ou nao um palindromo.
    * @param line Cadeia de caracteres a ser testada.
    * @param i Indice inicial para comparacao
    * @param j Indice final para comparacao
    * @return true, se for um palindromo; false, caso contrario.
    */
bool isPalindrome(char *line, int i, int j)
{
    if (i == j)
    {
        return true;
    }
    else if (line[i] != line[j])
    {
        return false;
    }
    else if (i < j)
    {
        return isPalindrome(line, i+1, j-1);
    }
    return true;
}

int main (void)
{
    // definir dados
    char buffer[200];
    int j;

    // ler entrada
    fgets(buffer, 200, stdin);

    while (!(buffer[0] == 'F' &&
             buffer[1] == 'I' &&
             buffer[2] == 'M' &&
             strlen(buffer) == 4))
    {
        // encontrar ultimo indice da string
        int j = strlen(buffer) - 2;

        // chamar metodo para verificacao e saida
        if (isPalindrome(buffer, 0, j))
            printf("SIM\n");
        else
            printf("NAO\n");

        // ler novamente
        fgets(buffer, 200, stdin);
    }

    return 0;
}
