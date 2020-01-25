package com.example.information;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ItemResultInfoAdapter extends RecyclerView.Adapter<ItemResultInfoAdapter.RecyclerViewHolderForResult> {

    private List<Result> list;
    private Context context;

    public ItemResultInfoAdapter(Context context, List<Result> list) {
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
        View itemView = inflater.inflate(R.layout.info_result, viewGroup, false);
        return new RecyclerViewHolderForResult(itemView);
    }

    //chưa xong...
    @Override
    public void onBindViewHolder(RecyclerViewHolderForResult recyclerViewHolderForResult, int position) {
        Result result = list.get(position);
        recyclerViewHolderForResult.averagePoint.setText("Điểm trung bình: "+(int) result.getAveragePoint());
        recyclerViewHolderForResult.firstPoint.setText("Điểm thi lần 1: "+(int) result.getFirstPoint());
        recyclerViewHolderForResult.secondPoint.setText("Điểm thi lần 2: "+(int) result.getSecondPoint());
        recyclerViewHolderForResult.finalPoint.setText("Điểm cuối cùng: "+(int) result.getFinalPoint());
        recyclerViewHolderForResult.conduct.setText("Hạnh kiểm: "+result.getConduct());
        recyclerViewHolderForResult.note.setText("Ghi chú: "+result.getNote());
        if(result.getAveragePoint()<5){
            recyclerViewHolderForResult.xepLoai.setText("Điểm dưới trung bình. \nChưa đạt!");
        }else if(result.getAveragePoint()>=5|result.getAveragePoint()<6.5){
            recyclerViewHolderForResult.xepLoai.setText("Trung bình! Cần cố gắng hơn");
        }else if(result.getAveragePoint()>=6.5|result.getAveragePoint()<8){
            recyclerViewHolderForResult.xepLoai.setText("Khá! Ra trường ok rùi nhé :))");
        }else {
            recyclerViewHolderForResult.xepLoai.setText("Chúc mừng, em khá giỏi đấy");
        }
    }

    public class RecyclerViewHolderForResult extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView   xepLoai, averagePoint,firstPoint,secondPoint,finalPoint,conduct,note;


        public RecyclerViewHolderForResult(View itemView) {
            super(itemView);

            averagePoint = itemView.findViewById(R.id.diem_average);
            firstPoint = itemView.findViewById(R.id.diemLan1);
            secondPoint = itemView.findViewById(R.id.diemLan2);
            finalPoint = itemView.findViewById(R.id.diemLan3);
            conduct = itemView.findViewById(R.id.hanh_kiem);
            note = itemView.findViewById(R.id.ghi_chu);

        }

        @Override
        public void onClick(View v) {
            Var.showToast(context, list.get(getAdapterPosition()).getCodeStudent());
        }
    }
}
