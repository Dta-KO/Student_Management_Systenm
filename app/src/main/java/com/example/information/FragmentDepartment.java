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


public class FragmentDepartment extends Fragment {
    FloatingActionButton floatingActionButton;
    BottomSheetBehavior sheetBehavior;
    //department
    EditText codeDepartment, departmentName;
    Button btnReset, btnInsert, btnRemove, btnUpdate;
    LoadJson loadJson4, loadJsonUpdate, loadJsonDelete;
    ProgressDialog progressDialog4;


    ArrayList<Department> list;
    ItemDepartmentAdapter adapter;

    public void connectViewDepartment() {
        codeDepartment = getView().findViewById(R.id.codeDepartment);
        departmentName = getView().findViewById(R.id.departmentName);
        btnReset = getView().findViewById(R.id.resetDepartment);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codeDepartment.setText("");
                departmentName.setText("");
                codeDepartment.requestFocus();
            }
        });


    }

    //insertDepartment
    public void insertDepartment() {
        btnInsert = getView().findViewById(R.id.insertDepartment);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getText
                String CodeDepartment = codeDepartment.getText().toString().trim();
                String DepartmentName = departmentName.getText().toString().trim();

                //check null
                if (CodeDepartment.length() == 0) {
                    codeDepartment.requestFocus();
                    Var.showToast(getContext(), getContext().getResources().getString(R.string.enter_data));
                    return;
                }
                if (DepartmentName.length() == 0) {
                    departmentName.requestFocus();
                    Var.showToast(getContext(), getContext().getResources().getString(R.string.enter_data));
                    return;
                }

                //put data into json
                HashMap<String, String> map = new HashMap<>();
                map.put(Var.KEY_CODE_DEPARTMENT, CodeDepartment);
                map.put(Var.KEY_DEPARTMENT_NAME, DepartmentName);

                loadJson4.sendDataToServer(Var.METHOD_INSERT_DEPARTMENT, map);
                progressDialog4.show();
            }
        });
        loadJson4 = new LoadJson();
        progressDialog4 = new ProgressDialog(getContext());
        progressDialog4.setMessage(getContext().getResources().getString(R.string.wait));
        loadJson4.setOnFinishLoadJSonListener(new LoadJson.OnFinishLoadJSonListener() {
            @Override
            public void finishLoadJSon(String error, String json) {
                if (progressDialog4.isShowing()) {
                    progressDialog4.hide();
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


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View layoutBottom = getView().findViewById(R.id.design_bottom_sheet);
        sheetBehavior = BottomSheetBehavior.from(layoutBottom);
        connectViewDepartment();


        floatingActionButton = getView().findViewById(R.id.floatActionButtonDepartment);
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
        insertDepartment();
        setSwipeListener();
        setDelete();
        setUpdate();
    }


    //refresh list
    SwipeRefreshLayout swipeRefreshLayout;
    LoadJson loadJson44;

    public void setSwipeListener() {
        swipeRefreshLayout = getView().findViewById(R.id.design_above_sheet_department);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                HashMap<String, String> map = new HashMap<>();
                loadJson44.sendDataToServer(Var.METHOD_SELECT_ALL_DEPARTMENT, map);

            }
        });
        //set event when finish load data to server
        loadJson44 = new LoadJson();
        loadJson44.setOnFinishLoadJSonListener(new LoadJson.OnFinishLoadJSonListener() {
            @Override
            public void finishLoadJSon(String error, String json) {
                recyclerView = getView().findViewById(R.id.recyclerView_Department);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                ArrayList<Department> newList = DownJsonDepartment.jsonToListDepartment(json);
                list = new ArrayList<>();

                if (newList.size() == 0) {
                    Toast.makeText(getContext(), "Không có dữ liệu nheseeeeeeeeeee bạn...", Toast.LENGTH_SHORT);
                    System.out.println("rỗng");
                }
                list.addAll(newList);
                adapter = new ItemDepartmentAdapter(getContext(), list);
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

    RecyclerView recyclerView;


    public void setDelete() {
        btnRemove = getView().findViewById(R.id.removeDepartment);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<String, String> map = new HashMap<>();
                map.put(Var.KEY_CODE_DEPARTMENT, codeDepartment.getText().toString().trim());
                loadJsonDelete.sendDataToServer(Var.METHOD_REMOVE_DEPARTMENT, map);

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

    public void setUpdate() {
        btnUpdate = getView().findViewById(R.id.updateDepartment);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> map = new HashMap<>();
                map.put(Var.KEY_CODE_DEPARTMENT, codeDepartment.getText().toString().trim());
                loadJsonUpdate.sendDataToServer(Var.METHOD_UPDATE_DEPARTMENT, map);

            }
        });
        loadJsonUpdate = new LoadJson();
        loadJsonUpdate.setOnFinishLoadJSonListener(new LoadJson.OnFinishLoadJSonListener() {
            @Override
            public void finishLoadJSon(String error, String json) {
                try {
                    if (json != null) {
                        int jsonStart = json.indexOf("{");
                        int jsonEnd = json.lastIndexOf("}");
                        if (jsonStart >= 0 && jsonEnd >= 0 && jsonEnd > jsonStart) {
                            json = json.substring(jsonStart, jsonEnd + 1);
                            JSONObject jsonObject = new JSONObject(json);
                            if (jsonObject.getBoolean(Var.KEY_UPDATE)) {
                                Var.showToast(getContext(), getContext().getResources().getString(R.string.success));

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewDepartment = inflater.inflate(R.layout.fragment_department, container, false);

        return viewDepartment;
    }


}
