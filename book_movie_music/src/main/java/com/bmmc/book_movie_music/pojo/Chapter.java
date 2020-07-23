package com.bmmc.book_movie_music.pojo;

/**
 * Title: Chapter
 * Description:章节pojo类
 * Version:1.0.0
 * @author Zephaniah.
 * @date 2020.4.30
 */

public class Chapter {
    private int chapterid;
    private int bookid;
    private String content;
    private String title;
    private String words;
    private String is_delete;
    private String ctime;//创建时间
//    private String mtime;//修改时间

    public Chapter(int chapterid, int bookid, String content, String title, String words, String is_delete, String ctime) {
        this.chapterid = chapterid;
        this.bookid = bookid;
        this.content = content;
        this.title = title;
        this.words = words;
        this.is_delete = is_delete;
        this.ctime = ctime;
    }

    public Chapter(){}

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

}
