package com.blueweather.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.blueweather.app.R;
import com.blueweather.app.base.BaseActivity;
import com.blueweather.app.db.DBManager;
import com.blueweather.app.db.WeatherDB;
import com.blueweather.app.domain.City;
import com.blueweather.app.domain.Province;
import com.blueweather.app.modules.SelectAdapter;
import com.blueweather.app.utils.LogUtils;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016/4/9.
 */
public class SelectActivity extends BaseActivity implements View.OnClickListener {

    private static String TAG = SelectActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private DBManager mDBManager;
    private WeatherDB mWeatherDB;
    private TextView mTitle;
    private EditText mSearchEditText;
    private ImageView mDeleteImageView, mCrossButtonImg;

    private ArrayList<String> dataList = new ArrayList<String>();
    private Province selectedProvince;
    private City selectedCity;
    private List<Province> provincesList;
    private List<City> cityList;
    private List<City> allCityList = new ArrayList<City>();
    private SelectAdapter mAdapter;

    public static final int LEVEL_PROVINCE = 1;
    public static final int LEVEL_CITY = 2;
    private int currentLevel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);
        mDBManager = new DBManager(this);
        mDBManager.openDatabase();
        mWeatherDB = new WeatherDB(this);
        initView();
        initRecyclerView();
        queryProvinces();
        loadAllCities();
    }


    private void initView() {
        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setTitle("选择城市");
        //setSupportActionBar(toolbar);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mSearchEditText = (EditText) findViewById(R.id.search_et_input);
        mDeleteImageView = (ImageView) findViewById(R.id.search_iv_delete);
        mCrossButtonImg = (ImageView) findViewById(R.id.cross_button);
        mTitle = (TextView) findViewById(R.id.selectcitytoolbar_title);
        mDeleteImageView.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
        mSearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    mDeleteImageView.setVisibility(View.INVISIBLE);
                    queryProvinces();
                } else {
                    mDeleteImageView.setVisibility(View.VISIBLE);
                    searchCity(mSearchEditText.getText().toString());
                    mTitle.setText("搜索城市");
                }
            }
        });
        mDeleteImageView.setOnClickListener(this);
        mCrossButtonImg.setOnClickListener(this);
    }


    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.select_city_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getApplicationContext()).marginResId(R.dimen.divider_margin, R.dimen.divider_margin).build());
        mAdapter = new SelectAdapter(this, dataList);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new SelectAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, final int pos) {
                if (currentLevel == LEVEL_PROVINCE) {
                    selectedProvince = provincesList.get(pos);
                    mRecyclerView.scrollTo(0, 0);
                    queryCities();
                } else if (currentLevel == LEVEL_CITY) {
                    selectedCity = cityList.get(pos);
                    Intent intent = new Intent();
                    String cityName = selectedCity.cityName.replace("市", "");
                    intent.putExtra("selectedCity", cityName);
                    setResult(2, intent);
                    LogUtils.i(TAG, "send city " + cityName);
                    finish();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_iv_delete: {
                mSearchEditText.setText("");
                //queryProvinces();
            }
            break;
            case R.id.cross_button: {
                finish();
            }
            break;
            default:
                break;
        }
    }

    /**
     * 查询全国所有的省，从数据库查询
     */
    private void queryProvinces() {
        mTitle.setText("选择省份");
        Observer<Province> observer = new Observer<Province>() {
            @Override
            public void onCompleted() {
                currentLevel = LEVEL_PROVINCE;
                mAdapter.notifyDataSetChanged();
                mProgressBar.setVisibility(View.GONE);
            }


            @Override
            public void onError(Throwable e) {

            }


            @Override
            public void onNext(Province province) {
                dataList.add(province.ProName);
            }
        };

        Observable.defer(new Func0<Observable<Province>>() {
            @Override
            public Observable<Province> call() {
                provincesList = mWeatherDB.loadProvinces(mDBManager.getDatabase());
                dataList.clear();
                return Observable.from(provincesList);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);


    }


    /**
     * 查询选中省份的所有城市，从数据库查询
     */
    private void queryCities() {
        dataList.clear();
        mTitle.setText(selectedProvince.ProName);
        Observer<City> observer = new Observer<City>() {
            @Override
            public void onCompleted() {
                currentLevel = LEVEL_CITY;
                mAdapter.notifyDataSetChanged();
                //定位到第一个item
                mRecyclerView.smoothScrollToPosition(0);
            }


            @Override
            public void onError(Throwable e) {

            }


            @Override
            public void onNext(City city) {
                dataList.add(city.cityName);
            }
        };


        Observable.defer(new Func0<Observable<City>>() {
            @Override
            public Observable<City> call() {
                cityList = mWeatherDB.loadCities(mDBManager.getDatabase(), selectedProvince.ProSort);
                return Observable.from(cityList);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 查询所有城市，从数据库库
     */

    /**
     * 查询选中省份的所有城市，从数据库查询
     */
    private synchronized void loadAllCities() {
        allCityList.clear();
        Observer<City> observer = new Observer<City>() {
            @Override
            public void onCompleted() {
                // currentLevel = LEVEL_CITY;
                // mAdapter.notifyDataSetChanged();
                //定位到第一个item
                // mRecyclerView.smoothScrollToPosition(0);
            }


            @Override
            public void onError(Throwable e) {

            }


            @Override
            public void onNext(City city) {
                allCityList.add(city);
            }
        };


        Observable.defer(new Func0<Observable<City>>() {
            @Override
            public Observable<City> call() {
                cityList = mWeatherDB.loadAllCities(mDBManager.getDatabase());
                return Observable.from(cityList);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    private void searchCity(final String searchedCity) {
        dataList.clear();
        cityList.clear();

        Observer<City> observer1 = new Observer<City>() {
            @Override
            public void onCompleted() {
                currentLevel = LEVEL_CITY;
                mAdapter.notifyDataSetChanged();
                //定位到第一个item
                mRecyclerView.smoothScrollToPosition(0);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(City city) {
                cityList.add(city);
                dataList.add(city.cityName);
            }
        };

        Observable.from(allCityList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<City, Boolean>() {
                    @Override
                    public Boolean call(City city) {
                        return city.cityName.contains(searchedCity);
                    }
                })
                .subscribe(observer1);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (currentLevel == LEVEL_PROVINCE) {
                finish();
            } else {
                queryProvinces();
                mRecyclerView.smoothScrollToPosition(0);
            }
        }
        return false;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDBManager.closeDatabase();
    }
}
