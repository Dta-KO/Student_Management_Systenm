package com.example.information;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemClassInfoAdapter extends RecyclerView.Adapter<ItemClassInfoAdapter.RecyclerViewHolderForClass> {

    private List<String> list;
    private Context context;

    public ItemClassInfoAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * connect to item view
     */
    @Override
    public RecyclerViewHolderForClass onCreateViewHolder(ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.info_class, viewGroup, false);
        return new RecyclerViewHolderForClass(itemView);
    }

    //chưa xong...
    @Override
    public void onBindViewHolder(RecyclerViewHolderForClass recyclerViewHolderForClass, int position) {

        recyclerViewHolderForClass.nameClass.setText(list.get(position));


    }

    public class RecyclerViewHolderForClass extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nameClass;


        public RecyclerViewHolderForClass(View itemView) {
            super(itemView);

            nameClass = itemView.findViewById(R.id.class_name_info);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
