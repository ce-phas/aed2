#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define MAX_FIELD_SIZE 100
#define MAX_LINE_SIZE 250
#define PREFIXO "/tmp/series/"

typedef struct {
    char nome[MAX_FIELD_SIZE];
    char formato[MAX_FIELD_SIZE];
    char duracao[MAX_FIELD_SIZE];
    char pais[MAX_FIELD_SIZE];
    char idioma[MAX_FIELD_SIZE];
    char emissora[MAX_FIELD_SIZE];
    char transmissao[MAX_FIELD_SIZE];
    int num_temporadas;
    int num_episodios;
} Serie;

char *remove_line_break(char *line) {
    while (*line != '\r' && *line != '\n') line++;
    *line = '\0';
    return line;
}

char *freadline(char *line, int max_size, FILE *file) {
    return remove_line_break(fgets(line, max_size, file));
}

char *readline(char *line, int max_size) {
    return freadline(line, max_size, stdin);
}

void print_serie(Serie *serie) {
    printf("%s %s %s %s %s %s %s %d %d\n",
        serie->nome,
        serie->formato,
        serie->duracao,
        serie->pais,
        serie->idioma,
        serie->emissora,
        serie->transmissao,
        serie->num_temporadas,
        serie->num_episodios
    );
}

// Retorna o tamanho em bytes de um arquivo.
long tam_arquivo(FILE *file) {
    fseek(file, 0L, SEEK_END);
    long size = ftell(file);
    rewind(file);
    return size;
}

// Retorna todo o conteúdo do arquivo numa string.
char *ler_html(char filename[]) {
    FILE *file = fopen(filename, "r");
    if (!file) {
        fprintf(stderr, "Falha ao abrir arquivo %s\n", filename);
        exit(1);
    }
    long tam = tam_arquivo(file);
    char *html = (char *) malloc(sizeof(char) * (tam + 1));
    fread(html, 1, tam, file);
    fclose(file);
    html[tam] = '\0';
    return html;
}

/**
 * @brief Extrai os textos de uma tag html.
 *
 * @param html Ponteiro para o caractere que abre a tag '<'.
 * @param texto Ponteiro para onde o texto deve ser colocado.
 *
 * @return Ponteiro para o texto extraído.
 */
char *extrair_texto(char *html, char *texto) {
    char *start = texto;
    int contagem = 0;
    while (*html != '\0') {
        if (*html == '<') {
            if (
                (*(html + 1) == 'p') ||
                (*(html + 1) == 'b' && *(html + 2) == 'r') ||
                (*(html + 1) == '/' && *(html + 2) == 'h' && *(html + 3) == '1') ||
                (*(html + 1) == '/' && *(html + 2) == 't' && *(html + 3) == 'h') ||
                (*(html + 1) == '/' && *(html + 2) == 't' && *(html + 3) == 'd')
            ) break;
            else contagem++;
        }
        else if (*html == '>') contagem--;
        else if (contagem == 0 && *html != '"') {
            if (*html == '&') html = strchr(html, ';');
            else if (*html != '\r' && *html != '\n') *texto++ = *html;
        }
        html++;
    }
    *texto = '\0';
    return *start == ' ' ? start + 1 : start;
}

/**
 * @brief Lê o HTML da série e popula os campos da struct.
 *
 * @param serie Struct Serie que vai receber os dados.
 * @param html String contendo todo o HTML do arquivo.
 */
void ler_serie(Serie *serie, char *html) {
    char texto[MAX_FIELD_SIZE];

    char *ptr = strstr(html, "<h1");
    extrair_texto(ptr, texto);

    char *parenteses_ptr = strchr(texto, '(');
    if (parenteses_ptr != NULL) *(parenteses_ptr - 1) = '\0';

    strcpy(serie->nome, texto);

    ptr = strstr(ptr, "<table class=\"infobox_v2\"");

    ptr = strstr(ptr, "Formato");
    ptr = strstr(ptr, "<td");
    strcpy(serie->formato, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "Duração");
    ptr = strstr(ptr, "<td");
    strcpy(serie->duracao, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "País de origem");
    ptr = strstr(ptr, "<td");
    strcpy(serie->pais, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "Idioma original");
    ptr = strstr(ptr, "<td");
    strcpy(serie->idioma, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "Emissora de televisão original");
    ptr = strstr(ptr, "<td");
    strcpy(serie->emissora, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "Transmissão original");
    ptr = strstr(ptr, "<td");
    strcpy(serie->transmissao, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "N.º de temporadas");
    ptr = strstr(ptr, "<td");
    sscanf(extrair_texto(ptr, texto), "%d", &serie->num_temporadas);

    ptr = strstr(ptr, "N.º de episódios");
    ptr = strstr(ptr, "<td");
    sscanf(extrair_texto(ptr, texto), "%d", &serie->num_episodios);
}

