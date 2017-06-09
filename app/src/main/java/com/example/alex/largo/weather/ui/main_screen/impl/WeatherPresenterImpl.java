package com.example.alex.largo.weather.ui.main_screen.impl;

import android.location.Location;

import com.example.alex.largo.weather.api.OpenWeatherService;
import com.example.alex.largo.weather.model.open_weather.OpenWeatherResponse;
import com.example.alex.largo.weather.ui.main_screen.WeatherPresenter;
import com.example.alex.largo.weather.ui.main_screen.WeatherView;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class WeatherPresenterImpl implements WeatherPresenter {

    private static final String API_KEY = "3fd71ee534b49b0c8ec9bfd2f0bb79e9";
    private WeatherView mWeatherView;
    private OpenWeatherService mWeatherService;

    public WeatherPresenterImpl(WeatherView weatherView) {
        mWeatherView = weatherView;
        mWeatherService = OpenWeatherService.retrofit.create(OpenWeatherService.class);
    }

    @Override
    public void getWeatherInfo(Location location) {
        double lat = location.getLatitude();
        double lon = location.getLongitude();
        Observable<OpenWeatherResponse> observable = mWeatherService.getWeatherByCoordinates(lat, lon, API_KEY);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OpenWeatherResponse>() {
                    @Override
                    public void onCompleted() {
                       mWeatherView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mWeatherView.showErrorDialog();
                    }

                    @Override
                    public void onNext(OpenWeatherResponse openWeatherResponse) {
                        mWeatherView.inflateData(openWeatherResponse);
                    }
                });
    }

    @Override
    public void getWeatherInfo(String cityName) {
        Observable<OpenWeatherResponse> observable = mWeatherService.getWeatherByCityName(cityName, API_KEY);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OpenWeatherResponse>() {
                    @Override
                    public void onCompleted() {
                        mWeatherView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mWeatherView.showErrorDialog();
                    }

                    @Override
                    public void onNext(OpenWeatherResponse openWeatherResponse) {
                        mWeatherView.inflateData(openWeatherResponse);
                    }
                });
    }

    @Override
    public void onDestroy() {
        mWeatherView = null;
    }
}
