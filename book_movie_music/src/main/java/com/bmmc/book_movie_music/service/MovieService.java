package com.bmmc.book_movie_music.service;

import com.bmmc.book_movie_music.pojo.AllMovie;
import com.bmmc.book_movie_music.pojo.HallSeat;
import com.bmmc.book_movie_music.pojo.MovieComment;
import com.bmmc.book_movie_music.pojo.MovieSchedule;

import java.util.List;

public interface MovieService {

    List<AllMovie> findAllMovie();

    List<AllMovie> findHighPointMovies();

    AllMovie findOneMovieByid(int movieid);

    List<AllMovie> findMovieByState(String moviestate);

    List<AllMovie> findMovieByState1();

    List<AllMovie> findMovieByType(String movietype);

    List<AllMovie> findMovieByName(String moviename);

    boolean deleteMovieByid(int movieid);

    boolean addMovie(AllMovie allMovie);

    boolean updateMovie(AllMovie allMovie);

    List<AllMovie> findMovieBySearch(String movieSearch);

    List<MovieSchedule> findAllSchedule();

    MovieSchedule findScheduleByid(int schedule_id);

    List<MovieSchedule> findScheduleBymovieid(int movieid);

    List<MovieSchedule> findScheduleByState(String moviestate);

    List<MovieSchedule> findScheduleByday(String day);

    boolean deleteScheduleByid(int scheduleid);

    boolean addSchedule(MovieSchedule movieSchedule);

    boolean updateSchedule(MovieSchedule movieSchedule);

    List<MovieSchedule> findScheduleBySearch(String scheduleSearch);

    List<MovieComment> findMovieCommentByuserid(int userid);

    List<MovieComment> findMovieCommentBymovieid(int movieid);

    boolean deleteMovieComment(int moviecommentid);

    List<MovieComment> findMCommentBySearch(String mcommentSearch);

    boolean addMovieComment(MovieComment movieComment);

    boolean great(MovieComment movieComment);

    boolean step(MovieComment movieComment);

    List<HallSeat> findSeatBysidhid(HallSeat hallSeat);

    boolean updataHallSeat(int seatid);
}
