package com.devsu.cliente.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodes {

    SALDO_INSUFICIENTE (799, "SALDO_INSUFICIENTE"),
    BUSQUEDA_MAS_DE_UN_ELEMENTO(798, "BUSQUEDA_MAS_DE_UN_ELEMENTO");
    private final int CODE;
    private final  String description;


}
