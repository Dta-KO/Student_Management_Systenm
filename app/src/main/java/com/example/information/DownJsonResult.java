package com.example.information;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DownJsonResult {
    public static Result jsonToResult(JSONObject jsonObject) {
        try {
            String codeSubject = jsonObject.getString(Var.KEY_CODE_SUBJECT);
            String codeStudent = jsonObject.getString(Var.KEY_CODE_STUDENT);
            double averagePoint = jsonObject.getDouble(Var.KEY_AVERAGE_POINT);
            double firstPoint = jsonObject.getDouble(Var.KEY_FIRST_POINT);
            double secondPoint = jsonObject.getDouble(Var.KEY_SECOND_POINT);
            double finalPoint = jsonObject.getDouble(Var.KEY_FINAL_POINT);
            String conduct = jsonObject.getString(Var.KEY_CONDUCT);
            String note = jsonObject.getString(Var.KEY_NOTE);


            return new Result(codeSubject, codeStudent, averagePoint,firstPoint,secondPoint,finalPoint,conduct,note);


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //sắp xong rùi đó, ahihi
    public static ArrayList<Result> jsonToListResult(String json) {
        ArrayList<Result> list = new ArrayList<>();

        try {
            if (json != null) {
                int jsonStart = json.indexOf("[");
                int jsonEnd = json.lastIndexOf("]");
                if (jsonStart >= 0 && jsonEnd >= 0 && jsonEnd > jsonStart) {
                    json = json.substring(jsonStart, jsonEnd + 1);
                    JSONArray arraySMSJson = new JSONArray(json);
                    for (int i = 0; i < arraySMSJson.length(); i++) {
                        JSONObject jsonObject = arraySMSJson.getJSONObject(i);
                        Result result = jsonToResult(jsonObject);
                        if (result != null) {
                            list.add(result);
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
