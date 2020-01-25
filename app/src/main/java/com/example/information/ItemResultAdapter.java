package com.example.information;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ItemResultAdapter extends RecyclerView.Adapter<ItemResultAdapter.RecyclerViewHolderForResult> {

    private List<Result> list;
    private Context context;

    public ItemResultAdapter(Context context, List<Result> list) {
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
    public RecyclerViewHolderForResult onCreateViewHolder(ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.item_result, viewGroup, false);
        return new RecyclerViewHolderForResult(itemView);
    }

    //chưa xong...
    @Override
    public void onBindViewHolder(RecyclerViewHolderForResult recyclerViewHolderForResult, int position) {
        Result result = list.get(position);
        recyclerViewHolderForResult.position.setText(""+(position+1));
        recyclerViewHolderForResult.codeSubject.setText("Mã môn: "+result.getCodeSubject());
        recyclerViewHolderForResult.codeStudent.setText("Mã SV: "+result.getCodeStudent());
        recyclerViewHolderForResult.averagePoint.setText("Điểm trung bình: "+(int) result.getAveragePoint());
        recyclerViewHolderForResult.firstPoint.setText("Điểm thi lần 1: "+(int) result.getFirstPoint());
        recyclerViewHolderForResult.secondPoint.setText("Điểm thi lần 2: "+(int) result.getSecondPoint());
        recyclerViewHolderForResult.finalPoint.setText("Điểm cuối cùng: "+(int) result.getFinalPoint());
        recyclerViewHolderForResult.conduct.setText("Hạnh kiểm: "+result.getConduct());
        recyclerViewHolderForResult.note.setText("Ghi chú: "+result.getNote());
    }

    public class RecyclerViewHolderForResult extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CardView containerResult;
        public TextView position, codeSubject, codeStudent, averagePoint,firstPoint,secondPoint,finalPoint,conduct,note;


        public RecyclerViewHolderForResult(View itemView) {
            super(itemView);
            containerResult = itemView.findViewById(R.id.container_result);
            position = itemView.findViewById(R.id.position_result);
            codeSubject = itemView.findViewById(R.id.code_subject_result);
            codeStudent = itemView.findViewById(R.id.code_student_result);
            averagePoint = itemView.findViewById(R.id.average_point);
            firstPoint = itemView.findViewById(R.id.first_point);
            secondPoint = itemView.findViewById(R.id.second_point);
            finalPoint = itemView.findViewById(R.id.final_point);
            conduct = itemView.findViewById(R.id.conduct_result);
            note = itemView.findViewById(R.id.note_result);


//            containerResult.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Var.showToast(context, list.get(getAdapterPosition()).getCodeStudent());
        }
    }
}
