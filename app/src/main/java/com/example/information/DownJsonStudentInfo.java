package com.example.information;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DownJsonStudentInfo {

    public static Students jsonToStudent(JSONObject jsonObject) {
        try {
            String codeStudent = jsonObject.getString(Var.KEY_CODE_STUDENT);
            String studentName = jsonObject.getString(Var.KEY_STUDENT_NAME);
            String birthday = jsonObject.getString(Var.KEY_BIRTHDAY);
            String sex = jsonObject.getString(Var.KEY_SEX);
            String address = jsonObject.getString(Var.KEY_ADDRESS);
            String codeClass = jsonObject.getString(Var.KEY_CODE_CLASS);
            return new Students(codeStudent,studentName,birthday,sex,address,codeClass);


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //sắp xong rùi đó, ahihi
    public static ArrayList<Students> jsonToListStudent(String json) {
        ArrayList<Students> list = new ArrayList<>();

        try {
            if (json != null) {
                int jsonStart = json.indexOf("[");
                int jsonEnd = json.lastIndexOf("]");
                if (jsonStart >= 0 && jsonEnd >= 0 && jsonEnd > jsonStart) {
                    json = json.substring(jsonStart, jsonEnd + 1);
                    JSONArray arraySMSJson = new JSONArray(json);
                    for (int i = 0; i < arraySMSJson.length(); i++) {
                        JSONObject jsonObject = arraySMSJson.getJSONObject(i);
                        Students students = jsonToStudent(jsonObject);
                        if (students != null) {
                            list.add(students);
                        }
                    }
                } else {
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
