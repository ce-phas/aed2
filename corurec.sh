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
# Version: 1.0
# Date:    2021-08-11
#

USAGE="Utilizacao: $0 <nome da classe> <nome da entrada> <nome da saida>"
SUCCESS="SUCESSO: saida correta!"

if [ "$#" -lt "3" ]; then
    echo -e $USAGE;
else
    if gcc -o $1 $1.c; then
        ./$1 < $2.in > $3.out
        if diff -wB $2.out $3.out; then
            echo $SUCCESS;
        fi
    fi
fi
