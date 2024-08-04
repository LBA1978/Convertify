package com.example.convertify.data;

import com.example.convertify.model.ExchangeRate;
import com.example.convertify.network.ApiClient;
import com.example.convertify.network.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrencyRepository {
    private ApiService apiService;

    public CurrencyRepository() {
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    public void getExchangeRate(String from, String to, ExchangeRateCallback callback) {
        Call<ExchangeRate> call = apiService.getExchangeRate(from, to);
        call.enqueue(new Callback<ExchangeRate>() {
            @Override
            public void onResponse(Call<ExchangeRate> call, Response<ExchangeRate> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(new Throwable("Error fetching exchange rate"));
                }
            }

            @Override
            public void onFailure(Call<ExchangeRate> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public interface ExchangeRateCallback {
        void onSuccess(ExchangeRate exchangeRate);
        void onFailure(Throwable t);
    }
}
