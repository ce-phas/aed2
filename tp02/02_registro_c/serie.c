#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

// definir struct
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


/**
 * Remove as tags de uma linha no formato HTML e outros caracteres indesejados.
 * @param  old  Linha em formato HTML.
 * @return      String contendo o titulo da serie.
 */
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


/**
 * Remove as tags de uma linha no formato HTML e outros caracteres indesejados.
 * @param  old  Linha em formato HTML.
 * @return      String contendo o valor procurado.
 */
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


/**
 * Remove as tags de uma linha no formato HTML e outros caracteres indesejados.
 * @param  old  Linha em formato HTML.
 * @return      Valor procurado, convertido para inteiro.
 */
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


/**
 * Le de um arquivo HTML as linhas contendo os valores que serao designados
 * a cada atributo.
 * @param path Caminho do arquivo no SO.
 * @param serie Ponteiro do objeto a modificar.
 */
void ler(char *path, Serie *serie) {
    // abrir arquivo e definir buffer
    FILE *fp = fopen(path, "r");
    char buffer[2000];

    // ler primeira linha do arquivo
    fgets(buffer, 2000, fp);

    // procurar linha que contem titulo
    while (strstr(buffer, "<title>") == NULL) fgets(buffer, 2000, fp);

    // fazer parse do valor e atribuir
    strcpy(serie->nome, parseTitle(buffer));
    fgets(buffer, 2000, fp);

    // procurar tabela que contem o restante dos dados
    while (strstr(buffer, "infobox_v2") == NULL) fgets(buffer, 2000, fp);

    // buscar as linhas que precedem dado procurado, fazer parse e passar
    // valor ao respectivo atributo
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

    // fechar arquivo
    fclose(fp);
}


/**
 * Mostra na tela os valores de cada atributo, separados por espaco.
 * @param serie Objeto a acessar.
*/
void imprimir(Serie serie) {
    printf("%s %s %s %s %s %s %s %d %d\n",
           serie.nome, serie.formato, serie.duracao, serie.paisDeOrigem,
           serie.idiomaOriginal, serie.emissoraDeTelevisao, serie.transmissaoOriginal,
           serie.numeroTemporadas, serie.numeroEpisodios);
}


/* Main */

int main(void) {
    // criar struct
    struct Series serie;

    // definir buffer e ler nome do arquivo
    char buffer[200];
    fgets(buffer, 200, stdin);

    while (strcmp(buffer, "FIM\n") != 0)
    {
        char path[80] = "/tmp/series/";

        // limpar quebra de linha no buffer
        buffer[strcspn(buffer, "\n")] = 0;

        // concatenar diretorio e nome do arquivo
        strcat(path, buffer);

        // ler arquivo HTML e atribuir valores
        ler(path, &serie);

        // mostrar valores da struct
        imprimir(serie);

        // ler nome do proximo arquivo
        fgets(buffer, 200, stdin);
    }

    return 0;
}
