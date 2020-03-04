package com.imooc.passbook.vo;

import com.imooc.passbook.entity.Merchants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <h1>优惠券模版信息</h1>
 * @author BobJiang
 * @version 1.0
 * @date 2020/2/25 11:56
 */

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassTemplateInfo extends PassTemplate {

    /** 优惠券模版 */
    private PassTemplate passTemplate;

    /** 优惠券对应的商户 */
    private Merchants merchants;

}
