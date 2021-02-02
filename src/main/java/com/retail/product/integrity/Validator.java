package com.retail.product.integrity;

import com.retail.product.error.BadRequestException;
import com.retail.product.error.ProductAPIErrors;
import com.retail.schema.Product;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class Validator {

    public static enum ProductObjectValidatorStategy{
        CREATE, UPDATE
    }

    public void validateProductObject(Product product, ProductObjectValidatorStategy productObjectValidatorStategy){
        List<ProductAPIErrors.ErrorCode> errorCodes = new ArrayList<>();
        if(ProductObjectValidatorStategy.CREATE.equals(productObjectValidatorStategy)){
            if(product.getId()!=null){
                errorCodes.add(ProductAPIErrors.ErrorCode.PRODUCT_ERROR_001);
            }
        }
        if(ProductObjectValidatorStategy.UPDATE.equals(productObjectValidatorStategy)){
            if(product.getId()==null){
                errorCodes.add(ProductAPIErrors.ErrorCode.PRODUCT_ERROR_006);
            }
            if(product.getId()!=null && !isValidProductID(product.getId())){
                errorCodes.add(ProductAPIErrors.ErrorCode.PRODUCT_ERROR_007);
            }
        }
        if(product.getCurrentPrice()==null || product.getCurrentPrice().getCurrencyValue()==null){
            errorCodes.add(ProductAPIErrors.ErrorCode.PRODUCT_ERROR_002);
        }

        if(product.getCurrentPrice()==null || product.getCurrentPrice().getCurrencyValue()==null || !isValidCurrency(product.getCurrentPrice().getCurrencyValue())){
            errorCodes.add(ProductAPIErrors.ErrorCode.PRODUCT_ERROR_008);
        }

        if(ObjectUtils.isEmpty(product.getProductDescription())){
            errorCodes.add(ProductAPIErrors.ErrorCode.PRODUCT_ERROR_003);
        }

        if(!errorCodes.isEmpty()){
            throw new BadRequestException(errorCodes);
        }
    }

    private boolean isValidCurrency(String currencyValue) {
        boolean isValid = true;
        try{
            new BigDecimal(currencyValue);
        }catch (Exception e){
            isValid = false;
        }
        return isValid;
    }


    public void validateProductID(String productID){
      List<ProductAPIErrors.ErrorCode> errorCodes = new ArrayList<>();
      if(productID==null){
          errorCodes.add(ProductAPIErrors.ErrorCode.PRODUCT_ERROR_006);
      }
      if(productID!=null && !isValidProductID(productID)){
          errorCodes.add(ProductAPIErrors.ErrorCode.PRODUCT_ERROR_007);
      }
      if(!errorCodes.isEmpty()){
          throw new BadRequestException(errorCodes);
      }
  }

    public boolean isValidProductID(String productId){
        boolean isValid = true;
        try{
            Long.valueOf(productId);
        }catch (Exception e){
            isValid = false;
        }
        return isValid;
    }
}
