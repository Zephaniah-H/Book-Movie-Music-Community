package com.bmmc.book_movie_music.pojo;

public class AllMusic {
    private int musicid;
    private String musictype;
    private String singer;
    private String lyricurl;
    private String musicurl;
    private String point;
    private int musiccommentid;

    public AllMusic(){}

    public AllMusic(int musicid, String musictype, String singer, String lyricurl, String musicurl, String point, int musiccommentid) {
        this.musicid = musicid;
        this.musictype = musictype;
        this.singer = singer;
        this.lyricurl = lyricurl;
        this.musicurl = musicurl;
        this.point = point;
        this.musiccommentid = musiccommentid;
    }

    public int getMusicid() {
        return musicid;
    }

    public void setMusicid(int musicid) {
        this.musicid = musicid;
    }

    public String getMusictype() {
        return musictype;
    }

    public void setMusictype(String musictype) {
        this.musictype = musictype;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getLyricurl() {
        return lyricurl;
    }

    public void setLyricurl(String lyricurl) {
        this.lyricurl = lyricurl;
    }

    public String getMusicurl() {
        return musicurl;
    }

    public void setMusicurl(String musicurl) {
        this.musicurl = musicurl;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public int getMusiccommentid() {
        return musiccommentid;
    }

    public void setMusiccommentid(int musiccommentid) {
        this.musiccommentid = musiccommentid;
    }
}
