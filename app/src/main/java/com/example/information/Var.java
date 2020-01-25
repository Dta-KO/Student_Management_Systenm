package com.example.information;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class Var {

    //for account
    public static final String KEY_USER = "user";
    public static final String KEY_PASS = "pass";

    //key for student
    public static final String KEY_CODE_STUDENT = "codeStudent";
    public static final String KEY_STUDENT_NAME = "studentName";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_CODE_CLASS = "codeClass";

    //key for result
    public static final String KEY_CODE_SUBJECT = "codeSubject";
    public static final String KEY_AVERAGE_POINT = "averagePoint";
    public static final String KEY_FIRST_POINT = "firstPoint";
    public static final String KEY_SECOND_POINT = "secondPoint";
    public static final String KEY_FINAL_POINT = "finalPoint";
    public static final String KEY_NOTE = "note";
    public static final String KEY_CONDUCT = "conduct";
    //key for subject
    public static final String KEY_NUMBER_UNITS = "numberUnits";
    public static final String KEY_SUBJECT_NAME = "subjectName";


    //key for action
    public static final String KEY_LOGIN = "login";
    public static final String KEY_SELECT = "select";
    public static final String KEY_INSERT = "insert";
    public static final String KEY_UPDATE = "update";
    public static final String KEY_REMOVE = "remove";

    //key for teacher
    public static final String KEY_TYPE = "type";
    public static final String KEY_TEL = "tel";
    public static final String KEY_SEX = "sex";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_BIRTHDAY = "birthday";
    public static final String KEY_TEACHER_NAME = "teacherName";
    public static final String KEY_CODE_TEACHER = "codeTeacher";
    //
    public static final String KEY_CLASS_NAME = "className";

    public static final String KEY_DEPARTMENT_NAME = "departmentName";
    public static final String KEY_CODE_DEPARTMENT = "codeDepartment";

    public static final String KEY_METHOD = "method";
    //key for method
    public static final int METHOD_LOGIN = 1;
    public static final int METHOD_SELECT_STUDENT = 440;
    public static final int METHOD_SELECT_CLASS = 220;
    public static final int METHOD_SELECT_SUBJECT = 550;
    public static final int METHOD_SELECT_RESULT = 660;
    public static final int METHOD_SELECT_ALL_STUDENT = 441;
    public static final int METHOD_SELECT_ALL_TEACHER = 331;
    public static final int METHOD_SELECT_ALL_DEPARTMENT = 111;
    public static final int METHOD_SELECT_ALL_SUBJECT = 551;
    public static final int METHOD_SELECT_ALL_RESULT= 661;
    public static final int METHOD_SELECT_ALL_CLASS = 221;
    public static final int METHOD_REMOVE_STUDENT = 444;
    public static final int METHOD_REMOVE_DEPARTMENT = 114;
    public static final int METHOD_REMOVE_CLASS = 224;
    public static final int METHOD_REMOVE_TEACHER = 334;
    public static final int METHOD_REMOVE_RESULT = 664;
    public static final int METHOD_REMOVE_SUBJECT = 554;
    public static final int METHOD_INSERT_STUDENT = 443;
    public static final int METHOD_INSERT_CLASS = 223;
    public static final int METHOD_INSERT_DEPARTMENT = 113;
    public static final int METHOD_INSERT_TEACHER = 333;

    public static final int METHOD_UPDATE_STUDENT = 442;
    public static final int METHOD_UPDATE_DEPARTMENT = 112;
    public static final int METHOD_UPDATE_CLASS = 222;
    public static final int METHOD_UPDATE_TEACHER = 332;



    public static void showToast(Context context, String sms) {
        Toast.makeText(context, sms, Toast.LENGTH_SHORT).show();
    }

    // method for save and get nick and pass user

    public static void save(Context context, String key, String value) {
        SharedPreferences.Editor editor = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext())
                .edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String get(Context context, String key) {
        SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        return settings.getString(key, null);
    }
}
