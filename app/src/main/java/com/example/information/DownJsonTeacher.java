package com.example.information;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DownJsonTeacher {
    public static Teachers jsonToTeacher(JSONObject jsonObject) {
        try {
            String codeTeacher = jsonObject.getString(Var.KEY_CODE_TEACHER);
            String teacherName = jsonObject.getString(Var.KEY_TEACHER_NAME);
            String birthdayTeacher = jsonObject.getString(Var.KEY_BIRTHDAY);
            String sexTeacher = jsonObject.getString(Var.KEY_SEX);
            String email = jsonObject.getString(Var.KEY_EMAIL);
            String tel = jsonObject.getString(Var.KEY_TEL);
            String type = jsonObject.getString(Var.KEY_TYPE);


            return new Teachers(codeTeacher, teacherName, birthdayTeacher, sexTeacher, tel, email, type);


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //sắp xong rùi đó, ahihi
    public static ArrayList<Teachers> jsonToListTeacher(String json) {
        ArrayList<Teachers> list = new ArrayList<>();

        try {
            if (json != null) {
                int jsonStart = json.indexOf("[");
                int jsonEnd = json.lastIndexOf("]");
                if (jsonStart >= 0 && jsonEnd >= 0 && jsonEnd > jsonStart) {
                    json = json.substring(jsonStart, jsonEnd + 1);
                    JSONArray arraySMSJson = new JSONArray(json);
                    for (int i = 0; i < arraySMSJson.length(); i++) {
                        JSONObject jsonObject = arraySMSJson.getJSONObject(i);
                        Teachers teachers = jsonToTeacher(jsonObject);
                        if (teachers != null) {
                            list.add(teachers);
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
