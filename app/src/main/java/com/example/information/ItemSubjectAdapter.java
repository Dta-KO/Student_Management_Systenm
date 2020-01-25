package com.example.information;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemSubjectAdapter extends RecyclerView.Adapter<ItemSubjectAdapter.RecyclerViewHolderForSubject> {

    private ArrayList<Subject> list;
    private Context context;

    public ItemSubjectAdapter(Context context, ArrayList<Subject> list) {
        this.context = context;
        this.list = list;
    }


    /**
     * connect to item view
     */
    @Override
    public RecyclerViewHolderForSubject onCreateViewHolder(ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.item_subject, viewGroup, false);
        return new RecyclerViewHolderForSubject(itemView);
    }

    //chưa xong...
    @Override
    public void onBindViewHolder(RecyclerViewHolderForSubject recyclerViewHolderForSubject, int position) {
        recyclerViewHolderForSubject.position.setText(""+(position+1));
        recyclerViewHolderForSubject.nameSubject.setText(list.get(position).getSubjectName());
        recyclerViewHolderForSubject.codeDepartment.setText("Mã Khoa: "+list.get(position).getCodeDepartment());
        recyclerViewHolderForSubject.codeTeacher.setText("Mã GV: "+list.get(position).getCodeTeacher());
        recyclerViewHolderForSubject.codeSubject.setText("Mã Môn: "+list.get(position).getCodeSubject());
        recyclerViewHolderForSubject.numberUnits.setText("Số đơn vị học phần: "+list.get(position).getNumberUnits());
    }

    public class RecyclerViewHolderForSubject extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CardView containerSubject;
        public TextView position, nameSubject, codeDepartment,codeTeacher, codeSubject,numberUnits;


        public RecyclerViewHolderForSubject(View itemView) {
            super(itemView);
            containerSubject = itemView.findViewById(R.id.container_subject);
            position = itemView.findViewById(R.id.position_subject);
            nameSubject = itemView.findViewById(R.id.name_subject);
            codeSubject = itemView.findViewById(R.id.code_subject);
            numberUnits = itemView.findViewById(R.id.number_units);
            codeDepartment = itemView.findViewById(R.id.code_department_subject);
            codeTeacher = itemView.findViewById(R.id.code_teacher_subject);
            containerSubject.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Var.showToast(context, list.get(getAdapterPosition()).getSubjectName());
        }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

}