/* Definições do registro de célula dupla */

typedef struct CelulaDupla {
    Serie elemento;
    struct CelulaDupla* prox;
    struct CelulaDupla* ant;
} CelulaDupla;

CelulaDupla* novaCelulaDupla(Serie elemento) {
    CelulaDupla *nova = (CelulaDupla*) malloc(sizeof(CelulaDupla));
    nova->elemento = elemento;
    nova->ant = nova->prox = NULL;
    return nova;
}

/* Definições da lista dupla */
CelulaDupla* primeiro;
CelulaDupla* ultimo;

void start() {
    Serie ini = {0};
    primeiro = novaCelulaDupla(ini);
    ultimo = primeiro;
}

void inserir(Serie s) {
    ultimo->prox = novaCelulaDupla(s);
    ultimo->prox->ant = ultimo;
    ultimo = ultimo->prox;
}

int tamanho() {
    int tamanho = 0;
    CelulaDupla* i;
    for (i = primeiro; i != ultimo; i = i->prox) tamanho++;
    return tamanho;
}

void swap(Serie *i, Serie *j) {
    Serie tmp = *i;
    *i = *j;
    *j = tmp;
}

CelulaDupla* getCel(int pos) {
    CelulaDupla* cel = primeiro->prox;
    for (int i = 0; i < pos; i++, cel = cel->prox);
    return cel;
}

char *ltrim(char *s)
{
    while(isspace(*s)) s++;
    return s;
}

char *rtrim(char *s)
{
    char* back = s + strlen(s);
    while(isspace(*--back));
    *(back+1) = '\0';
    return s;
}

char *trim(char *s)
{
    return rtrim(ltrim(s)); 
}

void quicksort(int l, int r) {
    int i = l;
    int j = r;
    Serie p = getCel((l + r) / 2)->elemento;

    while (i <= j) {
        char *s1 = trim(getCel(i)->elemento.pais);
        char *s2 = trim(getCel(j)->elemento.pais);
        char *t1 = trim(getCel(i)->elemento.nome);
        char *t2 = trim(getCel(j)->elemento.nome);

        while (strcmp(s1, p.pais) < 0 || (strcmp(s1, p.pais) == 0 && strcmp(t1, p.nome) < 0)) {
            i++;
            s1 = trim(getCel(i)->elemento.pais);
            t1 = trim(getCel(i)->elemento.nome);
        }

        while (strcmp(s2, p.pais) > 0 || (strcmp(s2, p.pais) == 0 && strcmp(t2, p.nome) > 0)) {
            j--;
            s2 = trim(getCel(j)->elemento.pais);
            t2 = trim(getCel(j)->elemento.nome);
        }

        if (i <= j) {
            swap(&getCel(i)->elemento, &getCel(j)->elemento);
            i++;
            j--;
        }
    }

    if (l < j) quicksort(l, j);
    if (i < r) quicksort(i, r);
}

/**
 * Mostra os array separados por espacos.
 */
void mostrar() {
   CelulaDupla* i;
   for (i = primeiro->prox; i != NULL; i = i->prox) print_serie(&i->elemento);
}

int isFim(char line[]) {
    return line[0] == 'F' && line[1] == 'I' && line[2] == 'M';
}

int main() {
    start();
    Serie serie;
    size_t tam_prefixo = strlen(PREFIXO);
    char line[MAX_LINE_SIZE];

    strcpy(line, PREFIXO);
    readline(line + tam_prefixo, MAX_LINE_SIZE);

    while (!isFim(line + tam_prefixo)) {
        char *html = ler_html(line);
        ler_serie(&serie, html);
        inserir(serie);
        free(html);
        readline(line + tam_prefixo, MAX_LINE_SIZE);
    }

    quicksort(0, tamanho() - 1);
    mostrar();
    return EXIT_SUCCESS;
}