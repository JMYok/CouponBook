package com.imooc.merchants.controller;

import com.alibaba.fastjson.JSON;
import com.imooc.merchants.service.IMerchantsServ;
import com.imooc.merchants.vo.CreateMerchantsRequest;
import com.imooc.merchants.vo.PassTemplate;
import com.imooc.merchants.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <h1>商户服务 Controller</h1>
 * @author BobJiang
 * @version 1.0
 * @date 2020/2/20 11:30
 */
@Slf4j
@RestController
@RequestMapping("/merchants")
public class MerchantsCtl {

    /** 商户服务接口 */
    private final IMerchantsServ merchantsServ;

    @Autowired
    public MerchantsCtl(IMerchantsServ merchantsServ) {
        this.merchantsServ = merchantsServ;
    }

    @ResponseBody
    @PostMapping("/create")
    public Response createMerchants(@RequestBody CreateMerchantsRequest request){
        log.info("CreateMerchants :{}", JSON.toJSONString(request));
        return merchantsServ.createMerchants(request);
    }

    @ResponseBody
    @GetMapping("/{id}")
    public Response buildMerchantsInfo(@PathVariable Integer id){
        log.info("BulidMerchantsInfo:{}",id);
        return merchantsServ.buildMerchantsById(id);
    }

    @ResponseBody
    @PostMapping("/drop")
    public Response dropPassTemplate(@RequestBody PassTemplate passTemplate){
        log.info("DropPassTemplate:{}",passTemplate);
        return merchantsServ.dropPassTemplate(passTemplate);
    }
}
