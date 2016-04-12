package com.blueweather.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Window;
import com.blueweather.app.R;
import com.blueweather.app.base.BaseActivity;
import com.blueweather.app.domain.City;
import com.blueweather.app.modules.FragmentAdapter;
import com.blueweather.app.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/7.
 */
public class PagerActivity extends BaseActivity  implements MainFragment.OnSettingsChangedListener{
    private ViewPager mViewPager;
    private FragmentAdapter mAdapter;
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private static final String TAG = PagerActivity.class.getSimpleName();
    private List<City> curCityList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_pager);

        curCityList = mSettings.cityList;

        initView();
        mViewPager = (ViewPager)findViewById(R.id.vp_view);
        mAdapter=new FragmentAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(mAdapter);
        LogUtils.i(TAG,"setAdapter" + mAdapter.getCount());
    }

    /**
     * 初始化Fragment列表
     */
    public void initView(){
        for(int i = 0; i < curCityList.size(); i++){
            Bundle bundle = new Bundle();
            bundle.putInt("curIndex",i);
            MainFragment mainFragment = new MainFragment();
            mainFragment.setArguments(bundle);
            fragments.add(mainFragment);
        }
    }

    @Override
    public void OnSettingsChanged(List<City> cities) {
        LogUtils.i(TAG, "call back");
        mViewPager.removeAllViews();
        fragments.clear();
        initView();
        mAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
