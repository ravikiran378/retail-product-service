package com.retail.product.error;

import java.util.List;

public abstract class ProductsException extends RuntimeException{

    private final List<ProductAPIErrors.ErrorCode> errorsCodes;
    protected final int httpStatusCode;

    public ProductsException(List<ProductAPIErrors.ErrorCode> errorsCodes, int httpStatusCode) {
        super();
        this.errorsCodes = errorsCodes;
        this.httpStatusCode = httpStatusCode;
    }

    public ErrorResponse getErrorResponse(){
        return new ErrorResponse(httpStatusCode, errorsCodes);
    }

}
