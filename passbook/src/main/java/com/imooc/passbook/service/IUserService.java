package com.imooc.passbook.service;

import com.imooc.passbook.vo.Response;
import com.imooc.passbook.vo.User;

/**
 * <h1>用户服务：创建User服务</h1>
 * @author BobJiang
 * @version 1.0
 * @date 2020/2/25 10:47
 */
public interface IUserService {
    /**
     * <h2>创建用户</h2>
     * @param user {@link User}
     * @return {@link Response}
     * @throws Exception
     */
    Response createUser(User user) throws Exception;
}
