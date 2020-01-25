package com.example.information;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class FragmentStudents extends Fragment {
    FloatingActionButton floatingActionButton;
    BottomSheetBehavior sheetBehavior;
    LoadJson loadJson1, loadJsonSwipe, loadJsonDelete;
    ProgressDialog progressDialog1;


    EditText codeStudent, birthdayStudent, studentName, address;
    CheckBox sexStudent;
    AutoCompleteTextView codeClass;
    Button btnReset, btnInsert, btnRemove;
    private int mYear, mMonth, mDay;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    //đáng lẽ tôi nên tách từng khúc từng phân đoạn ra thì code sẽ clean hơn nhưng tui lại quá lười để làm việc đó :)));
    // bên teacher sẽ làm tốt hơn

    public FragmentStudents() {

    }

    public void connectViewStudent() {
        View layoutBottom = getView().findViewById(R.id.design_bottom_sheet);
        sheetBehavior = BottomSheetBehavior.from(layoutBottom);


        codeClass = getView().findViewById(R.id.codeClassStudent);
        codeStudent = getView().findViewById(R.id.codeStudent);
        studentName = getView().findViewById(R.id.studentName);
        birthdayStudent = getView().findViewById(R.id.birthdayStudent);
        birthdayStudent.setOnClickListener(new View.OnClickListener() {
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

                                    birthdayStudent.setText(new StringBuilder().append(dayOfMonth).append("/").append(monthOfYear + 1).append("/").append(year).toString());

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                } else {
                    Toast.makeText(getContext(), "This feature is only available on API 24 and above!", Toast.LENGTH_LONG);
                }

            }
        });
        sexStudent = getView().findViewById(R.id.sexCheckboxStudent);
        address = getView().findViewById(R.id.address);


// điều hướng bottom sheet
        floatingActionButton = getView().findViewById(R.id.floatActionButtonStudent);
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


    }

    public void setInsert() {
        btnInsert = getView().findViewById(R.id.insertStudent);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get Text
                String CodeStudent = codeStudent.getText().toString().trim();
                String CodeClass = codeClass.getText().toString().trim();
                String StudentName = studentName.getText().toString().trim();
                String BirthdayStudent = birthdayStudent.getText().toString().trim();
                String Address = address.getText().toString().trim();

                //check null
                if (CodeStudent.length() == 0) {
                    codeStudent.requestFocus();
                    Var.showToast(getContext(), getContext().getResources().getString(R.string.enter_data));
                    return;
                }
                if (CodeClass.length() == 0) {
                    codeClass.requestFocus();
                    Var.showToast(getContext(), getContext().getResources().getString(R.string.enter_data));
                    return;
                }
                if (StudentName.length() == 0) {
                    studentName.requestFocus();
                    Var.showToast(getContext(), getContext().getResources().getString(R.string.enter_data));
                    return;
                }
                if (BirthdayStudent.length() == 0) {
                    birthdayStudent.requestFocus();
                    Var.showToast(getContext(), getContext().getResources().getString(R.string.enter_data));
                    return;
                }
                if (Address.length() == 0) {
                    address.requestFocus();
                    Var.showToast(getContext(), getContext().getResources().getString(R.string.enter_data));
                    return;
                }

                //put data into json
                HashMap<String, String> map = new HashMap<>();
                map.put(Var.KEY_STUDENT_NAME, StudentName);
                map.put(Var.KEY_CODE_STUDENT, CodeStudent);
                map.put(Var.KEY_CODE_CLASS, CodeClass);
                map.put(Var.KEY_BIRTHDAY, BirthdayStudent);
                if (sexStudent.isChecked()) {
                    map.put(Var.KEY_SEX, "nam");
                } else {
                    map.put(Var.KEY_SEX, "nữ");
                }
                map.put(Var.KEY_ADDRESS, Address);

                loadJson1.sendDataToServer(Var.METHOD_INSERT_STUDENT, map);
                progressDialog1.show();


            }
        });
        //lấy dữ liệu user


        loadJson1 = new LoadJson();
        progressDialog1 = new ProgressDialog(getContext());
        progressDialog1.setMessage(getContext().getResources().getString(R.string.wait));
        loadJson1.setOnFinishLoadJSonListener(new LoadJson.OnFinishLoadJSonListener() {
            @Override
            public void finishLoadJSon(String error, String json) {
                if (progressDialog1.isShowing()) {
                    progressDialog1.hide();
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

    public void setReset() {
        btnReset = getView().findViewById(R.id.resetStudent);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codeClass.setText("");
                codeStudent.setText("");
                studentName.setText("");
                birthdayStudent.setText("");
                address.setText("");
                codeClass.requestFocus();
            }
        });

    }

    public void setDelete() {
        btnRemove = getView().findViewById(R.id.removeStudent);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadJsonDelete = new LoadJson();
                HashMap<String, String> map = new HashMap<>();
                map.put(Var.KEY_CODE_STUDENT, codeStudent.getText().toString().trim());
                loadJsonDelete.sendDataToServer(Var.METHOD_REMOVE_STUDENT, map);
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

    public ArrayList<Students> list2;
    public ItemStudentAdapter adapter;


    //refresh list
    public void setSwipeListener() {
        swipeRefreshLayout = getView().findViewById(R.id.design_above_sheet_student);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                HashMap<String, String> map = new HashMap<>();
                loadJsonSwipe.sendDataToServer(Var.METHOD_SELECT_ALL_STUDENT, map);

            }
        });
        //set event when finish load data to server
        loadJsonSwipe = new LoadJson();
        loadJsonSwipe.setOnFinishLoadJSonListener(new LoadJson.OnFinishLoadJSonListener() {
            @Override
            public void finishLoadJSon(String error, String json) {
                recyclerView = getView().findViewById(R.id.recyclerView_student);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                ArrayList<Students> newList = DownJsonStudent.jsonToListStudent(json);
                list2 = new ArrayList<>();

                if (newList.size() == 0) {
                    Toast.makeText(getContext(), "Không có dữ liệu nhé bạn...", Toast.LENGTH_SHORT);
                    System.out.println("rỗng");
                }
                list2.addAll(newList);
                adapter = new ItemStudentAdapter(getContext(), list2);
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


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        connectViewStudent();
        setSwipeListener();
        setDelete();
        setInsert();
        setReset();

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewStudent = inflater.inflate(R.layout.fragment_student, container, false);


        return viewStudent;
    }
}
