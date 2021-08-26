#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

/**
 * Este metodo determina se uma cadeia de caracteres configura
 * ou nao um palindromo.
 * @param line Cadeia de caracteres a ser testada.
 * @return true, se for um palindromo; false, caso contrario.
 */
bool isPalindrome(char *line)
{
    // definir dados
    bool pal = true;
    int length = strlen(line) - 1;
    int i = 0;
    int j = length - 1;

    while (pal && j > 1)
    {
        // testar se caractere na posicao atual e' igual ao
        // caractere na posicao espelhada
        pal = pal && (line[j] == line[i]);
        i++;
        j--;
    }

    return pal;
}

int main(void)
{
    // definir dados
    char buffer[200];

    // ler entrada
    fgets(buffer, 200, stdin);

    while (!(buffer[0] == 'F' &&
             buffer[1] == 'I' &&
             buffer[2] == 'M' &&
             strlen(buffer) == 4))
    {
        // testar se e' palindromo e mostrar resultado
        if (isPalindrome(buffer))
        {
            printf("SIM\n");
        }
        else
        {
            printf("NAO\n");
        }

        fgets(buffer, 200, stdin);
    }

    return 0;
}

