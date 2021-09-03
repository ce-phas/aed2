#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int getMax(char *s)
{
    int m;
    int f1;
    int f2;
    int f3;
    sscanf(s, "%d %d %d", &m, &f1, &f2);

    f3 = m - f1 - f2;

    int max = f1;

    if (f2 > max)
    {
        max = f2;
    }
    if (f3 > max)
    {
        max = f3;
    }

    return max;
}

int main(void)
{
    int max;
    char in[10];
    fgets(in, 10, stdin);

    while (strlen(in) > 3)
    {
        max = getMax(in);
        printf("%d\n", max);
        fgets(in, 10, stdin);
    }
}
