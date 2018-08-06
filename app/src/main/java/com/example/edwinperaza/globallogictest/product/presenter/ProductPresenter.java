package com.example.edwinperaza.globallogictest.product.presenter;

import com.example.edwinperaza.globallogictest.product.model.ProductModel;
import com.example.edwinperaza.globallogictest.product.view.ProductView;
import com.squareup.otto.Subscribe;

public class ProductPresenter {

    private ProductModel model;
    private ProductView view;

    public ProductPresenter(ProductModel model, ProductView view) {
        this.model = model;
        this.view = view;

    }

    public void onResumed() {
        this.model.requestProducts();
    }

    @Subscribe
    public void onProductsResponseSuccessful(ProductModel.RequestProductsSuccess event) {
        view.updateProducts(event.getProducts());
    }

    @Subscribe
    public void onProductsResponseFailure(ProductModel.RequestProductsNetworkFailure event) {
        view.showNetworkFailure();
    }

    @Subscribe
    public void onProductsResponseFailure(ProductModel.RequestProductsConverterFailure event) {
        view.showConverterFailure();
    }

    @Subscribe
    public void onProductClickListenerEvent(ProductView.ProductClickListenerEvent event) {
        view.showProduct(event.getProduct());
    }

}
