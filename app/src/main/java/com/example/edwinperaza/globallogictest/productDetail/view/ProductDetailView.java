package com.example.edwinperaza.globallogictest.productDetail.view;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.edwinperaza.globallogictest.R;
import com.example.edwinperaza.globallogictest.product.modelObject.ProductModelObject;
import com.squareup.otto.Bus;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailView {

    @BindView(R.id.iv_product_detail_image) ImageView image;
    @BindView(R.id.tv_product_detail_description) TextView description;

    private Bus bus;
    private AppCompatActivity activity;

    public ProductDetailView(AppCompatActivity activity, final Bus bus) {
        this.bus = bus;
        this.activity = activity;
        ButterKnife.bind(this, activity);
    }

    public void showProduct(ProductModelObject product) {
        Picasso.get().load(product.getImage()).into(image);
        description.setText(product.getDescription());
    }
}
