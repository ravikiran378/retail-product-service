package com.retail.product.error;

public class ProductAPIErrors {
    public static enum ErrorType {SERVER, CLIENT, UNKNOWN}
    public static enum ErrorCode {

        PRODUCT_ERROR_001("PRODUCT_ERROR_001","No ID is allowed while creating Product",ErrorType.CLIENT),
        PRODUCT_ERROR_006("PRODUCT_ERROR_006","Product ID is required",ErrorType.CLIENT),
        PRODUCT_ERROR_007("PRODUCT_ERROR_007","Bad Product ID.",ErrorType.CLIENT),
        PRODUCT_ERROR_008("PRODUCT_ERROR_008","Invalid Price.",ErrorType.CLIENT),
        PRODUCT_ERROR_002("PRODUCT_ERROR_002","Current price is mandatory",ErrorType.CLIENT),
        PRODUCT_ERROR_003("PRODUCT_ERROR_003","Product Description is mandatory",ErrorType.CLIENT),
        PRODUCT_ERROR_004("PRODUCT_ERROR_004","Database not reachable",ErrorType.SERVER),
        PRODUCT_ERROR_005("PRODUCT_ERROR_005","No Product ID found",ErrorType.CLIENT),
        PRODUCT_ERROR_UNK("PRODUCT_ERROR_UNK","Unknown issue", ErrorType.UNKNOWN);
        ;

        private final String errorCode;
        private final String errorDescription;
        private final ErrorType errorType;

        ErrorCode(String errorCode, String errorDescription, ErrorType errorType){
            this.errorCode = errorCode;
            this.errorDescription = errorDescription;
            this.errorType = errorType;
        }

        public Error getError(){
            return new Error(errorCode, errorDescription, errorType);
        }

    }

}
