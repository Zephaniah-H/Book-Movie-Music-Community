package com.bmmc.book_movie_music.pojo;

/**
 * Title: HaveReadChapter
 * Description:看过的书的章节pojo类
 * Version:1.0.0
 * @author Zephaniah.
 * @date 2020.4.30
 */
public class HaveReadChapter {
    private int id;
    private int chapterid;
    private int bookid;
    private int userid;

    public HaveReadChapter(){}

    public HaveReadChapter(int id, int chapterid, int bookid, int userid) {
        this.id = id;
        this.chapterid = chapterid;
        this.bookid = bookid;
        this.userid = userid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChapterid() {
        return chapterid;
    }

    public void setChapterid(int chapterid) {
        this.chapterid = chapterid;
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
}
