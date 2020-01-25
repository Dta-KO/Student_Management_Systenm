package com.example.information;

public class Subject {
    private String codeSubject, codeDepartment,codeTeacher,subjectName;
    private int numberUnits;

    public Subject() {
    }

    public Subject( String codeDepartment, String codeTeacher,String codeSubject, String subjectName, int numberUnits) {
        this.codeSubject = codeSubject;
        this.codeDepartment = codeDepartment;
        this.codeTeacher = codeTeacher;
        this.subjectName = subjectName;
        this.numberUnits = numberUnits;
    }

    public String getCodeSubject() {
        return codeSubject;
    }

    public Subject setCodeSubject(String codeSubject) {
        this.codeSubject = codeSubject;
        return this;
    }

    public String getCodeDepartment() {
        return codeDepartment;
    }

    public Subject setCodeDepartment(String codeDepartment) {
        this.codeDepartment = codeDepartment;
        return this;
    }

    public String getCodeTeacher() {
        return codeTeacher;
    }

    public Subject setCodeTeacher(String codeTeacher) {
        this.codeTeacher = codeTeacher;
        return this;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public Subject setSubjectName(String subjectName) {
        this.subjectName = subjectName;
        return this;
    }

    public int getNumberUnits() {
        return numberUnits;
    }

    public Subject setNumberUnits(int numberUnits) {
        this.numberUnits = numberUnits;
        return this;
    }
}
