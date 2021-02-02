package com.retail.product.integration.db.repository;

import com.retail.product.integration.db.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> { }
