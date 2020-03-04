package com.imooc.merchants.dao;

import com.imooc.merchants.entity.Merchants;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <h1>Merchants Dao 接口</h1>
 * @author BobJiang
 * @version 1.0
 * @date 2020/2/18 16:37
 */
public interface MerchantsDao extends JpaRepository<Merchants,Integer> {
    /**
     * <h2>根据id获取商户对象</h2>
     * @param id 商户 id
     * @return {@link Merchants}
     */
    Merchants findMerchantsById(Integer id);

    /**
     * <h2>根据商户名称获取商户对象</h2>
     * @param name 商户名称
     * @return {@link Merchants}
     */
    Merchants findByName(String name);
}
