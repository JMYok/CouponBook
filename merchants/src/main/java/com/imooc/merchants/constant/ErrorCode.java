package com.imooc.merchants.constant;

/**
 * <h2>错误代码枚举定义</h2>
 * @author BobJiang
 * @version 1.0
 * @date 2020/2/18 16:12
 */
public enum ErrorCode {
    SUCEESS(0,""),
    DUPLICATE_NAME(1,"商户名称重复"),
    EMPTY_LOGO(2,"商户 logo 为空"),
    EMPTY_BUSSINESS_LICENSE(3,"商户营业执照为空"),
    EMPYT_PHONE(4,"商户联系电话为空"),
    EMPTY_ADDRESS(5,"商户地址为空"),
    MERCHANTS_NOT_EXIST(6,"商户不存在"),
    EMPTY_NAME(7,"商户名字为空");

    /** 错误码  */
    private  Integer code;

    /** 错误描述 */
    private String desc;

    ErrorCode(Integer code, String desc) {
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
