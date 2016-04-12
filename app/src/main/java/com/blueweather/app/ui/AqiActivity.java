package com.blueweather.app.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.blueweather.app.R;
import com.blueweather.app.base.BaseActivity;
import com.blueweather.app.domain.Weather;
import com.blueweather.app.modules.AqiAdapter;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

/**
 * Created by user on 2016/3/27.
 */
public class AqiActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private AqiAdapter mAqiAdapter;
    private Weather.AqiEntity mAqiEntity;
    private static final String TAG = AqiActivity.class.getSimpleName();
    public static final String KEY = "AqiCity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aqi);
        initView();
    }

    public void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.aqi_recyclerview);
        mAqiEntity = (Weather.AqiEntity) this.getIntent().getSerializableExtra(KEY);
        mAqiAdapter = new AqiAdapter(mAqiEntity.city, getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getApplicationContext()).marginResId(R.dimen.double_divider_margin, R.dimen.double_divider_margin).build());
        mRecyclerView.setAdapter(mAqiAdapter);
    }

}
