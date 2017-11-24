package com.harry.web.model;

public class User {
    private Integer id;

    private String username;

    private String password;

    private String realname;

    private String introduction;

    private String phone;

    private String picturelink;

    private String email;

    public User(Integer id, String username, String password, String realname, String introduction, String phone, String picturelink, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.realname = realname;
        this.introduction = introduction;
        this.phone = phone;
        this.picturelink = picturelink;
        this.email = email;
    }

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPicturelink() {
        return picturelink;
    }

    public void setPicturelink(String picturelink) {
        this.picturelink = picturelink == null ? null : picturelink.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
}