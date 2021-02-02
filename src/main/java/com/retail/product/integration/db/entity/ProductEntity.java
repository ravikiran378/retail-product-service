package com.retail.product.integration.db.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name="PRODUCT")
public class ProductEntity {

    @Id
    @Column(name="PRODUCT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(name="CURRENT_PRICE")
    private BigDecimal currnetPrice;
    @Column(name="CURRENCY_TYPE")
    private String currencyType;
    @Column(name="PRODUCT_DESC")
    private String productDescription;
    @Column(name="CREATE_DATE")
    private Instant createDate;
    @Column(name="UPDATE_DATE")
    private Instant updateDate;


    public BigDecimal getCurrnetPrice() {
        return currnetPrice;
    }

    public void setCurrnetPrice(BigDecimal currnetPrice) {
        this.currnetPrice = currnetPrice;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

}
