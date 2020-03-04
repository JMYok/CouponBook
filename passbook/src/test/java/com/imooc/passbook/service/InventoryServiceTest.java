package com.imooc.passbook.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <h1>库存服务测试</h1>
 * @author BobJiang
 * @version 1.0
 * @date 2020/2/29 1:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InventoryServiceTest extends AbstractServiceTest{

    @Autowired
    private IIventoryService iventoryService;

    @Test
    public void testGetInventoryInfo() throws Exception{
        System.out.println(JSON.toJSONString(
                iventoryService.genInventoryInfo(userId),
            SerializerFeature.DisableCircularReferenceDetect //不产生递归引用
        ));
    }
}
