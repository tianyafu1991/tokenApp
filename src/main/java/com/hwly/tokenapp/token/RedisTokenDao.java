package com.hwly.tokenapp.token;

import com.alibaba.fastjson.JSONObject;
import com.hwly.tokenapp.modal.Token;
import com.hwly.tokenapp.utils.RedisUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component
public class RedisTokenDao implements TokenDao {
    @Resource
    private RedisUtil redisUtil;
    private long expireIn = 2 * 3600 * 1000L;

    /**
     * 生成或查询token
     * @param key   唯一用来识别token的key,
     * @param values token对应的业务参数。
     * @return 返回token对象。
     */
    @Override
    public Token buildOrQuery(String key, Map<String, String> values) {
        Token token;
        Map<String, String> tokenMap = redisUtil.hgetall(key);
        if (tokenMap == null || tokenMap.size() == 0) {
            token = Token.createToken(expireIn);
            tokenMap = new HashMap<>();
            tokenMap.put("generateTime", String.valueOf(token.getGenerateTime()));
            tokenMap.put("expireTime", String.valueOf(token.getExpireTime()));
            tokenMap.put("expireIn", String.valueOf(token.getExpireIn()));
            tokenMap.put("accessToken", String.valueOf(token.getAccessToken()));
            redisUtil.hmsetWithTime(key, tokenMap, expireIn);
            redisUtil.hmsetWithTime(getTokenKey(token.getAccessToken()), values, expireIn);
        } else {
            token = JSONObject.parseObject(JSONObject.toJSONString(tokenMap), Token.class);
        }
        return token;
    }

    @Override
    public Map<String, String> tokenQuery(String token) {
        return redisUtil.hgetall(getTokenKey(token));
    }

    private String getTokenKey(String token) {
        return "token:" + token;
    }
}
