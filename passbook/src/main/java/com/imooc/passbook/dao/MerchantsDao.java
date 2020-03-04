package com.imooc.passbook.dao;

import com.imooc.passbook.entity.Merchants;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * <h1>Merchants Dao 接口</h1>
 * @author BobJiang
 * @version 1.0
 * @date 2020/2/23 10:37
 */
public interface MerchantsDao extends JpaRepository<Merchants,Integer> {

    /**
     * <h2> 通过id获取商户对象 </h2>
     * @param id 商户 id
     * @return {@link Merchants}
     */
    Merchants findById(Integer id);

    /**
     * <h2>通过商户名称获取商户对象</h2>
     * @param name 商户 name
     * @return {@link Merchants}
     */
    Merchants findByName(String name);

    /**
     * <h2>根据商户 ids 获取用户对象</h2>
     * @param ids 商户ids
     * @return {@link Merchants}
     */
    List<Merchants> findByIdIn(List<Integer> ids);
}
