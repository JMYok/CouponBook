package com.imooc.passbook.service;

import com.imooc.passbook.vo.Pass;
import com.imooc.passbook.vo.Response;

/**
 * <h1>获取用户个人优惠券信息</h1>
 * @author BobJiang
 * @version 1.0
 * @date 2020/2/25 12:12
 */
public interface IUserPassService {

    /**
     * <h2>获取用户个人优惠券信息，即我的优惠券功能实现</h2>
     * @param userId 用户 id
     * @return {@link Response}
     * @throws Exception
     */
    Response getUserPassInfo(Long userId) throws Exception;

    /**
     * <h2>获取用户已经消费了的优惠券: 即以使用的优惠券功能实现</h2>
     * @param userId 用户 id
     * @return  {@link Response}
     * @throws Exception
     */
    Response getUserUsedPassInfo(Long userId) throws  Exception;

    /**
     * <h2>获取用户所有的优惠券</h2>
     * @param userId 用户 id
     * @return {@link Response}
     * @throws Exception
     */
    Response getUserAllPassInfo(Long userId) throws Exception;

    /**
     * <h2>用户使用优惠券</h2>
     * @param pass {@link Pass}
     * @return {@link Response}
     */
    Response userUsePass(Pass pass);

}
