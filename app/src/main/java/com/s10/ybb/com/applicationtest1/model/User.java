package com.s10.ybb.com.applicationtest1.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/2/7.
 */
public class User implements Serializable {

    private int id;
    private String username;
    private String u_pass;
    private int staute;  //账号状态
    private int studentId;
    private int photo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User(String username, String password, int staute, int studentId) {
        this.username = username;
        this.u_pass = password;
        this.staute = staute;
        this.studentId = studentId;
    }

    public User(String username, String password, int staute, int studentId, int photo) {
        this.username = username;
        this.u_pass = password;
        this.staute = staute;
        this.studentId = studentId;
        this.photo = photo;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return u_pass;
    }

    public int getStaute() {
        return staute;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.u_pass = password;
    }

    public void setStaute(int staute) {
        this.staute = staute;
    }
}
