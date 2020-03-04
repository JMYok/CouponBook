package com.imooc.merchants.service;

import com.alibaba.fastjson.JSON;
import com.imooc.merchants.vo.CreateMerchantsRequest;
import com.imooc.merchants.vo.PassTemplate;
import org.apache.commons.lang.time.DateUtils;
import org.assertj.core.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <h1>商户测试类</h1>
 * @author BobJiang
 * @version 1.0
 * @date 2020/2/19 12:33
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MerchantsServTest {

    @Autowired
    private IMerchantsServ merchantsServ;

    @Test
    @Transactional //实现回滚
    public void testCreateMerchantServ(){
        CreateMerchantsRequest request = new CreateMerchantsRequest();
        request.setName("慕课");
        request.setLogoUrl("www.imooc.com");
        request.setBusinessLicenseUrl("www.imooc.com");
        request.setPhone("12345678");
        request.setAddress("北京市");
        System.out.println(JSON.toJSONString(merchantsServ.createMerchants(request)));
    }

    @Test
    public void testBuildMerchantsInfoById(){
        System.out.println(JSON.toJSONString(merchantsServ.buildMerchantsById(19)));
    }

    @Test
    public void testDropPassTemplate(){
        PassTemplate passTemplate = new PassTemplate();
        passTemplate.setId(11);
        passTemplate.setTitle("慕课-1");
        passTemplate.setSummary("简介： 慕课");
        passTemplate.setDesc("详情： 慕课");
        passTemplate.setLimit(10000L);
        passTemplate.setHasToken(false);
        passTemplate.setBackground(2);
        passTemplate.setStart(DateUtils.addDays(new Date(),-10));
        passTemplate.setEnd(DateUtils.addDays(new Date(),10));
        System.out.println(JSON.toJSONString(
                merchantsServ.dropPassTemplate(passTemplate)
        ));
    }
}
