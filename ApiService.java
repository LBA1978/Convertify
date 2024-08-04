package com.example.convertify.network;

import com.example.convertify.model.ExchangeRate;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("latest")
    Call<ExchangeRate> getExchangeRate(@Query("base") String base, @Query("symbols") String symbols);
}
