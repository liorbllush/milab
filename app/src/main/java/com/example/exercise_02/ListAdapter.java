package com.example.exercise_02;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends RecyclerView.Adapter {
    private Person[] mDataSet;

    public ListAdapter(Person[] data) {
        mDataSet = data;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private View view;
        public MyViewHolder(View view) {
            super(view);
            this.view = view;
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new MyViewHolder(v);
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Person person = mDataSet[position];
        TextView name = ((MyViewHolder) holder).view.findViewById(R.id.name);
        ImageView picture = ((MyViewHolder) holder).view.findViewById(R.id.picture);
        ImageView house = ((MyViewHolder) holder).view.findViewById(R.id.rank_pic);
        name.setText(person.getName());
        picture.setImageResource(person.getPicture());
        house.setImageResource(person.getHouse());
    }
    @Override
    public int getItemCount() {
        return mDataSet.length;
    }


}


