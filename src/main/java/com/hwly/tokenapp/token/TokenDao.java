package com.hwly.tokenapp.token;

import com.hwly.tokenapp.modal.Token;

import java.util.Map;

/**
 * @Description token存储
 **/
public interface TokenDao {
    /**
     * 生成或查询token
     * @param key   唯一用来识别token的key,
     * @param values token对应的业务参数。
     * @return 返回token对象。
     */
    Token buildOrQuery(String key, Map<String,String> values);

    /**
     *查询token,查询token对应的业务参数。
     * @param token
     * @return
     */
    Map<String,String> tokenQuery(String token);
}
