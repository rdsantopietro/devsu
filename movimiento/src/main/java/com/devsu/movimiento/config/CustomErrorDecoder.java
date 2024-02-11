package com.devsu.movimiento.config;

import com.devsu.movimiento.enums.ErrorCodes;
import com.devsu.movimiento.service.error.MoreThanOneResultException;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == ErrorCodes.BUSQUEDA_MAS_DE_UN_ELEMENTO.getCODE()) {
            return new MoreThanOneResultException();
        }
        return FeignException.errorStatus(methodKey, response);
    }
}
