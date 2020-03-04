package com.imooc.passbook.service;

import com.imooc.passbook.vo.PassTemplate;

/**
 * <h1>Pass Hbase 服务</h1>
 * @author BobJiang
 * @version 1.0
 * @date 2020/2/24 16:21
 */
public interface IHBasePassService {

    /**
     * <h2>将PassTemplate 写入 HBase</h2>
     * @param passTemplate {@link PassTemplate}
     * @return true/false
     */
    boolean dropPassTemplateToHBase(PassTemplate passTemplate);
}
