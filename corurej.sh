#!/bin/bash
#
# COmpile, RUn, REview (Java)
#
# Este Bash script compila, executa e verifica a saida
# produzida por exercicios desenvolvidos em Java para a
# disciplina de Algoritmos e Estruturas de Dados II, do
# ICEI PUC Minas.
#
# Author:  Pedro Sa (pedro-as)
# Version: 1.0
# Date:    2021-08-04
#

PUBIN=pub.in
PUBOUT=pub.out
USAGE="Utilizacao: $0 <nome da classe> <nome da saida>"
SUCCESS="SUCESSO: saida correta!"

if [ "$#" -lt "2" ]; then
    echo -e $USAGE;
else
    if javac $1.java; then
        java $1 < $PUBIN > $2.out
        if diff -wB $PUBOUT $2.out; then
            echo $SUCCESS;
        fi
    fi
fi
