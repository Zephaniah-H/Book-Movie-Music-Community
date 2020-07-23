package com.bmmc.book_movie_music.pojo;

import lombok.ToString;

@ToString
public class MyOrder {
    private String order_id;
    private int userid;
    private String order_name;
    private String order_time;
    private String order_type;
    private String order_price;
    private String order_state;

    public MyOrder(){}

    public MyOrder(String order_id, int userid, String order_name, String order_time, String order_type, String order_price, String order_state) {
        this.order_id = order_id;
        this.userid = userid;
        this.order_name = order_name;
        this.order_time = order_time;
        this.order_type = order_type;
        this.order_price = order_price;
        this.order_state = order_state;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getOrder_state() {
        return order_state;
    }

    public void setOrder_state(String order_state) {
        this.order_state = order_state;
    }

    public String getOrder_price() {
        return order_price;
    }

    public void setOrder_price(String order_price) {
        this.order_price = order_price;
    }

    @Override
    public String toString() {
        return "MyOrder{" +
                "order_id=" + order_id +
                ", userid=" + userid +
                ", order_name='" + order_name + '\'' +
                ", order_time='" + order_time + '\'' +
                ", order_type='" + order_type + '\'' +
                ", order_price='" + order_price + '\'' +
                ", order_state='" + order_state + '\'' +
                '}';
    }
}
