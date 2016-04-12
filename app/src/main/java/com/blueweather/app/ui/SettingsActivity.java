package com.blueweather.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import com.blueweather.app.R;
import com.blueweather.app.base.BaseActivity;
import com.blueweather.app.domain.City;
import com.blueweather.app.modules.CityAdapter;
import com.blueweather.app.utils.LogUtils;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.List;

/**
 * Created by user on 2016/4/8.
 */
public class SettingsActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private ImageView addImageview, settingsImageView;
    private CityAdapter mCityAdapter;
    private List<City> curCityList;
    private static final String TAG = SettingsActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initView();
        initAdapter();

    }

    /**
     * 初始化View
     */
    public void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.city_recyclerview);
        addImageview = (ImageView) findViewById(R.id.add_img);
        settingsImageView = (ImageView) findViewById(R.id.setting_img);
        addImageview.setOnClickListener(this);
        settingsImageView.setOnClickListener(this);

        //mToolbar.setTitle("管理城市");

    }

    /**
     * 初始化适配器
     */
    public void initAdapter() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        curCityList = mSettings.cityList;
        mCityAdapter = new CityAdapter(curCityList);
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getApplicationContext()).marginResId(R.dimen.divider_margin, R.dimen.divider_margin).build());
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mCityAdapter);
        mCityAdapter.setOnItemClickListener(new CityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {

            }

            @Override
            public void onItemLongClick(View view, int pos) {
                LogUtils.i(TAG, "pos is " + pos + " size is " + curCityList.size());
                mCityAdapter.removeData(pos);
                LogUtils.i(TAG, "" + curCityList.size());

                //curCityList.remove(1);
                // mSettings.cityList.remove(1);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_img: {
                Intent intent = new Intent(SettingsActivity.this, SelectActivity.class);
                startActivityForResult(intent, 1);
            }
            break;
            case R.id.setting_img: {
                Intent intent = new Intent(SettingsActivity.this, ChangingSettingsActivity.class);
                startActivity(intent);
            }
            break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(2);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtils.i(TAG, "result is " + resultCode);
        if (requestCode == 1 && resultCode == 2) {
            City city = new City(data.getStringExtra("selectedCity"));
            curCityList.add(city);
            mCityAdapter.notifyDataSetChanged();
            LogUtils.i(TAG, "city is " + city.cityName);
        }
    }
}
