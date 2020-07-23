package com.bmmc.book_movie_music.pojo;

/**
 * Title: BookType
 * Description:书分类pojo类
 * Version:1.0.0
 * @author Zephaniah.
 * @date 2020.4.30
 */

public class BookType {
    private int booktypeid;
    private String booktype;

    public BookType(int booktypeid, String booktype) {
        this.booktypeid = booktypeid;
        this.booktype = booktype;
    }

    public BookType(){
    }

    public int getBooktypeid() {
        return booktypeid;
    }

    public void setBooktypeid(int booktypeid) {
        this.booktypeid = booktypeid;
    }

    public String getBooktype() {
        return booktype;
    }

    public void setBooktype(String booktype) {
        this.booktype = booktype;
    }

    @Override
    public String toString() {
        return "BookType{" +
                "booktypeid=" + booktypeid +
                ", booktype='" + booktype + '\'' +
                '}';
    }
}
