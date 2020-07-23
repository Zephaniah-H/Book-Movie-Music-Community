package com.bmmc.book_movie_music.service;

import com.bmmc.book_movie_music.pojo.MyOrder;
import com.bmmc.book_movie_music.pojo.User;

import java.util.List;

public interface UserService {

    /**
     * 根据手机号密码查询用户
     * @param phone
     * @param password
     * @return
     */
    User findUserByNamePsw(String phone, String password);

    /**
     * 添加用户
     * @param user
     * return
     */
    boolean addUserByPhone(User user);

    /**
     * 根据用户id修改用户信息
     * @param user
     * @return
     */
    boolean updateUserByid(User user);

    /**
     * 查询所有用户
     * @return
     */
    List<User> findAllUser();

    /**
     * 根据用户id修改用户信息
     * @param userid
     * @return
     */
    boolean deleteUserByid(int userid);

    /**
     * 查询所有订单
     * @return
     */
    List<MyOrder> findAllOrder();

    /**
     * 删除订单
     * @return
     */
    boolean deleteOrderByid(String order_id);

    List<MyOrder> findOrderByUseridState(int userid,String order_state);

    /**
     * 模糊查询订单
     * @return
     */
    List<MyOrder> findOrderLikeSth(String orderSearch);

    /**
     * 根据用户id查询订单
     * @return
     */
    List<MyOrder> findOrderByUserid(int userid);

    /**
     * 添加订单
     * @return
     */
    boolean addOrder(MyOrder myOrder);

    /**
     * 查询一共多少订单
     * @return
     */
    int countOrder();

    /**
     * 模糊查询用户
     * @return
     */
    List<User> findUserLikeSth(String userSearch);

    /**
     * 根据用户id查用户
     * @return
     */
    User findByid(int userid);

    boolean paySuccess(String order_id);
}
