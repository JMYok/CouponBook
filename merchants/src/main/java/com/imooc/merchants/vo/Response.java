package com.imooc.merchants.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1>通用的响应对象</h1>
 * @author BobJiang
 * @version 1.0
 * @date 2020/2/18 17:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    /** 错误码，正确返回0*/
    private Integer errorCode = 0;

    /**错误信息，正确返回空字符串*/
    private String errorMsg="";

    /** 返回值对象 */
    private Object data;

    /**
     * @param data 返回值对象
     */
//    public Response(Object data) {
//        this.data = data;
//    }
}
