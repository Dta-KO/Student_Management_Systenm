package com.example.information;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DownJsonSubject {

    public static Subject jsonToSubject(JSONObject jsonObject) {
        try {
            String codeDepartment = jsonObject.getString(Var.KEY_CODE_DEPARTMENT);
            String codeTeacher = jsonObject.getString(Var.KEY_CODE_TEACHER);
            int numberUnits = jsonObject.getInt(Var.KEY_NUMBER_UNITS);
            String codeSubject = jsonObject.getString(Var.KEY_CODE_SUBJECT);
            String subjectName = jsonObject.getString(Var.KEY_SUBJECT_NAME);
            return new Subject(codeDepartment, codeTeacher,codeSubject,subjectName,numberUnits);


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //sắp xong rùi đó, ahihi
    public static ArrayList<Subject> jsonToListSubject(String json) {
        ArrayList<Subject> list = new ArrayList<>();

        try {
            if (json != null) {
                int jsonStart = json.indexOf("[");
                int jsonEnd = json.lastIndexOf("]");
                if (jsonStart >= 0 && jsonEnd >= 0 && jsonEnd > jsonStart) {
                    json = json.substring(jsonStart, jsonEnd + 1);
                    JSONArray arraySMSJson = new JSONArray(json);
                    for (int i = 0; i < arraySMSJson.length(); i++) {
                        JSONObject jsonObject = arraySMSJson.getJSONObject(i);
                        Subject subject = jsonToSubject(jsonObject);
                        if (subject != null) {
                            list.add(subject);
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
