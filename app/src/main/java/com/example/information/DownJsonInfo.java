package com.example.information;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DownJsonInfo {
    public static Result jsonToResult(JSONObject jsonObject) {
        try {
            String codeStudent = jsonObject.getString(Var.KEY_CODE_STUDENT);
            String codeSubject = jsonObject.getString(Var.KEY_CODE_SUBJECT);
            double averagePoint = jsonObject.getDouble(Var.KEY_AVERAGE_POINT);
            double firstPoint = jsonObject.getDouble(Var.KEY_FIRST_POINT);
            double secondPoint = jsonObject.getDouble(Var.KEY_SECOND_POINT);
            double finalPoint = jsonObject.getDouble(Var.KEY_FINAL_POINT);
            String conduct = jsonObject.getString(Var.KEY_CONDUCT);
            String note = jsonObject.getString(Var.KEY_NOTE);


            return new Result(codeSubject, codeStudent, averagePoint, firstPoint, secondPoint, finalPoint, conduct, note);


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Students jsonToStudent(JSONObject jsonObject) {
        try {
            String codeStudent = jsonObject.getString(Var.KEY_CODE_STUDENT);
            String studentName = jsonObject.getString(Var.KEY_STUDENT_NAME);
            String birthday = jsonObject.getString(Var.KEY_BIRTHDAY);
            String sex = jsonObject.getString(Var.KEY_SEX);
            String address = jsonObject.getString(Var.KEY_ADDRESS);
            String codeClass = jsonObject.getString(Var.KEY_CODE_CLASS);


            return new Students(codeStudent, studentName, birthday, sex, address, codeClass);


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Subject jsonToSubject(JSONObject jsonObject) {
        try {
            String codeDepartment = jsonObject.getString(Var.KEY_CODE_DEPARTMENT);
            String codeTeacher = jsonObject.getString(Var.KEY_CODE_TEACHER);
            int numberUnits = jsonObject.getInt(Var.KEY_NUMBER_UNITS);
            String codeSubject = jsonObject.getString(Var.KEY_CODE_SUBJECT);
            String subjectName = jsonObject.getString(Var.KEY_SUBJECT_NAME);
            return new Subject(codeDepartment, codeTeacher, codeSubject, subjectName, numberUnits);


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Class jsonToClass(JSONObject jsonObject) {
        try {
            String codeClass = jsonObject.getString(Var.KEY_CODE_CLASS);
            String codeDepartment = jsonObject.getString(Var.KEY_CODE_DEPARTMENT);
            String className = jsonObject.getString(Var.KEY_CLASS_NAME);

            return new Class(codeDepartment, codeClass, className);


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
