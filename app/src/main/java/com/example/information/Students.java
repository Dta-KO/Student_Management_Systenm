package com.example.information;

import org.json.JSONObject;

public class Students {
    private String codeStudent, name,birthday,sex,address,codeClass;

    public Students() {
    }

    public String getCodeStudent() {
        return codeStudent;
    }

    public Students setCodeStudent(String codeStudent) {
        this.codeStudent = codeStudent;
        return this;
    }

    public String getName() {
        return name;
    }

    public Students setName(String name) {
        this.name = name;
        return this;
    }

    public String getBirthday() {
        return birthday;
    }

    public Students setBirthday(String birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public Students setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Students setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCodeClass() {
        return codeClass;
    }

    public Students setCodeClass(String codeClass) {
        this.codeClass = codeClass;
        return this;
    }



    public Students(String codeStudent, String name, String birthday, String sex, String address, String codeClass) {
        this.codeStudent = codeStudent;
        this.name = name;
        this.birthday = birthday;
        this.sex = sex;
        this.address = address;
        this.codeClass = codeClass;
    }
    public String studentToJson(){
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put(Var.KEY_STUDENT_NAME, getName());
            jsonObject.put(Var.KEY_ADDRESS,getAddress());
            jsonObject.put(Var.KEY_CODE_CLASS,getCodeClass());
            jsonObject.put(Var.KEY_CODE_STUDENT, getCodeStudent());
            jsonObject.put(Var.KEY_SEX,getSex());
            jsonObject.put(Var.KEY_BIRTHDAY,getBirthday());

            return jsonObject.toString();
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }
}
