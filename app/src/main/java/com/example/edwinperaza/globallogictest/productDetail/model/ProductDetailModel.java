package com.example.edwinperaza.globallogictest.productDetail.model;

import com.example.edwinperaza.globallogictest.product.modelObject.ProductModelObject;

public class ProductDetailModel {

    private ProductModelObject product;

    public ProductDetailModel(ProductModelObject product) {
        this.product = product;
    }

    public ProductModelObject getProduct() {
        return product;
    }
}
