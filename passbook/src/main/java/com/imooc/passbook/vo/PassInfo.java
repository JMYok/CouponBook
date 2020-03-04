package com.imooc.passbook.vo;

import com.imooc.passbook.entity.Merchants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1>用户已领取的优惠券 具体信息</h1>
 * @author BobJiang
 * @version 1.0
 * @date 2020/2/25 12:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassInfo {

    /** 优惠券 */
    private Pass pass;

    /** 优惠券模版 */
    private PassTemplate passTemplate;

    /** 优惠券对应的商户 */
    private Merchants merchants;
}
