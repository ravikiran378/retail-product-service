package com.retail.product.error;

import org.springframework.http.HttpStatus;

import java.util.List;


public class InternalServerException extends ProductsException{

    public InternalServerException(List<ProductAPIErrors.ErrorCode> errorCodes) {
        super(errorCodes, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

}
