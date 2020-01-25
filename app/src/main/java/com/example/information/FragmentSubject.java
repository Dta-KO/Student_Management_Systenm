package com.example.information;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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


public class FragmentSubject extends Fragment {
    FloatingActionButton floatingActionButton;
    BottomSheetBehavior sheetBehavior;
    EditText codeSubject, codeTeacher, codeDepartment, subjectName, numberUnits;

    public void connecting() {
        codeSubject = getView().findViewById(R.id.codeSubject);
        codeTeacher = getView().findViewById(R.id.codeTeacherSubject);
        codeDepartment = getView().findViewById(R.id.codeDepartmentSubject);
        subjectName = getView().findViewById(R.id.subjectName);
        numberUnits = getView().findViewById(R.id.numberUnits);
    }


    public FragmentSubject() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View layoutBottom = getView().findViewById(R.id.design_bottom_sheet);
        sheetBehavior = BottomSheetBehavior.from(layoutBottom);


        floatingActionButton = getView().findViewById(R.id.floatActionButtonSubject);
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
        connecting();
        setReset();
        setSwipeListener();
        setDelete();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    Button btnReset;

    public void setReset() {
        btnReset = getView().findViewById(R.id.resetSubject);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codeSubject.setText("");
                codeTeacher.setText("");
                codeDepartment.setText("");
                numberUnits.setText("");
                subjectName.setText("");
                codeDepartment.requestFocus();
            }
        });
    }

    SwipeRefreshLayout swipeRefreshLayout;
    LoadJson loadJson44;
    RecyclerView recyclerView;
    ArrayList<Subject> list;
    ItemSubjectAdapter adapter;

    public void setSwipeListener() {
        swipeRefreshLayout = getView().findViewById(R.id.design_above_sheet_subject);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                HashMap<String, String> map = new HashMap<>();
                loadJson44.sendDataToServer(Var.METHOD_SELECT_ALL_SUBJECT, map);

            }
        });
        //set event when finish load data to server
        loadJson44 = new LoadJson();
        loadJson44.setOnFinishLoadJSonListener(new LoadJson.OnFinishLoadJSonListener() {
            @Override
            public void finishLoadJSon(String error, String json) {
                recyclerView = getView().findViewById(R.id.recyclerView_subject);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                ArrayList<Subject> newList = DownJsonSubject.jsonToListSubject(json);
                list = new ArrayList<>();

                if (newList.size() == 0) {
                    Toast.makeText(getContext(), "Không có dữ liệu nheseeeeeeeeeee bạn...", Toast.LENGTH_SHORT);
                    System.out.println("rỗng");
                }
                list.addAll(newList);
                adapter = new ItemSubjectAdapter(getContext(), list);
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
        btnRemove = getView().findViewById(R.id.removeSubject);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<String, String> map = new HashMap<>();
                map.put(Var.KEY_CODE_SUBJECT, codeSubject.getText().toString().trim());
                loadJsonDelete.sendDataToServer(Var.METHOD_REMOVE_SUBJECT, map);

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



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewSubject = inflater.inflate(R.layout.fragment_subject, container, false);
        return viewSubject;
    }
}
