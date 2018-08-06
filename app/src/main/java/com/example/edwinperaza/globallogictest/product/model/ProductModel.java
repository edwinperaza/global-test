package com.example.edwinperaza.globallogictest.product.model;

import com.example.edwinperaza.globallogictest.product.modelObject.ProductModelObject;
import com.example.edwinperaza.globallogictest.utils.data.APIService;
import com.squareup.otto.Bus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductModel {

    private APIService apiService;
    private Bus bus;

    public ProductModel(Bus bus, APIService apiService) {
        this.bus = bus;
        this.apiService = apiService;
    }

    public void requestProducts() {
        apiService.getProducts().enqueue(new Callback<List<ProductModelObject>>() {
            @Override
            public void onResponse(Call<List<ProductModelObject>> call, Response<List<ProductModelObject>> response) {
                if (response.isSuccessful()) {
                    List<ProductModelObject> temporalProducts = response.body();
                    List<ProductModelObject> products = new ArrayList<>();
                    if (temporalProducts != null) {
                        for (ProductModelObject pr : temporalProducts) {
                            if (pr != null && pr.isValidProduct()) {
                                products.add(pr);
                            }
                        }
                        if (products.size() > 0) {
                            bus.post(new RequestProductsSuccess(products));
                        } else {
                            bus.post(new RequestProductsConverterFailure());
                        }

                    } else {
                        bus.post(new RequestProductsConverterFailure());
                    }
                } else {
                    bus.post(new RequestProductsConverterFailure());
                }
            }

            @Override
            public void onFailure(Call<List<ProductModelObject>> call, Throwable t) {
                if (t instanceof IOException) {
                    bus.post(new RequestProductsNetworkFailure());
                } else {
                    bus.post(new RequestProductsConverterFailure());
                }
            }
        });
    }

    public class RequestProductsSuccess {
        private List<ProductModelObject> products;

        RequestProductsSuccess(List<ProductModelObject> products) {
            this.products = products;
        }

        public List<ProductModelObject> getProducts() {
            return products;
        }
    }

    public class RequestProductsNetworkFailure {
    }

    public class RequestProductsConverterFailure {
    }
}
