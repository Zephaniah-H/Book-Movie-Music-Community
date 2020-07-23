package com.bmmc.book_movie_music.dao;

import com.bmmc.book_movie_music.pojo.MyOrder;
import com.bmmc.book_movie_music.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByName(@Param("username") String username);

    @Select("SELECT * FROM user WHERE username LIKE ")
    List<User> findByNameMohu(@Param("username") String username);

    /**
     * 根据用户id查询用户信息
     * @param userid
     * @return
     */
    @Select("SELECT * FROM user WHERE userid = #{userid}")
    User findByid(@Param("userid") int userid);

    /**
     * 根据手机号密码查询用户信息
     * @param phone
     * @param password
     * @return
     */
    @Select("SELECT * FROM user WHERE phone = #{phone} AND password = #{password}")
    User findByNamePsw(@Param("phone") String phone,@Param("password") String password);

    /**
     * 根据角色id查找用户
     * @param roleid
     * @return
     */
    @Select("SELECT * FROM user WHERE roleid = #{roleid}")
    List<User> findByRoleid(@Param("roleid") int roleid);

    /**
     * 添加用户
     * @param user,roleid
     */
    /*@Insert("INSERT INTO user(roleid,username,password,email,phone,icon,gender) " +
            "VALUES (#{roleid},#{u.username},#{u.password},#{u.email},#{u.phone},#{u.icon},#{u.gender}) ")
    void addUser(@Param("u") User user,@Param("roleid") int roleid);
     */

    @Insert("INSERT INTO user(roleid, phone, password) VALUES ('普通用户', #{u.phone}, #{u.password})")
    void addUserByPhone(@Param("u") User user);

    /**
     * 根据用户名删除用户
     * @param username
     */
    @Delete("DELETE FROM user WHERE username = #{username}")
    void deleteUser(@Param("username") String username);

    /**
     * 根据用户id删除用户
     * @param userid
     */
    @Delete("DELETE FROM user WHERE userid = #{userid}")
    void deleteUserByid(@Param("userid") int userid);

    /**
     * 根据用户id修改用户信息
     * @param user
     */
    @Update("UPDATE user SET username = #{u.username},email = #{u.email},phone = #{u.phone},icon = #{u.icon},gender = #{u.gender} " +
            "WHERE userid = #{u.userid} ")
    void updateUser(@Param("u") User user);

    /**
     * 根据用户id修改用户信息
     * @param user
     * @param userid
     */
//    @Update("UPDATE user SET username = #{u.username},email = #{u.email},phone = #{u.phone},icon = #{u.icon},gender = #{u.gender} " +
//            "WHERE userid = #{userid} ")
//    void updateUserByid(@Param("u") User user, @Param("userid") int userid);

    /**
     * 查找所有用户
     * @return
     */
    @Select("SELECT * FROM user WHERE role = '普通用户'")
    List<User> findAllUser();

    /**
     * 查找所有订单
     * @return
     */
    @Select("SELECT * FROM myorder")
    List<MyOrder> findAllOrder();

    @Select("SELECT * FROM myorder WHERE userid = #{userid} AND order_state = #{order_state} ORDER BY order_time DESC")
    List<MyOrder> findOrderByUseridState(int userid,String order_state);

    /**
     * 删除订单
     * @return
     */
    @Delete("DELETE FROM myorder WHERE order_id = #{order_id}")
    void deleteOrderByid(String order_id);

    /**
     * 模糊查询订单
     * @return
     */
    @Select("SELECT * FROM myorder WHERE CONCAT(order_id,order_name,order_type,order_state,order_time,userid,order_price) LIKE CONCAT('%',#{orderSearch},'%')")
    List<MyOrder> findOrderLikeSth(String orderSearch);

    @Select("SELECT * FROM myorder WHERE userid = #{userid}")
    List<MyOrder> findOrderByUserid(int userid);

    @Insert("INSERT INTO myorder(order_id,userid,order_name,order_type,order_time,order_price,order_state) VALUES(#{m.order_id},#{m.userid},#{m.order_name},#{m.order_type},#{m.order_time},#{m.order_price},'未支付')")
    void addOrder(@Param("m") MyOrder myOrder);

    @Select("SELECT COUNT(*) FROM myorder")
    int countOrder();

    /**
     * 模糊查询用户
     * @return
     */
    @Select("SELECT * FROM user WHERE CONCAT(userid,username,phone,email,gender) LIKE CONCAT('%',#{userSearch},'%')")
    List<User> findUserLikeSth(String userSearch);

    @Update("UPDATE myorder SET order_state = '已支付' WHERE order_id = #{order_id}")
    void paySuccess(String order_id);
}
