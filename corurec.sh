#!/bin/bash
#
# COmpile, RUn, REview (C language)
#
# Este Bash script compila, executa e verifica a saida
# produzida por exercicios desenvolvidos em C para a
# disciplina de Algoritmos e Estruturas de Dados II, do
# ICEI PUC Minas.
#
# Author:  Pedro Sa (pedro-as)
# Version: 1.1
# Date:    2021-08-11
#

PUBIN=pub.in
PUBOUT=pub.out
USAGE="Utilizacao: $0 <nome do arquivo sem extensao>  <nome da saida>"
SUCCESS="SUCESSO: saída correta!"

if [ "$#" -lt "2" ]; then
    echo -e $USAGE;
else
    if gcc -o $1 $1.c; then
        ./$1 < $PUBIN > $2.out
        if diff -wB $2.out $PUBOUT; then
            echo $SUCCESS;
        fi
    fi
fi
