package com.bmmc.book_movie_music.pojo;

public class MovieComment {
    private int moviecommentid;
    private int userid;
    private int movieid;
    private String comment;
    private int great;
    private int step;
    private String ctime;
    private double moviepoint;
    private String title;
    private User user;

    public MovieComment(){}

    public MovieComment(int moviecommentid, int userid, int movieid, String comment, int great, int step, String ctime, double moviepoint, String title, User user) {
        this.moviecommentid = moviecommentid;
        this.userid = userid;
        this.movieid = movieid;
        this.comment = comment;
        this.great = great;
        this.step = step;
        this.ctime = ctime;
        this.moviepoint = moviepoint;
        this.title = title;
        this.user = user;
    }

    public int getMoviecommentid() {
        return moviecommentid;
    }

    public void setMoviecommentid(int moviecommentid) {
        this.moviecommentid = moviecommentid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
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

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public double getMoviepoint() {
        return moviepoint;
    }

    public void setMoviepoint(double moviepoint) {
        this.moviepoint = moviepoint;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
