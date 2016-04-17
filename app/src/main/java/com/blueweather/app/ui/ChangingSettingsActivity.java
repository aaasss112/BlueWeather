package com.blueweather.app.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import com.blueweather.app.R;
import com.blueweather.app.base.BaseActivity;
import com.blueweather.app.utils.LogUtils;

/**
 * Created by Administrator on 2016/4/12.
 */
public class ChangingSettingsActivity extends BaseActivity implements View.OnClickListener {
    private TextView tmpMetricTv, updateTimeTv;
    private Switch mSwtich;
    private RelativeLayout tmpRelativeLayout, isUpateRelativeLayout, updateTimeRelativeLayout;
    private static final int ONEHOUR = 60 * 60 * 1000;
    private static final String TAG = ChangingSettingsActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changing_settings_activity);
        initView();
    }

    public void initView() {
        tmpMetricTv = (TextView) findViewById(R.id.tmpmetric_tv);
        updateTimeTv = (TextView) findViewById(R.id.updatetime_tv);
        mSwtich = (Switch) findViewById(R.id.update_switch);
        tmpRelativeLayout = (RelativeLayout) findViewById(R.id.tmp_layout);
        isUpateRelativeLayout = (RelativeLayout) findViewById(R.id.isupdate_layout);
        updateTimeRelativeLayout = (RelativeLayout) findViewById(R.id.updatetime_layout);

        mSwtich.setChecked(mSettings.isUpdating);
        updateTimeTv.setText(mSettings.update_time / ONEHOUR + "小时");

        tmpRelativeLayout.setOnClickListener(this);
        isUpateRelativeLayout.setOnClickListener(this);
        updateTimeRelativeLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tmp_layout:
                break;
            case R.id.isupdate_layout: {
                mSwtich.setChecked(!mSwtich.isChecked());
                mSettings.isUpdating = mSwtich.isChecked();
            }
            break;
            case R.id.updatetime_layout: {
                LogUtils.i(TAG, "click updatetime");
                ListDialog();
            }
            break;
            default:
                break;
        }
    }

    private void ListDialog() {
        final String items[] = {"每1小时", "每2小时", "每6小时", "每12小时", "每24小时"};

        //dialog参数设置
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("更新间隔");
        builder.setIcon(R.drawable.menu);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        mSettings.update_time = ONEHOUR;
                        break;
                    case 1:
                        mSettings.update_time = ONEHOUR * 2;
                        break;
                    case 2:
                        mSettings.update_time = ONEHOUR * 6;
                        break;
                    case 3:
                        mSettings.update_time = ONEHOUR * 12;
                        break;
                    case 4:
                        mSettings.update_time = ONEHOUR * 24;
                        break;
                    default:
                        break;
                }
                updateTimeTv.setText(mSettings.update_time / ONEHOUR + "小时");
            }
        });

        builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}
