package com.imooc.merchants.service;

import com.imooc.merchants.vo.CreateMerchantsRequest;
import com.imooc.merchants.vo.PassTemplate;
import com.imooc.merchants.vo.Response;

/**
 * <h1>对商户服务接口定义</h1>
 * @author BobJiang
 * @version 1.0
 * @date 2020/2/19 11:59
 */
public interface IMerchantsServ {

    /**
     * <h2>创建商户服务</h2>
     * @param request {@link CreateMerchantsRequest} 创建商户请求
     * @return {@link Response}
     */
    Response createMerchants(CreateMerchantsRequest request);

    /**
     * <h2>根据id构建商户信息</h2>
     * @param id 商户 id
     * @return {@link Response}
     */
    Response buildMerchantsById(Integer id);

    /**
     * <h2>投放优惠券</h2>
     * @param template {@link PassTemplate}
     * @return {@link Response}
     */
    Response dropPassTemplate(PassTemplate template);

}
