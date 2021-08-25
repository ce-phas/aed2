#include <stdio.h>
#include <stdlib.h>

void fprintFloats(char *fileName, int len)
{
    FILE *afile = fopen(fileName, "wt");
    int n = 0;
    double x = 0.0;

    while (n < len)
    {
        scanf("%lf", &x);
        fprintf(afile, "%g\n", x);

        n++;
    }

    fclose(afile);
}

void fshowInv(FILE *afile)
{
    double x;

    fscanf(afile, "%lf", &x);

    if (!feof(afile))
    {
        fshowInv(afile);
        printf("%g\n", x);
    }
}

int main(void)
{
    int n;
    char *fileName = "out.txt";
    scanf("%d", &n);
    fprintFloats(fileName, n);

    FILE *afile = fopen(fileName, "rt");
    fshowInv(afile);
    fclose(afile);

    return 0;
}
