package com.imooc.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1>用户领取优惠券的请求对象</h1>
 * @author BobJiang
 * @version 1.0
 * @date 2020/2/25 12:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GainPassTemplateRequest {

    /** 用户id */
    private Long userId;

    /** PassTemplate 对象 */
    private PassTemplate passTemplate;


}
