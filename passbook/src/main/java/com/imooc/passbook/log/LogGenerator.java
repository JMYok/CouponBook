package com.imooc.passbook.log;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * @author BobJiang
 * @version 1.0
 * @date 2020/2/23 10:52
 */
@Slf4j
public class LogGenerator {
    /**
     * <h2>生成log</h2>
     * @param request {@link HttpServletRequest}
     * @param userId 用户id
     * @param action 日志类型
     * @param info 日志信息，可以是null
     */
    public static void genlog(HttpServletRequest request,Long userId,String action,Object info){
        log.info(
                JSON.toJSONString(
                        new LogObject(action,userId,System.currentTimeMillis(),request.getRemoteAddr(),info)
                )
        );
    }
}
