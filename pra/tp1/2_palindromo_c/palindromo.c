#include <stdio.h>
#include <string.h>
#include <stdbool.h>

bool isPalindrome(char *line)
{
    bool pal = true;
    int length = strlen(line) - 1;
    int aux = length - 1;
    int i = 0;

    while (pal && aux > 0)
    {
        pal = pal && (line[aux] == line[i]);
        aux--;
        i++;
    }

    return pal;
}

int main(void)
{
    char buffer[200];
    int length;

    fgets(buffer, 200, stdin);

    while (!(strlen(buffer) == 4 &&
             buffer[0] == 'F' &&
             buffer[1] == 'I' &&
             buffer[2] == 'M'))
    {
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

