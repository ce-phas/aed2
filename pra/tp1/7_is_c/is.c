#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

/**
 * Is - Programa para testar uma string quanto a sua composicao e
 * mostrar os resultados em uma sequencia, de acordo com os testes:
 * 1 - composta somente por vogais;
 * 2 - composta somente por consoantes;
 * 3 - e' numero inteiro;
 * 4 - e' numero real.
 *
 * @author Pedro Sa (742626)
 * @version 1.0
 * @since 2021-08-20
 */

// metodos auxiliares

/**
 * Converte o caractere recebido em maiusculo, caso seja minusculo.
 * @param c Caractere a converter.
 * @return Caractere convertido, se aplicavel. Caso contrario, retorna
 * o proprio caractere.
 */
char toUpper(char c)
{
    return ('a' <= c && c <= 'z') ? ((char) (c - 32)) : c;
}

/**
 * Verifica se o caractere recebido e' uma letra.
 * @param c Caractere a testar.
 * @return true, se for letra; false, caso contrario.
 */
bool isLetter(char c)
{
    c = toUpper(c);
    return ('A' <= c && c <= 'Z');
}

/**
 * Verifica se o caractere recebido e' uma vogal.
 * @param c Caractere a testar.
 * @return true, se for vogal; false, caso contrario.
 */
bool isVowel(char c)
{
    c  = toUpper(c);
    return (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U');
}

/**
 * Verifica se o caractere recebido e' uma consoante.
 * @param c Caractere a testar.
 * @return true, se for consoante; false, caso contrario.
 */
bool isCons(char c)
{
    return (isLetter(c) && !isVowel(c));
}

/**
 * Verifica se o caractere recebido e' um digito (entre 0 e 9).
 * @param c Caractere a testar.
 * @return true, se for digito; false, caso contrario.
 */
bool isDigit(char c)
{
    return ('0' <= c && c <= '9');
}

// metodos principais

/**
 * Verifica se a string recebida e' composta somente por vogais.
 * @param line Cadeia de caracteres a testar.
 * @return true, se composta exclusivamente por vogais;
 * false, caso contrario.
 */
bool isAllVowel(char *line)
{
    // definir dados
    bool result = true;
    char c;
    int i = 0;
    int n = strlen(line) - 2;

    // repetir enquanto o caractere atual for vogal
    // e i < n
    while (result && i < n)
    {
        c = line[i];

        // verificar se e' uma letra e se e' vogal
        result = isLetter(c) && isVowel(c);

        // incrementar iterador
        i++;
    }

    return result;
}

/**
 * Verifica se a string recebida e' composta somente por consoantes.
 * @param line Cadeia de caracteres a testar.
 * @return true, se composta exclusivamente por consoantes;
 * false, caso contrario.
 */
bool isAllCons(char *line)
{
    // definir dados
    bool result = true;
    char c;
    int i = 0;
    int n = strlen(line) - 2;

    // repetir enquanto o caractere atual nao for vogal
    // e i < n
    while (result && i < n)
    {
        c = line[i];

        // verificar se caractere e' letra e nao e' vogal
        result = isLetter(c) && !(isVowel(c));

        // incrementar iterador
        i++;
    }

    return result;
}

/**
 * Verifica se a string recebida e' equivalente a um numero inteiro.
 * @param line Cadeia de caracteres a testar.
 * @return true, se equivalente a um inteiro; false, caso contrario.
 */
bool isInt(char *line)
{
    // definir dados
    bool result = true;
    int i = 0;
    int n = strlen(line) - 2;

    while (result && i < n)
    {
        // verificar se caractere atual e' digito
        result = isDigit(line[i]);

        // incrementar
        i++;
    }

    return result;
}

/**
 * Verifica se a string recebida e' equivalente a um numero real.
 * @param line Cadeia de caracteres a testar.
 * @return true, se equivalente a um real; false, caso contrario.
 */
bool isFloat(char *line)
{
    // definir dados
    bool result = true;
    char c;
    int i = 0;
    int n = strlen(line) - 2;
    int sep = 0;

    while (result && i < n)
    {
        c = line[i];

        if (isDigit(c))
            // continuar iteracao se caractere atual for digito
            result = true;
        else if (sep < 1 && (c == '.' || c == ','))
        {
            // verificar se caractere atual e' um separador decimal
            // e se e' o unico separador encontrado
            result = true;

            // incrementar quantidade encontrada de separadores
            sep++;
        }
        else
            result = false;

        i++;
    }

    return result;
}

int main(void)
{
    // definir dados
    char buffer[200];

    // ler a primeira string
    fgets(buffer, 200, stdin);

    while (!(buffer[0] == 'F' &&
             buffer[1] == 'I' &&
             buffer[2] == 'M' &&
             strlen(buffer) == 4))
    {
        if (isAllVowel(buffer))
            // verificar se a entrada contem somente vogais
            // e gerar saida
            printf("SIM NAO NAO NAO\n");
        else if (isAllCons(buffer))
            // verificar se a entrada contem somente consoantes
            // e gerar saida
            printf("NAO SIM NAO NAO\n");
        else if (isInt(buffer))
            // verificar se a entrada corresponde a um inteiro e gerar
            // saida (sinalizando que tambem corresponde a um real)
            printf("NAO NAO SIM SIM\n");
        else if (isFloat(buffer))
            // verificar se a entrada corresponde a um numero real
            // e gerar saida
            printf("NAO NAO NAO SIM\n");
        else
            printf("NAO NAO NAO NAO\n");

        // ler nova string
        fgets(buffer, 200, stdin);
    }
}

