package com.example.information;

import android.app.ProgressDialog;
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


public class FragmentClass extends Fragment {
    FloatingActionButton floatingActionButton;
    BottomSheetBehavior sheetBehavior;
    EditText codeClass, codeDepartment, className;
    Button btnReset, btnInsert, btnRemove1;
    LoadJson loadJson2, loadJsonDelete1;
    ProgressDialog progressDialog2;

    public void connectViewClass() {
        View layoutBottom = getView().findViewById(R.id.design_bottom_sheet);
        sheetBehavior = BottomSheetBehavior.from(layoutBottom);
        floatingActionButton = getView().findViewById(R.id.floatActionButtonClass);
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

        //declare element of class

        codeClass = getView().findViewById(R.id.codeClass);
        codeDepartment = getView().findViewById(R.id.codeDepartment);
        className = getView().findViewById(R.id.className);
        btnReset = getView().findViewById(R.id.resetClass);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codeClass.setText("");
                codeDepartment.setText("");
                className.setText("");
                codeClass.requestFocus();
            }
        });


    }

    public void insertClass() {
        btnInsert = getView().findViewById(R.id.insertClass);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get Text
                String CodeClass = codeClass.getText().toString().trim();
                String CodeDepartment = codeDepartment.getText().toString().trim();
                String ClassName = className.getText().toString().trim();

                if (CodeClass.length() == 0) {
                    codeClass.requestFocus();
                    Var.showToast(getContext(), getContext().getResources().getString(R.string.enter_data));
                    return;
                }
                if (CodeDepartment.length() == 0) {
                    codeDepartment.requestFocus();
                    Var.showToast(getContext(), getContext().getResources().getString(R.string.enter_data));
                    return;
                }
                if (ClassName.length() == 0) {
                    className.requestFocus();
                    Var.showToast(getContext(), getContext().getResources().getString(R.string.enter_data));
                    return;
                }

                //put data into json
                HashMap<String, String> map = new HashMap<>();
                map.put(Var.KEY_CODE_CLASS, CodeClass);
                map.put(Var.KEY_CODE_DEPARTMENT, CodeDepartment);
                map.put(Var.KEY_CLASS_NAME, ClassName);


                loadJson2.sendDataToServer(Var.METHOD_INSERT_CLASS, map);
                progressDialog2.show();
            }
        });
        loadJson2 = new LoadJson();
        progressDialog2 = new ProgressDialog(getContext());
        progressDialog2.setMessage(getContext().getResources().getString(R.string.wait));
        loadJson2.setOnFinishLoadJSonListener(new LoadJson.OnFinishLoadJSonListener() {
            @Override
            public void finishLoadJSon(String error, String json) {
                if (progressDialog2.isShowing()) {
                    progressDialog2.hide();
                }
                try {
                    if (json != null) {
                        JSONObject jsonObject = new JSONObject(json);
                        if (jsonObject.getBoolean(Var.KEY_INSERT)) {
                            Var.showToast(getContext(), getContext().getResources().getString(R.string.insert_success));
                        } else {
                            Var.showToast(getContext(), getContext().getResources().getString(R.string.insert_fail));
                        }
                    } else {
                        Var.showToast(getContext(), error);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    public FragmentClass() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        connectViewClass();
        insertClass();
        setDelete();
        setSwipeListener();
    }


    public ArrayList<Class> list;
    public ItemClassAdapter adapter;


    SwipeRefreshLayout swipeRefreshLayout;
    LoadJson loadJson22;
    RecyclerView recyclerView;

    //refresh list
    public void setSwipeListener() {
        swipeRefreshLayout = getView().findViewById(R.id.design_above_sheet_class);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                HashMap<String, String> map = new HashMap<>();
                loadJson22.sendDataToServer(Var.METHOD_SELECT_ALL_CLASS, map);

            }
        });
        //set event when finish load data to server
        loadJson22 = new LoadJson();
        loadJson22.setOnFinishLoadJSonListener(new LoadJson.OnFinishLoadJSonListener() {
            @Override
            public void finishLoadJSon(String error, String json) {
                recyclerView = getView().findViewById(R.id.recyclerView_class);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                ArrayList<Class> newList = DownJsonClass.jsonToListClass(json);
                list = new ArrayList<>();

                if (newList.size() == 0) {
                    Toast.makeText(getContext(), "Không có dữ liệu nheseeeeeeeeeee bạn...", Toast.LENGTH_SHORT);
                    System.out.println("rỗng");
                }
                list.addAll(newList);
                adapter = new ItemClassAdapter(getContext(), list);
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

    public void setDelete() {
        btnRemove1 = getView().findViewById(R.id.removeClass);
        btnRemove1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadJsonDelete1 = new LoadJson();
                HashMap<String, String> map = new HashMap<>();
                map.put(Var.KEY_CODE_CLASS, codeClass.getText().toString().trim());
                loadJsonDelete1.sendDataToServer(Var.METHOD_REMOVE_CLASS, map);
                loadJsonDelete1.setOnFinishLoadJSonListener(new LoadJson.OnFinishLoadJSonListener() {
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
        });
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewClass = inflater.inflate(R.layout.fragment_class, container, false);
        return viewClass;
    }


}
