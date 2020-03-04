package com.imooc.merchants.service.impl;

import com.alibaba.fastjson.JSON;
import com.imooc.merchants.constant.Constants;
import com.imooc.merchants.constant.ErrorCode;
import com.imooc.merchants.dao.MerchantsDao;
import com.imooc.merchants.entity.Merchants;
import com.imooc.merchants.service.IMerchantsServ;
import com.imooc.merchants.vo.CreateMerchantsRequest;
import com.imooc.merchants.vo.CreateMerchantsResponse;
import com.imooc.merchants.vo.PassTemplate;
import com.imooc.merchants.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <h1>商户服务接口实现</h1>
 * @author BobJiang
 * @version 1.0
 * @date 2020/2/19 12:05
 */
@Slf4j
@Service
public class MerchantsServImpl implements IMerchantsServ {

    /** Merchants 数据库接口 */
    private final  MerchantsDao merchantsDao;

    /** Kafka 客户端 */
    private final KafkaTemplate<String,String> kafkaTemplate;

    @Autowired //kafka报错不影响，可能IDEA识别不了
    public MerchantsServImpl(MerchantsDao merchantsDao,
                             KafkaTemplate<String, String> kafkaTemplate) {
        this.merchantsDao = merchantsDao;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @Transactional
    public Response createMerchants(CreateMerchantsRequest request) {
        Response response = new Response();
        CreateMerchantsResponse merchantsResponse = new CreateMerchantsResponse();
        ErrorCode errorCode = request.validate(merchantsDao);
        if(errorCode!=ErrorCode.SUCEESS){
            merchantsResponse.setId(-1);
            response.setErrorCode(errorCode.getCode());
            response.setErrorMsg(errorCode.getDesc());
        }else{
            merchantsResponse.setId(merchantsDao.save(request.toMerchants()).getId());
        }
        response.setData(merchantsResponse);
        return response;
    }

    @Override
    public Response buildMerchantsById(Integer id) {
        Response response = new Response();

        Merchants merchants = merchantsDao.findMerchantsById(id);
        if(null == merchants){
            response.setErrorCode(ErrorCode.MERCHANTS_NOT_EXIST.getCode());
            response.setErrorMsg(ErrorCode.MERCHANTS_NOT_EXIST.getDesc());
        }
        response.setData(merchants);
        return response;
    }

    @Override
    public Response dropPassTemplate(PassTemplate template) {
        Response response = new Response();
        ErrorCode errorCode = template.validate(merchantsDao);
        if(errorCode != ErrorCode.SUCEESS){
            response.setErrorCode(errorCode.getCode());
            response.setErrorMsg(errorCode.getDesc());
        }else{
            String passTemplate = JSON.toJSONString(template);
            kafkaTemplate.send(
                    Constants.TEMPLATE_TOPIC, //value
                    Constants.TEMPLATE_TOPIC, //key
                    passTemplate
            );
            response.setData(passTemplate);
            log.info("DropPassTemplats:{}",passTemplate);
        }
        return response;
    }
}
