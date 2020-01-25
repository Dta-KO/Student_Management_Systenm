package com.example.information;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ItemClassAdapter extends RecyclerView.Adapter<ItemClassAdapter.RecyclerViewHolderForClass> {

    private List<Class> list;
    private Context context;

    public ItemClassAdapter(Context context, List<Class> list) {
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
        View itemView = inflater.inflate(R.layout.item_class, viewGroup, false);
        return new RecyclerViewHolderForClass(itemView);
    }

    //chưa xong...
    @Override
    public void onBindViewHolder(RecyclerViewHolderForClass recyclerViewHolderForClass, int position) {
        Class aClass = list.get(position);
        recyclerViewHolderForClass.position.setText(""+(position+1));
        recyclerViewHolderForClass.nameClass.setText(aClass.getClassName());
        recyclerViewHolderForClass.codeClass.setText(aClass.getCodeClass());
        recyclerViewHolderForClass.codeDepartment.setText(aClass.getCodeDepartment());
    }

    public class RecyclerViewHolderForClass extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CardView containerClass;
        public TextView position, nameClass, codeClass, codeDepartment;


        public RecyclerViewHolderForClass(View itemView) {
            super(itemView);
            containerClass = itemView.findViewById(R.id.container_class);
            position = itemView.findViewById(R.id.position_class);
            nameClass = itemView.findViewById(R.id.name_class);
            codeClass = itemView.findViewById(R.id.code_class);
            codeDepartment = itemView.findViewById(R.id.code_department1);


            containerClass.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Var.showToast(context, list.get(getAdapterPosition()).getClassName());
        }
    }
}
