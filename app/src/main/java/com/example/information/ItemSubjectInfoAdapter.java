package com.example.information;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemSubjectInfoAdapter extends RecyclerView.Adapter<ItemSubjectInfoAdapter.RecyclerViewHolderForSubject> {

    private ArrayList<String> list;
    private Context context;

    public ItemSubjectInfoAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }


    /**
     * connect to item view
     */
    @Override
    public RecyclerViewHolderForSubject onCreateViewHolder(ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.info_subject, viewGroup, false);
        return new RecyclerViewHolderForSubject(itemView);
    }

    //chưa xong...
    @Override
    public void onBindViewHolder(RecyclerViewHolderForSubject recyclerViewHolderForSubject, int position) {
        recyclerViewHolderForSubject.nameSubject.setText(list.get(position));
    }

    public class RecyclerViewHolderForSubject extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nameSubject;


        public RecyclerViewHolderForSubject(View itemView) {
            super(itemView);

            nameSubject = itemView.findViewById(R.id.ten_mon);


        }

        @Override
        public void onClick(View v) {
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
