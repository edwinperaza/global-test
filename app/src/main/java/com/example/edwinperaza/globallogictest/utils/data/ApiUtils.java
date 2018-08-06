package com.example.edwinperaza.globallogictest.utils.data;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "http://private-f0eea-mobilegllatam.apiary-mock.com/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
