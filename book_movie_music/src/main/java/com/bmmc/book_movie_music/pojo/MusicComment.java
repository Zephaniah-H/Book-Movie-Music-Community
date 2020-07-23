package com.bmmc.book_movie_music.pojo;

public class MusicComment {
    private int musiccommentid;
    private int userid;
    private int musicid;
    private String comment;
    private String great;
    private String step;
    private String ctime;

    public MusicComment(){}

    public MusicComment(int musiccommentid, int userid, int musicid, String comment, String great, String step, String ctime) {
        this.musiccommentid = musiccommentid;
        this.userid = userid;
        this.musicid = musicid;
        this.comment = comment;
        this.great = great;
        this.step = step;
        this.ctime = ctime;
    }

    public int getMusiccommentid() {
        return musiccommentid;
    }

    public void setMusiccommentid(int musiccommentid) {
        this.musiccommentid = musiccommentid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getMusicid() {
        return musicid;
    }

    public void setMusicid(int musicid) {
        this.musicid = musicid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getGreat() {
        return great;
    }

    public void setGreat(String great) {
        this.great = great;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }
}
