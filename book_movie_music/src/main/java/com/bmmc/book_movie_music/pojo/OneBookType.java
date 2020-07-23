package com.bmmc.book_movie_music.pojo;

/**
 * Title: OneBookType
 * Description:一本书的类型pojo类
 * Version:1.0.0
 * @author Zephaniah.
 * @date 2020.4.30
 */

public class OneBookType {
    private int id;
    private int bookid;
    private int booktypeid;

    public OneBookType(int id, int bookid, int booktypeid) {
        this.id = id;
        this.bookid = bookid;
        this.booktypeid = booktypeid;
    }

    public OneBookType(){}

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

    public int getBooktypeid() {
        return booktypeid;
    }

    public void setBooktypeid(int booktypeid) {
        this.booktypeid = booktypeid;
    }

    @Override
    public String toString() {
        return "OneBookType{" +
                "id=" + id +
                ", bookid=" + bookid +
                ", booktypeid=" + booktypeid +
                '}';
    }
}
