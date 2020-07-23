package com.bmmc.book_movie_music.service.impl;

import com.bmmc.book_movie_music.dao.MovieDao;
import com.bmmc.book_movie_music.dao.UserDao;
import com.bmmc.book_movie_music.pojo.AllMovie;
import com.bmmc.book_movie_music.pojo.HallSeat;
import com.bmmc.book_movie_music.pojo.MovieComment;
import com.bmmc.book_movie_music.pojo.MovieSchedule;
import com.bmmc.book_movie_music.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieDao movieDao;

    @Override
    public List<AllMovie> findAllMovie() {
        return movieDao.findAllMovie();
    }

    @Override
    public List<AllMovie> findHighPointMovies() {
        return movieDao.findHighPointMovies();
    }

    @Override
    public AllMovie findOneMovieByid(int movieid) {
        return movieDao.findOneMovieByid(movieid);
    }

    @Override
    public List<AllMovie> findMovieByState(String moviestate) {
        return movieDao.findMovieByState(moviestate);
    }

    @Override
    public List<AllMovie> findMovieByState1() {
        return movieDao.findMovieByState1();
    }

    @Override
    public List<AllMovie> findMovieByType(String movietype) {
        return movieDao.findMovieByType(movietype);
    }

    @Override
    public List<AllMovie> findMovieByName(String moviename) {
        return movieDao.findMovieByName(moviename);
    }

    @Override
    public boolean deleteMovieByid(int movieid) {
        boolean flag = false;
        try {
            movieDao.deleteMovieByid(movieid);
            flag = true;
        }catch (Exception e){
            System.out.println("删除电影byid失败");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean addMovie(AllMovie allMovie) {
        boolean flag = false;
        try {
            movieDao.addMovie(allMovie);
            flag = true;
        }catch (Exception e){
            System.out.println("添加movie失败");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateMovie(AllMovie allMovie) {
        boolean flag = false;
        try {
            System.out.println("修改电影Service" + allMovie);
            movieDao.updateMovie(allMovie);
            flag = true;
        }catch (Exception e){
            System.out.println("修改movie失败");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<AllMovie> findMovieBySearch(String movieSearch) {
        return movieDao.findMovieBySearch(movieSearch);
    }

    @Override
    public List<MovieSchedule> findAllSchedule() {
        return movieDao.findAllSchedule();
    }

    @Override
    public MovieSchedule findScheduleByid(int schedule_id) {
        return movieDao.findScheduleByid(schedule_id);
    }

    @Override
    public List<MovieSchedule> findScheduleBymovieid(int movieid) {
        return movieDao.findScheduleBymovieid(movieid);
    }

    @Override
    public List<MovieSchedule> findScheduleByState(String moviestate) {
        return movieDao.findScheduleByState(moviestate);
    }

    @Override
    public List<MovieSchedule> findScheduleByday(String day) {
        return movieDao.findScheduleByday(day);
    }

    @Override
    public boolean deleteScheduleByid(int scheduleid) {
        boolean flag = false;
        try {
            movieDao.deleteScheduleByid(scheduleid);
            flag = true;
        }catch (Exception e){
            System.out.println("删除排片失败");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean addSchedule(MovieSchedule movieSchedule) {
        boolean flag = false;
        try {
            movieDao.addSchedule(movieSchedule);
            flag = true;
        }catch (Exception e){
            System.out.println("添加排片失败");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateSchedule(MovieSchedule movieSchedule) {
        boolean flag = false;
        try {
            movieDao.updateSchedule(movieSchedule);
            flag = true;
        }catch (Exception e){
            System.out.println("修改排片失败");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<MovieSchedule> findScheduleBySearch(String scheduleSearch) {
        return movieDao.findScheduleBySearch(scheduleSearch);
    }

    @Override
    public List<MovieComment> findMovieCommentByuserid(int userid) {
        return movieDao.findMovieCommentByuserid(userid);
    }

    @Override
    public List<MovieComment> findMovieCommentBymovieid(int movieid) {
        return movieDao.findMovieCommentBymovieid(movieid);
    }

    @Override
    public boolean deleteMovieComment(int moviecommentid) {
        boolean flag = false;
        try {
            movieDao.deleteMovieComment(moviecommentid);
            flag = true;
        }catch (Exception e){
            System.out.println("删除影评失败");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<MovieComment> findMCommentBySearch(String mcommentSearch) {
        return movieDao.findMCommentBySearch(mcommentSearch);
    }

    @Override
    public boolean addMovieComment(MovieComment movieComment) {
        boolean flag = false;
        try {
            movieDao.addMovieComment(movieComment);
            flag = true;
        }catch (Exception e){
            System.out.println("添加影评失败");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean great(MovieComment movieComment) {
        boolean flag = false;
        try {
            movieDao.great(movieComment);
            flag = true;
        }catch (Exception e){
            System.out.println("赞失败");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean step(MovieComment movieComment) {
        boolean flag = false;
        try {
            movieDao.step(movieComment);
            flag = true;
        }catch (Exception e){
            System.out.println("踩失败");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<HallSeat> findSeatBysidhid(HallSeat hallSeat) {
        return movieDao.findSeatBysidhid(hallSeat);
    }

    @Override
    public boolean updataHallSeat(int seatid) {
        boolean flag = false;
        try {
            movieDao.updataHallSeat(seatid);
            flag = true;
        }catch (Exception e){
            System.out.println("踩失败");
            e.printStackTrace();
        }
        return flag;
    }
}
