package com.retail.product.integration.db.mappers;

import com.retail.product.integration.db.entity.ProductEntity;
import com.retail.schema.Price;
import com.retail.schema.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;

@Component
public class ProductMapper {

    public ProductEntity mapSchemaObjectToEntity(Product product){

        ProductEntity productEntity = new ProductEntity();
        if(product.getId()!=null) {
            productEntity.setProductId(Long.valueOf(product.getId()));
        }
        productEntity.setProductDescription(product.getProductDescription());
        productEntity.setCurrencyType(product.getCurrentPrice().getCurrencyCode());
        productEntity.setCurrnetPrice(new BigDecimal(product.getCurrentPrice().getCurrencyValue()));
        productEntity.setCreateDate(Instant.now());
        productEntity.setUpdateDate(Instant.now());
        return productEntity;

    }

    public Product mapEntityToSchemaObject(ProductEntity productEntity){
        Product product = new Product();
        product.setId(productEntity.getProductId().toString());
        product.setProductDescription(productEntity.getProductDescription());
        Price currentPrice = new Price();
        currentPrice.setCurrencyCode(productEntity.getCurrencyType());
        currentPrice.setCurrencyValue(productEntity.getCurrnetPrice().toString());
        product.setCurrentPrice(currentPrice);
        return product;
    }
}
