package com.example.information;

public class Result {
    private String codeSubject,codeStudent,conduct,note, subjectName;
    private double averagePoint,firstPoint,secondPoint,finalPoint;

    public Result() {
    }


    public Result(String codeSubject, String codeStudent, double averagePoint, double firstPoint,
                  double secondPoint, double finalPoint, String conduct, String note) {
        this.codeSubject = codeSubject;
        this.codeStudent = codeStudent;
        this.averagePoint = averagePoint;
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
        this.conduct = conduct;
        this.note = note;
        this.finalPoint = finalPoint;
    }

    public String getCodeSubject() {
        return codeSubject;
    }

    public Result setCodeSubject(String codeSubject) {
        this.codeSubject = codeSubject;
        return this;
    }

    public String getCodeStudent() {
        return codeStudent;
    }

    public Result setCodeStudent(String codeStudent) {
        this.codeStudent = codeStudent;
        return this;
    }

    public double getAveragePoint() {
        return averagePoint;
    }

    public double getFinalPoint() {
        return finalPoint;
    }

    public Result setFinalPoint(double finalPoint) {
        this.finalPoint = finalPoint;
        return this;
    }

    public Result setAveragePoint(double averagePoint) {
        this.averagePoint = averagePoint;
        return this;
    }

    public double getFirstPoint() {
        return firstPoint;
    }

    public Result setFirstPoint(double firstPoint) {
        this.firstPoint = firstPoint;
        return this;
    }

    public double getSecondPoint() {
        return secondPoint;
    }

    public Result setSecondPoint(double secondPoint) {
        this.secondPoint = secondPoint;
        return this;
    }


    public String getConduct() {
        return conduct;
    }

    public Result setConduct(String conduct) {
        this.conduct = conduct;
        return this;
    }

    public String getNote() {
        return note;
    }

    public Result setNote(String note) {
        this.note = note;
        return this;
    }
}
