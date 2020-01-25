package com.example.information;

import org.json.JSONObject;

public class Department {
    private String codeDepartment, departmentName;

    public Department() {
    }

    public Department(String codeDepartment, String departmentName) {

        this.codeDepartment = codeDepartment;
        this.departmentName = departmentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public Department setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
        return this;
    }


    public String getCodeDepartment() {
        return codeDepartment;
    }

    public Department setCodeDepartment(String codeDepartment) {
        this.codeDepartment = codeDepartment;
        return this;
    }


    public String departmentToJson() {
        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put(Var.KEY_CODE_DEPARTMENT, getCodeDepartment());
            jsonObject.put(Var.KEY_DEPARTMENT_NAME,getDepartmentName());

            return jsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
