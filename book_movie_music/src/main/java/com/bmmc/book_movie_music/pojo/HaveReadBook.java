package com.bmmc.book_movie_music.pojo;

/**
 * Title: HaveReadBook
 * Description:看过的书pojo类
 * Version:1.0.0
 * @author Zephaniah.
 * @date 2020.4.30
 */

public class HaveReadBook {
    private int id;
    private int bookid;
    private int userid;

    public HaveReadBook(int id, int bookid, int userid) {
        this.id = id;
        this.bookid = bookid;
        this.userid = userid;
    }

    public HaveReadBook(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "HaveReadBook{" +
                "id=" + id +
                ", bookid=" + bookid +
                ", userid=" + userid +
                '}';
    }
}
