package com.bmmc.book_movie_music.service.impl;

import com.bmmc.book_movie_music.dao.UserDao;
import com.bmmc.book_movie_music.pojo.MyOrder;
import com.bmmc.book_movie_music.pojo.User;
import com.bmmc.book_movie_music.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User findUserByNamePsw(String phone, String password) {
        return userDao.findByNamePsw(phone,password);
    }

    @Override
    public boolean addUserByPhone(User user) {
        boolean flag = false;
        try {
            userDao.addUserByPhone(user);
            flag = true;
        }catch (Exception e){
            System.out.println("添加用户失败");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateUserByid(User user) {
        boolean flag = false;
        try {
            userDao.updateUser(user);
            flag = true;
        }catch (Exception e){
            System.out.println("通过用户id修改用户失败");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<User> findAllUser() {
        return userDao.findAllUser();
    }

    @Override
    public boolean deleteUserByid(int userid) {
        boolean flag = false;
        try {
            userDao.deleteUserByid(userid);
            flag = true;
        }catch (Exception e){
            System.out.println("通过用户id删除用户失败");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<MyOrder> findAllOrder() {
        return userDao.findAllOrder();
    }

    @Override
    public boolean deleteOrderByid(String order_id) {
        boolean flag = false;
        try {
            userDao.deleteOrderByid(order_id);
            flag = true;
        }catch (Exception e){
            System.out.println("通过用户id删除订单失败");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<MyOrder> findOrderByUseridState(int userid, String order_state) {
        return userDao.findOrderByUseridState(userid,order_state);
    }

    @Override
    public List<MyOrder> findOrderLikeSth(String orderSearch) {
        return userDao.findOrderLikeSth(orderSearch);
    }

    @Override
    public List<MyOrder> findOrderByUserid(int userid) {
        return userDao.findOrderByUserid(userid);
    }

    @Override
    public boolean addOrder(MyOrder myOrder) {
        boolean flag = false;
        try {
            userDao.addOrder(myOrder);
            flag = true;
        }catch (Exception e){
            System.out.println("添加订单失败");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public int countOrder() {
        return userDao.countOrder();
    }

    @Override
    public List<User> findUserLikeSth(String userSearch) {
        return userDao.findUserLikeSth(userSearch);
    }

    @Override
    public User findByid(int userid) {
        return userDao.findByid(userid);
    }

    @Override
    public boolean paySuccess(String order_id) {
        boolean flag = false;
        try {
            userDao.paySuccess(order_id);
            flag = true;
        }catch (Exception e){
            System.out.println("支付成功");
            e.printStackTrace();
        }
        return flag;
    }
}
