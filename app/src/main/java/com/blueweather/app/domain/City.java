package com.blueweather.app.domain;

import java.io.Serializable;

/**
 * Created by user on 2016/4/4.
 */
public class City implements Serializable {
    public String cityName;
    public int ProID;

    public City(){

    }
    public City(String cityName) {
        this.cityName = cityName;
    }
}
