package com.blueweather.app.modules;

import com.blueweather.app.domain.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/12.
 */
public class Settings {
    public static final String KEY = "29c9e5a3eb814981919a5afcdaeebba9";
    //最大缓存数值，大约4MB
    public static final int MAXCACHESIZE = (int) (Runtime.getRuntime().maxMemory() / 8);
    //更新时间，默认为1小时
    public static int update_time = 60 * 60 * 1000;
    public boolean isUpdating = true;
    //城市列表
    public static List<City> cityList;
    //选择添加的城市
    public static final String Chosen_CITY_NAME = "城市";//选择城市


    private static Settings settings;

    public static Settings newInstance() {

        if (settings == null) {
            settings = new Settings();
            cityList = new ArrayList<City>();
            cityList.add(new City("广州"));
            cityList.add(new City("阳江"));
        }
        return settings;
    }


}
