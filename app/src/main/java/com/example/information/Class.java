package com.example.information;

import org.json.JSONObject;

public class Class {
    private String codeClass, className, codeDepartment;

    public Class() {
    }

    public Class(String codeClass, String codeDepartment, String className) {

        this.codeDepartment = codeDepartment;
        this.codeClass = codeClass;
        this.className = className;
    }

    public String getCodeClass() {
        return codeClass;
    }

    public Class setCodeClass(String codeClass) {
        this.codeClass = codeClass;
        return this;
    }


    public String getCodeDepartment() {
        return codeDepartment;
    }

    public Class setCodeDepartment(String codeDepartment) {
        this.codeDepartment = codeDepartment;
        return this;
    }

    public String getClassName() {
        return className;
    }

    public Class setClassName(String className) {
        this.className = className;
        return this;
    }

    public String departmentToJson() {
        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put(Var.KEY_CODE_DEPARTMENT, getCodeDepartment());
            jsonObject.put(Var.KEY_CODE_CLASS, getCodeClass());
            jsonObject.put(Var.KEY_CLASS_NAME, getClassName());
            return jsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
