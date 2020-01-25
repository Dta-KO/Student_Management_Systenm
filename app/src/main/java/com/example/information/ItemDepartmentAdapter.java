package com.example.information;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemDepartmentAdapter extends RecyclerView.Adapter<ItemDepartmentAdapter.RecyclerViewHolderForDepartment> {

    private ArrayList<Department> list;
    private Context context;

    public ItemDepartmentAdapter(Context context, ArrayList<Department> list) {
        this.context = context;
        this.list = list;
    }


    /**
     * connect to item view
     */
    @Override
    public RecyclerViewHolderForDepartment onCreateViewHolder(ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.item_department, viewGroup, false);
        return new RecyclerViewHolderForDepartment(itemView);
    }

    //chưa xong...
    @Override
    public void onBindViewHolder(RecyclerViewHolderForDepartment holderForDepartment, int position) {
        holderForDepartment.position.setText(""+(position+1));
        holderForDepartment.nameDepartment.setText(list.get(position).getDepartmentName());
        holderForDepartment.codeDepartment.setText("Mã Khoa: "+list.get(position).getCodeDepartment());
    }

    public class RecyclerViewHolderForDepartment extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CardView containerDepartment;
        public TextView position, nameDepartment, codeDepartment;


        public RecyclerViewHolderForDepartment(View itemView) {
            super(itemView);
            containerDepartment = itemView.findViewById(R.id.container_department);
            position = itemView.findViewById(R.id.position_department);
            nameDepartment = itemView.findViewById(R.id.name_department);
            codeDepartment = itemView.findViewById(R.id.code_department);
            containerDepartment.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Var.showToast(context, list.get(getAdapterPosition()).getDepartmentName());
        }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

}
