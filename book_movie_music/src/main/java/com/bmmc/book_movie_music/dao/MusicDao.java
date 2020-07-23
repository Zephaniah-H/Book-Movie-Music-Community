package com.bmmc.book_movie_music.dao;

import com.bmmc.book_movie_music.pojo.AllMusic;
import com.bmmc.book_movie_music.pojo.MusicComment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MusicDao {

    @Select("SELECT * FROM allmusic")
    List<AllMusic> findAllMusic();

    @Select("SELECT * FROM allmusic WHERE musictype = #{musictype}")
    List<AllMusic> findMusicByType(String musictype);

    @Select("SELECT * FROM allmusic WHERE musicname = #{musicname}")
    List<AllMusic> findMusicByName(String musicname);

    @Update("UPDATE allmusic SET is_delete = 0 WHERE musicid = #{musicid}")
    void deleteMusicByid(int musicid);

//    @Insert("INSERT INTO allmusic(musicname,music,bookinfo,author,press,publication_year,cover,is_delete VALUES (#{a.bookname},#{a.booktypeid},#{a.bookinfo},#{a.author},#{a.press},#{a.publication_year},#{a.cover},1))")
//    void addMusic(AllMusic allmusic);

    @Select("SELECT * FROM musiccomment WHERE musicid = #{musicid}")
    List<MusicComment> findMusicComment(int musicid);

    @Select("SELECT * FROM musiccomment WHERE userid = #{userid}")
    List<MusicComment> findMovieCommentByuserid(int userid);

    @Select("SELECT * FROM musiccomment WHERE musicid = #{musicid}")
    List<MusicComment> findMovieCommentBymusicid(int musicid);

    @Delete("DELETE FROM musiccomment WHERE musiccommentid = #{musiccommentid}")
    void deleteMovieComment(int musiccommentid);

    @Select("SELECT * FROM musiccomment WHERE CONCAT(userid,comment,ctime) LIKE CONCAT('%' , #{mucommentSearch} , '%')")
    List<MusicComment> findMUCommentBySearch(String mucommentSearch);

    @Insert("INSERT INTO musiccomment(comment,userid,great,step,comment_time) VALUES(#{b.comment},#{b.userid},'0','0',#{b.comment_time})")
    void addMovieComment(@Param("m") MusicComment musicComment);
}
