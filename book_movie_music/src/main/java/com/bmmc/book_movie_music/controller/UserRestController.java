package com.bmmc.book_movie_music.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayTradePayResponse;
import com.bmmc.book_movie_music.pojo.MyOrder;
import com.bmmc.book_movie_music.pojo.Result;
import com.bmmc.book_movie_music.pojo.User;
import com.bmmc.book_movie_music.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class UserRestController {
    @Autowired
    private UserService userService;

    private final String APP_ID = "2016102300742974";
    private final String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCX82cGRB9E1MbQjQ8gTRW2THMU2+nrmZWVHWB68Gb/uLLq8PMLpnmtgzKq7RlbQID2n9rxKWgjlGgCOXXYxI6snaFAdubtklqo8KG2/ult6UsZjQfWpcUhk3M5Jm0E0VjuX+5hGgJR4yzTPBmgQ5UdpCHfmnHp9dCdNFYRCBFUu0+B8oq4zjHHg7nQqVyrF7SaqhNoJAyDf32tiW3x+h0gwGVPN4m/qkTcRkjkhFocGaKsW1Cm62VCxMsgWjPER2ZAiMEE3YnSu0wVG8yI74osAQw5jpg6Dz4mLY+VAAFkj6nT8UpCUXUREQUhDlhb7CTIqXezhNg6p20yvYzw5F9TAgMBAAECggEABS6U1d7e3h8PhK8609QtKGFlD1IMoS9Eragp0Sd/q7rlxyNedog/4A34XWHmmnkRrNd4xP6vgX8fKOxCu5jAcNyTUjp716BFHTvPmAkLducc24s2uDhBB8sag9luoFpvWPnGjGMxrw87SRzkltWXMUKWsSR4PA1ZBvNw9/W5eZU39nvXOjqtchQKr1StJd4faSMeH0MFkm+Tf6JBdGhcQfrKq9Vhgy9zHgSbXsrYILGvyNAedG/e9AkZN5Dr83l6oZVy6UpaduSScmSbiWV+taMNKruIEO2HiX3oCszNl23j7uyFNshQaE0YHEA+u+cicYQEjFVpmRLyBhbYtiHL8QKBgQD0cnPZEp2YBJRlVJyeu5Dt/rHVyMujUpiWg9EDgfau0cvSt5bcau8m2JthngzDKVTTVruK9Tq/YPix0ZjWCLLDQylaevGTfjT31T4CsZFsQoPmpj4cW8nBMXDOjds7LVJI161eO1TJaD7rMX658xaB7qXhWcSDseRT+i7lGtTZ+wKBgQCfIdgRvcvXaaa851Vrv0YHLksGQWep+BKM/q5TdAsku+DpV9J2g+0jHXiTRUwStLVoE1DSktdRecmqIFmlno1okQHgyMZOYmFLFxVZXHYLOgnwy+6wRozon1pqlTFUOHHMGlZ2Pnqk8hHajgurOKCtqJgGj5caZ/DnZDZd9Z2oiQKBgH5NXLo+Ohs/0oUA0OPbOikePjidxUof9SNf/HD5h8IQOGYp4dMFlYfWx4IKrcFw65k+6SwFRVN5AUava267k9H8M7PCbmus/CVJkv7FTMRJIenc3567dmVR7F1tQmczJZ7pgXR853QhXtEbA8WEao68Bg2H2lfJZJZXYD+UO4JdAoGAWUZkcLeZB1XHbE0gGB2rzNWE05KX9LwutBkhdcIxij85E8/oTAMBY+Thw0VqQ0hMSYIkoq4szBHWOHKDuK21yYcolZIt5sW8raLaoFKGCxBZiVTvqMc7wDm+QPNIRGaSOR5BiX7N0YHY0FNGap2PTz25ZFzkObU8aqRG9/mWpHkCgYBemeLbCKgluIR+9qxftD1SdFrG8/4QWDIEKc35R10Cgqes+SFrGwF1DXavGCOjyh20wmiT3D/i3rrS4r1LYmRJn5aqgBM1tmJrhlJhVyYMhVI1C5W8IP3t4gpr1ufauGKIJ1gMQKK1j4tsUoApipVlTqUdLJPhrymnR5dqA8pF9Q==";
    private final String CHARSET = "UTF-8";
    private final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA3n8NX6eyMCsAG0ZkQ9ZmjmjsA5nfT63Pa2NDH9eTiUEeb7E9gQH/F2MTk4k0+FAj1FN5QVbIqnL2++v+EVu73eL2FZuvo2a2ke72ehubOvtvYgI+4IdDvR6OqLss6LILcdVp+lXaQ3F3zJEoNAAPOL/ZS3NtZGu3nWttQT50s+y3cHd2LkM+P9+N+h54nqxNBjZdqb6y2bdGz11tQl4zPO0glvHUloWPvz+tNefoIYH19U4e13bY6Mf1WAit41tt2MB9BsiS8ndQMiOCDmd/Pss+DzTQfiRj5olQo+LlhTPeyxuZYHQyNi1+0CBsCK6yKK7Y+R/Euk/1Uky7oULWkwIDAQAB";
    //这是沙箱接口路径,正式路径为https://openapi.alipay.com/gateway.do
    private final String GATEWAY_URL ="https://openapi.alipaydev.com/gateway.do";
    private final String FORMAT = "JSON";
    //签名方式
    private final String SIGN_TYPE = "RSA2";
    //支付宝异步通知路径,付款完毕后会异步调用本项目的方法,必须为公网地址
    private final String NOTIFY_URL = "http://localhost:8080/#/notifyUrl";
    //支付宝同步通知路径,也就是当付款完毕后跳转本项目的页面,可以不是公网地址
    private final String RETURN_URL = "http://localhost:8080/#/buyticket";

    @PostMapping(value = "/findByPhonePsw")
    public User findByNamePsw(@RequestBody User user){
        System.out.println("登录验证...");
        System.out.println(user);
        System.out.println(userService.findUserByNamePsw(user.getPhone(),user.getPassword()));
        return  userService.findUserByNamePsw(user.getPhone(),user.getPassword());
    }

    @PostMapping(value = "/addUserByPhone")
    public Result addUser(@RequestBody User user){
        System.out.println("开始添加...");
        if (userService.addUserByPhone(user)){
            return new Result(200);
        }else{
            System.out.println("注册失败");
            return new Result(400);
        }
    }

    @GetMapping(value = "/findAllUser")
    public List<User> findAllUser(){
        return userService.findAllUser();
    }

    @GetMapping(value = "/findAllOrder")
    public List<MyOrder> findAllOrder(){
        return userService.findAllOrder();
    }

    @PostMapping(value = "/deleteOrderByid")
    public boolean deleteOrderByid(@RequestBody MyOrder myOrder){
        System.out.println("删除订单成功");
        return userService.deleteOrderByid(myOrder.getOrder_id());
    }

    @PostMapping(value = "/findOrderLikeSth")
    public List<MyOrder> findOrderLikeSth(@RequestBody MyOrder myOrder){
        System.out.println("模糊查询订单"+myOrder.getOrder_name());
        return userService.findOrderLikeSth(myOrder.getOrder_name());
    }

    @PostMapping(value = "/findOrderByUseridState")
    public List<MyOrder> findOrderByUseridState(@RequestBody MyOrder myOrder){
        System.out.println("查询订单byuseridordertype");
//        System.out.println(myOrder);
        return userService.findOrderByUseridState(myOrder.getUserid(),myOrder.getOrder_state());
    }

    @PostMapping(value = "/deleteUserByid")
    public boolean deleteUserByid(@RequestBody User user){
        System.out.println("删除用户成功");
        return userService.deleteUserByid(user.getUserid());
    }

    @PostMapping(value = "/findUserLikeSth")
    public List<User> findUserLikeSth(@RequestBody User user){
        System.out.println("模糊查询用户"+user.getUsername());
        return userService.findUserLikeSth(user.getUsername());
    }

    @PostMapping(value = "/findByid")
    public User findByid(@RequestBody User user){
        System.out.println("查一个用户"+user.getUserid());
        return userService.findByid(user.getUserid());
    }

    @PostMapping(value = "/updateUserByid")
    public boolean updateUserByid(@RequestBody User user){
        System.out.println("修改用户信息");
        return userService.updateUserByid(user);
    }

    @PostMapping(value = "/paySuccess")
    public boolean paySuccess(@RequestBody MyOrder myOrder){
        System.out.println("支付成功");
        return userService.paySuccess(myOrder.getOrder_id());
    }

    @PostMapping("/alipay")
    public void alipay(HttpServletResponse httpResponse,@RequestBody MyOrder myOrder) throws IOException {
        System.out.println(myOrder);
        Random r=new Random();
        //实例化客户端,填入所需参数
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        //在公共参数中设置回跳和通知地址
        request.setReturnUrl(RETURN_URL);
        request.setNotifyUrl(NOTIFY_URL);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        //生成随机Id
        String out_trade_no = myOrder.getOrder_id();
        //付款金额，必填
        String total_amount =myOrder.getOrder_price();
//        int userid = 1;
//        String order_type = "电影";
//        String order_time = "2019-10-25";
//        String order_state = "未支付";
        //订单名称，必填
        String subject =myOrder.getOrder_name();
        //商品描述，可空
//        String body = "尊敬的会员欢迎购买奥迪A8 2016款 A8L 60 TFSl quattro豪华型";
        request.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
//                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        if (myOrder.getUserid() != 0){
            userService.addOrder(myOrder);
        }
        String form = "";
        try {
            form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

    @RequestMapping(value = "/returnUrl", method = RequestMethod.GET)
    public String returnUrl(HttpServletRequest request, HttpServletResponse response)throws IOException, AlipayApiException {
        System.out.println("=================================同步回调=====================================");

        // 获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("utf-8"), "utf-8");
            params.put(name, valueStr);
        }

        System.out.println(params);//查看参数都有哪些
        boolean signVerified = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, CHARSET, SIGN_TYPE); // 调用SDK验证签名
        //验证签名通过
        if(signVerified){
            // 商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            // 支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            // 付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
            String order_type = new String(request.getParameter("order_type").getBytes("ISO-8859-1"), "UTF-8");

            System.out.println("商户订单号="+out_trade_no);
            System.out.println("支付宝交易号="+trade_no);
            System.out.println("付款金额="+total_amount);
            System.out.println("订单类型="+order_type);

            //支付成功，修复支付状态
//            payService.updateById(Integer.valueOf(out_trade_no));
            return "ok";//跳转付款成功页面
        }else{
            return "no";//跳转付款失败页面
        }

    }

}
