package com.example.edwinperaza.globallogictest.productDetail.presenter;

import com.example.edwinperaza.globallogictest.productDetail.model.ProductDetailModel;
import com.example.edwinperaza.globallogictest.productDetail.view.ProductDetailView;

public class ProductDetailPresenter  {

    private ProductDetailModel model;
    private ProductDetailView view;

    public ProductDetailPresenter(ProductDetailModel model, ProductDetailView view) {
        this.model = model;
        this.view = view;
    }

    public void onResumed(){
        view.showProduct(model.getProduct());
    }

}
