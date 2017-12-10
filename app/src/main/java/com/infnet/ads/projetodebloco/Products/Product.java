package com.infnet.ads.projetodebloco.Products;

import java.io.Serializable;

public class Product implements Serializable {

    public String id;
    public String name;
    public String productType;
    public String productSubType;
    public String price;
    public String description;
    private Boolean isHalfHalf;
    private int quantity;

    public Product(String productType, String name, String description, String price) {
        this.productType = productType;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductSubType() {
        return productSubType;
    }

    public void setProductSubType(String productSubType) {
        this.productSubType = productSubType;
    }

    public Boolean getHalfHalf() {
        return isHalfHalf;
    }

    public void setHalfHalf(Boolean halfHalf) {
        isHalfHalf = halfHalf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
