package com.imooc.passbook.service;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <h1>服务测试抽象类</h1>
 * @author BobJiang
 * @version 1.0
 * @date 2020/2/29 1:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AbstractServiceTest {

    Long userId;

    @Before
    public void init(){
        userId=123L;
    }
}
