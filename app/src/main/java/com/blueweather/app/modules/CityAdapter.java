package com.blueweather.app.modules;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.blueweather.app.R;
import com.blueweather.app.domain.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/4.
 */
public class CityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<City> mCityList = new ArrayList<City>();

    public CityAdapter(List<City> cities) {
        mCityList = cities;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int pos);

        void onItemLongClick(View view, int pos);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.city_item, viewGroup, false));
        return myViewHolder;
    }
    /*城市列表*/

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder) holder).cityTv.setText(mCityList.get(position).cityName);


        if (mOnItemClickListener != null) {
            ((MyViewHolder) holder).cityTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getAdapterPosition();
                    mOnItemClickListener.onItemClick(v, pos);
                }
            });

            ((MyViewHolder) holder).cityTv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getAdapterPosition();
                    mOnItemClickListener.onItemLongClick(v, pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mCityList.size();
    }

    /**
     * 删除对应位置的item
     * @param pos
     */
    public synchronized void removeData(int pos) {
        if (mCityList.size() > 1) {
            mCityList.remove(pos);
            notifyItemRemoved(pos);
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView cityTv;

        public MyViewHolder(View view) {
            super(view);
            cityTv = (TextView) view.findViewById(R.id.city_tv);
        }
    }
}
