package com.blueweather.app.modules;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2016/4/6.
 */
public class CityWeatherService extends Service {

    public static final String BROADCASTACTION = "com.blueweather.app.modules.cityweatherservice";

    private static final String TAG = CityWeatherService.class.getSimpleName();


    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent();
            intent.setAction(BROADCASTACTION);
            sendBroadcast(intent);
            handler.postDelayed(this, Settings.update_time);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "service");
        handler.postDelayed(runnable, Settings.update_time);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (handler != null)
            handler.removeCallbacks(runnable);
    }

}
