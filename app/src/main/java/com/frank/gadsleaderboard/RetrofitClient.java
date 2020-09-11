package com.frank.gadsleaderboard;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://docs.google.com/forms/d/e/";
    private static RetrofitClient mInstance;
    private Retrofit mRetrofit;

    private RetrofitClient() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null){
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public GoogleFormsApi getGoogleFormsApi() {
        return mRetrofit.create(GoogleFormsApi.class);
    }


}
