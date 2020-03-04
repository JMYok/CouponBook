package com.imooc.passbook.utils;

import com.imooc.passbook.vo.Feedback;
import com.imooc.passbook.vo.GainPassTemplateRequest;
import com.imooc.passbook.vo.PassTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * <h1>RowKey 生成器工具类</h1>
 * @author BobJiang
 * @version 1.0
 * @date 2020/2/24 15:55
 */
@Slf4j
public class RowKeyGenUtil {

    /**
     * <h2>根据提供的 PassTemplate 对象生成 RowKey</h2>
     * @param passTemplate {@link PassTemplate}
     * @return String RowKey
     */
    public static String genPassTemplateRowKey(PassTemplate passTemplate){
        //rowKey越分散，数据也就也分散，更容易达到负载均衡
        String passInfo = String.valueOf(passTemplate.getId()+"_"+passTemplate.getTitle());
        String rowKey = DigestUtils.md5Hex(passInfo);
        log.info("GenPassTemplateRowKey:{},{}",passInfo,rowKey);

        return rowKey;
    }

    /**
     * <h2>根据提供的领取优惠券请求生成 RowKey ，只可以在领取优惠券的时候使用</h2>
     * Pass RowKey = reversed(useId) + inverse(timestamp) + PassTemplate RowKey
     * @param request {@link GainPassTemplateRequest}
     * @return String RowKey
     */
    public static String genPassRowKey(GainPassTemplateRequest request){
        return new StringBuilder(String.valueOf(request.getUserId())).reverse().toString().toString()
                + (Long.MAX_VALUE - System.currentTimeMillis())
                + genPassTemplateRowKey(request.getPassTemplate());
    }

    /**
     * <h2>根据Feedback 构造Rowkey</h2>
     * @param feedback {@link Feedback}
     * @return String RowKey
     */
    public static String genFeedbackRowKey(Feedback feedback){
        //用户Id反转后更易实现负载均衡。同一用户的feedback存储在相近位置。越早发布的feedback值越大，在越后面。
        return new StringBuilder(String.valueOf(feedback.getUserId())).reverse().toString()
                + (Long.MAX_VALUE - System.currentTimeMillis());
    }
}
