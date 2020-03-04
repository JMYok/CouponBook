package com.imooc.passbook.service;

import com.alibaba.fastjson.JSON;
import com.imooc.passbook.vo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <h1>用户服务测试</h1>
 * @author BobJiang
 * @version 1.0
 * @date 2020/2/29 0:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private IUserService userService;

    @Test
    public void testCreateUser() throws Exception{
        User user = new User();
        user.setBaseInfo(new User.BaseInfo("immoc",10,"m"));
        user.setOtherInfo(new User.OtherInfo("123455","北京市朝阳市"));
        System.out.println(JSON.toJSONString(userService.createUser(user)));
    }

}
