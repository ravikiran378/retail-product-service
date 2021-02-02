package com.retail.product.resource;

import com.retail.schema.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface ProductResourceService {

    String PRODUCT_URL = "v1/product";

    Product createProduct(Product product);
    Product getProductByID(String productId);
    Product updateProduct(Product product);
    Product deleteProduct(String productId);
}
