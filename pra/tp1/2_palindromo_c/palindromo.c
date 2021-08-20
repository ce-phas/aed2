#include <stdio.h>
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
    int aux = length - 1;
    int i = 0;

    while (pal && aux > 0)
    {
        // testar se caractere na posicao atual e' igual ao
        // caractere na posicao espelhada
        pal = pal && (line[aux] == line[i]);
        aux--;
        i++;
    }

    return pal;
}

int main(void)
{
    // definir dados
    char buffer[200];
    int length;

    // ler entrada
    fgets(buffer, 200, stdin);

    while (!(strlen(buffer) == 4 &&
             buffer[0] == 'F' &&
             buffer[1] == 'I' &&
             buffer[2] == 'M'))
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
}

