package com.example.information;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DownJsonDepartment {

    public static Department jsonToDepartment(JSONObject jsonObject) {
        try {
            String codeDepartment = jsonObject.getString(Var.KEY_CODE_DEPARTMENT);
            String departmentName = jsonObject.getString(Var.KEY_DEPARTMENT_NAME);
            return new Department(codeDepartment, departmentName);


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //sắp xong rùi đó, ahihi
    public static ArrayList<Department> jsonToListDepartment(String json) {
        ArrayList<Department> list = new ArrayList<>();

        try {
            if (json != null) {
                int jsonStart = json.indexOf("[");
                int jsonEnd = json.lastIndexOf("]");
                if (jsonStart >= 0 && jsonEnd >= 0 && jsonEnd > jsonStart) {
                    json = json.substring(jsonStart, jsonEnd + 1);
                    JSONArray arraySMSJson = new JSONArray(json);
                    for (int i = 0; i < arraySMSJson.length(); i++) {
                        JSONObject jsonObject = arraySMSJson.getJSONObject(i);
                        Department department = jsonToDepartment(jsonObject);
                        if (department != null) {
                            list.add(department);
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
