package com.blueweather.app.modules;

import com.blueweather.app.domain.WeatherApi;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by user on 2016/3/9.
 */
public interface ApiInterface {

    String HOST = "https://api.heweather.com/x3/";

    @GET("weather")
    Observable<WeatherApi> mWeatherAPI(@Query("city") String city, @Query("key") String key);

}
