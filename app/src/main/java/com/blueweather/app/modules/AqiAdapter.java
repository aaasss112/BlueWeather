package com.blueweather.app.modules;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.blueweather.app.R;
import com.blueweather.app.domain.Weather;

/**
 * Created by Administrator on 2016/3/4.
 */
public class AqiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Weather.AqiEntity.CityEntity mAqi;
    private static final int AQISIZE = 6;
    private Context mContext;

    public AqiAdapter(Weather.AqiEntity.CityEntity cityEntity, Context mContext) {
        mAqi = cityEntity;
        this.mContext = mContext;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.aqi_item, viewGroup, false));
        return myViewHolder;
    }

    /*未来数天的天气*/
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {

        switch (i) {
            case 0:
                ((MyViewHolder) holder).aqiTypeTV.setText("PM10");
                ((MyViewHolder) holder).aqiValueTV.setText(mAqi.pm10 + mContext.getResources().getString(R.string.aqi_metric));
                break;
            case 1:
                ((MyViewHolder) holder).aqiTypeTV.setText("PM2.5");
                ((MyViewHolder) holder).aqiValueTV.setText(mAqi.pm25 + mContext.getResources().getString(R.string.aqi_metric));
                break;
            case 2:
                ((MyViewHolder) holder).aqiTypeTV.setText("NO2");
                ((MyViewHolder) holder).aqiValueTV.setText(mAqi.no2 + mContext.getResources().getString(R.string.aqi_metric));
                break;
            case 3:
                ((MyViewHolder) holder).aqiTypeTV.setText("SO2");
                ((MyViewHolder) holder).aqiValueTV.setText(mAqi.so2 + mContext.getResources().getString(R.string.aqi_metric));
                break;
            case 4:
                ((MyViewHolder) holder).aqiTypeTV.setText("O3");
                ((MyViewHolder) holder).aqiValueTV.setText(mAqi.o3 + mContext.getResources().getString(R.string.aqi_metric));
                break;
            case 5:
                ((MyViewHolder) holder).aqiTypeTV.setText("CO");
                ((MyViewHolder) holder).aqiValueTV.setText(mAqi.co + mContext.getResources().getString(R.string.aqi_metric));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return AQISIZE;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView aqiTypeTV;
        private TextView aqiValueTV;

        public MyViewHolder(View view) {
            super(view);
            aqiTypeTV = (TextView) view.findViewById(R.id.aqitype_tv);
            aqiValueTV = (TextView) view.findViewById(R.id.aqivalue_tv);
        }
    }
}
