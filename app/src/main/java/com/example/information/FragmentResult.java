package com.example.information;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class FragmentResult extends BottomSheetDialogFragment {
    FloatingActionButton floatingActionButton;
    BottomSheetBehavior sheetBehavior;
    EditText codeSubject, codeStudent, averagePoint, firstPoint, secondPoint, finalPoint, conduct, note;


    public void connecting() {
        codeStudent = getView().findViewById(R.id.codeStudentResult);
        codeSubject = getView().findViewById(R.id.codeSubjectResult);
        averagePoint = getView().findViewById(R.id.averagePoint);
        firstPoint = getView().findViewById(R.id.firstPoint);
        secondPoint = getView().findViewById(R.id.secondPoint);
        finalPoint = getView().findViewById(R.id.finalPoint);
        conduct = getView().findViewById(R.id.conduct);
        note = getView().findViewById(R.id.note);
    }


    public FragmentResult() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View layoutBottom = getView().findViewById(R.id.design_bottom_sheet);
        sheetBehavior = BottomSheetBehavior.from(layoutBottom);


        floatingActionButton = getView().findViewById(R.id.floatActionButtonResult);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }

            }
        });
        setSwipeListener();
        connecting();
        setDelete();
        setReset();
    }


    Button btnReset;
    public void setReset(){
       btnReset = getView().findViewById(R.id.resetResult);
       btnReset.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               codeSubject.setText("");
               codeStudent.setText("");
               averagePoint.setText("");
               firstPoint.setText("");
               finalPoint.setText("");
               conduct.setText("");
               note.setText("");
               secondPoint.setText("");
               codeSubject.requestFocus();
           }
       });
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewResult = inflater.inflate(R.layout.fragment_result, container, false);
        return viewResult;
    }

    SwipeRefreshLayout swipeRefreshLayout;
    LoadJson loadJson44;
    RecyclerView recyclerView;
    ArrayList<Result> list;
    ItemResultAdapter adapter;

    public void setSwipeListener() {
        swipeRefreshLayout = getView().findViewById(R.id.design_above_sheet_result);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                HashMap<String, String> map = new HashMap<>();
                loadJson44.sendDataToServer(Var.METHOD_SELECT_ALL_RESULT, map);

            }
        });
        //set event when finish load data to server
        loadJson44 = new LoadJson();
        loadJson44.setOnFinishLoadJSonListener(new LoadJson.OnFinishLoadJSonListener() {
            @Override
            public void finishLoadJSon(String error, String json) {
                recyclerView = getView().findViewById(R.id.recyclerView_result);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                ArrayList<Result> newList = DownJsonResult.jsonToListResult(json);
                list = new ArrayList<>();

                if (newList.size() == 0) {
                    Toast.makeText(getContext(), "Không có dữ liệu nheseeeeeeeeeee bạn...", Toast.LENGTH_SHORT);
                    System.out.println("rỗng");
                }
                list.addAll(newList);
                adapter = new ItemResultAdapter(getContext(), list);
                recyclerView.setAdapter(adapter);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });

    }

    Button btnRemove;
    LoadJson loadJsonDelete;

    public void setDelete() {
        btnRemove = getView().findViewById(R.id.removeResult);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<String, String> map = new HashMap<>();
                map.put(Var.KEY_CODE_SUBJECT, codeSubject.getText().toString().trim());
                map.put(Var.KEY_CODE_STUDENT, codeStudent.getText().toString().trim());
                loadJsonDelete.sendDataToServer(Var.METHOD_REMOVE_RESULT, map);

            }
        });
        loadJsonDelete = new LoadJson();
        loadJsonDelete.setOnFinishLoadJSonListener(new LoadJson.OnFinishLoadJSonListener() {
            @Override
            public void finishLoadJSon(String error, String json) {
                Toast.makeText(getContext(), "Remove success", Toast.LENGTH_SHORT);
                try {
                    if (json != null) {
                        int jsonStart = json.indexOf("{");
                        int jsonEnd = json.lastIndexOf("}");
                        if (jsonStart >= 0 && jsonEnd >= 0 && jsonEnd > jsonStart) {
                            json = json.substring(jsonStart, jsonEnd + 1);
                            JSONObject jsonObject = new JSONObject(json);
                            if (jsonObject.getBoolean(Var.KEY_REMOVE)) {
                                Var.showToast(getContext(), getContext().getResources().getString(R.string.remove_success));

                            } else {
                                Var.showToast(getContext(), getContext().getResources().getString(R.string.remove_fail));
                            }
                        } else {
                            Var.showToast(getContext(), error);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
