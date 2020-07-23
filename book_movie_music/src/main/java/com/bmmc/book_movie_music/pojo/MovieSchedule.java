package com.bmmc.book_movie_music.pojo;

public class MovieSchedule {
    private int schedule_id;
    private int movieid;
    private String release_time;
    private int hallid;

    public MovieSchedule(){}

    public MovieSchedule(int schedule_id, int movieid, String release_time, int hallid) {
        this.schedule_id = schedule_id;
        this.movieid = movieid;
        this.release_time = release_time;
        this.hallid = hallid;
    }

    public int getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(int schedule_id) {
        this.schedule_id = schedule_id;
    }

    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
    }

    public String getRelease_time() {
        return release_time;
    }

    public void setRelease_time(String release_time) {
        this.release_time = release_time;
    }

    public int getHallid() {
        return hallid;
    }

    public void setHallid(int hallid) {
        this.hallid = hallid;
    }
}
