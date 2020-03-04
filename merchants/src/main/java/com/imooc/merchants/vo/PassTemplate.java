package com.imooc.merchants.vo;

import com.imooc.merchants.constant.ErrorCode;
import com.imooc.merchants.dao.MerchantsDao;
import lombok.Data;

import java.util.Date;

/**
 * <h1>投放优惠券对象定义</h1>
 * @author BobJiang
 * @version 1.0
 * @date 2020/2/18 17:12
 */
@Data
public class PassTemplate {
    /** 所属商户id */
    private Integer id;

    /** 优惠券标题 */
    private String title;

    /** 优惠券摘要 */
    private String summary;

    /** 优惠券详细信息*/
    private String desc;

    /** 最大个数限制 */
    private Long limit;

    /**优惠券是否有Token,用于用户核销*/
    private Boolean hasToken; //token 存储于Redis Set中，每次领取从Redis中获取

    /** 优惠券背景色*/
    private Integer background;

    /** 优惠券开始时间 */
    private Date start;

    /**优惠券结束时间*/
    private Date end;

    /**
     * <h2>校验优惠券对象的有效性</h2>
     * @param merchantsDao {@link MerchantsDao}
     * @return {@link ErrorCode}
     */
    public ErrorCode validate(MerchantsDao merchantsDao){
        if(null == merchantsDao.findMerchantsById(id)){
            return ErrorCode.MERCHANTS_NOT_EXIST;
        }
        return ErrorCode.SUCEESS;
    }
}
