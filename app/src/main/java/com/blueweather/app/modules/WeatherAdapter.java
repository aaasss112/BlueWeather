package com.blueweather.app.modules;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.blueweather.app.R;
import com.blueweather.app.domain.Weather;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/4.
 */
public class WeatherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Weather.DailyForecastEntity> mForecastList = new ArrayList<Weather.DailyForecastEntity>();
    private Context mContext;
    private Time mTime;

    public WeatherAdapter(List<Weather.DailyForecastEntity> mForecastList, Context context) {
        this.mForecastList = mForecastList;
        this.mContext = context;
        this.mTime = new Time();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.forecast_item, viewGroup, false));
        return myViewHolder;
    }

    /*未来数天的天气*/
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {
        ((MyViewHolder) holder).forecastTmp[i].setText(mForecastList.get(i).tmp.max + mContext.getResources().getString(R.string.temperature_metric) + "/" + mForecastList.get(i).tmp.min + mContext.getResources().getString(R.string.temperature_metric));
        try {
            ((MyViewHolder) holder).forecastDate[i].setText(mTime.getWeek(mForecastList.get(i).date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ((MyViewHolder) holder).forecastDesc[i].setText(mForecastList.get(i).cond.txtD);
    }

    /*
    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {




        if (mOnItemClickListener != null) {
            myViewHolder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = myViewHolder.getPosition();
                    mOnItemClickListener.onItemClick(v, pos);
                }
            });

            myViewHolder.textView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = myViewHolder.getPosition();
                    mOnItemClickListener.onItemLongClick(v, pos);
                    return false;
                }
            });
        }

    }
    */
    @Override
    public int getItemCount() {
        return mForecastList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView[] forecastDesc = new TextView[mForecastList.size()];
        private TextView[] forecastTmp = new TextView[mForecastList.size()];
        private TextView[] forecastDate = new TextView[mForecastList.size()];

        public MyViewHolder(View view) {
            super(view);
            for (int i = 0; i < mForecastList.size(); i++) {
                forecastDesc[i] = (TextView) view.findViewById(R.id.tv_forecast_desc);
                forecastTmp[i] = (TextView) view.findViewById(R.id.tv_forecast_tmp);
                forecastDate[i] = (TextView) view.findViewById(R.id.tv_forecast_date);
            }
        }
    }
}
