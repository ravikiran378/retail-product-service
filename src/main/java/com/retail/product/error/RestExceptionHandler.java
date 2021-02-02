package com.retail.product.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.NoSuchElementException;

@ControllerAdvice
public class RestExceptionHandler {

    public void handleException(Throwable t){
        if(t instanceof NoSuchElementException){
            throw new BadRequestException(Arrays.asList(ProductAPIErrors.ErrorCode.PRODUCT_ERROR_005));
        }
        throw new InternalServerException(Arrays.asList(ProductAPIErrors.ErrorCode.PRODUCT_ERROR_UNK));
    }

    @ExceptionHandler(value = ProductsException.class)
    public ResponseEntity<ErrorResponse> exception(ProductsException exception) {
        return new ResponseEntity<ErrorResponse>(exception.getErrorResponse(), HttpStatus.valueOf(exception.httpStatusCode==0?500:exception.httpStatusCode));
    }

}
