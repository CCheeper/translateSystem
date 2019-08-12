package com.example.demo.entity;

import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Document(indexName = "translatedatabase" ,type="cetranslate")
public class TranslateEntity implements Serializable{
    private Long id;
    private String language;
    private String sourcelanguage;
    private String destinationlanguage;
    private String creator;
    private String other;
    private String notes;
    private String industry;
    private String createtime;
    private String edittime;
    private String editor;
    private String checker;

    public TranslateEntity(Long id,String sourcelanguage, String destinationlanguage, String creator, String other, String notes, String industry, String createtime, String edittime, String editor, String checker,String language) {
        this.language = language;
        this.id = id;
        this.sourcelanguage = sourcelanguage;
        this.destinationlanguage = destinationlanguage;
        this.creator = creator;
        this.other = other;
        this.notes = notes;
        this.industry = industry;
        this.createtime = createtime;
        this.edittime = edittime;
        this.editor = editor;
        this.checker = checker;
    }

    public TranslateEntity() {
    }


    public String getSourcelanguage() {
        return sourcelanguage;
    }

    public void setSourcelanguage(String sourcelanguage) {
        this.sourcelanguage = sourcelanguage;
    }

    public String getDestinationlanguage() {
        return destinationlanguage;
    }

    public void setDestinationlanguage(String destinationlanguage) {
        this.destinationlanguage = destinationlanguage;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getEdittime() {
        return edittime;
    }

    public void setEdittime(String edittime) {
        this.edittime = edittime;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TranslateEntity{" +
                "id=" + id +
                ", language='" + language + '\'' +
                ", sourcelanguage='" + sourcelanguage + '\'' +
                ", destinationlanguage='" + destinationlanguage + '\'' +
                ", creator='" + creator + '\'' +
                ", other='" + other + '\'' +
                ", notes='" + notes + '\'' +
                ", industry='" + industry + '\'' +
                ", createtime='" + createtime + '\'' +
                ", edittime='" + edittime + '\'' +
                ", editor='" + editor + '\'' +
                ", checker='" + checker + '\'' +
                '}';
    }
}
