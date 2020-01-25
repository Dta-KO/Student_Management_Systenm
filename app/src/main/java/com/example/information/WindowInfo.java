//package com.example.information;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//class WindowInfo extends AppCompatActivity{
//
//    RecyclerView recyclerView;
//    ItemAdapter itemAdapter;
//    ArrayList<Students> list;
//    @Override
//    protected void onCreate( Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main_window);
//
//}
package com.example.information;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class WindowInfo extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigationView;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.main_window);

        editText = findViewById(R.id.edt_search);

        toolbar = findViewById(R.id.toolBar_main);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.menu_navigation_main);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setHomeButtonEnabled(true);
        navigationView = findViewById(R.id.navigationView_main);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.about:
                        Toast.makeText(getApplicationContext(), R.string.about, Toast.LENGTH_LONG).show();
                        break;
                    case R.id.help:
                        Toast.makeText(getApplicationContext(), R.string.help, Toast.LENGTH_LONG).show();
                        break;
                    case R.id.logout:

                        drawerLayout.closeDrawers();
                        Intent intent = new Intent(WindowInfo.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }
                return true;
            }
        });
//        setSwipeListener();
    }
    LoadJson loadJsonStudent,loadJsonClass,loadJsonSubject,loadJsonResult;
    ArrayList<Students> listStudent;
    ArrayList<String> listClass;
    ArrayList<Result> listResult;
    ArrayList<String> listSubject;
    ItemStudentInfoAdapter studentInfoAdapter;
    ItemClassInfoAdapter classInfoAdapter;
    ItemSubjectInfoAdapter subjectInfoAdapter;
    ItemResultInfoAdapter resultInfoAdapter;


//    public void setSwipeListener() {
//        swipeRefreshLayout = findViewById(R.id.swipe_main);
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                HashMap<String, String> map = new HashMap<>();
//                map.put(Var.KEY_CODE_STUDENT,editText.getText().toString().trim());
//                loadJsonStudent.sendDataToServer(Var.METHOD_SELECT_STUDENT, map);
//                loadJsonResult.sendDataToServer(Var.METHOD_SELECT_RESULT,map);
//                loadJsonSubject.sendDataToServer(Var.METHOD_SELECT_SUBJECT, map);
//                loadJsonClass.sendDataToServer(Var.METHOD_SELECT_CLASS,map);
//
//            }
//        });
//        //set event when finish load data to server
//        loadJsonStudent = new LoadJson();
//        loadJsonStudent.setOnFinishLoadJSonListener(new LoadJson.OnFinishLoadJSonListener() {
//            @Override
//            public void finishLoadJSon(String error, String json) {
//                recyclerView = findViewById(R.id.recyclerView_main);
//                recyclerView.setHasFixedSize(true);
//                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                ArrayList<Students> newList = DownJsonStudentInfo.jsonToListStudent(json);
//                listStudent = new ArrayList<>();
//                listStudent.addAll(newList);
//                studentInfoAdapter = new ItemStudentInfoAdapter(getApplicationContext(), listStudent);
//                recyclerView.setAdapter(studentInfoAdapter);
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        swipeRefreshLayout.setRefreshing(false);
//                    }
//                }, 1000);
//            }
//        });
//        //class
//        loadJsonClass = new LoadJson();
//        loadJsonClass.setOnFinishLoadJSonListener(new LoadJson.OnFinishLoadJSonListener() {
//            @Override
//            public void finishLoadJSon(String error, String json) {
//                recyclerView = findViewById(R.id.recyclerView_main);
//                recyclerView.setHasFixedSize(true);
//                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                json = json.substring(2,json.length()-1);
//                String newList = json;
//                listClass = new ArrayList<>();
//                listClass.add(newList);
//                classInfoAdapter = new ItemClassInfoAdapter(getApplicationContext(), listClass);
//                recyclerView.setAdapter(classInfoAdapter);
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        swipeRefreshLayout.setRefreshing(false);
//                    }
//                }, 1000);
//            }
//        });
//        //result
//        loadJsonResult = new LoadJson();
//        loadJsonResult.setOnFinishLoadJSonListener(new LoadJson.OnFinishLoadJSonListener() {
//            @Override
//            public void finishLoadJSon(String error, String json) {
//                recyclerView = findViewById(R.id.recyclerView_main);
//                recyclerView.setHasFixedSize(true);
//                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                int jsonStart = json.indexOf("{");
//                int jsonEnd = json.lastIndexOf("}");
//                if (jsonStart >= 0 && jsonEnd >= 0 && jsonEnd > jsonStart){
//                    json = json.substring(jsonStart,jsonEnd+1);
//                    JSONObject jsonObject =
//                }
//
//                ArrayList<Result> newList = DownJsonResultInfo.jsonToListResult(json);
//
//                listResult = new ArrayList<>();
//                listResult.addAll(newList);
//                resultInfoAdapter = new ItemResultInfoAdapter(getApplicationContext(), listResult);
//                recyclerView.setAdapter(resultInfoAdapter);
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        swipeRefreshLayout.setRefreshing(false);
//                    }
//                }, 1000);
//            }
//        });
//        loadJsonSubject = new LoadJson();
//        loadJsonSubject.setOnFinishLoadJSonListener(new LoadJson.OnFinishLoadJSonListener() {
//            @Override
//            public void finishLoadJSon(String error, String json) {
//                recyclerView = findViewById(R.id.recyclerView_main);
//                recyclerView.setHasFixedSize(true);
//                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                json = json.substring(2,json.length()-1);
//                String result = json;
//                listSubject = new ArrayList<>();
//                listSubject.add(result);
//                subjectInfoAdapter = new ItemSubjectInfoAdapter(getApplicationContext(), listSubject);
//                recyclerView.setAdapter(subjectInfoAdapter);
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        swipeRefreshLayout.setRefreshing(false);
//                    }
//                }, 1000);
//            }
//        });
//
//    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
