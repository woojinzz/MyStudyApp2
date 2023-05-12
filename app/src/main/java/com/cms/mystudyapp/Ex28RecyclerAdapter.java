package com.cms.mystudyapp;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Ex28RecyclerAdapter extends RecyclerView.Adapter<Ex28RecyclerAdapter.ViewHolder> {

    public ArrayList<Ex28ItemData> itemData;
    public Ex28RecyclerAdapter(ArrayList<Ex28ItemData> itemData) {
        this.itemData = itemData;
    }

    public interface MyRecyclerViewClickListener{
        void onNameClicked(int position);
        void onHpClicked(int position);
        void onAddrClicked(int position);
        void onSexClicked(int position);
        void onAgeClicked(int position);
        void onItemLongClicked(int position);

    }


    private MyRecyclerViewClickListener mListener;

    public void setOnClickListener(MyRecyclerViewClickListener listener) {
        this.mListener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_ex28_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Ex28ItemData item = itemData.get(position);
        holder.tvName.setText(item.getName());
        holder.tvHp.setText(item.getHp());
        holder.tvAddr.setText(item.getAddr());
        holder.tvSex.setText(item.getSex());
        holder.tvAge.setText("나이:"+item.getAge()+"살");

        if (mListener != null) {
            final int pos = position;

            holder.tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onNameClicked(pos);
                }
            });
            holder.tvAddr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onAddrClicked(pos);
                }
            });
            holder.tvHp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onHpClicked(pos);
                }
            });
            holder.tvAge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onAgeClicked(pos);
                }
            });
            holder.tvSex.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onSexClicked(pos);
                }
            });


            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mListener.onItemLongClicked(holder.getAdapterPosition());
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return itemData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvHp, tvAddr, tvSex,tvAge;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvHp = itemView.findViewById(R.id.tvHp);
            tvAddr = itemView.findViewById(R.id.tvAddr);
            tvSex = itemView.findViewById(R.id.tvSex);
            tvAge = itemView.findViewById(R.id.tvAge);
        }
    }

    //리스트 삭제 이벤트
    public void remove(int position){
        try {
            itemData.remove(position);
            notifyDataSetChanged();
        } catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }
}