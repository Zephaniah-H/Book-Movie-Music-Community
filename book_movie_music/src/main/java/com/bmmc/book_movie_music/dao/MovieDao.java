package com.bmmc.book_movie_music.dao;

import com.bmmc.book_movie_music.pojo.AllMovie;
import com.bmmc.book_movie_music.pojo.HallSeat;
import com.bmmc.book_movie_music.pojo.MovieComment;
import com.bmmc.book_movie_music.pojo.MovieSchedule;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MovieDao {

    @Select("SELECT * FROM allmovies WHERE is_delete = '0'")
    List<AllMovie> findAllMovie();

    @Select("SELECT * FROM allmovies WHERE is_delete = '0' ORDER BY point DESC")
    List<AllMovie> findHighPointMovies();

    @Select("SELECT * FROM allmovies WHERE movieid = #{movieid}")
    AllMovie findOneMovieByid(int movieid);

    @Select("SELECT * FROM allmovies WHERE moviestate = #{moviestate}")
    List<AllMovie> findMovieByState(String moviestate);

    @Select("SELECT * FROM allmovies WHERE moviestate <> '已经下映' AND is_delete = '0' ORDER BY moviestate DESC")
    List<AllMovie> findMovieByState1();

    @Select("SELECT * FROM allmoives WHERE movietype = #{movietype}")
    List<AllMovie> findMovieByType(String movietype);

    @Select("SELECT * FROM allmovies WHERE moviename = #{moviename}")
    List<AllMovie> findMovieByName(String moviename);

    @Update("UPDATE allmovies SET is_delete = '1' WHERE movieid = #{movieid}")
    void deleteMovieByid(int movieid);

    @Insert("INSERT INTO allmovies(moviename,movietype,movieinfo,director,leadingrole,releaseday,cover,is_delete,movieprice,language,duration,country,moviestate) VALUES(#{a.moviename},#{a.movietype},#{a.movieinfo},#{a.director},#{a.leadingrole},#{a.releaseday},#{a.cover},'0',#{a.movieprice}, #{a.language}, #{a.duration}, #{a.country},#{a.moviestate})")
    void addMovie(@Param("a") AllMovie allMovie);

    @Update("UPDATE allmovies SET moviename = #{a.moviename}, releaseday= #{a.releaseday}, leadingrole= #{a.leadingrole}, director= #{a.director},movieinfo= #{a.movieinfo}, movieprice= #{a.movieprice}, language= #{a.language}, country= #{a.country}, duration= #{a.duration}, moviestate = #{a.moviestate}, movieprice = #{a.movieprice} WHERE movieid = #{a.movieid}")
    void updateMovie(@Param("a") AllMovie allMovie);

    @Select("SELECT * FROM allmovies WHERE CONCAT(moviename,movieinfo,director,leadingrole,moviestate,movietype) LIKE CONCAT('%',#{movieSearch},'%')")
    List<AllMovie> findMovieBySearch(String movieSearch);

    @Select("SELECT * FROM movie_schedule")
    List<MovieSchedule> findAllSchedule();

    @Select("SELECT * FROM movie_schedule WHERE schedule_id = #{schedule_id}")
    MovieSchedule findScheduleByid(int schedule_id);

    @Select("SELECT * FROM movie_schedule WHERE movieid = #{movieid}")
    List<MovieSchedule> findScheduleBymovieid(int movieid);

    @Select("SELECT * FROM movie_schedule WHERE moviestate = #{moviestate}")
    List<MovieSchedule> findScheduleByState(String moviestate);

    @Select("SELECT * FROM movie_schedule WHERE release_time LIKE CONCAT('%',#{day},'%')")
    List<MovieSchedule> findScheduleByday(String day);

    @Delete("DELETE FROM movie_schedule WHERE schedule_id = #{scheduleid}")
    void deleteScheduleByid(int scheduleid);

    @Insert("INSERT INTO movie_schedule(movieid,release_time,hallid) VALUES (#{m.movieid},#{m.release_time},#{m.hallid})")
    void addSchedule(@Param("m") MovieSchedule movieSchedule);

    @Update("UPDATE movie_schedule SET release_time = #{m.release_time}, hallid = #{m.hallid} WHERE schedule_id = #{m.schedule_id}")
    void updateSchedule(@Param("m") MovieSchedule movieSchedule);

    @Select("SELECT * FROM movie_schedule WHERE CONCAT(release_time,hallid) LIKE CONCAT('%',#{scheduleSearch},'%')")
    List<MovieSchedule> findScheduleBySearch(String scheduleSearch);

    @Select("SELECT * FROM moviecomment WHERE userid = #{userid}")
    List<MovieComment> findMovieCommentByuserid(int userid);

    @Select("SELECT * FROM moviecomment WHERE movieid = #{movieid} ORDER BY great DESC")
    @Results(id = "finduser",value = {
            @Result(column = "userid",property = "user",one = @One(select = "com.bmmc.book_movie_music.dao.UserDao.findByid")),
    })
    List<MovieComment> findMovieCommentBymovieid(int movieid);

    @Select("SELECT * FROM moviecomment WHERE movieid = #{movieid} ORDER BY ctime DESC")
    List<MovieComment> findMovieCommentBymovieidAndobTime(int movieid);

    @Delete("DELETE FROM moviecomment WHERE moviecommentid = #{moviecommentid}")
    void deleteMovieComment(int moviecommentid);

    @Select("SELECT * FROM moviecomment WHERE CONCAT(userid,comment,ctime,moviepoint) LIKE CONCAT('%' , #{mcommentSearch} , '%')")
    List<MovieComment> findMCommentBySearch(String mcommentSearch);

    @Insert("INSERT INTO moviecomment(movieid,comment,userid,great,step,moviepoint,ctime,title) VALUES(#{m.movieid},#{m.comment},#{m.userid},'0','0',#{m.moviepoint},#{m.ctime},#{m.title})")
    void addMovieComment(@Param("m") MovieComment movieComment);

    @Update("UPDATE moviecomment SET great = #{m.great} WHERE moviecommentid = #{m.moviecommentid}")
    void great(@Param("m") MovieComment movieComment);

    @Update("UPDATE moviecomment SET step = #{m.step} WHERE moviecommentid = #{m.moviecommentid}")
    void step(@Param("m") MovieComment movieComment);

    @Select("SELECT * FROM hall_seat WHERE schedule_id = #{h.schedule_id} AND hallid = #{h.hallid}")
    List<HallSeat> findSeatBysidhid(@Param("h") HallSeat hallSeat);

    @Update("UPDATE hall_seat SET is_reserve = '1' WHERE seatid = #{seatid}")
    void updataHallSeat(int seatid);
}
