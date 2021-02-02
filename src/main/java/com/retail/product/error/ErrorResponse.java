package com.retail.product.error;

import java.util.List;
import java.util.stream.Collectors;

public class ErrorResponse {

    private int httpStatusCode;
    private List<Error> errors;

    public ErrorResponse(int httpStatusCode, List<ProductAPIErrors.ErrorCode> errorsCodes) {
        this.httpStatusCode = httpStatusCode;
        errors = errorsCodes.stream().map(errorCode -> errorCode.getError()).collect(Collectors.toList());
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }
}
