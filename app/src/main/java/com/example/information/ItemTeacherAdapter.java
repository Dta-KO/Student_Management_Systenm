package com.example.information;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ItemTeacherAdapter extends RecyclerView.Adapter<ItemTeacherAdapter.RecyclerViewHolderForTeacher> {

    private List<Teachers> list;
    private Context context;

    public ItemTeacherAdapter(Context context, List<Teachers> list) {
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
    public RecyclerViewHolderForTeacher onCreateViewHolder(ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.item_teacher, viewGroup, false);
        return new RecyclerViewHolderForTeacher(itemView);
    }

    //chưa xong...
    @Override
    public void onBindViewHolder(RecyclerViewHolderForTeacher recyclerViewHolderForTeacher, int position) {
        Teachers teachers = list.get(position);
        recyclerViewHolderForTeacher.position.setText(""+(position+1));
        recyclerViewHolderForTeacher.type.setText(teachers.getType());
        recyclerViewHolderForTeacher.tel.setText(teachers.getTel());
        recyclerViewHolderForTeacher.nameTeacher.setText(teachers.getName());
        recyclerViewHolderForTeacher.birthdayTeacher.setText(teachers.getBirthday());
        recyclerViewHolderForTeacher.sexTeacher.setText(teachers.getSex());
        recyclerViewHolderForTeacher.codeTeacher.setText(teachers.getCodeTeacher());
        recyclerViewHolderForTeacher.email.setText(teachers.getEmail());
    }

    public class RecyclerViewHolderForTeacher extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CardView containerTeacher;
        public TextView position, nameTeacher, codeTeacher, sexTeacher, birthdayTeacher, tel, type, email;


        public RecyclerViewHolderForTeacher(View itemView) {
            super(itemView);
            containerTeacher = itemView.findViewById(R.id.container_teacher);
            position = itemView.findViewById(R.id.position_teacher);
            nameTeacher = itemView.findViewById(R.id.name_teacher);
            codeTeacher = itemView.findViewById(R.id.code_teacher);
            sexTeacher = itemView.findViewById(R.id.sex_teacher);
            birthdayTeacher = itemView.findViewById(R.id.birthday_teacher);
            email = itemView.findViewById(R.id.email_teacher);
            tel = itemView.findViewById(R.id.tel_teacher);
            type = itemView.findViewById(R.id.type_teacher);


            containerTeacher.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Var.showToast(context, list.get(getAdapterPosition()).getName());
        }
    }
}
