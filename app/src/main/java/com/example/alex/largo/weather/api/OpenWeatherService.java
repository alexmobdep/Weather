package com.example.alex.largo.weather.api;


import com.example.alex.largo.weather.model.open_weather.OpenWeatherResponse;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface OpenWeatherService {

    Retrofit retrofit = new Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://api.openweathermap.org/")
            .build();

    @GET("data/2.5/weather?")
    Observable<OpenWeatherResponse> getWeatherByCoordinates(@Query("lat") double lat,
                                                            @Query("lon") double lon,
                                                            @Query("appid") String apiKey);
    @GET("data/2.5/weather?")
    Observable<OpenWeatherResponse> getWeatherByCityName(@Query("q") String cityName,
                                                         @Query("appid") String apiKey);
}
