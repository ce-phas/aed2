#include <stdio.h>
#include <stdlib.h>

void fprintFloats(char *fileName, int len)
{
    // definir dados
    FILE *afile = fopen(fileName, "wt");
    int n = 0;
    double x = 0.0;

    while (n < len)
    {
        // ler valor real e imprimir
        scanf("%lf", &x);
        fprintf(afile, "%g\n", x);

        n++;
    }

    fclose(afile);
}

void fshowInv(FILE *afile)
{
    // definir dados
    double x;

    // ler valor
    fscanf(afile, "%lf", &x);

    if (!feof(afile))
    {
        // mostrar recursivamente
        fshowInv(afile);
        printf("%g\n", x);
    }
}

int main(void)
{
    // definir dados
    int n;
    char *fileName = "out.txt";
    scanf("%d", &n);

    // gravar em arquivo
    fprintFloats(fileName, n);

    // mostrar valores a partir do fim
    FILE *afile = fopen(fileName, "rt");
    fshowInv(afile);
    fclose(afile);

    return 0;
}
