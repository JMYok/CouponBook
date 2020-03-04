package com.imooc.passbook.constant;

/**
 * <h1>优惠券的状态</h1>
 * @author BobJiang
 * @version 1.0
 * @date 2020/2/23 8:07
 */
public enum PassStatus {

    UNUSED(1,"未被使用的"),
    USED(2,"已经被使用的"),
    ALL(3,"全部领取的");

    /**状态码*/
    private Integer code;

    /** 状态描述 */
    private String desc;

    PassStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
