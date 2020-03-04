package com.imooc.merchants.security;

/**
 * <h1>用Thread local 去单独存储每个线程携带的header信息</h1>
 * @author BobJiang
 * @version 1.0
 * @date 2020/2/18 16:17
 */
public class AccessContext {
    /** 线程内部存储类 */
    private static  final ThreadLocal<String> token = new ThreadLocal<String>();

    public static String getToken(){
        return token.get();
    }

    public static void setToken(String tokenStr){
        token.set(tokenStr);
    }

    public static void clearAccessKey(){
        token.remove();
    }
}
