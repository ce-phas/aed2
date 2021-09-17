#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

typedef struct Series {
    char nome[100];
    char formato[100];
    char duracao[100];
    char paisDeOrigem[100];
    char idiomaOriginal[100];
    char emissoraDeTelevisao[100];
    char transmissaoOriginal[100];
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
            while (isalnum(old[i+1]) || old[i+1] == ' ' ||
                   old[i+1] == '\'' || old[i+1] == '-')
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

char *parseHtml(char *old) {
    int i = 0;
    int j = 0;
    int len = strlen(old);
    char *new = (char*) malloc(len * sizeof(char));
    len -= 2;

    while (i < len) {
        if (old[i] == '<') {
            i++;
            while (old[i] != '>') i++;
        } else if (old[i+1] == '&') {
            i += 6;
        } else {
            i++;
            while (old[i] != '<')
            {
                new[j] = old[i];
                j++;
                i++;
            }
        }
    }

    new[j] = '\0';
    return new;
}

int parseInt(char *old) {
    int i = 0;
    int j = 0;
    int len = strlen(old);
    char *new = (char*) malloc(len * sizeof(char));

    while (i < len) {
        if (old[i] == '<') {
            i++;
            while (old[i] != '>') i++;
            i++;
        } else if (old[i] == '&') {
            i += 6;
        } else {
            while (isdigit(old[i]))
            {
                new[j] = old[i];
                j++;
                i++;
            }
            i = len;
        }
    }

    new[j] = '\0';
    return atoi(new);
}

void ler(char *path, Serie *serie) {
    FILE *fp = fopen(path, "r");
    char buffer[2000];

    fgets(buffer, 2000, fp);

    while (strstr(buffer, "<title>") == NULL) fgets(buffer, 2000, fp);
    strcpy(serie->nome, parseTitle(buffer));
    fgets(buffer, 2000, fp);


    while (strstr(buffer, "infobox_v2") == NULL) fgets(buffer, 2000, fp);
    while (strstr(buffer, "Formato") == NULL) fgets(buffer, 2000, fp);
    fgets(buffer, 2000, fp);
    strcpy(serie->formato, parseHtml(buffer));

    while (strstr(buffer, "Duração") == NULL) fgets(buffer, 2000, fp);
    fgets(buffer, 2000, fp);
    strcpy(serie->duracao, parseHtml(buffer));

    while (strstr(buffer, "País de origem") == NULL) fgets(buffer, 2000, fp);
    fgets(buffer, 2000, fp);
    strcpy(serie->paisDeOrigem, parseHtml(buffer));

    while (strstr(buffer, "Idioma original") == NULL) fgets(buffer, 2000, fp);
    fgets(buffer, 2000, fp);
    strcpy(serie->idiomaOriginal, parseHtml(buffer));

    while (strstr(buffer, "Emissora") == NULL) fgets(buffer, 2000, fp);
    fgets(buffer, 2000, fp);
    strcpy(serie->emissoraDeTelevisao, parseHtml(buffer));

    while (strstr(buffer, "Transmissão") == NULL) fgets(buffer, 2000, fp);
    fgets(buffer, 2000, fp);
    strcpy(serie->transmissaoOriginal, parseHtml(buffer));

    while (strstr(buffer, "N.º de temporadas") == NULL) fgets(buffer, 2000, fp);
    fgets(buffer, 2000, fp);
    serie->numeroTemporadas = parseInt(buffer);

    while (strstr(buffer, "N.º de episódios") == NULL) fgets(buffer, 2000, fp);
    fgets(buffer, 2000, fp);
    serie->numeroEpisodios = parseInt(buffer);

    fclose(fp);
}

void imprimir(Serie serie) {
    printf("%s %s %s %s %s %s %s %d %d\n",
           serie.nome, serie.formato, serie.duracao, serie.paisDeOrigem,
           serie.idiomaOriginal, serie.emissoraDeTelevisao, serie.transmissaoOriginal,
           serie.numeroTemporadas, serie.numeroEpisodios);
}

int main(void) {
    struct Series serie;
    char buffer[200];
    fgets(buffer, 200, stdin);

    while (strcmp(buffer, "FIM\n") != 0)
    {
        char path[80] = "/tmp/series/";
        buffer[strcspn(buffer, "\n")] = 0;
        strcat(path, buffer);
        ler(path, &serie);
        imprimir(serie);
        fgets(buffer, 200, stdin);
    }

    return 0;
}