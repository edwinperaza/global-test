package com.example.edwinperaza.globallogictest.productDetail;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.edwinperaza.globallogictest.MainActivity;
import com.example.edwinperaza.globallogictest.R;
import com.example.edwinperaza.globallogictest.product.modelObject.ProductModelObject;
import com.example.edwinperaza.globallogictest.productDetail.model.ProductDetailModel;
import com.example.edwinperaza.globallogictest.productDetail.presenter.ProductDetailPresenter;
import com.example.edwinperaza.globallogictest.productDetail.view.ProductDetailView;
import com.example.edwinperaza.globallogictest.utils.bus.BusProvider;

public class ProductDetailActivity extends AppCompatActivity {

    private ProductDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ProductModelObject product = (ProductModelObject) getIntent().getSerializableExtra(MainActivity.PRODUCT_TAG);

        presenter = new ProductDetailPresenter(
                new ProductDetailModel(product),
                new ProductDetailView(this, BusProvider.getInstance()));

    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.register(presenter);
        presenter.onResumed();
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(presenter);
    }

}
