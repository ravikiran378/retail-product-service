package com.retail.product.resource;

import com.retail.product.error.BadRequestException;
import com.retail.product.error.ProductAPIErrors;
import com.retail.product.integrity.Validator;
import com.retail.product.service.ProductService;
import com.retail.schema.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping(ProductResourceService.PRODUCT_URL)
public class ProductResource implements ProductResourceService {

    private static final Logger log = LogManager.getLogger(ProductResource.class);

    private final ProductService productService;
    private final Validator validator;

    public ProductResource(ProductService productService, Validator validator) {
        this.productService = productService;
        this.validator = validator;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Product createProduct(@RequestBody final Product product) {
        log.info("Create Product Requested, {}", product);
        validator.validateProductObject(product, Validator.ProductObjectValidatorStategy.CREATE);
        return productService.save(product);
    }

    @Override
    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Product getProductByID(@PathVariable final String productId) {
        log.info("Get Product Requested, {}", productId);
        validator.validateProductID(productId);
        return productService.get(Long.valueOf(productId));
    }

    @Override
    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public @ResponseBody Product updateProduct(@RequestBody final Product product) {
        log.info("Update Product Requested, {}", product);
        validator.validateProductObject(product, Validator.ProductObjectValidatorStategy.UPDATE);
        Product productOut = productService.update(product);
        if(productOut==null){
            throw new BadRequestException(Arrays.asList(ProductAPIErrors.ErrorCode.PRODUCT_ERROR_005));
        }
        return productOut;
    }

    @Override
    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public @ResponseBody Product deleteProduct(@PathVariable final String productId) {
        log.info("Delete Product Requested, {}", productId);
        validator.validateProductID(productId);
        return productService.delete(Long.valueOf(productId));
    }
}
