package com.bmmc.book_movie_music.service;

import com.bmmc.book_movie_music.pojo.AllBooks;
import com.bmmc.book_movie_music.pojo.BookComment;
import com.bmmc.book_movie_music.pojo.Chapter;
import com.bmmc.book_movie_music.pojo.WannaRead;

import java.util.List;

public interface BookService {

    /**
     * 查询所有书
     * @return
     */
    List<AllBooks> findAllBooks();

    /**
     * 按类型查书
     * @return
     */
    List<AllBooks> findBookByType(int book_typeid);

    List<AllBooks> findBookByName(String bookname);

    List<AllBooks> findHighPointBooks();

    boolean deleteBookByid(int bookid);

    boolean addBooks(AllBooks allBooks);

    List<Chapter> findAllChapter();

    List<Chapter> findChapterByBookid(int bookid);

    List<Chapter> findChapterBySearch(String chapterSearch);

    boolean deleteChapterByid(int chapterid);

    boolean updateChapterIsDel(int chapterid);

    boolean addChapter(Chapter chapter);

    boolean updateChapterByid(Chapter chapter);

    List<BookComment> findAllBookComment();

    List<BookComment> findBookCommentByuserid(int userid);

    List<BookComment> findBookCommentBybookid(int bookid);

    boolean deleteBookComment(int bookcommentid);

    boolean updateBookByid(AllBooks allBooks);

    List<AllBooks> findBookBySearch(String bookSearch);

    AllBooks findOneBookByid(int bookid);

    Chapter findOneChapterByid(int chapterid);

    List<BookComment> findBCommentBySearch(String bcommentSearch);

    boolean addBookComment(BookComment bookComment);

    boolean great(BookComment bookComment);

    boolean step(BookComment bookComment);

    List<WannaRead> findMyBooksUserid(int userid);

    boolean addInBookShelf(WannaRead wannaRead);

    boolean deleteBookInShelf(int bookid);

    double getAvgBookPoint(int bookid);
}
