package com.retail.product.error;

import org.springframework.http.HttpStatus;

import java.util.List;

public class BadRequestException extends ProductsException{
    public BadRequestException(List<ProductAPIErrors.ErrorCode> errorsCodes) {
        super(errorsCodes, HttpStatus.BAD_REQUEST.value());

    }
}
