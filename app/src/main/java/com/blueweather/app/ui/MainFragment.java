package com.blueweather.app.ui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.blueweather.app.R;
import com.blueweather.app.base.BaseFragment;
import com.blueweather.app.domain.City;
import com.blueweather.app.domain.Weather;
import com.blueweather.app.domain.WeatherApi;
import com.blueweather.app.modules.*;
import com.blueweather.app.utils.LogUtils;
import com.blueweather.app.utils.WeatherCacheUtil;
import com.yqritc.recyclerviewflexibledivider.VerticalDividerItemDecoration;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by user on 2016/3/3.
 */
public class MainFragment extends BaseFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = MainFragment.class.getSimpleName();
    private Button button;
    private TextView title_tv, mTextViewTMP, mTextViewWarn, mTextViewDesc, mTextViewCity, mTextViewWeek, mTextViewAir;
    private RecyclerView mRecyclerView;
    private ImageView mCloudImgButton, mSettingsImgButton;
    private WeatherAdapter mAdapter;
    private Myhandler mMyhandler;
    BroRecTimer mBroRecTimer;
    private LinearLayoutManager mLayoutManager;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    WeatherCacheUtil mWeatherCacheUtil;
    private Observer<Weather> observer;
    private Weather mWeather;
    private List<City> curCityList;
    private City currentCity;
    private View rootView = null;
    private int curIndex = 0;
    private Time mTime;
    private OnSettingsChangedListener mListener;


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            LocationSingleton.startLocate(mMyhandler);
        }
    };

    public interface OnSettingsChangedListener {
        public void OnSettingsChanged(List<City> cities);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (OnSettingsChangedListener) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_main, container, false);
        initView();
        return rootView;
    }

    @Override
    public void onFirstUserVisible() {
        super.onFirstUserVisible();
        initData();
        requestWeather();
    }

    @Override
    public void onUserVisible() {
        super.onUserVisible();
        requestWeatherByCache();
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (curIndex == 0) {
            Intent intent = new Intent();
            intent.setClass(getActivity(), CityWeatherService.class);
            getActivity().getApplicationContext().stopService(intent);
        }
    }

    /**
     * 初始化参数
     */
    public void initTimer() {
        //启动定时服务
        Intent intent = new Intent(getActivity(), CityWeatherService.class);
        getActivity().startService(intent);
        mBroRecTimer = new BroRecTimer();
        IntentFilter filter = new IntentFilter();
        filter.addAction(CityWeatherService.BROADCASTACTION);
        getActivity().registerReceiver(mBroRecTimer, filter);
    }

    public void initView() {
        button = (Button) rootView.findViewById(R.id.button1);
        title_tv = (TextView) rootView.findViewById(R.id.maintoolbar_title);
        mTextViewTMP = (TextView) rootView.findViewById(R.id.tv_tmp);
        mTextViewCity = (TextView) rootView.findViewById(R.id.tv_city);
        mTextViewDesc = (TextView) rootView.findViewById(R.id.tv_desc);
        mTextViewWeek = (TextView) rootView.findViewById(R.id.tv_week);
        mTextViewWarn = (TextView) rootView.findViewById(R.id.tv_warning);
        mTextViewAir = (TextView) rootView.findViewById(R.id.tv_air_quality);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView_id);
        mCloudImgButton = (ImageView) rootView.findViewById(R.id.cloud_button);
        mSettingsImgButton = (ImageView) rootView.findViewById(R.id.settings_button);
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swiperefreshlayout);
        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.google_blue,
                R.color.google_green,
                R.color.google_red,
                R.color.google_yellow
        );
        //配置RecyclerView布局
        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        button.setOnClickListener(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSettingsImgButton.setOnClickListener(this);
        mCloudImgButton.setOnClickListener(this);
    }

    public void initData() {
        //初始化数据
        LocationSingleton.init(getActivity().getApplicationContext());
        RetrofitSingleton.init(getActivity().getApplicationContext());
        mMyhandler = new Myhandler();
        mWeatherCacheUtil = new WeatherCacheUtil(getActivity().getApplicationContext());
        mTime = new Time();
        curCityList = mSettings.cityList;
        curIndex = getArguments().getInt("curIndex");
        currentCity = curCityList.get(curIndex);
        title_tv.setText(currentCity.cityName);
        LogUtils.i(TAG, "curIndex is " + curIndex + "currentCity is " + currentCity);
        if (curIndex == 0) {
            onRefresh();
            initTimer();
        } else {
            requestWeather();
        }
    }

    @Override
    public void onClick(View v) {
        LogUtils.i(TAG, "onClick");
        switch (v.getId()) {
            case R.id.settings_button: {
                /*
                Intent intent = new Intent(getActivity(), AqiActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(AqiActivity.KEY, mWeather.aqi);
                intent.putExtras(bundle);
                if (mWeather.aqi == null) {
                    Toast.makeText(getActivity().getApplicationContext(), "Please refresh ", Toast.LENGTH_SHORT).show();
                }*/
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                //this.startActivity(intent);
                this.startActivityForResult(intent, 1);
            }
            break;
            case R.id.cloud_button: {
                Intent intent = new Intent(getActivity(), AqiActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(AqiActivity.KEY, mWeather.aqi);
                intent.putExtras(bundle);
                if (mWeather.aqi == null) {
                    Toast.makeText(getActivity().getApplicationContext(), "Please refresh ", Toast.LENGTH_SHORT).show();
                }
                this.startActivity(intent);
            }
            break;
            default:
                break;
        }
    }

    @Override
    public void onRefresh() {
        if (!isNetworkConnected(getActivity().getApplicationContext()))
            requestWeatherByCache();
        else
            requestRecentLocation();
    }

    public void requestWeather() {
        //响应处理
        observer = new Observer<Weather>() {
            @Override
            public void onCompleted() {
                LogUtils.i(TAG, "Refresh success");
                //Toast.makeText(getActivity(), "Refresh success", Toast.LENGTH_SHORT).show();
                if (mSwipeRefreshLayout.isRefreshing())
                    mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "ERROR", e);

                if (mSwipeRefreshLayout.isRefreshing())
                    mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onNext(Weather weather) {
                if (!weather.basic.city.equals(currentCity.cityName)) {
                    return;
                }

                mWeather = weather;
                mTextViewTMP.setText(weather.now.tmp + getResources().getString(R.string.temperature_metric));
                //mTextViewWarn.setText(weather.now.cond.txt);
                mTextViewWarn.setVisibility(View.GONE);
                mTextViewDesc.setText(weather.now.cond.txt);
                try {
                    mTextViewWeek.setText(mTime.getWeek(weather.dailyForecast.get(0).date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                mTextViewCity.setText(weather.basic.city);
                if (weather.aqi != null)
                    mTextViewAir.setText("空气质量 " + weather.aqi.city.qlty);
                else {
                    mTextViewAir.setVisibility(View.INVISIBLE);
                }
                mAdapter = new WeatherAdapter(weather.dailyForecast, getActivity().getApplicationContext());
                mRecyclerView.addItemDecoration(new VerticalDividerItemDecoration.Builder(getActivity().getApplicationContext()).marginResId(R.dimen.divider_margin, R.dimen.divider_margin).build());
                mRecyclerView.setAdapter(mAdapter);
            }
        };
        requestWeatherByCache();
    }

    public void requestRecentLocation() {
        //请求地理位置
        mMyhandler.post(runnable);
    }

    public void requestWeatherByNnetwork() {
        //拼接城市列表为字符串
        String cityStr = curCityList.get(0).cityName;
        for (int i = 1; i < curCityList.size(); i++) {
            cityStr = cityStr + '、' + curCityList.get(i).cityName;
        }
        LogUtils.i(TAG, "cityString is " + cityStr);
        //循环获取多个城市天气
        Observable.from(curCityList)
                .flatMap(new Func1<City, Observable<WeatherApi>>() {
                    @Override
                    public Observable<WeatherApi> call(City city) {
                        return RetrofitSingleton.getApiService(getActivity().getApplicationContext()).mWeatherAPI(city.cityName, Settings.KEY);
                    }
                })
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<WeatherApi, Observable<Weather>>() {
                    @Override
                    public Observable<Weather> call(WeatherApi weatherApi) {
                        return Observable.just(weatherApi.mHeWeatherDataService30s.get(0));
                    }
                })
                .filter(new Func1<Weather, Boolean>() {//过滤并存储对应的天气信息
                    @Override
                    public Boolean call(Weather weather) {
                        //存入到缓存中
                        if (weather.status.equals("ok")) {
                            try {
                                mWeatherCacheUtil.putWeather(weather.basic.city, weather);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        LogUtils.i(TAG, "weather is " + weather.basic.city);
                        return weather.status.equals("ok");
                    }
                })
                .subscribe(observer);
    }

    /**
     * 请求缓存数据
     */
    public void requestWeatherByCache() {

        Observable.just(currentCity)
                .map(new Func1<City, Weather>() {
                    @Override
                    public Weather call(City city) {
                        try {
                            return mWeatherCacheUtil.getWeather(city.cityName);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public class Myhandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1: {
                    //接收到地址信息后停止请求城市信息
                    LocationSingleton.mLocationClient.stop();
                    //设置显示到界面上
                    //网络请求天气信息
                    LogUtils.i(TAG, "handleMessage");
                    curCityList.get(0).cityName = msg.getData().getString(LocationSingleton.TAG);
                    requestWeatherByNnetwork();
                }
                break;
                default:
                    break;
            }
        }
    }

    public class BroRecTimer extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            onRefresh();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtils.i(TAG, "requestcode = " + requestCode + "resultCode = " + resultCode);
        if (requestCode == 1) {
            mListener.OnSettingsChanged(curCityList);
        }
    }
}
