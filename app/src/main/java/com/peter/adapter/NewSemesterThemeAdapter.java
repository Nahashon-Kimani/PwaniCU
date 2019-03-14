package com.peter.adapter;

public class NewSemesterThemeAdapter {
    private String semesterVerse, semesterNarration, semesterVersion, semester, year;

    public NewSemesterThemeAdapter() {
    }

    public NewSemesterThemeAdapter(String semesterVerse, String semesterNarration, String semesterVersion, String semester, String year) {
        this.semesterVerse = semesterVerse;
        this.semesterNarration = semesterNarration;
        this.semesterVersion = semesterVersion;
        this.semester = semester;
        this.year = year;
    }

    public String getSemesterVerse() {
        return semesterVerse;
    }

    public void setSemesterVerse(String semesterVerse) {
        this.semesterVerse = semesterVerse;
    }

    public String getSemesterNarration() {
        return semesterNarration;
    }

    public void setSemesterNarration(String semesterNarration) {
        this.semesterNarration = semesterNarration;
    }

    public String getSemesterVersion() {
        return semesterVersion;
    }

    public void setSemesterVersion(String semesterVersion) {
        this.semesterVersion = semesterVersion;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}

