package com.blueweather.app.modules;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/4/7.
 */
public class FragmentAdapter extends FragmentPagerAdapter {
    private List<android.support.v4.app.Fragment> fragments;

    public FragmentAdapter(FragmentManager fm, List<android.support.v4.app.Fragment> fragments){
        super(fm);
        this.fragments = fragments;
    }

    public android.support.v4.app.Fragment getItem(int fragment){
        return fragments.get(fragment);
    }

    public int getCount(){
        return fragments.size();
    }

}
