package com.bmmc.book_movie_music.pojo;

/**
 * Title: BookComment
 * Description:书评pojo类
 * Version:1.0.0
 * @author Zephaniah.
 * @date 2020.4.30
 */
public class BookComment {
    private int bookcommentid;
    private int bookid;
    private int userid;
    private String comment;
    private int great;
    private int step;
    private double bookpoint;
    private String comment_title;
    private String comment_time;
    private String fold;
    private User user;

    /**
     * 构造方法
     */
    public BookComment(int bookcommentid, int bookid, int userid, String comment, int great, int step, double bookpoint, String comment_title, String comment_time, String fold, User user) {
        this.bookcommentid = bookcommentid;
        this.bookid = bookid;
        this.userid = userid;
        this.comment = comment;
        this.great = great;
        this.step = step;
        this.bookpoint = bookpoint;
        this.comment_title = comment_title;
        this.comment_time = comment_time;
        this.fold = fold;
        this.user = user;
    }

    /**
     * 无参构造方法
     */
    public BookComment(){
    }

    /**
     * getter & setter
     */
    public int getBookcommentid() {
        return bookcommentid;
    }

    public void setBookcommentid(int bookcommentid) {
        this.bookcommentid = bookcommentid;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getGreat() {
        return great;
    }

    public void setGreat(int great) {
        this.great = great;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public double getBookpoint() {
        return bookpoint;
    }

    public void setBookpoint(double bookpoint) {
        this.bookpoint = bookpoint;
    }

    public String getComment_title() {
        return comment_title;
    }

    public void setComment_title(String comment_title) {
        this.comment_title = comment_title;
    }

    public String getComment_time() {
        return comment_time;
    }

    public void setComment_time(String comment_time) {
        this.comment_time = comment_time;
    }

    public String getFold() {
        return fold;
    }

    public void setFold(String fold) {
        this.fold = fold;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
