package com.imooc.merchants.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1>创建商户相应对象</h1>
 * @author BobJiang
 * @version 1.0
 * @date 2020/2/19 11:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMerchantsResponse {

    /** 商户 id ： 创建失败则为-1*/
    private  Integer id;
}
