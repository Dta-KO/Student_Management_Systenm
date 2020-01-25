package com.example.information;

import org.json.JSONObject;

public class Teachers {
    private String codeTeacher, name, birthday, sex, tel, email, type;

    public Teachers() {
    }

    public Teachers(String codeTeacher, String name, String birthday, String sex, String tel, String email, String type) {
        this.codeTeacher = codeTeacher;
        this.name = name;
        this.birthday = birthday;
        this.sex = sex;
        this.tel = tel;
        this.email = email;
        this.type = type;
    }

    public String getCodeTeacher() {
        return codeTeacher;
    }

    public Teachers setCodeTeacher(String codeTeacher) {
        this.codeTeacher = codeTeacher;
        return this;
    }

    public String getName() {
        return name;
    }

    public Teachers setName(String name) {
        this.name = name;
        return this;
    }

    public String getBirthday() {
        return birthday;
    }

    public Teachers setBirthday(String birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public Teachers setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public String getTel() {
        return tel;
    }

    public Teachers setTel(String tel) {
        this.tel = tel;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Teachers setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getType() {
        return type;
    }

    public Teachers setType(String type) {
        this.type = type;
        return this;
    }



    public String teacherToJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Var.KEY_SEX, getSex());
            jsonObject.put(Var.KEY_BIRTHDAY, getBirthday());
            jsonObject.put(Var.KEY_TEACHER_NAME, getName());
            jsonObject.put(Var.KEY_CODE_TEACHER, getCodeTeacher());
            jsonObject.put(Var.KEY_TEL, getTel());
            jsonObject.put(Var.KEY_TYPE, getType());
            jsonObject.put(Var.KEY_EMAIL, getEmail());


            return jsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
