#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int getYear(int year)
{
    int rem = (year - 1986) % 76;

    if (rem == 0)
    {
        return (year + 76);
    }
    else
    {
        return (year + 76 - rem);
    }
}

int main(void)
{
    int year;
    int next;

    scanf("%d", &year);

    while (year != 0)
    {
        next = getYear(year);
        printf("%d\n", next);
        scanf("%d", &year);
    }
}
