package com.bmmc.book_movie_music.pojo;

/**
 * Title: AllBooks
 * Description:所有书pojo类
 * Version:1.0.0
 * @author Zephaniah.
 * @date 2020.4.30
 */
public class AllBooks {
    private int bookid;
    private int booktypeid;
    private String bookname;
    private String bookinfo;
    private String author;
    private String point;
    private String press;
    private String publication_year;
    private String cover;
    private int bookcommentid;
    private String is_orginal;
    private String is_delete;

    /**
     * 构造方法
     */
    public AllBooks(int bookid, int booktypeid, String bookname, String bookinfo, String author, String point, String press, String publication_year, String cover, int bookcommentid, String is_orginal, String is_delete) {
        this.bookid = bookid;
        this.booktypeid = booktypeid;
        this.bookname = bookname;
        this.bookinfo = bookinfo;
        this.author = author;
        this.point = point;
        this.press = press;
        this.publication_year = publication_year;
        this.cover = cover;
        this.bookcommentid = bookcommentid;
        this.is_orginal = is_orginal;
        this.is_delete = is_delete;
    }

    /**
     * 无参构造方法
     */
    public AllBooks(){
    }

    /**
     * getter & setter
     */
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

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBookinfo() {
        return bookinfo;
    }

    public void setBookinfo(String bookinfo) {
        this.bookinfo = bookinfo;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getPublication_year() {
        return publication_year;
    }

    public void setPublication_year(String publication_year) {
        this.publication_year = publication_year;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getBookcommentid() {
        return bookcommentid;
    }

    public void setBookcommentid(int bookcommentid) {
        this.bookcommentid = bookcommentid;
    }

    public String getIs_orginal() {
        return is_orginal;
    }

    public void setIs_orginal(String is_orginal) {
        this.is_orginal = is_orginal;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    /**
     * toString
     */
    @Override
    public String toString() {
        return "AllBooks{" +
                "bookid=" + bookid +
                ", booktypeid=" + booktypeid +
                ", bookname='" + bookname + '\'' +
                ", bookinfo='" + bookinfo + '\'' +
                ", author='" + author + '\'' +
                ", point='" + point + '\'' +
                ", press='" + press + '\'' +
                ", publication_year='" + publication_year + '\'' +
                ", cover='" + cover + '\'' +
                ", bookcommentid=" + bookcommentid +
                ", is_orginal='" + is_orginal + '\'' +
                ", is_delete='" + is_delete + '\'' +
                '}';
    }
}
