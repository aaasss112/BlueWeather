package com.blueweather.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.blueweather.app.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new SwitchHandler().sendEmptyMessageAtTime(1, 1000);

    }

    class SwitchHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //切换到主界面
            Intent intent1 = new Intent(SplashActivity.this, PagerActivity.class);
            SplashActivity.this.startActivity(intent1);
            SplashActivity.this.finish();

        }
    }
}
