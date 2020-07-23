package com.bmmc.book_movie_music.pojo;

public class HallSeat {
    private int seatid;
    private int schedule_id;
    private String row;
    private String col;
    private int hallid;
    private String is_reserve;

    public HallSeat(){}

    public HallSeat(int seatid, int schedule_id, String row, String col, int hallid, String is_reserve) {
        this.seatid = seatid;
        this.schedule_id = schedule_id;
        this.row = row;
        this.col = col;
        this.hallid = hallid;
        this.is_reserve = is_reserve;
    }

    public int getSeatid() {
        return seatid;
    }

    public void setSeatid(int seatid) {
        this.seatid = seatid;
    }

    public int getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(int schedule_id) {
        this.schedule_id = schedule_id;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public int getHallid() {
        return hallid;
    }

    public void setHallid(int hallid) {
        this.hallid = hallid;
    }

    public String getIs_reserve() {
        return is_reserve;
    }

    public void setIs_reserve(String is_reserve) {
        this.is_reserve = is_reserve;
    }
}
