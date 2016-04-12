package com.blueweather.app.domain;

/**
 * Created by user on 2016/3/3.
 */
public class Location {
    private String CityName;
    private String ProvId;
    private String loc;
    private String lat;

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public String getProvId() {
        return ProvId;
    }

    public void setProvId(String provId) {
        ProvId = provId;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
