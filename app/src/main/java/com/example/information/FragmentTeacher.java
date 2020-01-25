package com.example.information;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.icu.util.Calendar;
import android.os.Build;
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
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class FragmentTeacher extends Fragment {
    FloatingActionButton floatingActionButton;
    BottomSheetBehavior sheetBehavior;

    EditText codeTeacher, teacherName, birthdayTeacher, email, tel, type;
    CheckBox sexTeacher;
    Button btnReset, btnInsert, btnRemove;
    private int mYear, mMonth, mDay;
    LoadJson loadJson3, loadJsonDelete;
    ProgressDialog progressDialog3;
    String user;
    ArrayList<Teachers> list;
    ItemTeacherAdapter adapter;
    RecyclerView recyclerView;


    public void connectViewTeacher() {
        codeTeacher = getView().findViewById(R.id.codeTeacher);
        teacherName = getView().findViewById(R.id.teacherName);
        birthdayTeacher = getView().findViewById(R.id.birthdayTeacher);
        sexTeacher = getView().findViewById(R.id.sexCheckboxTeacher);
        email = getView().findViewById(R.id.email);
        tel = getView().findViewById(R.id.tel);
        type = getView().findViewById(R.id.typeTeacher);
        btnReset = getView().findViewById(R.id.resetTeacher);
        birthdayTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);


                    DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {

                                    birthdayTeacher.setText(new StringBuilder().append(dayOfMonth).append("/").append(monthOfYear + 1).append("/").append(year).toString());

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                } else {
                    Toast.makeText(getContext(), "This feature is only available on api 24 and above ", Toast.LENGTH_LONG);
                }

            }
        });

    }
    public void setReset(){
        btnReset.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            codeTeacher.setText("");
                                            teacherName.setText("");
                                            birthdayTeacher.setText("");
                                            email.setText("");
                                            tel.setText("");
                                            type.setText("");
                                            codeTeacher.requestFocus();

                                        }
                                    }
        );
    }


    public FragmentTeacher() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View layoutBottom = getView().findViewById(R.id.design_bottom_sheet);
        sheetBehavior = BottomSheetBehavior.from(layoutBottom);
        connectViewTeacher();


        floatingActionButton = getView().findViewById(R.id.floatActionButtonTeacher);
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
        setDelete();
        setInsert();
        setReset();
    }

    public void setDelete() {
        btnRemove = getView().findViewById(R.id.removeTeacher);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadJsonDelete = new LoadJson();
                HashMap<String, String> map = new HashMap<>();
                map.put(Var.KEY_CODE_TEACHER, codeTeacher.getText().toString().trim());
                loadJsonDelete.sendDataToServer(Var.METHOD_REMOVE_TEACHER, map);
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
        });
    }
    public void setInsert(){
        btnInsert = getView().findViewById(R.id.insertTeacher);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get Text
                String CodeTeacher = codeTeacher.getText().toString().trim();
                String TeacherName = teacherName.getText().toString().trim();
                String BirthdayTeacher = birthdayTeacher.getText().toString().trim();
                String Email = email.getText().toString().trim();
                String Tel = tel.getText().toString().trim();
                String Type = type.getText().toString().trim();

                //check null
                if (CodeTeacher.length() == 0) {
                    codeTeacher.requestFocus();
                    Var.showToast(getContext(), getContext().getResources().getString(R.string.enter_data));
                    return;
                }
                if (TeacherName.length() == 0) {
                    teacherName.requestFocus();
                    Var.showToast(getContext(), getContext().getResources().getString(R.string.enter_data));
                    return;
                }
                if (BirthdayTeacher.length() == 0) {
                    birthdayTeacher.requestFocus();
                    Var.showToast(getContext(), getContext().getResources().getString(R.string.enter_data));
                    return;
                }
                if (Email.length() == 0) {
                    email.requestFocus();
                    Var.showToast(getContext(), getContext().getResources().getString(R.string.enter_data));
                    return;
                }
                if (Tel.length() == 0) {
                    tel.requestFocus();
                    Var.showToast(getContext(), getContext().getResources().getString(R.string.enter_data));
                    return;
                }
                if (Type.length() == 0) {
                    type.requestFocus();
                    Var.showToast(getContext(), getContext().getResources().getString(R.string.enter_data));
                    return;
                }

                //lấy dữ liệu user
                Bundle bundleUser = getArguments();
                if (bundleUser != null) {
                    user = bundleUser.getString(Var.KEY_USER);
                }
                //put data into json
                HashMap<String, String> map = new HashMap<>();
                map.put(Var.KEY_CODE_TEACHER, CodeTeacher);
                map.put(Var.KEY_TEACHER_NAME, TeacherName);
                map.put(Var.KEY_BIRTHDAY, BirthdayTeacher);
                map.put(Var.KEY_EMAIL, Email);
                map.put(Var.KEY_TEL, Tel);
                map.put(Var.KEY_TYPE, Type);
                if (sexTeacher.isChecked()) {
                    map.put(Var.KEY_SEX, "nam");
                } else {
                    map.put(Var.KEY_SEX, "nữ");
                }

                loadJson3.sendDataToServer(Var.METHOD_INSERT_TEACHER, map);
                progressDialog3.show();

            }
        });
        loadJson3 = new LoadJson();
        progressDialog3 = new ProgressDialog(getContext());
        progressDialog3.setMessage(getContext().getResources().getString(R.string.wait));
        loadJson3.setOnFinishLoadJSonListener(new LoadJson.OnFinishLoadJSonListener() {
            @Override
            public void finishLoadJSon(String error, String json) {
                if (progressDialog3.isShowing()) {
                    progressDialog3.hide();
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
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewTeacher = inflater.inflate(R.layout.fragment_teacher, container, false);
        return viewTeacher;
    }


    SwipeRefreshLayout swipeRefreshLayout;
    LoadJson loadJsonSwipeTeacher;

    public void setSwipeListener() {
        swipeRefreshLayout = getView().findViewById(R.id.design_above_sheet_teacher);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                HashMap<String, String> map = new HashMap<>();
                loadJsonSwipeTeacher.sendDataToServer(Var.METHOD_SELECT_ALL_TEACHER, map);

            }
        });
        //set event when finish load data to server
        loadJsonSwipeTeacher = new LoadJson();
        loadJsonSwipeTeacher.setOnFinishLoadJSonListener(new LoadJson.OnFinishLoadJSonListener() {
            @Override
            public void finishLoadJSon(String error, String json) {
                recyclerView = getView().findViewById(R.id.recyclerView_teacher);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                ArrayList<Teachers> newList = DownJsonTeacher.jsonToListTeacher(json);
                list = new ArrayList<>();

                if (newList.size() == 0) {
                    Toast.makeText(getContext(), "Không có dữ liệu nheseeeeeeeeeee bạn...", Toast.LENGTH_SHORT);
                    System.out.println("rỗng");
                }
                list.addAll(newList);
                adapter = new ItemTeacherAdapter(getContext(), list);
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


}
