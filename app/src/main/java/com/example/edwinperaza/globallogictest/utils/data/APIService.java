package com.example.edwinperaza.globallogictest.utils.data;

import com.example.edwinperaza.globallogictest.product.modelObject.ProductModelObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("list")
    Call<List<ProductModelObject>> getProducts();

}