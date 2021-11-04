#!/bin/bash

SRC="~/puc/clone-aeds2/tps/entrada\ e\ saida/tp02/series/"

if eval cp -rv $SRC "/tmp/"; then
    echo "Directory successfully copied to /tmp/";
fi

