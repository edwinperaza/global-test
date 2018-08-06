package com.example.edwinperaza.globallogictest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.edwinperaza.globallogictest.product.model.ProductModel;
import com.example.edwinperaza.globallogictest.product.presenter.ProductPresenter;
import com.example.edwinperaza.globallogictest.product.view.ProductView;
import com.example.edwinperaza.globallogictest.utils.bus.BusProvider;
import com.example.edwinperaza.globallogictest.utils.data.ApiUtils;

public class MainActivity extends AppCompatActivity {

    public final static String PRODUCT_TAG = "Product";
    private ProductPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new ProductPresenter(
                new ProductModel(BusProvider.getInstance(), ApiUtils.getAPIService()),
                new ProductView(this, BusProvider.getInstance()));

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
