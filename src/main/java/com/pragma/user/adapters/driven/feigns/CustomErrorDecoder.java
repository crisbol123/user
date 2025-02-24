package com.pragma.user.adapters.driven.feigns;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {


        if (response.status() != 0) {
            return new Exception();
        }
        return null;
    }
    }

