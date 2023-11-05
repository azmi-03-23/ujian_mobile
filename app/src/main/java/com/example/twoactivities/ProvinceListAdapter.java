package com.example.twoactivities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class ProvinceListAdapter extends RecyclerView.Adapter<ProvinceListAdapter.ProvinceViewHolder>{
    class ProvinceViewHolder extends RecyclerView.ViewHolder{

        public final ImageView mImageView;
        public final TextView mTextView;
        final ProvinceListAdapter mAdapter;

        public ProvinceViewHolder(View itemView, ProvinceListAdapter adapter){
            super(itemView);

            this.mAdapter = adapter;

            this.mImageView = itemView.findViewById(R.id.province_image);
            this.mTextView = itemView.findViewById(R.id.province_name);
        }

    }

    private final LinkedList<Province> mProvinceList;
    private LayoutInflater mInflater;

    public ProvinceListAdapter(Context context, LinkedList<Province> provinceList){
        mInflater = LayoutInflater.from(context);
        this.mProvinceList = provinceList;
    }

    @NonNull
    @Override
    public ProvinceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.provincelist_item, parent, false);
        return new ProvinceViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ProvinceViewHolder holder, int position) {
        Province mCurrent = mProvinceList.get(position);
        holder.mTextView.setText(mCurrent.getName());
    }

    @Override
    public int getItemCount() {
        return mProvinceList.size();
    }

}
