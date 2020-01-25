package com.example.information;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DownJsonClass {
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

    //sắp xong rùi đó, ahihi
    public static ArrayList<Class> jsonToListClass(String json) {
        ArrayList<Class> list = new ArrayList<>();

        try {
            if (json != null) {
                int jsonStart = json.indexOf("[");
                int jsonEnd = json.lastIndexOf("]");
                if (jsonStart >= 0 && jsonEnd >= 0 && jsonEnd > jsonStart) {
                    json = json.substring(jsonStart, jsonEnd + 1);
                    JSONArray arraySMSJson = new JSONArray(json);
                    for (int i = 0; i < arraySMSJson.length(); i++) {
                        JSONObject jsonObject = arraySMSJson.getJSONObject(i);
                        Class aClass = jsonToClass(jsonObject);
                        if (aClass != null) {
                            list.add(aClass);
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
