package com.example.information;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ItemStudentInfoAdapter extends RecyclerView.Adapter<ItemStudentInfoAdapter.RecyclerViewHolderForStudent> {

    private List<Students> list;
    private Context context;

    public ItemStudentInfoAdapter(Context context, List<Students> list) {
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
    public RecyclerViewHolderForStudent onCreateViewHolder(ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.info_student, viewGroup, false);
        return new RecyclerViewHolderForStudent(itemView);
    }

    //chưa xong...
    @Override
    public void onBindViewHolder(RecyclerViewHolderForStudent recyclerViewHolderForStudent, int position) {
        Students students = list.get(position);
        recyclerViewHolderForStudent.position.setText(""+(position+1));
        recyclerViewHolderForStudent.nameStudent.setText(students.getName());
        recyclerViewHolderForStudent.codeStudent.setText(students.getCodeStudent());
        recyclerViewHolderForStudent.sex.setText(students.getSex());
        recyclerViewHolderForStudent.birthday.setText(students.getBirthday());
        recyclerViewHolderForStudent.address.setText(students.getAddress());
    }

    public class RecyclerViewHolderForStudent extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView position, nameStudent, codeStudent, sex,birthday,address;


        public RecyclerViewHolderForStudent(View itemView) {
            super(itemView);
            position = itemView.findViewById(R.id.position_info);
            nameStudent = itemView.findViewById(R.id.name_info);
            codeStudent = itemView.findViewById(R.id.code_info);
            sex = itemView.findViewById(R.id.sex_info);
            birthday = itemView.findViewById(R.id.birthday_info);
            address = itemView.findViewById(R.id.address_info);




        }

        @Override
        public void onClick(View v) {
            Var.showToast(context, list.get(getAdapterPosition()).getName());
        }
    }
}
