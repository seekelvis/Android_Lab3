package com.example.administrator.android_lab3;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Administrator on 2017/10/19.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerHolder>{
    private Context mContext;
    private int mLayoutId;
    private List<Goods> mDatas;
    private OnItemClickListener mOnItemClickListener;

    public RecyclerAdapter(Context context, int layoutId, List<Goods> datas){
        mContext = context;
        mLayoutId = layoutId;
        mDatas = datas;
        mOnItemClickListener = null;
    }

    @Override
    public RecyclerHolder onCreateViewHolder(final ViewGroup parent, int viewType){
        View view = LayoutInflater.from(mContext).inflate(mLayoutId, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerHolder holder, int position) {
        holder.alpha.setText(mDatas.get(position).name.substring(0,1));
        holder.name.setText(mDatas.get(position).name);
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    mOnItemClickListener.onClick(holder.getAdapterPosition());
                }
            });
        }
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v){
                mOnItemClickListener.onLongClick(holder.getAdapterPosition());
                return true;
            }
        });

    }


    @Override
    public int getItemCount(){
        if (mDatas!=null)  return mDatas.size();
        else return 0;
    }

    interface OnItemClickListener{
        void onClick(int postion);
        void onLongClick(int postion);
    }

    void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }

    void remove(int pos){
        mDatas.remove(pos);
        notifyItemRemoved(pos);
        if (pos != mDatas.size())
            notifyItemRangeChanged(pos,mDatas.size() - pos);
    }


}
