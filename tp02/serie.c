#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

typedef struct Series {
    char nome[50];
    char formato[50];
    char duracao[50];
    char paisDeOrigem[50];
    char idiomaOriginal[50];
    char emissoraDeTelevisao[50];
    char transmissaoOriginal[50];
    int numeroTemporadas;
    int numeroEpisodios;
} Serie;

char *parseTitle(char *old) {
    int i = 0;
    int j = 0;
    int len = strlen(old);
    char *new = (char*) malloc(len * sizeof(char));

    while (i < len) {
        if (old[i] == '<') {
            i++;
            while (old[i] != '>') i++;
        } else {
            i++;
            //while (!(old[i+5] == 'W' && old[i+6] == 'i' && old[i+7] == 'k'))
            while (isalnum(old[i+1]) || old[i+1] == ' ' || old[i+1] == '-')
            {
                new[j] = old[i];
                j++;
                i++;
            }
            i = len;
        }
    }

    new[j] = '\0';
    return new;
}

void ler(char *path, Serie *serie) {
    FILE *fp = fopen(path, "r");
    char buffer[1000];
    char *info;
    fgets(buffer, 1000, fp);

    while (strstr(buffer, "<title>") == NULL) fgets(buffer, 1000, fp);

    info = parseTitle(buffer);
    strcpy(serie->nome, info);
    fgets(buffer, 1000, fp);

    //while (strstr(buffer, "infobox_v2") == NULL) fgets(buffer, 1000, fp);
    free(info);
    fclose(fp);
}

void imprimir(Serie serie) {
    printf("%s\n", serie.nome);
}

int main(void) {
    struct Series serie;
    char buffer[200];
    fgets(buffer, 200, stdin);

    while (strcmp(buffer, "FIM\n") != 0)
    {
        //static const struct Series EmptyStruct;
        char path[80] = "/tmp/series/";
        buffer[strcspn(buffer, "\n")] = 0;
        strcat(path, buffer);
        ler(path, &serie);
        imprimir(serie);
        fgets(buffer, 200, stdin);
        //serie = EmptyStruct;
    }

    return 0;
}