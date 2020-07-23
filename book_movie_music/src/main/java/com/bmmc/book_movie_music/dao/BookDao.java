package com.bmmc.book_movie_music.dao;

import com.bmmc.book_movie_music.pojo.AllBooks;
import com.bmmc.book_movie_music.pojo.BookComment;
import com.bmmc.book_movie_music.pojo.Chapter;
import com.bmmc.book_movie_music.pojo.WannaRead;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BookDao {

    @Select("SELECT * FROM allbooks WHERE is_delete = '0'")
    List<AllBooks> findAllBooks();

    @Select("SELECT * FROM allbooks WHERE booktypeid = #{book_typeid} AND is_delete = '0'")
    List<AllBooks> findBookByType(int book_typeid);

    @Select("SELECT * FROM allbooks WHERE bookname = #{bookname}")
    List<AllBooks> findBookByName(String bookname);

    @Select("SELECT * FROM allbooks WHERE is_delete = '0' ORDER BY point DESC")
    List<AllBooks> findHighPointBooks();

    @Update("UPDATE allbooks SET is_delete = '1' WHERE bookid = #{bookid}")
    void deleteBookByid(int bookid);

    @Insert("INSERT INTO allbooks(bookname,booktypeid,bookinfo,author,press,publication_year,cover,is_delete,point) VALUES(#{a.bookname},#{a.booktypeid},#{a.bookinfo},#{a.author},#{a.press},#{a.publication_year},#{a.cover},'0',#{a.point})")
    void addBooks(@Param("a") AllBooks allBooks);

    @Update("UPDATE allbooks SET bookname = #{a.bookname},bookinfo = #{a.bookinfo},author = #{a.author},press= #{a.press},publication_year= #{a.publication_year},cover = #{a.cover} WHERE bookid = #{a.bookid}")
    void updateBookByid(@Param("a") AllBooks allBooks);

    @Select("SELECT * FROM allbooks WHERE bookid = #{bookid}")
    AllBooks findOneBookByid(int bookid);

    @Select("SELECT * FROM allbooks WHERE (CONCAT(bookname,author,press,publication_year,point,booktypeid) LIKE CONCAT('%' , #{bookSearch} , '%')) AND is_delete = '0'")
    List<AllBooks> findBookBySearch(String bookSearch);

    @Select("SELECT * FROM chapter")
    List<Chapter> findAllChapter();

    @Select("SELECT * FROM chapter WHERE bookid = #{bookid}")
    List<Chapter> findChapterByBookid(int bookid);

    @Select("SELECT * FROM chapter WHERE CONCAT(title,words,ctime,content) LIKE '%' + #{chapterSearch} + '%'")
    List<Chapter> findChapterBySearch(String chapterSearch);

    @Delete("DELETE FROM chapter WHERE chapterid = #{chapterid}")
    void deleteChapterByid(int chapterid);

    @Update("UPDATE chapter SET is_delete = 1 WHERE chapterid = #{chapterid}")
    void updateChapterIsDel(int chapterid);

    @Insert("INSERT INTO chapter(bookid,title,ctime,content,words) VALUES(#{c.bookid},#{c.title},#{c.ctime},#{c.content},#{c.words})")
    void addChapter(@Param("c") Chapter chapter);

    @Update("UPDATE chapter SET title = #{c.title},ctime = #{c.ctime},content = #{c.content},words = #{c.words} WHERE chapterid = #{c.chapterid}")
    void updateChapterByid(@Param("c") Chapter chapter);

    @Select("SELECT * FROM chapter WHERE chapterid = #{chapterid}")
    Chapter findOneChapterByid(int chapterid);

    @Select("SELECT * FROM bookcomment")
    List<BookComment> findAllBookComment();

    @Select("SELECT * FROM bookcomment WHERE userid = #{userid}")
    List<BookComment> findBookCommentByuserid(int userid);

    @Select("SELECT * FROM bookcomment WHERE bookid = #{bookid} ORDER BY great DESC")
    @Results(id = "finduser",value = {
            @Result(column = "userid",property = "user",one = @One(select = "com.bmmc.book_movie_music.dao.UserDao.findByid")),
    })
    List<BookComment> findBookCommentBybookid(int bookid);

    @Delete("DELETE FROM bookcomment WHERE bookcommentid = #{bookcommentid}")
    void deleteBookComment(int bookcommentid);

    @Select("SELECT * FROM bookcomment WHERE CONCAT(userid,comment,comment_title,comment_time) LIKE CONCAT('%' , #{bcommentSearch} , '%')")
    List<BookComment> findBCommentBySearch(String bcommentSearch);

    @Insert("INSERT INTO bookcomment(bookid,comment,userid,comment_title,great,step,bookpoint,comment_time) VALUES(#{b.bookid},#{b.comment},#{b.userid},#{b.comment_title},'0','0',#{b.bookpoint},#{b.comment_time})")
    void addBookComment(@Param("b") BookComment bookComment);

    @Update("UPDATE bookcomment SET great = #{b.great} WHERE bookcommentid = #{b.bookcommentid}")
    void great(@Param("b") BookComment bookComment);

    @Update("UPDATE bookcomment SET step = #{b.step} WHERE bookcommentid = #{b.bookcommentid}")
    void step(@Param("b") BookComment bookComment);

    @Select("SELECT * FROM wannaread WHERE userid = #{userid}")
    @Results(id = "userAndbook",value = {
            @Result(column = "userid",property = "user",one = @One(select = "com.bmmc.book_movie_music.dao.UserDao.findByid")),
            @Result(column = "bookid",property = "allBooks",one = @One(select = "com.bmmc.book_movie_music.dao.BookDao.findOneBookByid"))
    })
    List<WannaRead> findMyBooksUserid(int userid);

    @Insert("INSERT INTO wannaread(userid,bookid) VALUES(#{w.userid},#{w.bookid})")
    void addInBookShelf(@Param("w") WannaRead wannaRead);

    @Delete("DELETE FROM wannaread WHERE bookid = #{bookid}")
    void deleteBookInShelf(int bookid);

    @Select("SELECT AVG(bookpoint) FROM bookcomment WHERE bookid = #{bookid}")
    double getAvgBookPoint(int bookid);
}
