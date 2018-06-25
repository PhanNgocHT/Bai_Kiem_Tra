package com.example.admin.bai_kiem_tra;

import java.io.Serializable;

/**
 * Created by admin on 4/17/2017.
 */

public class SinhVien implements Serializable {
    private String fullname;
    private String classes;
    private float score;
    private int id;
    public SinhVien( String fullname, String classes, float score, int id) {
        this.fullname = fullname;
        this.classes = classes;
        this.score = score;
        this.id=id;
    }



    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
