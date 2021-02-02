package com.retail.product.error;

public class Error {

    private final String errorCode;
    private final String errorDescription;
    private final ProductAPIErrors.ErrorType errorType;

    Error(String errorCode, String errorDescription, ProductAPIErrors.ErrorType errorType){
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
        this.errorType = errorType;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public ProductAPIErrors.ErrorType getErrorType() {
        return errorType;
    }
}
