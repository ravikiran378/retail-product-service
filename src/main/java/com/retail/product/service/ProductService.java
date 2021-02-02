package com.retail.product.service;

import com.retail.product.error.RestExceptionHandler;
import com.retail.product.integration.db.entity.ProductEntity;
import com.retail.product.integration.db.mappers.ProductMapper;
import com.retail.product.integration.db.repository.ProductRepository;
import com.retail.schema.Product;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    private final ProductRepository repo;
    private final ProductMapper productMapper;
    private final RestExceptionHandler restExceptionHandler;

    public ProductService(final ProductRepository repo, final ProductMapper productMapper, final RestExceptionHandler restExceptionHandler) {
        this.repo = repo;
        this.productMapper = productMapper;
        this.restExceptionHandler = restExceptionHandler;
    }

    public Product save(final Product product) {
        Product productOut = null;
        try {
            productOut = productMapper.mapEntityToSchemaObject(repo.save(productMapper.mapSchemaObjectToEntity(product)));
        }catch (Exception e){
            restExceptionHandler.handleException(e);
        }
        return productOut;
    }

    public Product update(final Product product) {
        Product productOut = null;
        try {
            Optional<ProductEntity> possibleProductEntity = repo.findById(Long.valueOf(product.getId()));
            if(possibleProductEntity.isPresent()){
                ProductEntity projectEntity = possibleProductEntity.get();
                projectEntity.setCurrencyType(product.getCurrentPrice().getCurrencyCode());
                projectEntity.setCurrnetPrice(new BigDecimal(product.getCurrentPrice().getCurrencyValue()));
                projectEntity.setProductDescription(product.getProductDescription());
                productOut = productMapper.mapEntityToSchemaObject(repo.save(projectEntity));
            }
        }catch (Exception e){
            restExceptionHandler.handleException(e);
        }
        return productOut;
    }


    public Product get(Long id) {
        Product product = null;
        try {
            ProductEntity productEntity = repo.findById(id).get();
            product = productMapper.mapEntityToSchemaObject(repo.findById(id).get());
        }catch (Exception e){
            restExceptionHandler.handleException(e);
        }
        return product;
    }

    public Product delete(Long id) {
        Product product = null;
        try {
            ProductEntity productEntity = repo.findById(id).get();
            product = productMapper.mapEntityToSchemaObject(repo.findById(id).get());
            productEntity.setProductId(id);
            repo.deleteById(id);
        }catch (Exception e){
            restExceptionHandler.handleException(e);
        }
        return product;
    }
}
