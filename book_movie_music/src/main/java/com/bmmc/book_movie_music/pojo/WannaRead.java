package com.bmmc.book_movie_music.pojo;

/**
 * Title: WannaRead
 * Description:想看的书（书架）pojo类
 * Version:1.0.0
 * @author Zephaniah.
 * @date 2020.4.30
 */

public class WannaRead {
    private int id;
    private int bookid;
    private int userid;
    private User user;
    private AllBooks allBooks;

    public WannaRead(int id, int bookid, int userid, User user, AllBooks allBooks) {
        this.id = id;
        this.bookid = bookid;
        this.userid = userid;
        this.user = user;
        this.allBooks = allBooks;
    }

    public WannaRead(){}

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AllBooks getAllBooks() {
        return allBooks;
    }

    public void setAllBooks(AllBooks allBooks) {
        this.allBooks = allBooks;
    }
}
