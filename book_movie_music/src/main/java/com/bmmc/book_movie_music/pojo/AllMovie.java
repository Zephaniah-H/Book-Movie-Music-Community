package com.bmmc.book_movie_music.pojo;

public class AllMovie {

    private int movieid;
    private String moviename;
    private String releaseday;
    private String leadingrole;
    private String director;
    private String movieinfo;
    private String movieprice;
    private String moviestate;
    private String movietype;
    private String is_delete;
    private String cover;
    private String point;
    private String language;
    private String duration;
    private String country;

    public AllMovie(){}

    public AllMovie(int movieid, String moviename, String releaseday, String leadingrole, String director, String movieinfo, String movieprice, String moviestate, String movietype, String is_delete, String cover, String point, String language, String duration, String country) {
        this.movieid = movieid;
        this.moviename = moviename;
        this.releaseday = releaseday;
        this.leadingrole = leadingrole;
        this.director = director;
        this.movieinfo = movieinfo;
        this.movieprice = movieprice;
        this.moviestate = moviestate;
        this.movietype = movietype;
        this.is_delete = is_delete;
        this.cover = cover;
        this.point = point;
        this.language = language;
        this.duration = duration;
        this.country = country;
    }

    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
    }

    public String getMoviename() {
        return moviename;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }

    public String getReleaseday() {
        return releaseday;
    }

    public void setReleaseday(String releaseday) {
        this.releaseday = releaseday;
    }

    public String getLeadingrole() {
        return leadingrole;
    }

    public void setLeadingrole(String leadingrole) {
        this.leadingrole = leadingrole;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getMovieinfo() {
        return movieinfo;
    }

    public void setMovieinfo(String movieinfo) {
        this.movieinfo = movieinfo;
    }

    public String getMovieprice() {
        return movieprice;
    }

    public void setMovieprice(String movieprice) {
        this.movieprice = movieprice;
    }

    public String getMoviestate() {
        return moviestate;
    }

    public void setMoviestate(String moviestate) {
        this.moviestate = moviestate;
    }

    public String getMovietype() {
        return movietype;
    }

    public void setMovietype(String movietype) {
        this.movietype = movietype;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "AllMovie{" +
                "movieid=" + movieid +
                ", moviename='" + moviename + '\'' +
                ", releaseday='" + releaseday + '\'' +
                ", leadingrole='" + leadingrole + '\'' +
                ", director='" + director + '\'' +
                ", movieinfo='" + movieinfo + '\'' +
                ", movieprice='" + movieprice + '\'' +
                ", moviestate='" + moviestate + '\'' +
                ", movietype='" + movietype + '\'' +
                ", is_delete='" + is_delete + '\'' +
                ", cover='" + cover + '\'' +
                ", point='" + point + '\'' +
                ", language='" + language + '\'' +
                ", duration='" + duration + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
