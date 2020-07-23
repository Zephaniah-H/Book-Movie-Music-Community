package com.bmmc.book_movie_music.pojo;

/**
 * Title: User
 * Description:用户pojo类
 * Version:1.0.0
 * @author Zephaniah.
 * @date 2020.4.30
 */
public class User {
    /** 用户id */
    private int userid;
    /** 用户名 */
    private String username;
    /** 密码 */
    private String password;
    /** 邮箱 */
    private String email;
    /** 手机号 */
    private String phone;
    /** 余额 */
    private float balance;
    /** 头像存储路径 */
    private String icon;
    /** 性别 */
    private String gender;
    /** 角色  */
    private String role;

    /**
     * 构造方法
     */
    public User(int userid, String username, String password, String email, String phone, float balance, String icon, String gender, String role) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.balance = balance;
        this.icon = icon;
        this.gender = gender;
        this.role = role;
    }

    /**
     * 无参构造方法
     */
    public User(){
    }

    /**
     * getter & setter
     */
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
