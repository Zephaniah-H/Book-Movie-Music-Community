package com.bmmc.book_movie_music.controller;

import com.bmmc.book_movie_music.pojo.AllMovie;
import com.bmmc.book_movie_music.pojo.HallSeat;
import com.bmmc.book_movie_music.pojo.MovieComment;
import com.bmmc.book_movie_music.pojo.MovieSchedule;
import com.bmmc.book_movie_music.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class MovieRestController {
    @Autowired
    private MovieService movieService;

    @GetMapping(value = "/findAllMovie")
    public List<AllMovie> findAllMovie(){
        System.out.println("查所有电影");
//        System.out.println(movieService.findAllMovie());
        return movieService.findAllMovie();
    }

    @GetMapping(value = "/findHighPointMovies")
    public List<AllMovie> findHighPointMovies(){
        System.out.println("查高分电影");
        return movieService.findHighPointMovies();
    }

    @GetMapping(value = "/findMovieByState1")
    public List<AllMovie> findMovieByState1(){
        System.out.println("查电影正在上映和即将上映");
        return movieService.findMovieByState1();
    }

    @PostMapping(value = "/findOneMovieByid")
    public AllMovie findOneMovieByid(@RequestBody AllMovie allMovie){
        System.out.println("查一个电影");
        return movieService.findOneMovieByid(allMovie.getMovieid());
    }

    @PostMapping(value = "/findMovieByState")
    public List<AllMovie> findMovieByState(@RequestBody AllMovie allMovie){
        System.out.println("查电影bystate");
        return movieService.findMovieByState(allMovie.getMoviestate());
    }

    @PostMapping(value = "/deleteMovieByid")
    public boolean deleteMovieByid(@RequestBody AllMovie allMovie){
        System.out.println("删除电影");
        return movieService.deleteMovieByid(allMovie.getMovieid());
    }

    @PostMapping(value = "/updateMovie")
    public boolean updateMovie(@RequestBody AllMovie allMovie){
        System.out.println("修改电影");
        System.out.println(allMovie);
        return movieService.updateMovie(allMovie);
    }

    @PostMapping(value = "/addMovie")
    public boolean addMovie(@RequestBody AllMovie allMovie){
        System.out.println("添加电影");
        return movieService.addMovie(allMovie);
    }

    @PostMapping(value = "/findMovieBySearch")
    public List<AllMovie> findMovieBySearch(@RequestBody AllMovie allMovie){
        System.out.println("模糊查询电影");
        System.out.println("模查电影"+allMovie.getMoviename());
        return movieService.findMovieBySearch(allMovie.getMoviename());
    }

    @PostMapping(value = "/findScheduleBymovieid")
    public List<MovieSchedule> findScheduleBymovieid(@RequestBody AllMovie allMovie){
        System.out.println("查一部电影的排片");
        return movieService.findScheduleBymovieid(allMovie.getMovieid());
    }

    @PostMapping(value = "/findScheduleByid")
    public MovieSchedule findScheduleByid(@RequestBody MovieSchedule movieSchedule){
        System.out.println("查一个排片");
        return movieService.findScheduleByid(movieSchedule.getSchedule_id());
    }

    @PostMapping(value = "/deleteScheduleByid")
    public boolean deleteScheduleByid(@RequestBody MovieSchedule movieSchedule){
        System.out.println("删除排片");
        return movieService.deleteScheduleByid(movieSchedule.getSchedule_id());
    }

    @PostMapping(value = "/updateSchedule")
    public boolean updateSchedule(@RequestBody MovieSchedule movieSchedule){
        System.out.println("修改排片");
        return movieService.updateSchedule(movieSchedule);
    }

    @PostMapping(value = "/addSchedule")
    public boolean addSchedule(@RequestBody MovieSchedule movieSchedule){
        System.out.println("添加排片");
        return movieService.addSchedule(movieSchedule);
    }

    @PostMapping(value = "/findScheduleBySearch")
    public List<MovieSchedule> findScheduleBySearch(@RequestBody MovieSchedule movieSchedule){
        System.out.println("模糊查询排片");
        return movieService.findScheduleBySearch(movieSchedule.getRelease_time());
    }

    @PostMapping(value = "/findMovieCommentBymovieid")
    public List<MovieComment> findMovieCommentBymovieid(@RequestBody AllMovie allMovie){
        System.out.println("查一个电影的评论");
        return movieService.findMovieCommentBymovieid(allMovie.getMovieid());
    }

    @PostMapping(value = "/addMovieComment")
    public boolean addMovieComment(@RequestBody MovieComment movieComment){
        System.out.println("添加影评");
        return movieService.addMovieComment(movieComment);
    }

    @PostMapping(value = "/deleteMovieComment")
    public boolean deleteMovieComment(@RequestBody MovieComment movieComment){
        System.out.println("删除影评");
        return movieService.deleteMovieComment(movieComment.getMoviecommentid());
    }

    @PostMapping(value = "/findMCommentBySearch")
    public List<MovieComment> findMCommentBySearch(@RequestBody MovieComment movieComment){
        System.out.println("模糊查询影评");
        return movieService.findMCommentBySearch(movieComment.getComment());
    }

    @PostMapping(value = "/moviegreat")
    public boolean movieGreat(@RequestBody MovieComment movieComment){
        System.out.println("赞影评");
        return movieService.great(movieComment);
    }

    @PostMapping(value = "/moviestep")
    public boolean movieStep(@RequestBody MovieComment movieComment){
        System.out.println("踩影评");
        return movieService.step(movieComment);
    }

    @PostMapping(value = "/findSeatBysidhid")
    public List<HallSeat> findSeatBysidhid(@RequestBody HallSeat hallSeat){
        System.out.println("查座位");
        return movieService.findSeatBysidhid(hallSeat);
    }

    @PostMapping(value = "/updataHallSeat")
    public boolean updataHallSeat(@RequestBody HallSeat hallSeat){
        System.out.println("座位被选了");
        return movieService.updataHallSeat(hallSeat.getSeatid());
    }
}
