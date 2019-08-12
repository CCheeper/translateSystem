package com.example.demo.entity;

public class language {

    public language(String english, String chinese, String industry, String celanguage) {
        this.english = english;
        this.chinese = chinese;
        this.industry = industry;
        this.celanguage = celanguage;
    }

    private String date;
    private String english;
    private String chinese;
    private String industry;
    private String celanguage;

    public language(String english, String chinese) {
        this.english = english;
        this.chinese = chinese;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCelanguage() {
        return celanguage;
    }

    public void setCelanguage(String celanguage) {
        this.celanguage = celanguage;
    }

    @Override
    public String toString() {
        return "language{" +
                "date='" + date + '\'' +
                ", english='" + english + '\'' +
                ", chinese='" + chinese + '\'' +
                ", industry='" + industry + '\'' +
                ", celanguage='" + celanguage + '\'' +
                '}';
    }

    public language(String date, String english, String chinese, String industry, String celanguage) {
        this.date = date;
        this.english = english;
        this.chinese = chinese;
        this.industry = industry;
        this.celanguage = celanguage;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
