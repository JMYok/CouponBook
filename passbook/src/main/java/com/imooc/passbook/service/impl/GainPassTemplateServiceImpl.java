package com.imooc.passbook.service.impl;

import com.alibaba.fastjson.JSON;
import com.imooc.passbook.constant.Constants;
import com.imooc.passbook.mapper.PassTemplateRowMapper;
import com.imooc.passbook.service.IGainPassTemplateService;
import com.imooc.passbook.utils.RowKeyGenUtil;
import com.imooc.passbook.vo.GainPassTemplateRequest;
import com.imooc.passbook.vo.PassTemplate;
import com.imooc.passbook.vo.Response;
import com.spring4all.spring.boot.starter.hbase.api.HbaseTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <h1>用户领取优惠券功能实现</h1>
 * @author BobJiang
 * @version 1.0
 * @date 2020/2/26 9:10
 */
@Slf4j
@Service
public class GainPassTemplateServiceImpl implements IGainPassTemplateService {

    /** Hbase 客户端 */
    private final HbaseTemplate hbaseTemplate;

    /** redis客户端 */
    private StringRedisTemplate redisTemplate;

    @Autowired
    public GainPassTemplateServiceImpl(HbaseTemplate hbaseTemplate, StringRedisTemplate redisTemplate) {
        this.hbaseTemplate = hbaseTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Response gainPassTemplate(GainPassTemplateRequest request) throws Exception {

        PassTemplate passTemplate;
        String passTemplateId = RowKeyGenUtil.genPassTemplateRowKey(request.getPassTemplate());

        try{
            passTemplate = hbaseTemplate.get(
                    Constants.PassTemplateTable.TABLE_NAME,
                    passTemplateId,
                    new PassTemplateRowMapper()
            );
        } catch (Exception ex){
            log.error("Gain PassTemplate Error:{}", JSON.toJSONString(request.getPassTemplate()));
            return Response.faliure("Gain PassTemplate Error!");
        }

        if(passTemplate.getLimit() <=1 && passTemplate.getLimit()!=-1){
            log.error("PassTemplate Limit Max:{}", JSON.toJSONString(request.getPassTemplate()));
            return Response.faliure("PassTemplate Limit Max");
        }

        Date cur = new Date();
        if(!(cur.getTime() >= passTemplate.getStart().getTime()
        && cur.getTime() < passTemplate.getEnd().getTime())){
            log.error("PassTemplate ValidTime Error:{}",JSON.toJSONString(request.getPassTemplate()));
            return Response.faliure("PassTemplate ValidTime Error!");
        }

        //减去优惠券的limit
        List<Mutation> datas = new ArrayList<>();
        byte[] FAMILY_C = Constants.PassTemplateTable.FAMILY_C.getBytes();
        byte[] LIMIT = Constants.PassTemplateTable.LIMIT.getBytes();

        Put put = new Put(Bytes.toBytes(passTemplateId));
        if(passTemplate.getLimit()!=-1){
            put.addColumn(FAMILY_C,LIMIT,
                    Bytes.toBytes(passTemplate.getLimit() -1 ));
            datas.add(put);

            hbaseTemplate.saveOrUpdates(Constants.PassTemplateTable.TABLE_NAME,datas);
        }

        //将优惠券保存到用户优惠券表
        if(!addPassForUser(request,passTemplate.getId(),passTemplateId)){
            if(passTemplate.getLimit()!=-1){
                Put put_change = (Put) datas.get(0);
                put_change.addColumn(FAMILY_C,LIMIT,
                        Bytes.toBytes(passTemplate.getLimit() +1 ));

                hbaseTemplate.saveOrUpdates(Constants.PassTemplateTable.TABLE_NAME,datas);
            }
            return Response.faliure("GainPassTemplate Failure!");
        }

        return Response.success();
    }

    /**
     * <h2>给用户添加优惠券</h2>
     * @param request {@link GainPassTemplateRequest}
     * @param merchantsId 商户 id
     * @param passtemplateId 优惠券 id
     * @return true/false
     * @throws Exception
     */
    private boolean addPassForUser(GainPassTemplateRequest request,
                                   Integer merchantsId,String passtemplateId) throws Exception{
        byte[] FAMILY_I = Constants.PassTable.FAMILY_I.getBytes();
        byte[] USER_ID =  Constants.PassTable.USER_ID.getBytes();
        byte[] TEMPLATE_ID = Constants.PassTable.TOKEN.getBytes();
        byte[] TOKEN  = Constants.PassTable.ASSIGNED_DATE.getBytes();
        byte[] ASSIGNED_DATE = Constants.PassTable.ASSIGNED_DATE.getBytes();
        byte[] CON_DATE = Constants.PassTable.CON_DATE.getBytes();

        List<Mutation> datas = new ArrayList<>();
        Put put = new Put(Bytes.toBytes(RowKeyGenUtil.genPassRowKey(request)));
        put.addColumn(FAMILY_I, USER_ID, Bytes.toBytes(request.getUserId()));
        put.addColumn(FAMILY_I, TEMPLATE_ID, Bytes.toBytes(passtemplateId));

        if(request.getPassTemplate().getHasToken()){
            String token = redisTemplate.opsForSet().pop(passtemplateId);
            if(null == token){
                log.error("Token not exist {}",passtemplateId);
                return false;
            }
            recordTokenToFile(merchantsId,passtemplateId,token);
            put.addColumn(FAMILY_I, TOKEN,Bytes.toBytes(token));
        } else {
            put.addColumn(FAMILY_I, TOKEN, Bytes.toBytes("-1"));
        }

        put.addColumn(FAMILY_I, ASSIGNED_DATE,
                Bytes.toBytes(DateFormatUtils.ISO_DATE_FORMAT.format(new Date())));
        put.addColumn(FAMILY_I, CON_DATE, Bytes.toBytes("-1"));

        hbaseTemplate.saveOrUpdates(Constants.PassTable.TABLE_NAME,datas);

        return true;
    }

    /**
     * <h2>将已使用的 token 记录到文件中</h2>
     * @param merchantsId 商户 id
     * @param passTemplateId 优惠券 id
     * @param token 分配的优惠券 token
     */
    private void recordTokenToFile(Integer merchantsId, String passTemplateId,
                                   String token) throws Exception{
        Files.write(
                Paths.get(Constants.TOKEN_DIR,String.valueOf(merchantsId),
                passTemplateId + Constants.USED_TOKEN_SUFFIX),
                (token+"\n").getBytes(),
                StandardOpenOption.CREATE,StandardOpenOption.APPEND
        );
    }
}
