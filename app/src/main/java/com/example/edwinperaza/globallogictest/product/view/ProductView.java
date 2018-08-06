package com.example.edwinperaza.globallogictest.product.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.edwinperaza.globallogictest.MainActivity;
import com.example.edwinperaza.globallogictest.R;
import com.example.edwinperaza.globallogictest.product.adapter.ProductAdapter;
import com.example.edwinperaza.globallogictest.product.modelObject.ProductModelObject;
import com.example.edwinperaza.globallogictest.productDetail.ProductDetailActivity;
import com.example.edwinperaza.globallogictest.utils.listeners.RecyclerViewListener;
import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductView {

    @BindView(R.id.rv_products) RecyclerView rvProducts;
    @BindView(R.id.lv_loading) LottieAnimationView loading;

    private Bus bus;
    private ProductAdapter adapter;
    private AppCompatActivity activity;

    public ProductView(AppCompatActivity activity, final Bus bus) {
        this.bus = bus;
        this.activity = activity;
        ButterKnife.bind(this, activity);
        showLoadingOverlay();
        LinearLayoutManager llm = new LinearLayoutManager(activity);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvProducts.setLayoutManager(llm);

        adapter = new ProductAdapter(new ArrayList<ProductModelObject>());
        adapter.setListener(new RecyclerViewListener<ProductModelObject>() {
            @Override
            public void onItemClickListener(ProductModelObject product) {
                bus.post(new ProductClickListenerEvent(product));
            }
        });

        rvProducts.setAdapter(adapter);
    }

    public void updateProducts(List<ProductModelObject> products) {
        if (adapter != null) {
            adapter.setProducts(products);
            hideLoadingOverlay();
        }
    }

    public void showNetworkFailure() {
        hideLoadingOverlay();
        Toast.makeText(activity, R.string.product_list_network_error, Toast.LENGTH_LONG).show();
    }

    public void showConverterFailure() {
        hideLoadingOverlay();
        Toast.makeText(activity, R.string.product_list_converter_error, Toast.LENGTH_LONG).show();
    }

    public void showProduct(ProductModelObject product) {
        Intent intent = new Intent(activity, ProductDetailActivity.class);
        intent.putExtra(MainActivity.PRODUCT_TAG, product);
        activity.startActivity(intent);
    }

    public void showLoadingOverlay() {
        if (!isLoadingOverlayShowing()) {
            loading.bringToFront();
            loading.setVisibility(View.VISIBLE);
        }
    }

    public void hideLoadingOverlay() {
        if (isLoadingOverlayShowing()) {
            loading.setVisibility(View.GONE);
        }
    }

    private boolean isLoadingOverlayShowing() {
        return loading.getVisibility() == View.VISIBLE;
    }


    public static class ProductClickListenerEvent {

        private ProductModelObject product;

        ProductClickListenerEvent(ProductModelObject product) {
            this.product = product;
        }

        public ProductModelObject getProduct() {
            return product;
        }
    }
}
